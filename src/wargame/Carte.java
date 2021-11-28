package wargame;
import java.awt.Graphics;

import java.util.ArrayList;
import java.util.List;
 
public class Carte implements IConfig,ICarte {

	private static final long serialVersionUID = 1L;
	Element[][] plateau;
	private int tour = 0;
	
	Carte(){
		// Initialisation de la carte
		this.plateau = new Element[LARGEUR_CARTE][HAUTEUR_CARTE];
		for(int i = 0; i < LARGEUR_CARTE; i++)
			for(int j = 0; j < HAUTEUR_CARTE; j++) {
				plateau[i][j] = null;
			}		
		
		// Cration des Elements
		int inc = Math.max(NB_MONSTRES, Math.max(NB_OBSTACLES, NB_HEROS));
		while(inc > 0 ){
			if(inc <= NB_MONSTRES)
				new Monstre(this, Soldat.TypesM.getTypeMAlea(),""+inc, this.trouvePositionVide());
			if(inc <= NB_HEROS) 
				new Heros(this, Soldat.TypesH.getTypeHAlea(),"H",this.trouvePositionVide());	
			if (inc <= NB_OBSTACLES)
				new Obstacle(this, Obstacle.TypeObstacle.getObstacleAlea(), this.trouvePositionVide());
			inc--;
		}	
	}
	
	/* Fonction principale du jeu c'est ici que tout est gérer : les tours, les deplacement, les actions... */
	public void jouerSoldats(PanneauJeu pj) {
		this.tour = pj.tour;
		pj.nombreSoldatVivant();
		if(this.tour == 0) {
			this.joueTourHeros(pj);
			this.joueTour(this.tour);
		}
		else {
			this.joueTourMonstre(pj);
			this.joueTour(this.tour);
			pj.finTour.doClick();
			pj.herosSelectione = null;	
		}
		pj.repaint();
		
	}
	/* On effectue une action pour chaque monstre */
	private void joueTourMonstre(PanneauJeu pj) {
		Heros h;
		for(int i = 0; i < LARGEUR_CARTE; i++) {
			for(int j = 0; j < HAUTEUR_CARTE; j++) {
				if(this.plateau[i][j] instanceof Heros) {
					h = (Heros) this.plateau[i][j];	
					if(h.aJoue == false)
						h.repos();
				}
				else if(this.plateau[i][j] instanceof Monstre) {
					Monstre m = (Monstre) this.plateau[i][j];
					if(m.aJoue == false) 
						if(this.actionCombatMonstre(m) == false) {
							this.deplaceSoldat(this.trouvePositionVide(m.getPosition()),m);
						}
				}		
			}
		}		
		
	}
	/* Le generale joueur decide quelle action faire */
	private void joueTourHeros(PanneauJeu pj) {
		// si le premier clic est sur le boutton "fin tour" alors clic n'est pas initialise et engendre une erreur
		if(pj.clic == null)
			return;
		
		Element e = this.getElement(pj.clic); 
		if(e instanceof Heros) {
			pj.lastClic = new Position(pj.clic.getX(), pj.clic.getY());
			pj.herosSelectione = (Heros) e;
		}
		
		if (pj.lastClic == null)
			return;
		
		if( (pj.clic.getX() == pj.lastClic.getX() && pj.clic.getY() == pj.lastClic.getY()) == false) {
			if( (this.actionHeros(pj.lastClic, pj.clic)) == true ) {
				pj.lastClic = null;
			}
			pj.herosSelectione = null;
		}
	
	}
	
	/* 
	 * On cherche si un Heros est dans le champs visuelles d'un Monstre et on les fait combatre 
	 * --> A changer en utilisant trouveHeros
	 * */
	private boolean actionCombatMonstre(Monstre m) {
		for(int i = 0; i < LARGEUR_CARTE; i++)
			for(int j = 0; j < HAUTEUR_CARTE; j++) 
				if(this.plateau[i][j] instanceof Heros) {
					Heros h = (Heros) this.plateau[i][j];
					if(m.dedans(h.getPosition()) == true) {
						m.combat(h);
						return true;
					}
				}	
		return false;
	}
	
	/* Methode appelé lors de la mort d'un Soldat */
	public void mort(Soldat perso) { this.plateau[perso.getPosition().getX()][perso.getPosition().getY()] = null; }
	
	/* Deplace le Soldat a la position pos, si l'opperation a ete effectue alors retourne true sinon false */
	public boolean deplaceSoldat(Position pos, Soldat soldat) {
		if(pos.estValide() == true && this.plateau[pos.getX()][pos.getY()] == null && soldat.getPosition().estVoisine(pos)) {
			soldat.seDeplace(pos);
			return true;
		}
		return false;
	}
	
	/* Methode qui gere les actions des Heros */
	public boolean actionHeros(Position pos, Position pos2) {
		Soldat s = (Soldat)this.plateau[pos.getX()][pos.getY()];
		Heros h =  (Heros)this.plateau[pos.getX()][pos.getY()];
		
		if(h == null || this.getElement(pos2) instanceof Heros || h.aJoue == true) //Ajout :  pos.estVoisine(pos2) == false cf : enoncer pk ?
			return false;
		else if(this.plateau[pos2.getX()][pos2.getY()] instanceof Monstre) {
			Soldat s2 = (Soldat)this.plateau[pos2.getX()][pos2.getY()];
			if(h.dedans(s2.getPosition()) == true) 
				s.combat(s2);
		}
		
		this.deplaceSoldat(pos2, s);
		return true;
	}
	
	/*
	 * On remet aJoue = false en fonction du tour de la partie : tours des Monstre == 1 / tour des Heros == 0
	 * Lorsque c'est le tour des monstres alors les Heros doivent pouvoir bouger de nouveau, idem pour les Heros
	 *  */
	private void joueTour(int tour) {
		for(int i = 0; i < LARGEUR_CARTE; i++)
			for(int j = 0; j < HAUTEUR_CARTE; j++) {
				if(tour == 0 &&  this.plateau[i][j] instanceof Monstre) {	
					Monstre m = (Monstre) this.plateau[i][j];
					m.aJoue = false;
				}
				else if(tour == 1 && this.plateau[i][j] instanceof Heros) {
					Heros h = (Heros) this.plateau[i][j];
					h.aJoue = false;
				}
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
		List<Position> listePos= new ArrayList<>();
		for(int i = -1; i < 2; i++)
    		for(int j = -1; j < 2; j++) 
    			if(i != 0 || j != 0) 
    				listePos.add(new Position(pos.getX() + i, pos.getY() + j));
		return listePos;
	}
	
	/* 
	 * Trouve une position vide adjacente a pos sur la carte si aucune position adjacente est vide alors une position aleatoire est renvoye 
	 * Cette methode utilise la liste de position adjacente renvoyer par la methode ci-dessus
	 */
	public Position trouvePositionVide(Position pos) {
		List<Position> listePos = this.positionAdjacente(pos);
		int i = 0;
		while(listePos.size() != 0) {
			i = (int) (Math.random() * listePos.size());
			if(listePos.get(i).estValide() == true && this.plateau[listePos.get(i).getX()][listePos.get(i).getY()] == null) 
				return listePos.get(i);
			
			listePos.remove(i);
		}
		return listePos.get(i);
	}
	
	/* renvoie un Heros trouve aleatoirement sur la carte */
	public Heros trouveHeros() {
		List<Heros> listePosHeros = new ArrayList<>();
		for(int i = 0; i < LARGEUR_CARTE; i++)
			for(int j = 0; j < HAUTEUR_CARTE; j++) {
				Element e = this.getElement(new Position(i,j));
				if(e instanceof Heros)
					listePosHeros.add((Heros) e);
			}
		return listePosHeros.get((int)Math.random() * listePosHeros.size());
	}
	
	/* 
	 * Trouve un héros choisi aléatoirement parmi les 8 positions adjacentes de pos
	 * Si aucun Heros est trouver alors un Heros est choisi aleatoirement sur la carte
	 */
	public Heros trouveHeros(Position pos) {
		if (pos.estValide() == true && this.plateau[pos.getX()][pos.getY()] instanceof Heros)
			return (Heros) this.plateau[pos.getX()][pos.getY()];
		else {
			List<Position> listePos = this.positionAdjacente(pos);
			int i = 0;
			while(listePos.size() != 0) {
				i = (int) (Math.random() * listePos.size()-1);
				if(listePos.get(i).estValide() == true && this.plateau[listePos.get(i).getX()][listePos.get(i).getY()] instanceof Heros) 
					break;
				else 
					listePos.remove(i);
			}
			if(listePos.size() == 0) {
				System.out.println("Aucun Heros au Alentours ! ==> Selection au Hasard");
				return this.trouveHeros();
			}
			return (Heros) this.plateau[listePos.get(i).getX()][listePos.get(i).getY()];
		}
	}
	
	/* Retourne l'element sur la carte a la position pos */
	public Element getElement(Position pos) { return plateau[pos.getX()][pos.getY()]; }
	
	/* 
	 * Methode de dessin principale :
	 *  - Elle dessine la carte et tout les elements present 
	 *  - Pour chaque Heros trouver on va dessiner les element a sa portee 
	 */
	public void toutDessiner(Graphics g) {
		for(int i = 0; i < LARGEUR_CARTE; i++)
			for(int j = 0; j < HAUTEUR_CARTE; j++) {
				if (this.plateau[i][j] instanceof Heros) 
					this.plateau[i][j].seDessiner(g);
				g.setColor(COULEUR_GRILLE);
				g.drawRect(i * NB_PIX_CASE, j * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE); 
			}
	}		
}
