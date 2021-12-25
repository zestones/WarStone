/********************************************************************
 * 							WarStone								*
 *  -------------------------------------------------------------	*
 * |	 Université Jean-Monnet    L3-Infos 		    2021	 |	*
 *  -------------------------------------------------------------	*
 * 	  BEGGARI ISLEM - CHATAIGNIER ANTOINE - BENGUEZZOU Idriss		*
 * 																	*
 * ******************************************************************/
package element;
import java.awt.Graphics;
import java.awt.Image;

import carte.Camera;
import utile.Position;
import wargame.IConfig;

/**
 * The Class Element.
 */
public abstract class Element implements IConfig {	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Se dessiner minia.
	 *
	 * @param g the g
	 */
	public abstract void seDessinerMinia(Graphics g);
	
	/**
	 * Se dessiner.
	 *
	 * @param g the g
	 * @param cam the cam
	 */
	public abstract void seDessiner(Graphics g, Camera cam);
	
	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public abstract Position getPosition();
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public abstract String toString();
	
	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public abstract Image getImage();
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public abstract String getType();
}
