package wargame;

import java.awt.Graphics;
import javax.swing.JPanel;

public class Carte extends JPanel {
	Tuile[][] tuiles;
	int nbColonnes, nbLignes, tailleTuile;
	final int MARGIN = 1;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < this.nbColonnes; i++) {
			for (int j = 0; j < this.nbLignes; j++) {
				Tuile t = this.tuiles[j][i];
				g.setColor(t.couleur);
				g.fillRect(t.x + MARGIN, t.y + MARGIN, t.taille - 2 * MARGIN, t.taille - 2 * MARGIN);
			}
		}
	}
	
	private void initCarte() {
		for (int i = 0; i < this.nbLignes; i++) {
			for (int j = 0; j < this.nbColonnes; j++) {
				this.tuiles[i][j] = new Tuile(0, j * this.tailleTuile, i * this.tailleTuile, this.tailleTuile);
			}
		}
	}
	
	public Carte(int nbColonnes, int nbLignes, int tailleTuile) {
		this.tuiles = new Tuile[nbLignes][nbColonnes];
		this.nbColonnes = nbColonnes;
		this.nbLignes = nbLignes;
		this.tailleTuile = tailleTuile;
		
		this.initCarte();
	}
}
