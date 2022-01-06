package carte.element;

import java.awt.Graphics;

import carte.Camera;
import fenetrejeu.panneaujeu.IConfig;
import utile.Position;

/**
 * Class Projectile.
 */
public class Projectile implements IConfig {
	
	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private Position depart;
	private Position arrive;
	private Position pos;
	
	private int deplacementX;
	private int deplacementY;
	public boolean toucher;
	
	/**
	 * Instancie un nouveau projectile.
	 *
	 * @param depart 
	 * @param arrive
	 */
	Projectile(Position depart, Position arrive) {
		this.depart = depart;
		this.arrive = arrive;
		
		this.deplacementX = this.deplacementY = 0;
		this.toucher = false;
	}
	/**
	 * dessine le projectile
	 * 
	 * @param g
	 * @param cam
	 */
	void dessineProjectile(Graphics g, Camera cam) {
		int dx = cam.getDx() * TAILLE_CARREAU;
    	int dy = cam.getDy() * TAILLE_CARREAU;
    	
  		g.drawImage(fleche, (this.depart.getX() * TAILLE_CARREAU) - dx + this.deplacementX + TAILLE_CARREAU/2, (this.depart.getY() * TAILLE_CARREAU) - dy + this.deplacementY + TAILLE_CARREAU/2, TAILLE_CARREAU/2, TAILLE_CARREAU/4, null);
		
		this.effectuerDeplacement(cam);
	}
    /**
     * Change la position du projectile et verifie que celui ci na pas encore toucher un soldat
     * 
     * @param cam
     */
    private void effectuerDeplacement(Camera cam) {
    	if(!this.toucher) {
    		this.deplacementX += this.arrive.getX() - this.depart.getX();
    		this.deplacementY += this.arrive.getY() - this.depart.getY();
    		this.pos = new Position(this.depart.getX() + this.deplacementX / TAILLE_CARREAU, this.depart.getY() + this.deplacementY / TAILLE_CARREAU);
    		if(!this.depart.estIdentique(this.pos) && this.pos.estIdentique(this.arrive)) {
    			this.toucher = true;
    		}
    				
    	}
    }
}
