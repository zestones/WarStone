/********************************************************************
 * 							WarStone								*
 *  -------------------------------------------------------------	*
 * |	 Université Jean-Monnet    L3-Infos 		    2021	 |	*
 *  -------------------------------------------------------------	*
 * 	  BEGGARI ISLEM - CHATAIGNIER ANTOINE - BENGUEZZOU Idriss		*
 * 																	*
 * 														carte		*
 * ******************************************************************/
package carte;

import wargame.IConfig;

/**
 * 
 * @author Islem, Antoine, Idriss  
 *
 */
public class Camera implements IConfig {
	private static final long serialVersionUID = 1L;
	/**
	 * Definition de la class camera et ses des methodes 
	 */
	public Carte c;
	private int dx, dy;
	/**
	 * Le contructeur de camera prend la carte un indice de deplacement x et y.
	 * @param c
	 * @param dx
	 * @param dy
	 */
	 	public Camera(Carte c, int dx, int dy) {
		this.c = c ;
		this.dx = dx;
		this.dy = dy;
		this.centreCamera();
	}
	
	/**
	 * Verification que le deplacement ne sorte pas de la carte  
	 */ 
	public void estValideDeplacement() {
		if(dx < 0) 
			dx = 0;
		else if(dx > LARGEUR_CARTE_CASE - LARGEUR_CASE_VISIBLE)
			dx = LARGEUR_CARTE_CASE - LARGEUR_CASE_VISIBLE;
		
		if(dy < 0) 
			dy = 0;
		else if(dy > HAUTEUR_CARTE_CASE - HAUTEUR_CASE_VISIBLE)	
			dy = HAUTEUR_CARTE_CASE - HAUTEUR_CASE_VISIBLE;
	}
	
	/**
	 * Positionement de la camera au centre de notre carte
	 */
	private void centreCamera() {
		dx = LARGEUR_CASE_VISIBLE/2;    
		dy = HAUTEUR_CASE_VISIBLE/2;
		estValideDeplacement();
	}
	
	/**
	 *  Translatation des points
	 * @param x : int
	 * @param y : int
	 */
	public void deplacement(int x, int y) {
		dx += x;
		dy += y;
		estValideDeplacement();
	}
	/**
	 * Recuperation de la valeur de deplacement en X
	 * @return dx : int
	 */
	public int getDx() { return dx; }
	/**
	 * Recuperation de la valeur de deplacment en Y
	 * @return dy : int
	 */
	public int getDy() { return dy; }
	/**
	 * Modification de la valeur de deplacement X
	 * @param dx : int 
	 */
	public void setDx(int dx) { this.dx = dx; }
	/**
	 * Modification de la valeur de deplacement en Y
	 * @param dy : int
	 */
	public void setDy(int dy) { this.dy = dy; }
}