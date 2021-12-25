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

import java.awt.Graphics;

import carte.Carte;
import utile.Position;
import wargame.IConfig;

/**
 * The Class Projectile.
 */
public class Projectile implements IConfig {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The carte. */
	private Carte carte;
	
	/** The origine. */
	private Position origine;
	
	/** The arrive. */
	private Position arrive;
	
	/** The toucher. */
	public boolean toucher;
	
	/**
	 * Instantiates a new projectile.
	 *
	 * @param depart the depart
	 * @param arrive the arrive
	 * @param c the c
	 */
	Projectile(Position depart, Position arrive, Carte c){
		this.origine = depart;
		this.arrive = arrive;
		this.carte = c;
		this.toucher = false;
	}

	/**
	 * Dessine.
	 *
	 * @param g the g
	 */
	void dessine(Graphics g) {
		Position deplacement = this.origine;
		while(this.origine != this.arrive){
			if(this.carte.getElement(deplacement)instanceof Monstre) {
				this.toucher = true;
			}
	    	g.drawImage(fleche, deplacement.getX() * NB_PIX_CASE, deplacement.getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE, null);
	    	deplacement = new Position(deplacement.getX() + 10, deplacement.getY());
		}
	}
}
