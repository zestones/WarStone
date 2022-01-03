package utile;

import static java.lang.Math.pow;

import wargame.IConfig;

/**
 * Class Position. 
 */
public class Position implements IConfig {
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**  x, y. */
	public int x, y;
	
	/**
	 * Enum POINT_CARDINAUX.
	 */
	public enum POINT_CARDINAUX {NORD, SUD, EST, OUEST, NORD_EST, NORD_OUEST, SUD_EST, SUD_OUEST, MILIEU};
	
	/** sam aligne. */
	private static final int SAM_ALIGNE = 1;
	
	/** siam. */
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
	public Position() {this.x = (int) (Math.random() * LARGEUR_CARTE_CASE-1); this.y = (int) (Math.random() * HAUTEUR_CARTE_CASE-1); }
	
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
	 * @param x new x
	 */
	public void setX(int x) { this.x = x; }
	
	/**
	 * Sets y.
	 *
	 * @param y new y
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
	 * @param p la position
	 * @return double
	 */
	public double distance(Position p) { return Math.sqrt(pow(this.x-p.x,2)+pow(this.y-p.y,2)); }
	
	/**
	 * Est identique.
	 *
	 * @param p
	 * @return true, if successful
	 */
	public boolean estIdentique(Position p) { return (this.distance(p) == 0); }

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
	 * Est valide.
	 *
	 * @return true, if successful
	 */
	public boolean estValide() {
		if (x<0 || x>=LARGEUR_CARTE_CASE || y<0 || y>=HAUTEUR_CARTE_CASE) return false; else return true;
	}
	
	/**
	 * Est voisine.
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
	public int signeAngle(Position b, Position c) {
		double angle = (b.getX() - this.getX()) * (c.getY() - this.getY()) - (c.getX() - this.getX()) * (b.getY() - this.getY());
		if (angle < 0)
			return SIAM;
		return SAM_ALIGNE;	
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