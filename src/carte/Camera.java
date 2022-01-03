package carte;

import utile.Position;
import wargame.IConfig;

/**
 * Class Camera.
 */
public class Camera implements IConfig {
	
	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Definition de la class camera et ses des methodes. */
	public Carte c;
	private Position pos;
		
	/**
	 * Le contructeur de camera prend la carte un indice de deplacement x et y.
	 *
	 * @param c
	 * @param dx
	 * @param dy
	 */
	public Camera(Carte c, int dx, int dy) {
		this.c = c ;
		this.pos = new Position(dx, dy);
		this.centreCamera();
	}
	
	/**
	 * Verification que le deplacement ne sorte pas de la carte.
	 */ 
	public void estValideDeplacement() {
		if(this.getDx() < 0) 
			this.setDx(0);
		else if(this.getDx() > NB_COLONNES - NB_COLONNES_VISIBLES)
			this.setDx(NB_COLONNES - NB_COLONNES_VISIBLES);
		
		if(this.getDy() < 0) 
			this.setDy(0);
		else if(this.getDy() > NB_LIGNES - NB_LIGNES_VISIBLES)	
			this.setDy(NB_LIGNES - NB_LIGNES_VISIBLES);
	}
	
	/**
	 * Positionement de la camera au centre de notre carte.
	 */
	private void centreCamera() {
		this.setDx(NB_COLONNES_VISIBLES/2);
		this.setDy(NB_LIGNES_VISIBLES/2);
		estValideDeplacement();
	}
	
	/**
	 *  Translatation des points.
	 *
	 * @param x : int
	 * @param y : int
	 */
	public void deplacement(int x, int y) {
		this.pos.translater(x, y);
		estValideDeplacement();
	}
	
	/**
	 * Recuperation de la valeur de deplacement en X.
	 *
	 * @return dx : int
	 */
	public int getDx() { return this.pos.getX(); }
	
	/**
	 * Recuperation de la valeur de deplacment en Y.
	 *
	 * @return dy : int
	 */
	public int getDy() { return this.pos.getY(); }
	
	/**
	 * Modification de la valeur de deplacement X.
	 *
	 * @param dx : int
	 */
	public void setDx(int dx) { this.pos.setX(dx); estValideDeplacement(); }
	
	/**
	 * Modification de la valeur de deplacement en Y.
	 *
	 * @param dy : int
	 */
	public void setDy(int dy) { this.pos.setY(dy); estValideDeplacement(); }
}