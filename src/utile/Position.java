package utile;

import static java.lang.Math.pow;

import java.util.ArrayList;
import java.util.List;

import fenetrejeu.panneaujeu.IConfig;

/**
 * Class Position. 
 */
public class Position implements IConfig {
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	public int x, y;
	
	/**
	 * Enum POINT_CARDINAUX.
	 */
	public enum POINT_CARDINAUX {NORD, SUD, EST, OUEST, NORD_EST, NORD_OUEST, SUD_EST, SUD_OUEST, MILIEU};
	
	/** sens aiguille d'une montre et aligne. */
	private static final int SAM_ALIGNE = 1;
	
	/** sens inverse des aiguilles d'une montre. */
	private static final int SIAM = -1; 
	
	/**
	 * Instancie une nouvelle position.
	 *
	 * @param x
	 * @param y
	 */
	public Position(int x, int y) { this.x = x; this.y = y; }
	
	/**
	 * Instancie une nouvelle position aleatoire.
	 */
	public Position() {this.x = (int) (Math.random() * NB_COLONNES-1); this.y = (int) (Math.random() * NB_LIGNES-1); }
	
	/**
	 * Gets x.
	 *
	 * @return x
	 */
	public int getX() { return x; }
	
	/**
	 * Gets y.
	 *
	 * @return y
	 */
	public int getY() { return y; }
	
	/**
	 * Sets x.
	 *
	 * @param x
	 */
	public void setX(int x) { this.x = x; }
	
	/**
	 * Sets y.
	 *
	 * @param y
	 */
	public void setY(int y) { this.y = y; }
	
	/**
	 * To string.
	 *
	 * @return string
	 */
	public String toString() { return "("+x+","+y+")"; }
	
	/**
	 * Distance.
	 *
	 * @param p
	 * @return int
	 */
	public int getDistance(Position p) { return (int) Math.sqrt(pow(this.x-p.x,2)+pow(this.y-p.y,2)); }
	
	/**
	 * Est identique.
	 *
	 * Test si deux position son identique 
	 *
	 * @param p
	 * @return true, if successful
	 */
	public boolean estIdentique(Position p) { return (this.getDistance(p) == 0); }

	/**
	 * Translater une position.
	 *
	 * @param dx 
	 * @param dy 
	 */
	public void translater(int dx, int dy) {
		this.x += dx;  
		this.y += dy;
	}
	
	/**
	 * Est position valide.
	 *
	 * @return true, if successful
	 */
	public boolean estValide() {
		if (x<0 || x>=NB_COLONNES || y<0 || y>=NB_LIGNES) return false; else return true;
	}
	
	/**
	 * Est position voisine.
	 *
	 * @param pos
	 * @return true, if successful
	 */
	public boolean estVoisine(Position pos) {
		return ((Math.abs(x-pos.x)<=1) && (Math.abs(y-pos.y)<=1));
	}
		
	/**
	 * Signe d'un angle.
	 * 
	 * Renvoie le signe de l'angle, SAM et ALIGNE renvoie le meme entier 
	 * car le premier point de la zone du champ visuelle est le meme point que l'element chercher si la portee est egale a 1 
	 * ou si l'element est adjacent a la limite de la portee 
	 *
	 * @param b
	 * @param c
	 * @return signe angle
	 */
	public int getSigneAngle(Position b, Position c) {
		double angle = (b.getX() - this.getX()) * (c.getY() - this.getY()) - (c.getX() - this.getX()) * (b.getY() - this.getY());
		if (angle < 0)
			return SIAM;
		return SAM_ALIGNE;	
	}
	
	/**
	 * Methode renvoyant une liste contenant les 8 positions adjacente a une case .
	 *
	 * @param pos
	 * @return List
	 */
	public List<Position> getPositionAdjacente() {
		List<Position> listePos = new ArrayList<>();
		for (int i = -1; i < 2; i++)
			for (int j = -1; j < 2; j++)
				if (i != 0 || j != 0)
					listePos.add(new Position(this.getX() + i, this.getY() + j));
		return listePos;
	}
	
	/**
	 * Gets position cardinal.
	 *
	 * @param pos
	 * @return pointCardinal
	 */
	public POINT_CARDINAUX getPositionCardinal(Position pos) {
		if(this.getX() == pos.getX() && this.getY() < pos.getY()) {
			return POINT_CARDINAUX.NORD;
		}				
		else if(this.getX() < pos.getX() && this.getY() < pos.getY()) {
			return POINT_CARDINAUX.NORD_OUEST;
		}
		else if(this.getX() < pos.getX() && this.getY() == pos.getY() ) {
			return POINT_CARDINAUX.OUEST;
		}
		else if(this.getX() < pos.getX() && this.getY() > pos.getY()) {
			return POINT_CARDINAUX.SUD_OUEST;
		}
		else if(this.getX() == pos.getX() && this.getY() > pos.getY()) {
			return POINT_CARDINAUX.SUD;
		}
		else if(this.getX() > pos.getX() && this.getY() > pos.getY()) {
			return POINT_CARDINAUX.SUD_EST;
		}
		else if(this.getX() > pos.getX() && this.getY() == pos.getY()) {
			return POINT_CARDINAUX.EST;
		}
		else if(this.getX() > pos.getX() && this.getY() < pos.getY()) {
			return POINT_CARDINAUX.NORD_EST;
		}
		return POINT_CARDINAUX.MILIEU;
	}
}