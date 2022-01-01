/********************************************************************
 * 							WarStone								*
 *  -------------------------------------------------------------	*
 * |	 Université Jean-Monnet    L3-Infos 		    2021	 |	*
 *  -------------------------------------------------------------	*
 * 	  BEGGARI ISLEM - CHATAIGNIER ANTOINE - BENGUEZZOU Idriss		*
 * 																	*
 * 														wargame		*
 * ******************************************************************/
package wargame;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import carte.Camera;
import carte.Carte;
import element.Element;
import element.Heros;
import element.Obstacle;
import fenetrejeu.IFenetre;
import infosgame.InfosElement;
import infosgame.MiniCarte;
import sprite.ISprite;
import utile.Fleche;
import utile.Position;
import wargame.evenement.ButtonEvent;


/**
 * The Class PanneauJeu.
 */
public class PanneauJeu extends JPanel implements IFenetre, ISprite {
	private static final long serialVersionUID = 1L;

	public Position clic, lastClic, clicDragged;
	private Position draggedCam, releasedClic;
	public int nombreHeros, nombreMonstre;
	public Fleche flecheDirectionnelle;
	public ButtonEvent buttonEvent;
	private boolean dessineFleche;
	public Heros herosSelectione;
	public boolean estFiniAction;
	private	Position survol;
	public	Element elem;
	public Camera cam;
	public Carte c;
	public Position deposeObstacle;

	/**
	 * Instantiates a new panneau jeu.
	 */
	public PanneauJeu(Carte c) {	
		
		this.c = c;
		this.cam = new Camera(c, 0, 0);
		
		this.flecheDirectionnelle = new Fleche(this.cam);
	
		this.herosSelectione = null;		
		
		this.elem = null;
		this.dessineFleche = false;
		this.estFiniAction = true;
				
		this.deposeObstacle = null;
		
		this.buttonEvent = new ButtonEvent(this);		
		this.gestionEvenement();
		
		c.nombreSoldatVivant(this);
	} 
	
	public void setPanneauJeu(Carte c) {
		this.c = c;
			
		this.majMiniCarte();
		
		this.flecheDirectionnelle = new Fleche(this.cam);
		this.herosSelectione = null;		
			
		infosElementBody.removeAll();
		infosElementBody.revalidate();
				
		this.elem = null;
		this.dessineFleche = false;
		this.estFiniAction = true;
		
		c.nombreSoldatVivant(this);
	}		
	
	public void setPanneauJeuConf(Carte c) {
		this.c = c;
			
		this.majMiniCarte();
			
		this.flecheDirectionnelle = new Fleche(this.cam);
		this.herosSelectione = null;		
		
		this.elem = null;
		
		this.dessineFleche = false;
		this.estFiniAction = false;
	}		
			 
	/**
	 * Gestion evenement : souris / boutton.
	 */
	public void gestionEvenement() {
		PanneauJeu pj = this;
		
		// Actualisation des sprites
		ISprite.spriteEngine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carteMiniature.repaint();
				repaint();
			}
		});
		
		// Recuperation des clics a la souris
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					clic = new Position(e.getX() / NB_PIX_CASE + cam.getDx(), e.getY() / NB_PIX_CASE + cam.getDy());
					lastClic = new Position(e.getX() / NB_PIX_CASE + cam.getDx(), e.getY() / NB_PIX_CASE + cam.getDy());
					deposeObstacle = new Position(e.getX() / NB_PIX_CASE + cam.getDx(), e.getY() / NB_PIX_CASE + cam.getDy());
					
					if(!c.estCaseVide(clic)) dessineFleche = false;
											
					if(!clic.estValide()) return;	
					
					elem = c.getElement(clic);
				
					InfosElement.dessineInfosElement(elem);
					
					// Si on a Selectionnee un heros et que l'on a effectuer un clic autre part alors on appelle jouerSoldat
					if(elem instanceof Heros && estFiniAction) {
						herosSelectione = (Heros)elem;
						if(lastClic != null)
							c.jouerSoldats(pj, pj.buttonEvent.tour);
					}
				}
				if(SwingUtilities.isRightMouseButton(e) && Carte.modeConf) {
					Position delete = new Position(e.getX() / NB_PIX_CASE + cam.getDx(), e.getY() / NB_PIX_CASE + cam.getDy());
					if(c.getElement(delete) != null)
						c.setElementVide(delete);
				}
			}
			
			public void mouseReleased(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					deposeObstacle = null;
					dessineFleche = false;
					releasedClic = new Position((int)e.getX() / NB_PIX_CASE + cam.getDx(), (int)e.getY() / NB_PIX_CASE + cam.getDy());
					
					// On recupere les clic lorsque la souris est egalement relache
					lastClic = new Position((int)e.getX() / NB_PIX_CASE + cam.getDx(), (int)e.getY() / NB_PIX_CASE + cam.getDy());
					// Si On a un heros de selectionner et que clic actuellement sur autre chose alors on appelle jouerSoldat
					if(lastClic != null && herosSelectione != null && estFiniAction)
						c.jouerSoldats(pj, pj.buttonEvent.tour);
					
					/** 
					 * 	Option de jeu suplementaire avec MouseDragged 
					 *	Si le clic est relacher dans la case du heros alors on "memorise" le heros selectionner
					 *	Sinon si le clic est relacher sur un enemis ou sur case de deplacement alors laction est effectuer
					 */
					if(herosSelectione != null && lastClic != null)
						if(!lastClic.estIdentique(herosSelectione.getPosition()))
							herosSelectione = null;
					/**
					 * gestion des mouvements de la camera en fonction des position de la souris lorsqu'elle est dragged puis released
					 * Cette option fonction uniquement dans le mode jeu et non en mode config
					 *
					 */
					if(deposeObstacle == null && c.getElement(clic) == null && !clic.estIdentique(releasedClic) && c.estCaseVide(lastClic)) {
						int distance = (int) clic.distance(releasedClic);
						switch(clic.getPositionCardinal(releasedClic)) {
						case NORD: cam.deplacement(0, -distance);
						break;
						case NORD_OUEST: cam.deplacement(-distance, -distance);
						break;
						case OUEST: cam.deplacement(-distance, 0);
						break;
						case SUD_OUEST: cam.deplacement(-distance, distance);
						break;
						case SUD: cam.deplacement(0, distance);
						break;
						case SUD_EST: cam.deplacement(distance, distance);
						break;
						case EST: cam.deplacement(distance, 0);
						break;
						case NORD_EST: cam.deplacement(distance, -distance);
						break;
						default: break;
						}
					}
				}
			}
		});		
		
		/* Affiche les infos des elements survole avec la souris */
		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				clicDragged = new Position ((int)e.getX() / NB_PIX_CASE + cam.getDx(), (int)e.getY() / NB_PIX_CASE + cam.getDy());
				draggedCam = new Position ((int)e.getX() / NB_PIX_CASE + cam.getDx(), (int)e.getY() / NB_PIX_CASE + cam.getDy());		
				
				if(c.estCaseVide(clic)) dessineFleche = true;
				else dessineFleche = false;
			}	
			
			public void mouseMoved(MouseEvent e) {
				survol = new Position((int)e.getX() / NB_PIX_CASE + cam.getDx(), (int)e.getY() / NB_PIX_CASE + cam.getDy());

				if(!survol.estValide()) return;
					
				elem = c.getElement(survol);
				
				/* Si le clic est relacher dans la case du heros on continue a memoriser les position */ 
				if(herosSelectione != null) clicDragged = new Position(survol.getX(), survol.getY());
				// Ajouter un moyen de ne pas afficher les elements cache
			}
		});
	}

	/**
	 * Maj mini carte.
	 */
	public void majMiniCarte() {
		cam = new Camera(c, 0, 0);
		// Une supprime l'ancien conteneur
		carteMiniature.removeAll();
		// On valide les changement
		carteMiniature.revalidate();	
		// Ajout de la nouvelle MiniCarte
		carteMiniature.add(new MiniCarte(cam));
	}
	
	/**
	 * Paint component.
	 *
	 * @param g the g
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(Carte.modeConf) {
			for(int i = cam.getDx(); i < LARGEUR_CASE_VISIBLE + cam.getDx(); i++) {
				for(int j = cam.getDy(); j < HAUTEUR_CASE_VISIBLE + cam.getDy(); j++) {		
					g.drawImage(range, i * NB_PIX_CASE - cam.getDx() * NB_PIX_CASE, j  * NB_PIX_CASE - cam.getDy() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE, null);
					if(this.c.getElement(new Position(i, j)) != null) {
						this.c.getElement(new Position(i, j)).seDessiner(g, this.cam);	
					}
									
					g.setColor(COULEUR_GRILLE);
					g.drawRect(i * NB_PIX_CASE - cam.getDx() * NB_PIX_CASE, j  * NB_PIX_CASE - cam.getDy() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE); 
				}
			}
			if(deposeObstacle != null && InfosElement.obstacleSelectione != null) {
				c.setElement(new Obstacle(c, InfosElement.obstacleSelectione, deposeObstacle));
			}
			
		}
		else {
			g.drawImage(grass, 0, 0, NB_PIX_CASE * LARGEUR_CASE_VISIBLE, NB_PIX_CASE * HAUTEUR_CASE_VISIBLE, null);
			this.c.toutDessiner(g, cam);
		
			// Affichage du nombre de soldat restant
			soldatRestant.setText("" + nombreHeros + " Heros VS " + nombreMonstre + " Monstre");
			
			// Affichage du label en bas de la fenetre
			if(this.elem != null)
				footer.setText(" " + this.elem.toString());
					
		    // Affiche les deplacement possible du heros selectionne
			if(this.herosSelectione != null && !this.herosSelectione.aJoue) {
				this.herosSelectione.dessineSelection(g, this.herosSelectione, clicDragged, cam);
				this.herosSelectione.changeSprite(clicDragged, cam);
			}
		}
		
		 // On verifie si on doit dessiner la fleche ou non
	    if(flecheDirectionnelle.estFlecheDessinable(herosSelectione, dessineFleche, draggedCam)) 
	    	flecheDirectionnelle.dessineFleche(g, clic.getX() * NB_PIX_CASE - cam.getDx() * NB_PIX_CASE + NB_PIX_CASE/2, 
	    			clic.getY() * NB_PIX_CASE - cam.getDy() * NB_PIX_CASE + NB_PIX_CASE/2, 
	    			draggedCam.getX() * NB_PIX_CASE - cam.getDx() * NB_PIX_CASE + NB_PIX_CASE/2, 
	    			draggedCam.getY() * NB_PIX_CASE - cam.getDy() * NB_PIX_CASE + NB_PIX_CASE/2, 
	    			NB_PIX_CASE/4, NB_PIX_CASE/6, clic);
	    
	}
}
