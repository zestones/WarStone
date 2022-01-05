package element;
import java.awt.Graphics;
import java.awt.Image;

import carte.Camera;
import carte.Carte;
import utile.Position;
import wargame.IConfig;

/**
 * Class Element.
 */
public abstract class Element implements IConfig {	
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private Position pos;
	protected Carte carte;
	
	Element(Carte carte, Position pos) {
		this.pos = pos;
	}
	
	  /**
     * Sets position.
     *
     * @param nouvPos new position
     */
    public void setPosition(Position nouvPos) { pos = new Position(nouvPos.getX(), nouvPos.getY()); }
    
    /**
     * Gets position.
     *
     * @return position
     */
    public Position getPosition() { return pos; }
	
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
