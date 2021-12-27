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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import fenetrejeu.IFenetre;
import fenetrejeu.menubar.MenuBarHeader;
import infosgame.Fleche;
import infosgame.InformationElement;
import infosgame.MiniCarte;
import sprite.ISprite;
import sprite.SpriteController;
import utile.Position;
import utile.Sauvegarde;


/**
 * The Class PanneauJeu.
 */
public class PanneauJeu extends JPanel implements IFenetre, ISprite {
	private static final long serialVersionUID = 1L;

	public Position clic, lastClic, clicDragged;
	private Position draggedCam, releasedClic;
	private SpriteController spriteController;
	public int tour,nombreHeros, nombreMonstre;
	private boolean dessineFleche;
	public boolean estFiniAction;
	private Fleche flecheDirectionnelle;
	public Heros herosSelectione;
	private	Position survol;
	private	Element elem;
	public int lastTour;
	public Camera cam;
	public Carte c;

	
	/**
	 * Instantiates a new panneau jeu.
	 */
	public PanneauJeu(){ 
		this.c = new Carte();
		this.cam = new Camera(c, 0, 0);
		
		this.flecheDirectionnelle = new Fleche(cam);
		
		this.tour = 0;
		this.herosSelectione = null;		
		
		this.elem = null;
		this.dessineFleche = false;
		this.estFiniAction = true;

		this.spriteController = new SpriteController(this);
			
		new MenuBarHeader();
		
		this.creationElementPanneau();
		
		this.gestionEvenement();
		
		c.nombreSoldatVivant(this);
	} 
		
	// FOOTER A Garder ?
	private void creationElementPanneau() {
		footer.setBackground(COULEUR_FOOTER);
		footer.setPreferredSize(new Dimension(FOOTER_LARGEUR, FOOTER_HAUTEUR));
		footer.setOpaque(true);
		footer.setFont(new Font("Arial", Font.BOLD, 13));
		footer.setForeground(Color.WHITE);
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
		
		// Boutton restart recharge une carte cree aleatoirement
		restart.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){   
				// On genere une nouvelle carte / camera
				c = new Carte();
				
				majMiniCarte();
				
				tour = 0;
				herosSelectione = null;
				elem = null;
			}  
		});  
		 
		// Boutton Fin de Tour 
		finTour.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				
				System.out.println(estFiniAction);
									
				// On oublie le dernier heros selectionne
				herosSelectione = null;
				
				if(estFiniAction == true) {
					tour = tour == 0 ? 1 : 0; 
					spriteController.lanceSpriteAction(getGraphics());
				}
				
//				System.out.println("----------liste  : ------------\n\n" +  c.listeActionSoldat);
//				System.out.println("\n\n----------FIN-----------\n\n");
				
			}  
		});  
		
		// Boutton de sauvegarde de la partie
		sauvegarde.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){    			
    			new Sauvegarde(c);
    			c.nombreSoldatVivant(pj);   		
    		}
    	});
		
		// Bouton de recuperation de sauvegarde
		reprendre.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			
    			c  = Sauvegarde.recupSauvegarde(c);
    			c.nombreSoldatVivant(pj);
    			
    			// Mise a jour de la miniCarte
				majMiniCarte();
				
				frame.repaint();
    		}    		
    	});
		
		// Boutton restart recharge une carte cree aleatoirement
		cameraBas.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				cam.deplacement(0, 1);;
			}  
		});  
		cameraHaut.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				cam.deplacement(0, -1);
			}  
		});  
		cameraGauche.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				cam.deplacement(-1, 0);
			}  
		});  
		cameraDroite.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				cam.deplacement(1, 0);
			}  
		}); 
		
		// Recuperation des clics a la souris
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					clic = new Position(e.getX() / NB_PIX_CASE + cam.getDx(), e.getY() / NB_PIX_CASE + cam.getDy());
					lastClic = new Position(e.getX() / NB_PIX_CASE + cam.getDx(), e.getY() / NB_PIX_CASE + cam.getDy());
					
					if(!c.estCaseVide(clic))
						dessineFleche = false;
											
					if (!clic.estValide())
						return;	
					
					elem = c.getElement(clic);
					
					InformationElement.dessineInfosElement(elem);
					
					// Si on a Selectionnee un heros et que l'on a effectuer un clic autre part alors on appelle jouerSoldat
					if(elem instanceof Heros && estFiniAction) {
						herosSelectione = (Heros)elem;
						if(lastClic != null)
							c.jouerSoldats(pj);
					}
				}
			}
			
			public void mouseReleased(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					dessineFleche = false;
					releasedClic = new Position((int)e.getX() / NB_PIX_CASE + cam.getDx(), (int)e.getY() / NB_PIX_CASE + cam.getDy());
					// On recupere les clic lorsque la souris est egalement relache
					lastClic = new Position((int)e.getX() / NB_PIX_CASE + cam.getDx(), (int)e.getY() / NB_PIX_CASE + cam.getDy());
					// Si On a un heros de selectionner et que clic actuellement sur autre chose alors on appelle jouerSoldat
					if(lastClic != null && herosSelectione != null && estFiniAction)
						c.jouerSoldats(pj);
					
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
					 *
					 */
					if(c.getElement(clic) == null && !clic.estIdentique(releasedClic) && c.estCaseVide(lastClic)) {
						int distance = (int) clic.distance(releasedClic);
						switch(clic.getPositionCardinal(releasedClic)) {
						case NORD: 
							cam.deplacement(0, -distance);
							break;
						case NORD_OUEST:
							cam.deplacement(-distance, -distance);
							break;
						case OUEST:
							cam.deplacement(-distance, 0);
							break;
						case SUD_OUEST:
							cam.deplacement(-distance, distance);
							break;
						case SUD:
							cam.deplacement(0, distance);
							break;
						case SUD_EST:
							cam.deplacement(distance, distance);
							break;
						case EST:
							cam.deplacement(distance, 0);
							break;
						case NORD_EST:
							cam.deplacement(distance, -distance);
							break;
						default: break;
						}
					}
				}
			}
		});		
		
		/* Affiche les infos des elements survole avec la souris */
		this.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				clicDragged = new Position ((int)e.getX() / NB_PIX_CASE + cam.getDx(), (int)e.getY() / NB_PIX_CASE + cam.getDy());
				draggedCam = new Position ((int)e.getX() / NB_PIX_CASE + cam.getDx(), (int)e.getY() / NB_PIX_CASE + cam.getDy());		
				if(c.estCaseVide(clic))
					dessineFleche = true;
				else 
					dessineFleche = false;
			}	
			
			public void mouseMoved(MouseEvent e) {
				survol = new Position((int)e.getX() / NB_PIX_CASE + cam.getDx(), (int)e.getY() / NB_PIX_CASE + cam.getDy());

				if(!survol.estValide())
					return;
				elem = c.getElement(survol);
		
				/* Si le clic est relacher dans la case du heros on continue a memoriser les position */ 
				if(herosSelectione != null)
					clicDragged = new Position(survol.getX(), survol.getY());
				
				// Ajouter un moyen de ne pas afficher les elements cache
			}
		});
	}

	/**
	 * Maj mini carte.
	 */
	private void majMiniCarte() {
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
				
		// dessine le background avec l'image charger dans IConfig		
		// CHANGER LARGEUR_CASE_VISIBLE <--> LARGEUR_CARTE_CASE_VISIBLE 
		// 	|-> dans le cas ou la carte visible est couper (i.e la derniere case visible est calculer invisible mais visible a moitier sur l ecran)
		//  | --> si un monstrer est a la portee d'un hero dans cette partie alors seule les case a sa portee seront dessiner
		g.drawImage(grass, 0, 0, NB_PIX_CASE * LARGEUR_CASE_VISIBLE, NB_PIX_CASE * HAUTEUR_CASE_VISIBLE, null);
		
		this.c.toutDessiner(g, cam);
		
		// Affichage du label dans le menuBar
		top.setText("Il reste " + nombreHeros + " Heros et " + nombreMonstre + " Monstres !");
		
		soldatRestant.setText("" + nombreHeros + " Heros VS " + nombreMonstre + " Monstre");
		
		// Affichage du label en bas de la fenetre
		if(this.elem != null)
	    	footer.setText(" " + this.elem.toString());
				
	    // Affiche les deplacement possible du heros selectionne
	    if(this.herosSelectione != null && !this.herosSelectione.aJoue) {
	    	this.herosSelectione.dessineSelection(g, this.herosSelectione, clicDragged, cam);
	    	this.herosSelectione.changeSprite(g, clicDragged, cam);
	    }
	    
	    if(this.herosSelectione == null)
	    	clicDragged = null;   
	                
	    // On verifie si on doit dessiner la fleche ou non
	    if(flecheDirectionnelle.estFlecheDessinable(herosSelectione, dessineFleche, draggedCam)) 
	    	flecheDirectionnelle.dessineFleche(g, clic.getX() * NB_PIX_CASE - cam.getDx() * NB_PIX_CASE + NB_PIX_CASE/2, 
	    			clic.getY() * NB_PIX_CASE - cam.getDy() * NB_PIX_CASE + NB_PIX_CASE/2, 
	    			draggedCam.getX() * NB_PIX_CASE - cam.getDx() * NB_PIX_CASE + NB_PIX_CASE/2, 
	    			draggedCam.getY() * NB_PIX_CASE - cam.getDy() * NB_PIX_CASE + NB_PIX_CASE/2, 
	    			NB_PIX_CASE/4, NB_PIX_CASE/6, clic);
	    	
	}
}
