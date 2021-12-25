/********************************************************************
 * 							WarStone								*
 *  -------------------------------------------------------------	*
 * |	 Université Jean-Monnet    L3-Infos 		    2021	 |	*
 *  -------------------------------------------------------------	*
 * 	  BEGGARI ISLEM - CHATAIGNIER ANTOINE - BENGUEZZOU Idriss		*
 * 																	*
 * 														element		*
 * ******************************************************************/
package element;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import carte.Camera;
import carte.Carte;
import utile.Position;
import wargame.IConfig;

/**
 * The Class Obstacle.
 */
public class Obstacle extends Element implements IConfig {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The rocher. */
	private static Image rocher = Toolkit.getDefaultToolkit().getImage("./res/img/background/rocher.png");
	
	/** The water. */
	private static Image water = Toolkit.getDefaultToolkit().getImage("./res/img/background/water.png");
	
	/** The foret. */
	private static Image foret = Toolkit.getDefaultToolkit().getImage("./res/img/background/foret.png");
	
	/** The obstacle. */
	private static Image obstacle = Toolkit.getDefaultToolkit().getImage("./res/img/background/obstacle.jpg");
	
	/**
	 * The Enum TypeObstacle.
	 */
	public enum TypeObstacle {	
		
		/** The rocher. */
		ROCHER (COULEUR_ROCHER, rocher), 
 /** The foret. */
 FORET (COULEUR_FORET, foret), 
 /** The eau. */
 EAU (COULEUR_EAU, water);
		
		/** The couleur. */
		private final Color COULEUR;
		
		/** The image. */
		private final Image IMAGE;
		
		/**
		 * Gets the couleur.
		 *
		 * @return the couleur
		 */
		public Color getCouleur() { return COULEUR; }  
		
		/**
		 * Instantiates a new type obstacle.
		 *
		 * @param couleur the couleur
		 * @param img the img
		 */
		TypeObstacle(Color couleur, Image img) { COULEUR  = couleur; IMAGE = img; }
		
		/**
		 * Gets the image.
		 *
		 * @return the image
		 */
		public Image getImage() { return IMAGE; } 
		
		/**
		 * Gets the obstacle alea.
		 *
		 * @return the obstacle alea
		 */
		public static TypeObstacle getObstacleAlea() {
			return values()[(int)(Math.random()*values().length)];
		}
	}
	
	/** The type. */
	private TypeObstacle TYPE;
	
	/** The pos. */
	private Position pos;
	
	/**
	 * Instantiates a new obstacle.
	 *
	 * @param carte the carte
	 * @param type the type
	 * @param pos the pos
	 */
	public Obstacle(Carte carte, TypeObstacle type, Position pos) {
		this.pos = pos;
		this.TYPE = type; 
		carte.setElement(this);; 
	}

	
	/**
	 * Se dessiner.
	 *
	 * @param g the g
	 * @param cam the cam
	 */
	/* Dessin de l'obstacle */
	public void seDessiner(Graphics g, Camera cam) {
		g.drawImage(obstacle, (this.getPosition().getX() * NB_PIX_CASE) - cam.getDx() * NB_PIX_CASE, (this.getPosition().getY() * NB_PIX_CASE) - cam.getDy() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE, null);
		g.drawImage(this.TYPE.getImage(), (this.getPosition().getX() * NB_PIX_CASE) - cam.getDx() * NB_PIX_CASE, (this.getPosition().getY() * NB_PIX_CASE) - cam.getDy() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE, null);
	}
	
	/**
	 * Se dessiner minia.
	 *
	 * @param g the g
	 */
	/* Dessin de l'obstacle */
	public void seDessinerMinia(Graphics g) {
		g.drawImage(obstacle, this.getPosition().getX() * MINI_NB_PIX_CASE, this.getPosition().getY() * MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, null);
		g.drawImage(this.TYPE.getImage(), this.getPosition().getX() * MINI_NB_PIX_CASE, this.getPosition().getY() * MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, null);
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() { return ""+TYPE; }
	
	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public Position getPosition() {	return pos;	}

	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public Image getImage() { return this.TYPE.getImage(); }


	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	@Override
	public String getType() { return ""+this.TYPE.name(); }
}