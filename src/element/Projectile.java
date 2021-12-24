package element;

import java.awt.Graphics;

import carte.Carte;
import utile.Position;
import wargame.IConfig;

public class Projectile implements IConfig {
	private static final long serialVersionUID = 1L;
	
	private Carte carte;
	private Position origine;
	private Position arrive;
	public boolean toucher;
	
	Projectile(Position depart, Position arrive, Carte c){
		this.origine = depart;
		this.arrive = arrive;
		this.carte = c;
		this.toucher = false;
	}

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
