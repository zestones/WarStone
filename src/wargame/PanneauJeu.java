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

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicArrowButton;

import carte.Camera;
import carte.Carte;
import element.Element;
import element.Heros;
import infosgame.InformationElement;
import infosgame.MiniCarte;
import sprite.ISprite;
import sprite.SpriteController;
import utile.Position;
import utile.Sauvegarde;


/**
 * The Class PanneauJeu.
 */
public class PanneauJeu extends InformationElement implements IConfig, ISprite {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The camera droite. */
	private JButton sauvegarde, reprendre, restart,cameraBas,cameraHaut,cameraGauche, cameraDroite;
	
	/** The nombre monstre. */
	public int tour,nombreHeros, nombreMonstre;
	
	public int lastTour;
	
	/** The clic dragged. */
	public Position clic, lastClic, clicDragged;
	
	/** The released clic. */
	private Position draggedCam, releasedClic;
	
	/** The dessine fleche. */
	private boolean dessineFleche;
	
	public boolean estFiniAction;
	
	/** The heros selectione. */
	public Heros herosSelectione;
	
	private SpriteController spriteController;
	
	/** The survol. */
	private	Position survol;
	
	/** boutton fin tour. */
	public JButton finTour;
	
	/** element */
	private	Element elem;
	
	/** label top. */
	private JLabel  top; 
	
	/** la camera. */
	public Camera cam;
	
	/** Carte */
	public Carte c;

	
	/**
	 * Instantiates a new panneau jeu.
	 */
	PanneauJeu(){ 
		this.c = new Carte();
		this.cam = new Camera(c, 0, 0);
		this.tour = 0;
		
		this.herosSelectione = null;		
		
		this.elem = null;
		this.dessineFleche = false;
		this.estFiniAction = true;

		this.spriteController = new SpriteController(this);
		
		this.creationElementPanneau();	
		this.gestionEvenement();
		
		c.nombreSoldatVivant(this);
	} 
		
	/**
	 * Creation element panneau.
	 */
	private void creationElementPanneau() {
				
		finTour = new JButton("End Turn");   
		finTour.setSize(BOUTTON_LARGEUR, BOUTTON_HAUTEUR);
		finTour.setVisible(true);
        menuBar.add(finTour);
 		
        top = new JLabel("", SwingConstants.CENTER); 
		top.setBackground(COULEUR_MENUBAR);
		top.setOpaque(true); 
		this.top.setFont(new Font("Arial", Font.BOLD, 13));
		this.top.setForeground(Color.WHITE);
		menuBar.add(top); 
		
		sauvegarde = new JButton("Save");   
		sauvegarde.setSize(BOUTTON_LARGEUR/2, BOUTTON_HAUTEUR);
		sauvegarde.setVisible(true);
		menuBar.add(sauvegarde);
		
		reprendre = new JButton("Resume");   
		sauvegarde.setSize(BOUTTON_LARGEUR/2, BOUTTON_HAUTEUR);
		sauvegarde.setVisible(true);
		menuBar.add(reprendre);
		
		restart = new JButton("ReStart");   
		restart.setSize(BOUTTON_LARGEUR/2, BOUTTON_HAUTEUR);
		restart.setVisible(true);
		menuBar.add(restart);
		
		JPanel fleche = new JPanel(new BorderLayout());
		// Boutton descend Camera 
		cameraBas = new BasicArrowButton(BasicArrowButton.SOUTH);
//		cameraBas.setSize(BOUTTON_LARGEUR/2, BOUTTON_HAUTEUR);
		cameraBas.setVisible(true);
		fleche.add(cameraBas, BorderLayout.SOUTH);
		
		// Boutton descend Camera 
		cameraHaut = new BasicArrowButton(BasicArrowButton.NORTH);
//		cameraHaut.setSize(BOUTTON_LARGEUR/2, BOUTTON_HAUTEUR);
		cameraHaut.setVisible(true);
		fleche.add(cameraHaut, BorderLayout.NORTH);
		
		// Boutton descend Camera 
		cameraGauche = new BasicArrowButton(BasicArrowButton.WEST);
//		cameraGauche.setSize(BOUTTON_LARGEUR/2, BOUTTON_HAUTEUR);
		cameraGauche.setVisible(true);
		fleche.add(cameraGauche, BorderLayout.WEST);
		
		// Boutton descend Camera 
		cameraDroite = new BasicArrowButton(BasicArrowButton.EAST); 
//		cameraDroite.setSize(BOUTTON_LARGEUR/2, BOUTTON_HAUTEUR);
		cameraDroite.setVisible(true);
		fleche.add(cameraDroite, BorderLayout.EAST);
		
		menuBar.add(fleche);

				
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
			
					// Si on a Selectionnee un heros et que l'on a effectuer un clic autre part alors on appelle jouerSoldat
					if(elem instanceof Heros) {
						herosSelectione = (Heros)elem;
						if(lastClic != null && estFiniAction)
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
	 * Dessine fleche.
	 *
	 * @param g the g
	 * @param x1 the x 1
	 * @param y1 the y 1
	 * @param x2 the x 2
	 * @param y2 the y 2
	 * @param d the d
	 * @param h the h
	 * @param clic the clic
	 */
	// https://stackoverflow.com/questions/2027613/how-to-draw-a-directed-arrow-line-in-java
	private void dessineFleche(Graphics g, int x1, int y1, int x2, int y2, int d, int h, Position clic) {
	    int dx = x2 - x1, dy = y2 - y1;
	    double D = Math.sqrt(dx*dx + dy*dy);
	    double xm = D - d, xn = xm, ym = h, yn = -h, x;
	    double sin = dy / D, cos = dx / D;
	    	    
	    x = xm*cos - ym*sin + x1;
	    ym = xm*sin + ym*cos + y1;
	    xm = x;

	    x = xn*cos - yn*sin + x1;
	    yn = xn*sin + yn*cos + y1;
	    xn = x;

	    int[] xpoints = {x2, (int) xm, (int) xn};
	    int[] ypoints = {y2, (int) ym, (int) yn};


	    // On recupre les positions des point X2, Y2
	    // Pour enlever le bout de ligne debordant de la fleche
	    int Cx = x2 / NB_PIX_CASE + cam.getDx(); 
	    int Cy = y2 / NB_PIX_CASE + cam.getDy();
	    	
	    // On recupere la distance entre les deux point   
	    Position pos2 = new Position((x1/NB_PIX_CASE + cam.getDx()), (y1/NB_PIX_CASE + cam.getDy()));
	    Position pos1 = new Position(Cx, Cy);
	    
	    int distance = (int) pos1.distance(pos2);
	    
	    // SetStroke utilisable uniquement avec Graphicd2D
	    Graphics2D g2 = (Graphics2D) g;
	    g2.setStroke(new BasicStroke(2));
	    
	    g.setColor(COULEUR_EAU);
	    // On recalcule le deuxieme point de la ligne de la fleche pour qu'il soit centre
	    if(clic.getX() < Cx && clic.getY() == Cy) x2 -= d;
	    else if(clic.getX() > Cx && clic.getY() == Cy) x2 += d;
	    if(clic.getX() == Cx && clic.getY() > Cy) y2 += d;
	    else if(clic.getX() == Cx && clic.getY() < Cy) y2 -= d;
	    if(clic.getX() > Cx && clic.getY() < Cy) {
	    	x2 += d/(distance+1); y2 -= d/(distance+1);
	    }
	    else if(clic.getX() > Cx && clic.getY() > Cy) {
	    	x2 += d/(distance+1); y2 += d/(distance+1);
	    }
	    else if(clic.getX() < Cx && clic.getY() < Cy) {
	    	x2 -= d/(distance+1); y2 -= d/(distance+1);
	    }
	    else if(clic.getX() < Cx && clic.getY() > Cy) {
	    	x2 -= d/(distance+1); y2 += d/(distance+1);
	    }
	    g.drawLine(x1, y1, x2, y2);
	   
	    g.fillPolygon(xpoints, ypoints, 3);
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
	 * Dessine deplacement.
	 *
	 * @return true, if successful
	 */
	private boolean dessineDeplacement() {
		// Si aucun heros est selectione est que dessine Fleche est true alors on dessine
		if(herosSelectione == null && dessineFleche)
			return true;
		// Si on a selectione un heros on verifie que la position de draggedCam 
		// ne se situe pas dans la zone de deplacement du heros
		if(herosSelectione != null && dessineFleche)
			if(!herosSelectione.getPosition().estVoisine(draggedCam))
				return true;
		return false;			
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
		g.drawImage(grass, 0, 0, NB_PIX_CASE * LARGEUR_CARTE_CASE, NB_PIX_CASE * HAUTEUR_CARTE_CASE, null);
		
		this.c.toutDessiner(g, cam);
		
		// Affichage du label dans le menuBar
		this.top.setText("Il reste " + nombreHeros + " Heros et " + nombreMonstre + " Monstres !");
		
		soldatRestant.setText("" + nombreHeros + " Heros VS " + nombreMonstre + " Monstre");
		if(elem != null) dessineInfosElement(elem);
		
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
	    if(dessineDeplacement()) 
	    	dessineFleche(g, clic.getX() * NB_PIX_CASE - cam.getDx() * NB_PIX_CASE + NB_PIX_CASE/2, 
	    			clic.getY() * NB_PIX_CASE - cam.getDy() * NB_PIX_CASE + NB_PIX_CASE/2, 
	    			draggedCam.getX() * NB_PIX_CASE - cam.getDx() * NB_PIX_CASE + NB_PIX_CASE/2, 
	    			draggedCam.getY() * NB_PIX_CASE - cam.getDy() * NB_PIX_CASE + NB_PIX_CASE/2, 
	    			NB_PIX_CASE/4, NB_PIX_CASE/6, clic);
	    	
	}
}
