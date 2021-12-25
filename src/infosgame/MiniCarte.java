/********************************************************************
 * 							WarStone								*
 *  -------------------------------------------------------------	*
 * |	 Université Jean-Monnet    L3-Infos 		    2021	 |	*
 *  -------------------------------------------------------------	*
 * 	  BEGGARI ISLEM - CHATAIGNIER ANTOINE - BENGUEZZOU Idriss		*
 * 																	*
 * 														infosgame	*
 * ******************************************************************/
package infosgame;

import java.awt.Graphics;

import javax.swing.JPanel;

import carte.Camera;
import carte.Carte;
import wargame.IConfig;

/**
 * The Class MiniCarte.
 *
 * @author pc
 */
public class MiniCarte extends JPanel implements IConfig {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The c. */
	private Carte c;
	
	/** The cam. */
	private Camera cam;
	
	/**
	 * Instantiates a new mini carte.
	 *
	 * @param cam the cam
	 */
	public MiniCarte(Camera cam) {
		this.c = cam.c;
		this.cam = cam;
	}
	
	/**
	 * Dessine focus.
	 *
	 * @param g the g
	 * @param dx the dx
	 * @param dy the dy
	 */
	// Dessin de la zone visible sur la carte
	private void dessineFocus(Graphics g, int dx, int dy) {
		for(int i = 0; i < LARGEUR_CASE_VISIBLE; i++) {
			for(int j = 0; j < HAUTEUR_CASE_VISIBLE; j++) {
				g.setColor(COULEUR_FOCUS);
	    		g.fillRect(i * MINI_NB_PIX_CASE + dx * MINI_NB_PIX_CASE, j * MINI_NB_PIX_CASE + dy * MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, MINI_NB_PIX_CASE); 
			}
		}
	}
	
	/**
	 * Dessine mini carte.
	 *
	 * @param g the g
	 * @param dx the dx
	 * @param dy the dy
	 */
	// dessin de la carte entiere (les zones depassant l'ecran comprise)
	private void dessineMiniCarte(Graphics g, int dx, int dy) {
		// on dessine les heros et element a leur portee
		for(int n = 0; n < this.c.listeHeros.size(); n++)
			this.c.listeHeros.get(n).seDessinerMinia(g);
	
		// dessin de la grille
		for(int i = 0; i < LARGEUR_CARTE_CASE; i++) {
			for(int j = 0; j < HAUTEUR_CARTE_CASE; j++) {
				g.setColor(COULEUR_GRILLE);
				g.drawRect(i * MINI_NB_PIX_CASE, j * MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, MINI_NB_PIX_CASE); 
			}
		}
	}
	
	/**
	 * Paint component.
	 *
	 * @param g the g
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(grass, 0, 0, LARGEUR_MINI_CARTE, HAUTEUR_MINI_CARTE, null);
			
		this.dessineMiniCarte(g, this.cam.getDx(), this.cam.getDy());
		this.dessineFocus(g, this.cam.getDx(), this.cam.getDy());

	}
}
