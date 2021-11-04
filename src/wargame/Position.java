package wargame;

import static java.lang.Math.pow;


public class Position implements IConfig {
	private int x, y;
	private int SAM_ALIGNE = 1;
	private int SIAM = -1;
	
	Position(int x, int y) { this.x = x; this.y = y; }
	Position() {this.x = (int) (Math.random() * LARGEUR_CARTE-1); this.y = (int) (Math.random() * HAUTEUR_CARTE-1); }
	public int getX() { return x; }
	public int getY() { return y; }
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public boolean estValide() {
		if (x<0 || x>=LARGEUR_CARTE || y<0 || y>=HAUTEUR_CARTE) return false; else return true;
	}
	public String toString() { return "("+x+","+y+")"; }
	public boolean estVoisine(Position pos) {
		return ((Math.abs(x-pos.x)<=1) && (Math.abs(y-pos.y)<=1));
	}
	
	/* Renvoie la valeur entiere de la distance entre 2 position */
	public int distance(Position p) {
		return (int) Math.sqrt(pow((this.getX()-p.getX()),2)+pow(this.getY()-p.getY(),2));
	}
	
	/* Renvoie le signe de l'angle SAM et ALIGNE renvoie le meme entier 
	 * car le premier point de la zone du champ visuelle est le meme point que l'element chercher si la portee est egale a 1 
	 * ou si l'element est adjacent a la limite de la portee 
	 */
	public int signeAngle(Position b, Position c) {
		double angle = (b.x - this.x) * (c.y - this.y) - (c.x - this.x) * (b.y - this.y);
		if (angle < 0)
			return SIAM;
		return SAM_ALIGNE;	
	}
	
	/* Verifie les positions et les remplace par les limites de la carte si celle-ci ne sont pas valide */
	public void verifPosition() {
		if (this.getX() >= LARGEUR_CARTE ) 
			this.setX(LARGEUR_CARTE - 1);
		if (this.getY() >= HAUTEUR_CARTE) 
			this.setY(HAUTEUR_CARTE - 1);
		if(this.	getX() < 0) 
			this.setX(0); 
		if(this.getY() < 0) 
			this.setY(0);
	}
}