package element;
import java.awt.Graphics;
import java.awt.Image;

import carte.Camera;
import utile.Position;
import wargame.IConfig;

/**
 * Class Element.
 */
public abstract class Element implements IConfig {	
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Se dessiner minia.
	 *
	 * @param g
	 */
	public abstract void seDessinerMiniCarte(Graphics g);
	
	/**
	 * Se dessiner.
	 *
	 * @param g 
	 * @param cam
	 */
	public abstract void seDessiner(Graphics g, Camera cam);
	
	/**
	 * Gets  position.
	 *
	 * @return position
	 */
	public abstract Position getPosition();
	
	/**
	 * To string.
	 *
	 * @return string
	 */
	public abstract String toString();
	
	/**
	 * Gets  image.
	 *
	 * @return image
	 */
	public abstract Image getImage();
	
	/**
	 * Gets  type.
	 *
	 * @return type
	 */
	public abstract String getType();
	
	/**
	 * Gets histoire.
	 *
	 * @return histoire
	 */
	public abstract String getHistoire();
}
