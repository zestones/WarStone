package infosgame;

import java.awt.Graphics;

import javax.swing.JPanel;

import carte.Camera;
import carte.Carte;
import element.Heros;
import utile.Position;
import wargame.IConfig;

public class MiniCarte extends JPanel implements IConfig {
	private static final long serialVersionUID = 1L;
	private Carte c;
	private Camera cam;
	
	public MiniCarte(Camera cam) {
		this.c = cam.c;
		this.cam = cam;
	}
	
	private void dessineFocus(Graphics g, int dx, int dy) {
		for(int i = 0; i < LARGEUR_CASE_VISIBLE; i++) {
			for(int j = 0; j < HAUTEUR_CASE_VISIBLE; j++) {
				g.setColor(COULEUR_FOCUS);
	    		g.fillRect(i * MINI_NB_PIX_CASE + dx * MINI_NB_PIX_CASE, j * MINI_NB_PIX_CASE + dy * MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, MINI_NB_PIX_CASE); 
			}
		}
	}
	
	private void dessineMiniCarte(Graphics g, int dx, int dy) {
		for(int i = 0; i < LARGEUR_CARTE_CASE; i++) {
			for(int j = 0; j < HAUTEUR_CARTE_CASE; j++) {
				if(this.c.getElement(new Position(i, j)) instanceof Heros && i >= dx && i < LARGEUR_CASE_VISIBLE + dx  && j >= dy && j < HAUTEUR_CASE_VISIBLE + dy) { 
					((Heros)this.c.getElement(new Position(i, j))).seDessinerMinia(g);
				}		
				g.setColor(COULEUR_GRILLE);
				g.drawRect(i * MINI_NB_PIX_CASE, j * MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, MINI_NB_PIX_CASE); 
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(grass, 0, 0, LARGEUR_MINI_CARTE, HAUTEUR_MINI_CARTE, null);
			
		this.dessineMiniCarte(g, this.cam.getDx(), this.cam.getDy());
		this.dessineFocus(g, this.cam.getDx(), this.cam.getDy());

	}
}
