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
	
	private int coeff;
	
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
		this.coeff = (this.arrive.getY() - this.origine.getY()) / (this.arrive.getX() - this.origine.getX());
		this.carte = c;
		this.toucher = false;
	}

	/**
	 * Dessine.
	 *
	 * @param g the g
	 */
	void dessineProjectile(Graphics g) {
		System.out.println("origine  " + this.origine.toString());
		g.drawImage(grass, this.origine.getX() * NB_PIX_CASE, this.origine.getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE, null);
	}
}
