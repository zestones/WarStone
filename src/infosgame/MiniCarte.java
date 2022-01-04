package infosgame;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import carte.Camera;
import carte.Carte;
import fenetrejeu.IFenetre;
import utile.Position;
import wargame.IConfig;
import wargame.PanneauJeu;

/**
 * Class MiniCarte.
 */
public class MiniCarte extends JPanel implements IConfig, IFenetre {
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** La Carte. */
	private Carte c;
	
	/** La camera. */
	private Camera cam;
	
	/** La pos du clic sur la miniCarte. */
	private Position clique;
	
	/**
	 * Instancie une nouvelle mini carte.
	 *
	 * @param cam 
	 */
	public MiniCarte(Camera cam) {
		this.c = cam.c;
		this.cam = cam;
		this.miniCarteEvent();
	}
	
	/**
	 * Maj mini carte.
	 *
	 * @param pj
	 */
	public static void majMiniCarte(PanneauJeu pj) {
		pj.cam = new Camera(pj.c);
		// Une supprime l'ancien conteneur
		carteMiniaturePanel.removeAll();
		// On valide les changement
		carteMiniaturePanel.revalidate();	
		// Ajout de la nouvelle MiniCarte
		carteMiniaturePanel.add(new MiniCarte(pj.cam));
	}
	
	/**
	 * Mini carte event.
	 * 
	 * deplacement de la carte en fonctions des cliques sur la mini Carte
	 * 
	 */
	private void miniCarteEvent() {
			
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					clique = new Position(e.getX() / TAILLE_CARREAU_MINI_CARTE, e.getY() / TAILLE_CARREAU_MINI_CARTE);
					if(clique.getX() < cam.getDx()) {
						cam.setDx(clique.getX());
					}
					else if(clique.getX() > cam.getDx() - 1 + NB_COLONNES_VISIBLES) {
						Position pos = new Position(clique.getX() + 1, 0);
						cam.setDx(pos.getDistance(new Position(NB_COLONNES_VISIBLES, 0)));
					}
					if(clique.getY() < cam.getDy()) {
						cam.setDy(clique.getY());
					}
					else if(clique.getY() > (cam.getDy() - 1  + NB_LIGNES_VISIBLES)) {
						Position pos = new Position(0, clique.getY() + 1);
						cam.setDy(pos.getDistance(new Position(0, NB_LIGNES_VISIBLES)));
					}
				}
			}
		});
	}
	
	/**
	 * Dessine le focus.
	 *
	 * Dessin de la zone visible sur la carte
	 *
	 * @param g 
	 * @param dx 
	 * @param dy
	 */
	private void dessineFocus(Graphics g, int dx, int dy) {
		for(int i = 0; i < NB_COLONNES_VISIBLES; i++) {
			for(int j = 0; j < NB_LIGNES_VISIBLES; j++) {
				g.setColor(COULEUR_FOCUS);
	    		g.fillRect(i * TAILLE_CARREAU_MINI_CARTE + dx * TAILLE_CARREAU_MINI_CARTE, j * TAILLE_CARREAU_MINI_CARTE + dy * TAILLE_CARREAU_MINI_CARTE, TAILLE_CARREAU_MINI_CARTE, TAILLE_CARREAU_MINI_CARTE); 
			}
		}
	}
	
	/**
	 * Dessine mini carte.
	 *
	 * @param g
	 */
	private void dessineMiniCarte(Graphics g) {
		/** on dessine les heros et element a leur portee */
		for(int n = 0; n < this.c.listeHeros.size(); n++)
			this.c.listeHeros.get(n).seDessinerMiniCarte(g);
	
		/** dessin de la grille */
		for(int i = 0; i < NB_COLONNES; i++) {
			for(int j = 0; j < NB_LIGNES; j++) {
				g.setColor(COULEUR_GRILLE);
				g.drawRect(i * TAILLE_CARREAU_MINI_CARTE, j * TAILLE_CARREAU_MINI_CARTE, TAILLE_CARREAU_MINI_CARTE, TAILLE_CARREAU_MINI_CARTE); 
			}
		}
	}
	
	/**
	 * Dessine carte.
	 *
	 * @param g the g
	 */
	private void dessineCarte(Graphics g) {
		for(int i = 0; i < NB_COLONNES; i++) {
			for(int j = 0; j < NB_LIGNES; j++) {		
				g.drawImage(terre, i * TAILLE_CARREAU_MINI_CARTE, j  * TAILLE_CARREAU_MINI_CARTE, TAILLE_CARREAU_MINI_CARTE, TAILLE_CARREAU_MINI_CARTE, null);
				if(!this.c.estCaseVide(new Position(i, j)))
					this.c.getElement(new Position(i, j)).seDessinerMiniCarte(g);
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
		
		if(!Carte.modeConf)
			g.drawImage(herbe, 0, 0, MINI_CARTE_LARGEUR, MINI_CARTE_HAUTEUR, null);
		else
			dessineCarte(g);
		
		this.dessineMiniCarte(g);
		this.dessineFocus(g, this.cam.getDx(), this.cam.getDy());

	}
}
