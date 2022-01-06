package carte;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import carte.element.Element;
import carte.element.Heros;
import carte.element.Monstre;
import carte.element.Obstacle;
import carte.element.Soldat;
import fenetrejeu.finjeu.FinJeu;
import fenetrejeu.menubar.IMenuBar;
import fenetrejeu.panneaujeu.IConfig;
import fenetrejeu.panneaujeu.PanneauJeu;
import utile.Position;

/**
 * Class implementant la carte du jeu ainsi que ses methodes.
 */
public class Carte implements IConfig, ICarte {
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Le plateau. */
	private Element[][] plateau;
	
	/** Creation d'une Liste de Heros contenant les heros present sur la carte. */
	public List<Heros> listeHeros;
	
	/** Creation d'une liste de Monstres contenant les monstres present sur la carte. */
	public List<Monstre> listeMonstres;
	
	/**  Liste contenant les actions a realiser lors d'un tour. */
	public List<Soldat> listeActionAttaque;
	
	/** liste action deplacement. */
	public List<Soldat> listeActionDeplacement;
	
	/** liste action mort. */
	public List<Soldat> listeActionMort;
	
	/** variable fin de tour permettant de dessiner les heros d'une autre couleur si c'est le tour des monstres */
	public boolean tourMonstre;
		
	/** Boolean indiquant le mode de jeu. */
	public static boolean modeConfig = false;
	
	/**
	 * Instancie une nouvelle Carte.
	 */
	public Carte() {
		this.plateau = new Element[NB_COLONNES][NB_LIGNES];
		// Initialisation des listes
		this.listeActionAttaque = new ArrayList<>();
		this.listeActionDeplacement = new ArrayList<>();
		this.listeActionMort = new ArrayList<>();
		
		// liste de monstre et heros present sur la carte 
		this.listeHeros = new ArrayList<>();
		this.listeMonstres = new ArrayList<>();
			
		// Initialisation d'une carte vide
		this.setCarteVide();
		
		// Creation des Elements, en mode config les elements sont deposer par le joueur
        if(!Carte.modeConfig) {
            this.genereSoldats();
            this.genereObstacles();
        }
	}
	
	/**
	 * Genere obstacles.
	 */
    private void genereObstacles() {
        for(int i = 0; i < NB_OBSTACLES; i++)
            new Obstacle(this, Obstacle.TypeObstacle.getObstacleAlea(), this.trouvePositionVide());
    }

    /**
     * Genere soldats.
     */
    public void genereSoldats() {
    	boolean genereHeros = this.listeHeros.isEmpty();
    	int nbMaxSoldats = Math.max(NB_MONSTRES, NB_HEROS);
		
		for (int i = 0; i < nbMaxSoldats; i++) {
			if(i < NB_MONSTRES) {
				Monstre m = new Monstre(this, Soldat.TypesM.getTypeMAlea(), this.trouvePositionVide());
				this.listeMonstres.add(m);
			}
			if(genereHeros && i < NB_HEROS) {
				Heros h = new Heros(this, Soldat.TypesH.getTypeHAlea(), this.trouvePositionVide());
				this.listeHeros.add(h);
			}
		}
    }
	
	/**
	 * Set carte vide.
	 */
	public void setCarteVide() {
		// On initialise le plateau vide
		for(Element[] lignes : plateau)
			Arrays.fill(lignes, null);
	}

	/**
	 * 
	 * Jouer soldat va gerer les tours de jeux ainsi que les appels au methode 
	 * permettant de realiser des action sur la carte
	 * 
	 * @param pj
	 * @param tour
	 */
	public void jouerSoldats(PanneauJeu pj) {
		// On met a jour le nombre de soldat restant sur la carte
		this.nombreSoldatVivant(pj);
		
		this.tourMonstre = false;
		
		//On joue le tour de chacun des deux joueurs
		if (pj.buttonEvent.tour == 0) {
			this.joueTourHeros(pj);
		}
		else {
			this.joueTourMonstre();
			this.tourMonstre = true;
			// On effectue le clic pour jouer le tour des monstres
			IMenuBar.finTour.doClick();
		}
		pj.repaint();
	}

	/**
	 * Compte le nombre de soldat vivant sur la carte.
	 *
	 * @param pj
	 */
	public void nombreSoldatVivant(PanneauJeu pj) {
		pj.nombreMonstre = this.listeMonstres.size();
		pj.nombreHeros = this.listeHeros.size();
		pj.repaint();
		
		if(pj.nombreHeros == 0 || pj.nombreMonstre == 0) 
			new FinJeu(pj.nombreHeros, pj.nombreMonstre);		
	}

	/**
	 * IA du General monstre
	 * On decide quelle action realiser pour chaque monstre.
	 *
	 * @param pj
	 */
	private void joueTourMonstre() {
		
		/**  Creation d'une liste contenant les heros a la portee du Monstre */
		List<Heros> listePorteeHeros;
		
		/** On effectue une action pour chaque monstre */
		for(int i = 0; i < this.listeMonstres.size(); i++) {
			/** Si le monstre a deja Joue on passe */
			if(this.listeMonstres.get(i).aJoue) continue;
			
			/**  On initialise une liste de heros a la portee du monstre */
			listePorteeHeros = this.listeMonstres.get(i).getListHerosDansPortee();
			
			/** Si la liste est vide (i.e aucun Heros n'est a la porter du monstre) alors on deplace le monstre */
			if(listePorteeHeros.isEmpty()) {
				Position pos2 = this.trouvePositionVide(this.listeMonstres.get(i).getPosition());
				this.deplaceSoldat(pos2, this.listeMonstres.get(i));
			}
			/** Sinon il faut choisir un Heros et l'attaquer */
			else {
				// On cherhce le meilleur choix parmis les heros a portee du monstre
				Heros h = this.trouveHeros(this.listeMonstres.get(i).getPosition(), listePorteeHeros);
				Monstre m = this.listeMonstres.get(i);
				// on verifie la distance entre le heros et le monstre
				int distance = m.getPosition().getDistance(h.getPosition());	
				// si le heros est voisin du monstre
				if(distance == 1) {
					// 1- prendre la fuite
					if(m.getPoints() < h.getPuissance()) {
						m.prendFuite(h);
					}
				}
				// 2- se rapprocher 
				else {
					if(m.getPoints() > h.getPoints() && m.getPuissance() > h.getPuissance()) {
						m.seRapproche(h);
					}
					// Si la vie du monstre atteint un seuil critique il fuit
					else if(m.getPoints() < m.SEUIL_VIE_CRITIQUE) {
						m.prendFuite(h);
					}
				}
				// 3- si le monstre n'a toujours pas jouer alors il doit attaquer le heros trouver 
				this.listeMonstres.get(i).attaque(h.getPosition());				
			}
		}
		
		/* On cherche les heros qui n'ont effectuer aucune action et on leur donne un bonus de repos */
		for(int j = 0; j < this.listeHeros.size(); j++)
			if(!this.listeHeros.get(j).aJoue) this.listeHeros.get(j).repos();	
	}
			
	/**
	 * Le generale joueur realise les actions des heros.
	 *
	 * @param pj
	 */
	private void joueTourHeros(PanneauJeu pj) {
		// On verifie qu'un Heros a ete selectionne et qu'un deuxieme clic a ete enregistre
		if (pj.herosSelectione == null || pj.dernierClique == null)
            return;
		
		// Si la position du heros selectionne et la position du dernier clic sont differente alors on effectue une action
		if (!(pj.herosSelectione.getPosition().estIdentique(pj.dernierClique))) {
			// On recupere l'index du Heros pour le mettre a jour la listeHeros
			int index = this.listeHeros.indexOf(pj.herosSelectione);
			if ((this.actionHeros(pj.herosSelectione.getPosition(), pj.dernierClique))) {
				// Met a jour le heros dans la liste et on "oublie" le heros selectione
				this.listeHeros.set(index, pj.herosSelectione);
		    	pj.herosSelectione.aJoue = true;
				pj.herosSelectione = null;
			}
		}
	}
	
	/**
	 *  Methode qui gere les actions des Heros.
	 *  Combat ou deplacement
	 *
	 * @param pj
	 * @param pos
	 * @param pos2 
	 * @return boolean
	 */
	public boolean actionHeros(Position pos, Position pos2) {
		Heros h = (Heros) this.plateau[pos.getX()][pos.getY()];
		
		// Si l'element a attaquer est un heros ou que le heros selectionner a deja jouer alors on ne fait rien
		if (h == null || this.getElement(pos2) instanceof Heros || h.aJoue)
			return false;
		
		// Si l'element a la position pos2 est un Monstre et que ce monstre est a la porte du Heros alors il attaque
		if (this.getElement(pos2) instanceof Monstre) {
			Monstre m = (Monstre) this.getElement(pos2);
			if (h.estDedans(m.getPosition())) {
				h.combat = m.combat = true;
				this.listeActionAttaque.addAll(Arrays.asList(h, m, m, h));
				return true;
			}
		}  
		// Si l'element en pos2 n'est ni un Monstre ni un heros alors on essaye de deplacer le Heros
		return this.deplaceSoldat(pos2, h);
	}

	/**
	 * On supprime un soldat ayant perdu tout ses point de vie.
	 *
	 * @param perso the perso
	 */
	public void mort(Soldat perso) { 
		// On recupere l'index du soldat dans la liste
		int index = perso.getIndexSoldat();
		// On le supprime de la carte
		this.setElementVide(perso.getPosition());
		// Et on le tue
		perso.mort(index);
	}

	/**
	 * Methode de deplacement d'un Soldat
	 *
	 * @param pos 
	 * @param soldat 
	 * @return true, if Successful
	 */
	public boolean deplaceSoldat(Position pos, Soldat soldat) {
		// On verifie que le deplacement est valide
		if (pos.estValide() && this.estCaseVide(pos) && soldat.getPosition().estVoisine(pos) && !this.estPrisePosition(pos)) {
			// On cree une copie du soldat
			Soldat soldatCopie = null;
			try {
				soldatCopie = soldat.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			soldatCopie.setPosition(pos);
			// On met a jour le status du soldat
			soldat.seDeplace = true;
			soldat.aJoue = true; 
			// On enregistre le deplacement dans la liste d'action
			this.listeActionDeplacement.addAll(Arrays.asList(soldat, soldatCopie));
			
			return true;
		}
		return false;
	}
	
	/**
	 * Verification de la position a laquelle on veut se deplacer 
	 * si plusieurs soldat veulent se deplacer sur la meme case on retourne false.
	 *
	 * @param pos the pos
	 * @return true, if successful
	 */
	private boolean estPrisePosition(Position pos) {
		boolean positionPrise = false;
		int i = 0;
		while(i < listeActionDeplacement.size() - 1) {
			if(i % 2 != 0) {
				i++; 
				continue;
			}
			if(this.listeActionDeplacement.get(i + 1).getPosition().estIdentique(pos)) {
				positionPrise = true;
				break;
			}
			i++;
		 }
		return positionPrise;
	}

	/**
	 * On remet aJoue = false en fonction du tour de jeu
	 *
	 * @param tour
	 */
	public void joueTour(int tour) {
		if(tour == 1) {
			for(Heros h : this.listeHeros)
				h.aJoue = false;
		}
		else {
			for(Monstre m : this.listeMonstres) 	
				m.aJoue = false;
		}
	}

	/**
	 * trouve une position vide aleatoiremennt sur la Carte .
	 *
	 * @return Position
	 */
	public Position trouvePositionVide() {
		Position pos = new Position();
		if(pos.estValide() && this.estCaseVide(pos))
			return pos;
		return this.trouvePositionVide();
	}

	
	/**
	 *  Trouve une position vide adjacente a pos.
	 *
	 * @param pos the pos
	 * @return Position
	 */
	public Position trouvePositionVide(Position pos) {
		List<Position> listePos = pos.getPositionAdjacente();
		
		// On retire les position deja prise sur la carte et les position non valides
		for(int i = 0; i < listePos.size(); i++)
			if(!listePos.get(i).estValide() || !this.estCaseVide(listePos.get(i))) 
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

	/**
	 *  renvoie un Heros trouve aleatoirement sur la carte 
	 *  Les heros present sur la carte sont enregistre dans la liste "listeheros".
	 *
	 * @return Heros
	 */
	public Heros trouveHeros() { return this.listeHeros.get((int) Math.random() * this.listeHeros.size()); }

	/**
	 * Prend en parametre une liste de heros et retourne un heros choisi aleatoirement dans cette liste
	 * On utilise cette methode pour trouver un heros que le Monstre va attaquer durant le tour.
	 *
	 * @param pos the pos
	 * @param herosTrouve the heros trouve
	 * @return Heros
	 */
	public Heros trouveHeros(Position pos, List<Heros> herosTrouve) {
		int minVie = Integer.MAX_VALUE;
		Heros h = null;
		
		// On cherche le voisin du monstre avec le moins de vie 
		for(Heros voisin : herosTrouve) {
			if(minVie > voisin.getPoints() && pos.getDistance(voisin.getPosition()) == 1) {
				h = voisin;
				minVie = voisin.getPoints();				
			}
		}
		// Si on a trouver un heros on attaque celui ci
		if(h != null) return h;
		
		int attaque = minVie = Integer.MAX_VALUE;
		
		// sinon on cherche le heros a la portee du monstre avec le moins de vie
		// Et la force d'attaque la plus faible (le tir car la puissance est pour les voisins)
		for(Heros choixHeros : herosTrouve) {
			if(minVie > choixHeros.getPoints() && attaque > choixHeros.getTir()) {
				h = choixHeros;
				minVie = choixHeros.getPoints();
				attaque = choixHeros.getTir();
			}
		}
		
		return h;
	}
	
	/**
	 * Suppression des actions dans les listes.
	 */
	public void removeAllAction() {
		this.listeActionAttaque.clear();
		this.listeActionDeplacement.clear();
		this.listeActionMort.clear();	
	}

	/**
	 * Retourne l'element sur la carte a la position pos .
	 *
	 * @param pos 
	 * @return Element
	 */
	public Element getElement(Position pos) { return plateau[pos.getX()][pos.getY()]; }
	
	/**
	 * Ajoute un element sur la carte .
	 *
	 * @param e
	 */
	public void setElement(Element e) { this.plateau[e.getPosition().getX()][e.getPosition().getY()] = e; }
	
	/**
	 * Met une position donnee vide .
	 *
	 * @param pos
	 */
	public void setElementVide(Position pos) { this.plateau[pos.getX()][pos.getY()] = null; }
	
	/**
	 * Test si une case est vide.
	 *
	 * @param pos
	 * @return true, if successful
	 */
	public boolean estCaseVide(Position pos) { return (this.getElement(pos) == null); }
	
	/**
	 * Methode de dessin principal.
	 *
	 * @param g
	 * @param cam
	 */
	public void toutDessiner(Graphics g, Camera cam) {	
		// dessin de la zone a la portee des heros
		for(Heros h : this.listeHeros) {
			h.desssinerZone(g, cam);
		}
	
		// On dessine la grille visible, donc les positions donnee par la camera	
		for(int i = cam.getDx(); i < NB_COLONNES_VISIBLES + cam.getDx(); i++) {
			for(int j = cam.getDy(); j < NB_LIGNES_VISIBLES + cam.getDy(); j++) {		
				g.setColor(COULEUR_GRILLE);
				g.drawRect(i * TAILLE_CARREAU - cam.getDx() * TAILLE_CARREAU, j  * TAILLE_CARREAU - cam.getDy() * TAILLE_CARREAU, TAILLE_CARREAU, TAILLE_CARREAU); 
			}
		}
			
		// dessine les elements 					
		for(Heros h : this.listeHeros) {
			for(Monstre m : this.listeMonstres) {
				if(h.estDedans(m.getPosition()))
					m.seDessiner(g, cam);
			}
			h.seDessiner(g, cam);	  
		}
	}
}
