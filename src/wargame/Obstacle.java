package wargame;

import java.awt.Color;
import java.awt.Graphics;

public class Obstacle extends Element implements IConfig {
	public enum TypeObstacle {
		ROCHER (COULEUR_ROCHER), FORET (COULEUR_FORET), EAU (COULEUR_EAU);
		private final Color COULEUR;
		TypeObstacle(Color couleur) { COULEUR = couleur; }
		public Color getCouleur() { return COULEUR; } 
		public static TypeObstacle getObstacleAlea() {
			return values()[(int)(Math.random()*values().length)];
		}
	}
	private TypeObstacle TYPE;
	private Position pos;
	Obstacle(Carte carte, TypeObstacle type, Position pos) {
		this.pos = pos;
		this.TYPE = type; 
		carte.plateau[this.pos.getX()][this.pos.getY()] = this; 
	}

	
	/* Dessin de l'obstacle */
	public void seDessiner(Graphics g) {
		g.setColor(this.TYPE.getCouleur());
    	g.fillRect(this.pos.getX() * NB_PIX_CASE, this.pos.getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE); 
	}
	
	public String toString() { return ""+TYPE; }
	public Position getPosition() {	return pos;	}
}