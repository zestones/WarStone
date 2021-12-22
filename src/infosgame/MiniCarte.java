package infosgame;

import java.awt.Graphics;

import javax.swing.JPanel;

import carte.Carte;
import element.Heros;
import utile.Position;
import wargame.IConfig;

public class MiniCarte extends JPanel implements IConfig {
	private static final long serialVersionUID = 1L;
	private Carte c;
	
	public MiniCarte(Carte c) {
		this.c = c;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(grass, 0, 0, LARGEUR_MINI_CARTE, HAUTEUR_MINI_CARTE, null);
		
		for (int i = 0; i < LARGEUR_CARTE_CASE; i++) {
			for (int j = 0; j < HAUTEUR_CARTE_CASE; j++) {
				if(this.c.getElement(new Position(i, j)) instanceof Heros) { 
					((Heros)this.c.getElement(new Position(i, j))).seDessinerMinia(g);
				}
				g.setColor(COULEUR_GRILLE);
				g.drawRect(i * MINI_NB_PIX_CASE, j * MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, MINI_NB_PIX_CASE); 
			}
		}
	}
}
