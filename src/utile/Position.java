package utile;

import static java.lang.Math.pow;

import wargame.IConfig;

public class Position implements IConfig {
	private static final long serialVersionUID = 1L;
	private int x, y;
	private int SAM_ALIGNE = 1;
	private int SIAM = -1; 
	
	public Position(int x, int y) { this.x = x; this.y = y; }
	public Position() {this.x = (int) (Math.random() * LARGEUR_CARTE_CASE-1); this.y = (int) (Math.random() * HAUTEUR_CARTE_CASE-1); }
	public int getX() { return x; }
	public int getY() { return y; }
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public String toString() { return "("+x+","+y+")"; }
	public double distance(Position p) { return Math.sqrt(pow(this.x-p.x,2)+pow(this.y-p.y,2)); }
	public boolean estIdentique(Position p) { return (this.distance(p) == 0); }

	// Translater un point 
	public void translater(int dx, int dy) {
		this.x += dx;  
		this.y += dy;
	}
	
	public boolean estValide() {
		if (x<0 || x>=LARGEUR_CARTE_CASE || y<0 || y>=HAUTEUR_CARTE_CASE) return false; else return true;
	}
	
	public boolean estVoisine(Position pos) {
		return ((Math.abs(x-pos.x)<=1) && (Math.abs(y-pos.y)<=1));
	}
		
	/* Renvoie le signe de l'angle, SAM et ALIGNE renvoie le meme entier 
	 * car le premier point de la zone du champ visuelle est le meme point que l'element chercher si la portee est egale a 1 
	 * ou si l'element est adjacent a la limite de la portee 
	 */
	public int signeAngle(Position b, Position c) {
		double angle = (b.getX() - this.getX()) * (c.getY() - this.getY()) - (c.getX() - this.getX()) * (b.getY() - this.getY());
		if (angle < 0)
			return SIAM;
		return SAM_ALIGNE;	
	}
}