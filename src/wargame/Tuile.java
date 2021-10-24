package wargame;

import java.awt.*;

public class Tuile {
	int x = 0;
	int y = 0;
	int taille = 0;
	int type = 0;
	Color couleur = Color.WHITE;
	
	public Tuile(int type, int x, int y, int taille) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.taille = taille;
		
		this.majCouleur();
	}
	
	public void majCouleur() {
		switch (this.type) {
		case 0:
			this.couleur = Color.RED;
			break;
		default:
			this.couleur = Color.BLUE;
		}
	}
}
