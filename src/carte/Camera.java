package carte;

import wargame.IConfig;

public class Camera implements IConfig {
	private static final long serialVersionUID = 1L;
	
	public Carte c;
	private int dx, dy;

	public Camera(Carte c, int dx, int dy) {
		this.c = c ;
		this.dx = dx;
		this.dy = dy;
		this.centreCamera();
	}
	
	// On verifie si la camera ne sort pas de la carte 
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
	
	// Positionement de la camera au centre de notre carte
	private void centreCamera() {
		dx = LARGEUR_CASE_VISIBLE/2;    
		dy = HAUTEUR_CASE_VISIBLE/2;
		estValideDeplacement();
	}
	
	// Translatation des points
	public void deplacement(int x, int y) {
		dx += x;
		dy += y;
		estValideDeplacement();
	}
	
	public int getDx() { return dx; }
	public int getDy() { return dy; }

	public void setDx(int dx) { this.dx = dx; }
	public void setDy(int dy) { this.dy = dy; }
}