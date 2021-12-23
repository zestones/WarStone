package wargame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
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
import utile.Position;
import utile.Sauvegarde;

public class PanneauJeu extends InformationElement implements IConfig, ISprite {
	private static final long serialVersionUID = 1L;

	private JButton sauvegarde, reprendre, restart,cameraBas,cameraHaut,cameraGauche, cameraDroite;
	public int tour, nombreHeros, nombreMonstre;
	public Position clic, lastClic, clicDragged;
	private	Position survol;
	private Position clicCamera, lastCamera, releasedClic;
	public Heros herosSelectione;
	public JButton finTour;
	private	Element elem;
	private JLabel  top; 
	public Camera cam;
	public Carte c;
	private boolean dessineFleche = false;
	private int dx, dy;

	PanneauJeu(){ 
		this.c = new Carte();
		this.cam = new Camera(c, 0, 0);
		this.tour = 0;
		this.herosSelectione = null;			
		this.elem = null;
		
		this.creationElementPanneau();	
		this.gestionEvenement();
		
		c.nombreSoldatVivant(this);
	} 
		
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
		 
	/* Gestion des evenements : souris / boutton */
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
				c.nombreSoldatVivant(pj);
				repaint();
			}  
		});  
		 
		// Boutton Fin de Tour 
		finTour.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  			
				if (tour == 0 )
					tour = 1;
				else 
					tour = 0;
				
				// On oublie le dernier heros selectionne
				herosSelectione = null;
				
				c.jouerSoldats(pj); 
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
					
					if(c.getElement(clic) != null)
						dessineFleche = false;
											
					if (clic.estValide() == false)
						return;	
					
					elem = c.getElement(clic);

					if(elem != null) dessineInfosElement(elem);
			
					// Si on a Selectionnee un heros et que l'on a effectuer un clic autre part alors on appelle jouerSoldat
					if(elem instanceof Heros) {
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
					if(lastClic != null && herosSelectione != null)
						c.jouerSoldats(pj);
					
					/* 
					 * Option de jeu suplementaire avec MouseDragged 
					 *	Si le clic est relacher dans la case du heros alors on "memorise" le heros selectionner
					 *	Sinon si le clic est relacher sur un enemis ou sur case de deplacement alors laction est effectuer
					 */
					if(herosSelectione != null && lastClic != null)
						if(!lastClic.estIdentique(herosSelectione.getPosition()))
							herosSelectione = null;
				
					if(c.getElement(clic) == null && !clic.estIdentique(releasedClic)) {
						int distance = (int) clic.distance(releasedClic);
						System.out.println("distance " + distance);
						if(clic.getX() == releasedClic.getX() && clic.getY() < releasedClic.getY()) {
							dx = 0; dy = -distance;
							cam.deplacement(0, -distance);
							clic = new Position(releasedClic.getX(), releasedClic.getY());
						}				
						else if(clic.getX() < releasedClic.getX() && clic.getY() < releasedClic.getY()) {
							dx = -distance; dy = -distance;
							cam.deplacement(-distance, -distance);
							clic = new Position(releasedClic.getX(), releasedClic.getY());
						}
						else if(clic.getX() < releasedClic.getX() && clic.getY() == releasedClic.getY() ) {
							dx = -distance; dy = 0;
							cam.deplacement(-distance, 0);
							clic = new Position(releasedClic.getX(), releasedClic.getY());
						}
						else if(clic.getX() < releasedClic.getX() && clic.getY() > releasedClic.getY()) {
							dx = -distance; dy = distance;
							cam.deplacement(-distance, distance);
							clic = new Position(releasedClic.getX(), releasedClic.getY());
						}
						else if(clic.getX() == releasedClic.getX() && clic.getY() > releasedClic.getY()) {
							dx = 0; dy = distance;
							cam.deplacement(0, distance);
							clic = new Position(releasedClic.getX(), releasedClic.getY());
						}
						else if(clic.getX() > releasedClic.getX() && clic.getY() > releasedClic.getY()) {
							dx = distance; dy = distance;
							cam.deplacement(distance, distance);
							clic = new Position(releasedClic.getX(), releasedClic.getY());
						}
						else if(clic.getX() > releasedClic.getX() && clic.getY() == releasedClic.getY()) {
							dx = distance; dy = 0;
							cam.deplacement(distance, 0);
							clic = new Position(releasedClic.getX(), releasedClic.getY());
						}
						else if(clic.getX() > releasedClic.getX() && clic.getY() > releasedClic.getY()) {
							dx = distance; dy = distance;
							cam.deplacement(distance, distance);
							clic = new Position(releasedClic.getX(), releasedClic.getY());
						}
					}
					clicCamera = null;
				
				}
			}
		});		
		
		/* Affiche les infos des elements survole avec la souris */
		this.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				clicDragged = new Position ((int)e.getX() / NB_PIX_CASE + cam.getDx(), (int)e.getY() / NB_PIX_CASE + cam.getDy());
				clicCamera = new Position ((int)e.getX() / NB_PIX_CASE + cam.getDx(), (int)e.getY() / NB_PIX_CASE + cam.getDy());		
				if(c.getElement(clic) == null)
					dessineFleche = true;
				else 
					dessineFleche = false;
			}	
			
			public void mouseMoved(MouseEvent e) {
				survol = new Position((int)e.getX() / NB_PIX_CASE + cam.getDx(), (int)e.getY() / NB_PIX_CASE + cam.getDy());
				if(survol.estValide() == false)
					return;
				elem = c.getElement(survol);
		
				/* Si le clic est relacher dans la case du heros on continue a memoriser les position */ 
				if(herosSelectione != null)
					clicDragged = new Position(survol.getX(), survol.getY());
				
				// Ajouter un moyen de ne pas afficher les elements cache
			}
		});
	}
	
	private void dessineFleche(Graphics g, int x, int y) {
		g.setColor(Color.green); 
		g.drawLine(clic.getX() * NB_PIX_CASE, clic.getY() * NB_PIX_CASE, x * NB_PIX_CASE, y * NB_PIX_CASE); 
		dx = 0; dy = 0;
	}

	private void majMiniCarte() {
		cam = new Camera(c, 0, 0);
		// Une supprime l'ancien conteneur
		carteMiniature.removeAll();
		// On valide les changement
		carteMiniature.revalidate();
		// Ajout de la nouvelle MiniCarte
		carteMiniature.add(new MiniCarte(cam));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
				
		// dessine le background avec l'image charger dans IConfig		
		g.drawImage(grass, 0, 0, NB_PIX_CASE * LARGEUR_CARTE_CASE, NB_PIX_CASE * HAUTEUR_CARTE_CASE, null);
		
		this.c.toutDessiner(g, cam);
		
		// Affichage du label dans le menuBar
		this.top.setText("Il reste " + nombreHeros + " Heros et " + nombreMonstre + " Monstres !");
		
		soldatRestant.setText("" + nombreHeros + " Heros VS " + nombreMonstre + " Monstre");
		
		// Affichage du label en bas de la fenetre
		if(this.elem != null)
	    	footer.setText(" " + this.elem.toString());
				
	    // Affiche les deplacement possible du heros selectionne
	    if(this.herosSelectione != null && this.herosSelectione.aJoue != true) {
	    	this.herosSelectione.dessineSelection(g, this.herosSelectione, clicDragged, cam);
	    	this.herosSelectione.dessineSprite(g, clicDragged, cam);
	    }
	    
	    if(this.herosSelectione == null)
	    	clicDragged = null;   

	    if(clicCamera != null && dessineFleche == true) 
	    	dessineFleche(g, clicCamera.getX(), clicCamera.getY());

	}
}
