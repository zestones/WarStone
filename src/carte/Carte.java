package carte;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import element.Element;
import element.Heros;
import element.Monstre;
import element.Obstacle;
import element.Soldat;
import utile.Position;
import wargame.IConfig;
import wargame.PanneauJeu;

public class Carte implements IConfig, ICarte {
	private static final long serialVersionUID = 1L;
	private Element[][] plateau;
	// creation d'une Liste de Heros et de monstres present sur la carte 
	// ses liste nous permettent d'eviter des parcours de la carte type (LARGEUR & HAUTEUR)
	// On va simplement parcourir le nombre de heros et de monstres existant 
	public List<Heros> listeHeros;
	public List<Monstre> listeMonstres;
	
	public Carte() {
		// Initialisation de la carte
		this.plateau = new Element[LARGEUR_CARTE_CASE][HAUTEUR_CARTE_CASE];
		this.listeHeros = new ArrayList<>();
		this.listeMonstres = new ArrayList<>();
		
		// On initialise le plateau vide
		for (int i = 0; i < LARGEUR_CARTE_CASE; i++)
			for (int j = 0; j < HAUTEUR_CARTE_CASE; j++)
				this.setElementVide(new Position(i, j));
		
		// On position un soldat dans le focus de la camera
		this.listeHeros.add(new Heros(this, Soldat.TypesH.getTypeHAlea(), "H", new Position(LARGEUR_CARTE_CASE/2, HAUTEUR_CARTE_CASE/2)));
		
		// Cration des Elements !!! Modifier les noms !!!
		int inc = Math.max(NB_MONSTRES, Math.max(NB_OBSTACLES, NB_HEROS));
		while (inc > 0) {
			if (inc <= NB_MONSTRES)
				this.listeMonstres.add(new Monstre(this, Soldat.TypesM.getTypeMAlea(), "" + inc, this.trouvePositionVide()));
			if (inc < NB_HEROS)
				this.listeHeros.add(new Heros(this, Soldat.TypesH.getTypeHAlea(), "H", this.trouvePositionVide()));
			if (inc <= NB_OBSTACLES)
				new Obstacle(this, Obstacle.TypeObstacle.getObstacleAlea(), this.trouvePositionVide());
			inc--;
		}
	}

	/*
	 * Fonction principale du jeu c'est ici que tout est gérer : les tours, les
	 * deplacement, les actions...
	 */
	public void jouerSoldats(PanneauJeu pj) {
		// On met a jour le nombre de soldat restant sur la carte
		this.nombreSoldatVivant(pj);
		//On joue le tour de chacun des deux joueurs
		if (pj.tour == 0) {
			this.joueTourHeros(pj);
			this.joueTour(pj.tour);
		} 
		else {
			this.joueTourMonstre(pj);
			this.joueTour(pj.tour);
			// On effectue le clic pour jouer le tour des monstres
			pj.finTour.doClick();
		}
		pj.repaint();
	}

	/* Calcule du nombre de soldats encore present sur la carte */
	public void nombreSoldatVivant(PanneauJeu pj) {
		pj.nombreMonstre = this.listeMonstres.size();
		pj.nombreHeros = this.listeHeros.size();
	}

	/* On effectue une action pour chaque monstre */
	private void joueTourMonstre(PanneauJeu pj) {
		// Tour du monstre ?
		if (pj.tour != 1) return;
	
		// Creation d'une liste contenant les heros a la portee du Monstre
		List<Heros> listePorteeHeros;
		
		// On effectue une action pour chaque monstre
		for(int i = 0; i < this.listeMonstres.size(); i++) {
			// Si le monstre a deja Joue on passe 
			if(this.listeMonstres.get(i).aJoue) continue;
			
			// On initialise une liste de heros a la portee du monstre
			listePorteeHeros = this.listeMonstres.get(i).getListHerosInRange();
			// Si la liste est vide (i.e aucun Heros n'est a la porter du monstre) alors on deplace le monstre
			if(listePorteeHeros.isEmpty()) {
				Position pos2 = this.trouvePositionVide(this.listeMonstres.get(i).getPosition());
				this.deplaceSoldat(pos2, this.listeMonstres.get(i));
			}
			// Sinon il faut choisir un Heros et l'attaquer
			else {
				Heros h = this.trouveHeros(listePorteeHeros);
				this.actionMonstre(pj, this.listeMonstres.get(i).getPosition(), h.getPosition());
			}
		}
		
		// On cherche les heros qui n'ont effectuer aucune action
		for(int j = 0; j < this.listeHeros.size(); j++)
			if(!this.listeHeros.get(j).aJoue) this.listeHeros.get(j).repos();	
	}
	
	/* Methode qui gere les actions des Heros */
	public boolean actionMonstre(PanneauJeu pj, Position pos, Position pos2) {
		Monstre m = (Monstre) this.getElement(pos);
		// On verifie que le Monstre n'a pas deja jouer et que la position d'attaque (pos2) n'est pas un autre Monstre
		if (m == null || this.getElement(pos2) instanceof Monstre || m.aJoue == true) 
			return false;
		// Si un Heros est present en pos2 alors on fait combatre notre monstre
		if (this.getElement(pos2) instanceof Heros) 
			m.combat(((Heros)this.getElement(pos2)));

		return true;
	}

	/* Le generale joueur decide quelle action faire */
	private void joueTourHeros(PanneauJeu pj) {
		// On verifie qu'un Heros a ete selectionne et qu'un deuxieme clic a ete enregistre
		if (pj.herosSelectione == null || pj.lastClic == null)
			return;
		
		int index;
		// Si la position du heros selectionne et la position du dernier clic sont differente alors on effectue une action
		if (!(pj.herosSelectione.getPosition().estIdentique(pj.lastClic))) {
			// On recupere l'index du Heros pour le mettre a jour la listeHeros
			index = this.listeHeros.indexOf(pj.herosSelectione);
			if ((this.actionHeros(pj, pj.herosSelectione.getPosition(), pj.lastClic))) {
				// Met a jour le heros dans la liste et on "oublie" le heros selectione
				this.listeHeros.set(index, pj.herosSelectione);
				pj.herosSelectione = null;
			}
		}
	}
	
	/* Methode qui gere les actions des Heros */
	public boolean actionHeros(PanneauJeu pj, Position pos, Position pos2) {
		Heros h = (Heros) this.plateau[pos.getX()][pos.getY()];
		
		// Si l'element a attaquer est un heros ou que le heros selectionner a deja jouer alors on ne fait rien
		if (h == null || this.getElement(pos2) instanceof Heros || h.aJoue == true) // Ajout : pos.estVoisine(pos2) ==
			return false;
		
		// Si l'element a la position pos2 est un Monstre et que ce monstre est a la porte du Heros alors il attaque
		if (this.getElement(pos2) instanceof Monstre) {
			Monstre m = (Monstre) this.getElement(pos2);
			if (h.dedans(m.getPosition()) == true) {
				h.combat(m);
				return true;
			}
		}  
		// Si l'element en pos2 n'est ni un Monstre ni un heros alors on essaye de deplacer le Heros
		return this.deplaceSoldat(pos2, h);
	}

	/* Si le Soldat perd tout ses Points de vie alors il meurt */
	public void mort(Soldat perso) { 
		// On recupere l'index du soldat dans la liste
		int index = perso.getIndexSoldat();
		// On l'enleve de la carte
		this.setElementVide(perso.getPosition());
		// Et on le tue
		perso.mort(index);
	}

	/*
	 * Deplace le Soldat a la position pos, si l'opperation a ete effectue alors
	 * retourne true sinon false
	 */
	public boolean deplaceSoldat(Position pos, Soldat soldat) {
		if (pos.estValide() && this.getElement(pos) == null && soldat.getPosition().estVoisine(pos)) {
			soldat.seDeplace(pos);
			return true;
		}
		return false;
	}

	/*
	 * On remet aJoue = false en fonction du tour de la partie : 
	 * tours des Monstre == 1 / tour des Heros == 0 
	 * Lorsque c'est le tour des monstres alors les Heros
	 * doivent pouvoir bouger de nouveau, idem pour les Heros
	 */
	private void joueTour(int tour) {
		if(tour == 1) {
			for(int i = 0; i < this.listeHeros.size(); i++) 
				this.listeHeros.get(i).aJoue = false;
		}
		else {
			for(int j = 0; j < this.listeMonstres.size(); j++) 
				this.listeMonstres.get(j).aJoue = false;	
		}
	}

	/* trouve une position vide aleatoiremennt sur la Carte */
	public Position trouvePositionVide() {
		Position pos = new Position();
		if (pos.estValide() && this.getElement(pos) == null)
			return pos;
		return this.trouvePositionVide();
	}

	/* Methode renvoyant une liste contenant les 8 positions adjacente a une case */
	private List<Position> positionAdjacente(Position pos) {
		List<Position> listePos = new ArrayList<>();
		for (int i = -1; i < 2; i++)
			for (int j = -1; j < 2; j++)
				if (i != 0 || j != 0)
					listePos.add(new Position(pos.getX() + i, pos.getY() + j));
		return listePos;
	}
	
	/* Trouve une position vide adjacente a pos sur la carte si aucune position */
	public Position trouvePositionVide(Position pos) {
		List<Position> listePos = this.positionAdjacente(pos);
		
		// On retire les position deja prise sur la carte et les position non valides
		for(int i = 0; i < listePos.size(); i++)
			if(listePos.get(i).estValide() == false || this.getElement(listePos.get(i)) != null) 
				listePos.remove(i);
		
		// Si toute les position adjacente sont prise alors on revoie la meme pos
		if(listePos.isEmpty()) {
			System.out.println("Toute les position sont prise !");
			return pos;
		}
		
		// on choisi aleatoirement une positoin vide
		int index = (int) (Math.random() * listePos.size());
		
		return listePos.get(index);
	}

	/* renvoie un Heros trouve aleatoirement sur la carte 
	 * Les heros présent sur la carte sont stocke dans la liste "listeheros"
	 */
	public Heros trouveHeros() { return this.listeHeros.get((int) Math.random() * this.listeHeros.size()); }

	/*
	 * Prend en parametre une liste de heros et retourne un heros choisi aleatoirement dans cette liste
	 * On utilise cette methode pour trouver un heros que le Monstre va attaquer durant le tour
	 */
	public Heros trouveHeros(List<Heros> herosTrouve) {
		int index = (int) (Math.random() * herosTrouve.size());
		return listeHeros.get(index);
	}

	/* Retourne l'element sur la carte a la position pos */
	public Element getElement(Position pos) { return plateau[pos.getX()][pos.getY()]; }
	/* Ajoute un element sur la carte */
	public void setElement(Element e) { this.plateau[e.getPosition().getX()][e.getPosition().getY()] = e; }
	/* Met une position donnee vide */
	public void setElementVide(Position pos) { this.plateau[pos.getX()][pos.getY()] = null; }
	/* Renvoie true si la case est vide sinon false */
	public boolean estCaseVide(Position pos) { return (this.getElement(pos)==null); }
	/*
	 * Methode de dessin principal
	 * Les heros son dessiner en premier
	 */
	public void toutDessiner(Graphics g, Camera cam) {	
		// dessine tout les heros sur la carte ainsi que les elements a leurs portee
		for(int n = 0; n < this.listeHeros.size(); n++)
			this.listeHeros.get(n).seDessiner(g, cam);
		
		// On dessine la grille visible, donc les positions donnee par la camera	
		for(int i = cam.getDx(); i < LARGEUR_CASE_VISIBLE + cam.getDx(); i++) {
			for(int j = cam.getDy(); j < HAUTEUR_CASE_VISIBLE + cam.getDy(); j++) {		
				g.setColor(COULEUR_GRILLE);
				g.drawRect(i * NB_PIX_CASE - cam.getDx() * NB_PIX_CASE, j  * NB_PIX_CASE - cam.getDy() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE); 
			}
		}	
	}
}
