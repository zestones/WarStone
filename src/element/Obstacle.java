package element;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import carte.Carte;
import element.ISoldat.TypesH;
import utile.Position;
import wargame.IConfig;

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
	public Obstacle(Carte carte, TypeObstacle type, Position pos) {
		this.pos = pos;
		this.TYPE = type; 
		carte.plateau[this.getPosition().getX()][this.getPosition().getY()] = this; 
	}

	
	/* Dessin de l'obstacle */
	public void seDessiner(Graphics g) {
		g.drawImage(obstacle, this.getPosition().getX() * NB_PIX_CASE, this.getPosition().getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE, null);
		g.drawImage(this.TYPE.getImage(), this.getPosition().getX() * NB_PIX_CASE, this.getPosition().getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE, null);
	}
	
	/* Dessin de l'obstacle */
	public void seDessinerMinia(Graphics g) {
		g.drawImage(obstacle, this.getPosition().getX() * MINI_NB_PIX_CASE, this.getPosition().getY() * MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, null);
		g.drawImage(this.TYPE.getImage(), this.getPosition().getX() * MINI_NB_PIX_CASE, this.getPosition().getY() * MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, null);
	}
	
	public String toString() { return ""+TYPE; }
	public Position getPosition() {	return pos;	}

	public Image getImage() { return this.TYPE.getImage(); }


	@Override
	public String getType() { return ""+this.TYPE.name(); }
}