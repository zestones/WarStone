package wargame;
import java.awt.Graphics;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Carte implements IConfig {
	Element[][] plateau;
	
	Carte(){
		// Initialisation de la carte
		this.plateau = new Element[LARGEUR_CARTE][HAUTEUR_CARTE];
		for(int i = 0; i < LARGEUR_CARTE; i++)
			for(int j = 0; j < HAUTEUR_CARTE; j++) {
				plateau[i][j] = null;
			}		
		
		new Heros(this, Soldat.TypesH.getTypeHAlea(),"Z",new Position(0,0));
		
		int inc = Math.max(NB_MONSTRES, Math.max(NB_OBSTACLES, NB_HEROS));
		while(inc > 0 ){
			if(inc < NB_MONSTRES)
			new Monstre(this, Soldat.TypesM.getTypeMAlea(),"M", this.trouvePositionVide());
			if(inc < NB_HEROS)
				new Heros(this, Soldat.TypesH.getTypeHAlea(),"H",this.trouvePositionVide());
			if (inc < NB_OBSTACLES)
				new Obstacle(this, Obstacle.TypeObstacle.getObstacleAlea(), this.trouvePositionVide());
			inc--;
		}
		
		/*Teste des Fonction*/
		Position pos = this.trouvePositionVide(new Position(0,0));
		System.out.println("Ma pos Adjacente trouver : " + pos.toString());
		
		Heros h = this.trouveHeros();
		System.out.println("Nom Heros : " + h.nom);
		
		Heros a = this.trouveHeros(new Position(1,10));
		System.out.println("Nom Heros Adjacent : " + a.nom);
	}
	
	public Position trouvePositionVide() {
		Position pos = new Position();
		if (pos.estValide() == true && this.plateau[pos.getX()][pos.getY()] == null)
			return pos;
		return this.trouvePositionVide();
	}
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
	public Element getElement(Position pos) {
		if(plateau[pos.getX()][pos.getY()] instanceof Heros) {
			//System.out.println("Je suis un Heros ! ");
		}
		else if(plateau[pos.getX()][pos.getY()] instanceof Monstre) {
			//System.out.println("Je suis un Monstre ! ");
		}	
		return plateau[pos.getX()][pos.getY()];
	}
	
	boolean deplaceSoldat(Position pos, Soldat soldat) {
		if(pos.estValide() == false)
			return false;
			
		return true;
	}
	
	public void toutDessiner(Graphics g) {
		for(int i = 0; i < LARGEUR_CARTE; i++)
			for(int j = 0; j < HAUTEUR_CARTE; j++) {
				if(this.plateau[i][j] == null) {
					g.setColor(Color.black);
					g.drawRect(i * NB_PIX_CASE, j * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE); 
				}
				else {
					this.plateau[i][j].seDessiner(g);
				}
			}
	}	
}
