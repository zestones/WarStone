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
	public Element[][] plateau;
	private int tour = 0;

	public Carte() {
		// Initialisation de la carte
		this.plateau = new Element[LARGEUR_CARTE_CASE][HAUTEUR_CARTE_CASE];
	
		for (int i = 0; i < LARGEUR_CARTE_CASE; i++)
			for (int j = 0; j < HAUTEUR_CARTE_CASE; j++) {
				plateau[i][j] = null;
			}

		// Cration des Elements !!! Modifier les noms !!!
		int inc = Math.max(NB_MONSTRES, Math.max(NB_OBSTACLES, NB_HEROS));
		while (inc > 0) {
			if (inc <= NB_MONSTRES)
				new Monstre(this, Soldat.TypesM.TROLL, "" + inc, this.trouvePositionVide());
			if (inc <= NB_HEROS)
				new Heros(this, Soldat.TypesH.getTypeHAlea(), "H", this.trouvePositionVide());
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
		this.tour = pj.tour;
		// On met a jour le nombre de soldat restant sur la carte
		this.nombreSoldatVivant(pj);
		//On joue le tour de chacun des deux joueurs
		if (this.tour == 0) {
			this.joueTourHeros(pj);
			this.joueTour(this.tour);
		} 
		else {
//			System.out.println(this.listeMonstre);
			this.joueTourMonstre(pj);
			this.joueTour(this.tour);
			// On effectue le clic pour jouer le tour des monstres
			pj.finTour.doClick();
		}
		pj.repaint();
	}

	/* Calcule du nombre de soldats encore present sur la carte */
	public void nombreSoldatVivant(PanneauJeu pj) {
		int nbMonstre = 0;
		int nbHeros = 0;
		for (int i = 0; i < LARGEUR_CARTE_CASE; i++)
			for (int j = 0; j < HAUTEUR_CARTE_CASE; j++) {
				if (this.plateau[i][j] instanceof Heros)
					nbHeros++;
				else if (this.plateau[i][j] instanceof Monstre)
					nbMonstre++;
			}
		pj.nombreMonstre = nbMonstre;
		pj.nombreHeros = nbHeros;
	}

	/* On effectue une action pour chaque monstre */
	private void joueTourMonstre(PanneauJeu pj) {
		if (pj.tour != 1)
			return;
		// Creation d'une liste contenant les heros a la portee du Monstre
		List<Heros> listePorteeHeros;
		// On recherche les monstres sur la carte
		for(int i = 0; i < LARGEUR_CARTE_CASE; i++) {
			for(int j = 0; j < HAUTEUR_CARTE_CASE; j++) {
				if(this.plateau[i][j] instanceof Monstre) {
					Monstre m = (Monstre)this.plateau[i][j];
					// Si le monstre a deja Joue on passe (Utile car si le monstre est deplacer dans une partie pas encore exploré par la boucle alors le Monstre va se deplacer plusieurs fois)
					if(m.aJoue) continue;
					// On initialise la liste de heros 
					listePorteeHeros = m.getListHerosInRange(); 
					// Si la liste est vide (i.e aucun Heros n'est a la porter du monstre) alors on deplace le monstre
					if (listePorteeHeros.isEmpty() == true) {
						Position pos2 = this.trouvePositionVide(m.getPosition());
						this.deplaceSoldat(pos2, m);
						
					}
					// Si notre liste contient des Heros alors il faut en choisir un et l'attaquer
					else 
						this.actionMonstre(pj, m.getPosition(), this.trouveHeros(listePorteeHeros).getPosition());
							
					// On nettoie la liste
					listePorteeHeros.clear();
				}
				// Si l'element parcouru sur la cart est un heros et qu'il na pas jouer au tour precedent alors on lui rajoute un bonus PV
				else if(this.plateau[i][j] instanceof Heros) {
					Heros h = (Heros) this.plateau[i][j];
					if(!h.aJoue) h.repos();
				}
					
			}
		}
	}
				
	/* Methode qui gere les actions des Heros */
	public boolean actionMonstre(PanneauJeu pj, Position pos, Position pos2) {
		Monstre m = (Monstre) this.plateau[pos.getX()][pos.getY()];
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
		
		// Si la position du heros selectionne et la position du dernier clic sont differente alors on effectue une action
		if ((pj.herosSelectione.getPosition() == pj.lastClic) == false) {
			if ((this.actionHeros(pj, pj.herosSelectione.getPosition(), pj.lastClic)) == true) {
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
			Monstre m = (Monstre) this.plateau[pos2.getX()][pos2.getY()];
			if (h.dedans(m.getPosition()) == true) {
				h.combat(m);
				return true;
			}
		}  
		// Si l'element en pos2 n'est ni un Monstre ni un heros alors on essaye de deplacer le Heros
		return this.deplaceSoldat(pos2, h);
	}

	/* Si le Soldat perd tout ses PV alors il meurt */
	public void mort(Soldat perso) { this.plateau[perso.getPosition().getX()][perso.getPosition().getY()] = null; }

	/*
	 * Deplace le Soldat a la position pos, si l'opperation a ete effectue alors
	 * retourne true sinon false
	 */
	public boolean deplaceSoldat(Position pos, Soldat soldat) {
		if (pos.estValide() == true && this.plateau[pos.getX()][pos.getY()] == null && soldat.getPosition().estVoisine(pos)) {
			soldat.seDeplace(pos);
			return true;
		}
		return false;
	}

	/*
	 * On remet aJoue = false en fonction du tour de la partie : tours des Monstre == 1 
	 * tour des Heros == 0 Lorsque c'est le tour des monstres alors les Heros
	 * doivent pouvoir bouger de nouveau, idem pour les Heros
	 */
	private void joueTour(int tour) {
		for (int i = 0; i < LARGEUR_CARTE_CASE; i++)
			for (int j = 0; j < HAUTEUR_CARTE_CASE; j++) {
				if (tour == 0 && this.plateau[i][j] instanceof Monstre) 
					((Monstre)this.getElement(new Position(i,j))).aJoue = false;
				else if (tour == 1 && this.plateau[i][j] instanceof Heros) 
					((Heros)this.getElement(new Position(i, j))).aJoue = false;
			}
	}

	/* trouve une position vide aleatoiremennt sur la Carte */
	public Position trouvePositionVide() {
		Position pos = new Position();
		if (pos.estValide() == true && this.plateau[pos.getX()][pos.getY()] == null)
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
	
	/*
	 * Trouve une position vide adjacente a pos sur la carte si aucune position
	 * adjacente est vide alors une position aleatoire est renvoye
	 */
	public Position trouvePositionVide(Position pos) {
		List<Position> listePos = this.positionAdjacente(pos);
		
		// On retire les position deja prise sur la carte et les position non valides
		for(int i = 0; i < listePos.size(); i++)
			if(listePos.get(i).estValide() == false || this.getElement(listePos.get(i)) != null) 
				listePos.remove(i);
		
		// on choisi aleatoirement une positoin vide
		int index = (int) (Math.random() * listePos.size());
		
		return listePos.get(index);
	}

	/* renvoie un Heros trouve aleatoirement sur la carte */
	public Heros trouveHeros() {
		List<Heros> listePosHeros = new ArrayList<>();
		for (int i = 0; i < LARGEUR_CARTE_CASE; i++)
			for (int j = 0; j < HAUTEUR_CARTE_CASE; j++) {
				Element e = this.getElement(new Position(i, j));
				if (e instanceof Heros)
					listePosHeros.add((Heros) e);
			}
		return listePosHeros.get((int) Math.random() * listePosHeros.size());
	}

	/*
	 * Prend en parametre une liste de heros et retourne un heros choisi aleatoirement dans cette liste
	 * On utilise cette methode pour trouver le heros que le Monstre va attaquer durant le tour
	 */
	public Heros trouveHeros(List<Heros> listeHeros) {
		int index = (int) (Math.random() * listeHeros.size());
		return listeHeros.get(index);
	}

	/* Retourne l'element sur la carte a la position pos */
	public Element getElement(Position pos) { return plateau[pos.getX()][pos.getY()]; }

	/*
	 * Methode de dessin principal
	 * Les heros son dessiner en premier
	 */
	public void toutDessiner(Graphics g, Camera cam) {
	
		for(int i = 0; i < LARGEUR_CASE_VISIBLE; i++)
			for(int j = 0; j < HAUTEUR_CASE_VISIBLE; j++) {
				if (this.plateau[i][j] instanceof Heros) 
					this.plateau[i][j].seDessiner(g, cam);
				
				g.setColor(COULEUR_GRILLE);
				g.drawRect(i * NB_PIX_CASE - cam.getDx() * NB_PIX_CASE, j  * NB_PIX_CASE - cam.getDy() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE); 
			}
	}
}
