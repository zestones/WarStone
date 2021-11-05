package wargame;
import java.awt.Graphics;

import java.util.ArrayList;
import java.util.List;

public class Carte implements IConfig {
	Element[][] plateau;
	//Autre Solution a trouver c moche
	Heros lastHeros;
	
	Carte(){
		// Initialisation de la carte
		this.plateau = new Element[LARGEUR_CARTE][HAUTEUR_CARTE];
		for(int i = 0; i < LARGEUR_CARTE; i++)
			for(int j = 0; j < HAUTEUR_CARTE; j++) {
				plateau[i][j] = null;
			}		
		// Teste deplacement d'un Heros
		this.lastHeros = new Heros(this, Soldat.TypesH.NAIN,"H", new Position(10,10));	
		new Monstre(this, Soldat.TypesM.GOBELIN,"O", new Position(9,10));
		//System.out.println("==== POSITION ORIGINALE --> " + myHeros.getPosition().toString());
		
		this.actionHeros(new Position(10,10), new Position(9,10));
		
		// Cration des Elements
		/*int inc = Math.max(NB_MONSTRES, Math.max(NB_OBSTACLES, NB_HEROS));
		while(inc > 0 ){
			if(inc <= NB_MONSTRES)
				new Monstre(this, Soldat.TypesM.getTypeMAlea(),""+inc, this.trouvePositionVide());
			if(inc <= NB_HEROS) 
				this.lastHeros = new Heros(this, Soldat.TypesH.getTypeHAlea(),"H",this.trouvePositionVide());	
			if (inc <= NB_OBSTACLES)
				new Obstacle(this, Obstacle.TypeObstacle.getObstacleAlea(), this.trouvePositionVide());
			inc--;
		}*/
		
		//Teste des Fonction
		
		/*Position pos = this.trouvePositionVide(new Position(0,0));
		System.out.println("Ma pos Adjacente trouver : " + pos.toString());
		
		Heros h = this.trouveHeros();
		System.out.println("Nom Heros : " + h.nom);
		
		Heros a = this.trouveHeros(new Position(1,10));
		System.out.println("Nom Heros Adjacent : " + a.nom);
		
		this.mort(a);*/
		
		//System.out.println("deplacement --> " + this.deplaceSoldat(new Position(7,3), myHeros));
		
	}
	
	/* Methode appelé lors de la mort d'un Soldat */
	void mort(Soldat perso) {
		this.plateau[perso.getPosition().getX()][perso.getPosition().getY()] = null;
		System.out.println("Mort de ma position ==> " + perso.getPosition().toString());
	}
	
	/* Deplace le Soldat a la position pos, si l'opperation a ete effectue alors retourne true sinon false */
	boolean deplaceSoldat(Position pos, Soldat soldat) {
		//System.out.println("distance de deplacement : --> " + soldat.getPosition().distance(pos) + "Ma portee : " + soldat.getPortee());
		if(pos.estValide() == true && this.plateau[pos.getX()][pos.getY()] == null && soldat.getPosition().estVoisine(pos)) {
			soldat.seDeplace(pos);
			return true;
		}
		return false;
	}
	
	boolean actionHeros(Position pos, Position pos2) {
		if(this.plateau[pos.getX()][pos.getY()] == null || pos.estVoisine(pos2) == false || this.getElement(pos2) instanceof Heros ) //Ajout : si le heros a deja joue son tour
			return false;
		else if(this.plateau[pos2.getX()][pos2.getY()] instanceof Monstre) {
			Soldat s = (Soldat)this.plateau[pos.getX()][pos.getY()];
			Soldat s2 = (Soldat)this.plateau[pos2.getX()][pos2.getY()];
			s.combat(s2);
			return true;
		}
		this.deplaceSoldat(pos2, (Soldat)this.plateau[pos.getX()][pos.getY()]);
		return true;
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
		listePos.add(new Position(pos.getX()-1, pos.getY()-1));
		listePos.add(new Position(pos.getX(), pos.getY()-1));
		listePos.add(new Position(pos.getX()+1, pos.getY()-1));
		listePos.add(new Position(pos.getX()+1, pos.getY()));
		listePos.add(new Position(pos.getX()+1, pos.getY()+1));
		listePos.add(new Position(pos.getX(), pos.getY()+1));
		listePos.add(new Position(pos.getX()-1, pos.getY()+1));
		listePos.add(new Position(pos.getX()-1, pos.getY()));
		return listePos;
	}
	
	/* 
	 * Trouve une position vide adjacente a pos sur la carte si aucune position adjacente est vide alors une position aleatoire est renvoye 
	 * Cete methode utilise une liste de position adjacente renvoyer par la methode ci-dessus
	 */
	Position trouvePositionVide(Position pos) {
		if (pos.estValide() == true && this.plateau[pos.getX()][pos.getY()] == null)
			return pos;
		else {
			List<Position> listePos = this.positionAdjacente(pos);
			int i = 0;
			while(listePos.size() != 0) {
				i = (int) (Math.random() * listePos.size()-1);
				if(listePos.get(i).estValide() == true && this.plateau[listePos.get(i).getX()][listePos.get(i).getY()] == null) 
					break;
				else 
					listePos.remove(i);
			}
			if(listePos.size() == 0) {
				System.out.println("Aucun Heros au Alentours ! ==> Selection au Hasard");
				return this.trouvePositionVide();
			}
			return  listePos.get(i);
		}
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
	public Element getElement(Position pos) {
		if(plateau[pos.getX()][pos.getY()] instanceof Heros) {
			//System.out.println("Je suis un Heros ! ");
		}
		else if(plateau[pos.getX()][pos.getY()] instanceof Monstre) {
			//System.out.println("Je suis un Monstre ! ");
		}	
		return plateau[pos.getX()][pos.getY()];
	}
	
	/* 
	 * Methode de dessin principale :
	 *  - Elle dessine la carte et tout les elements present 
	 *  - Pour chaque Heros trouver on va dessiner les element a sa portee 
	 */
	public void toutDessiner(Graphics g) {
		for(int i = 0; i < LARGEUR_CARTE; i++)
			for(int j = 0; j < HAUTEUR_CARTE; j++) {
				if (this.plateau[i][j] instanceof Heros) {
					this.plateau[i][j].seDessiner(g);
					this.lastHeros = (Heros) this.plateau[i][j];
				}
				for(int k = 0; k < LARGEUR_CARTE; k++)
					for(int n = 0; n < HAUTEUR_CARTE; n++) 
						if(this.plateau[k][n] != null)
							if (this.lastHeros.dedans(this.plateau[k][n].getPosition()) == true)
								this.plateau[k][n].seDessiner(g);
				g.setColor(COULEUR_GRILLE);
				g.drawRect(i * NB_PIX_CASE, j * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE); 
				g.drawRect(i * NB_PIX_CASE, j * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE); 
			}
		
	}	
}
