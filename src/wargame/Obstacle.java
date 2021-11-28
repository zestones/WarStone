package wargame;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;

public class Obstacle extends Element implements IConfig {
	private static final long serialVersionUID = 1L;
	private static Image rocher = Toolkit.getDefaultToolkit().getImage("./res/img/background/rocher.png");
	private static Image water = Toolkit.getDefaultToolkit().getImage("./res/img/background/water.png");
	private static Image foret = Toolkit.getDefaultToolkit().getImage("./res/img/background/foret.png");
	private static Image obstacle = Toolkit.getDefaultToolkit().getImage("./res/img/background/obstacle.jpg");
	
	public enum TypeObstacle {	
		ROCHER (COULEUR_ROCHER, rocher), FORET (COULEUR_FORET, foret), EAU (COULEUR_EAU, water);
		private final Color COULEUR;
		private final Image IMAGE;
		public Color getCouleur() { return COULEUR; } 
		TypeObstacle(Color couleur, Image img) { COULEUR  = couleur; IMAGE = img; }
		public Image getImage() { return IMAGE; } 
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
		//g.setColor(COULEUR_VIDE);
		//g.fillRect(this.pos.getX() * NB_PIX_CASE, this.pos.getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE); 
		
		g.drawImage(obstacle, this.pos.getX() * NB_PIX_CASE, this.pos.getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE, null);
		g.drawImage(this.TYPE.getImage(), this.pos.getX() * NB_PIX_CASE, this.pos.getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE, null);
    	
	}
	
	public String toString() { return ""+TYPE; }
	public Position getPosition() {	return pos;	}
}