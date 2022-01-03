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

import carte.Camera;
import utile.Position;
import wargame.IConfig;

/**
 * The Class Projectile.
 */
public class Projectile implements IConfig {
	
	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private Position depart;
	private Position arrive;
	private Position pos;
	private Position ou;

	
	private int deplacementX;
	private int deplacementY;
	public boolean toucher;
	
	Projectile(Position depart, Position arrive) {
		this.depart = depart;
		this.arrive = arrive;
		this.pos = depart;
		this.deplacementX = this.deplacementY = 0;
		this.toucher = false;
	}
	
	void dessineProjectile(Graphics g, Camera cam) {
		int dx = cam.getDx() * TAILLE_CARREAU;
    	int dy = cam.getDy() * TAILLE_CARREAU;
    	
  		g.drawImage(fleche, (this.pos.getX() * TAILLE_CARREAU) - dx + this.deplacementX + TAILLE_CARREAU/2, (this.pos.getY() * TAILLE_CARREAU) - dy + this.deplacementY + TAILLE_CARREAU/2, TAILLE_CARREAU/2, TAILLE_CARREAU/4, null);
		
		this.effectuerDeplacement(cam);
	}
    
    private void effectuerDeplacement(Camera cam) {
    	if(!this.toucher) {
    		this.deplacementX += this.arrive.getX() - this.pos.getX();
    		this.deplacementY += this.arrive.getY() - this.pos.getY();
    		this.ou = new Position(this.pos.getX() + this.deplacementX / TAILLE_CARREAU, this.pos.getY() + this.deplacementY / TAILLE_CARREAU);
    		if(!this.depart.estIdentique(this.ou) && this.ou.estIdentique(this.arrive)) {
    			this.toucher = true;
    		}
    				
    	}
    }
}
