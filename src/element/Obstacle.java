package element;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import carte.Camera;
import carte.Carte;
import utile.Position;

/**
 * Class Obstacle.
 */
public class Obstacle extends Element{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**  Les images des Obstacles */
	private static Image rocher = Toolkit.getDefaultToolkit().getImage("./res/img/background/jeu/rocher.png");
	private static Image eau = Toolkit.getDefaultToolkit().getImage("./res/img/background/jeu/water.png");
	private static Image foret = Toolkit.getDefaultToolkit().getImage("./res/img/background/jeu/foret.png");
	
	/** Image permettant de changer le fond de l'obstacle */
	private static Image fondObstacle = Toolkit.getDefaultToolkit().getImage("./res/img/background/jeu/fondObstacle.jpg");
	
	/**
	 * Enum TypeObstacle.
	 */
	public enum TypeObstacle {	
		
		ROCHER (rocher), FORET (foret), EAU (eau), LAVE(lave);
		
		private final Image IMAGE;
		
		/**
		 * Instancie un nouveau type d'obstacle.
		 *
		 * @param couleur 
		 * @param img 
		 */
		TypeObstacle(Image img) { IMAGE = img; }
		
		/**
		 * Gets image de l'obstacle.
		 *
		 * @return image
		 */
		public Image getImage() { return IMAGE; } 
		
		/**
		 * Gets un obstacle aleatoire.
		 *
		 * @return obstacle
		 */
		public static TypeObstacle getObstacleAlea() {
			return values()[(int)(Math.random()*values().length)];
		}
	}
	
	/** le type. */
	private TypeObstacle TYPE;
	private Position pos;
	
	/**
	 * Instancie un nouvelle obstacle.
	 *
	 * @param carte
	 * @param type 
	 * @param pos 
	 */
	public Obstacle(Carte carte, TypeObstacle type, Position pos) {
		this.pos = pos;
		this.TYPE = type; 
		carte.setElement(this);; 
	}

	
	/**
	 * Se dessiner dessine l'obstacle
	 *
	 * @param g
	 * @param cam 
	 */
	public void seDessiner(Graphics g, Camera cam) {
		g.drawImage(fondObstacle, (this.getPosition().getX() * TAILLE_CARREAU) - cam.getDx() * TAILLE_CARREAU, (this.getPosition().getY() * TAILLE_CARREAU) - cam.getDy() * TAILLE_CARREAU, TAILLE_CARREAU, TAILLE_CARREAU, null);
		g.drawImage(this.TYPE.getImage(), (this.getPosition().getX() * TAILLE_CARREAU) - cam.getDx() * TAILLE_CARREAU, (this.getPosition().getY() * TAILLE_CARREAU) - cam.getDy() * TAILLE_CARREAU, TAILLE_CARREAU, TAILLE_CARREAU, null);
	}
	
	/**
	 * se dessiner MiniCarte dessine l'obstacle sur la miniCarte.
	 *
	 * @param g
	 */
	public void seDessinerMiniCarte(Graphics g) {
		g.drawImage(fondObstacle, this.getPosition().getX() * TAILLE_CARREAU_MINI_CARTE, this.getPosition().getY() * TAILLE_CARREAU_MINI_CARTE, TAILLE_CARREAU_MINI_CARTE, TAILLE_CARREAU_MINI_CARTE, null);
		g.drawImage(this.TYPE.getImage(), this.getPosition().getX() * TAILLE_CARREAU_MINI_CARTE, this.getPosition().getY() * TAILLE_CARREAU_MINI_CARTE, TAILLE_CARREAU_MINI_CARTE, TAILLE_CARREAU_MINI_CARTE, null);
	}
	
	/**
	 * To string.
	 *
	 * @return string
	 */
	public String toString() { return ""+TYPE; }
	
	/**
	 * Gets position.
	 *
	 * @return position
	 */
	public Position getPosition() {	return pos;	}

	/**
	 * Gets image.
	 *
	 * @return image
	 */
	public Image getImage() { return this.TYPE.getImage(); }

	public String getHistoire() {return null;}
	
	/**
	 * Gets type.
	 *
	 * @return type
	 */
	public String getType() { return ""+this.TYPE.name(); }
}