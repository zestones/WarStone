package wargame;

import static java.lang.Math.pow;

public class Position implements IConfig {
	private int x, y;
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
	public int distance(Position p) {
		return (int) Math.sqrt(pow((this.getX()-p.getX()),2)+pow(this.getY()-p.getY(),2));
	}
	
	 public void verifPosition() {
	    	if (this.getX() >= LARGEUR_CARTE ) 
	    		this.setX(LARGEUR_CARTE - 1);
			if (this.getY() >= HAUTEUR_CARTE) 
				this.setY(HAUTEUR_CARTE - 1);
			if(this.getX() < 0) 
				this.setX(0); 
			if(this.getY() < 0) 
				this.setY(0);
	    }
}