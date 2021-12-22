package wargame;

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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import carte.Carte;
import element.Element;
import element.Heros;
import infosgame.InformationElement;
import sprite.ISprite;
import utile.Position;
import utile.Sauvegarde;

public class PanneauJeu extends InformationElement implements IConfig, ISprite {
	private static final long serialVersionUID = 1L;

	private JButton sauvegarde, reprendre, restart;
	public int tour, nombreHeros, nombreMonstre;
	public Position clic, lastClic, clicDragged;
	public Heros herosSelectione;
	private	Position survol;
	public JButton finTour;
	private	Element elem;
	private JLabel  top; 
	public Carte c;
		
	PanneauJeu(){ 
		this.c = new Carte();
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
		
		footer.setBackground(COULEUR_FOOTER);
		footer.setPreferredSize(new Dimension(FOOTER_LARGEUR, FOOTER_HAUTEUR));
		footer.setOpaque(true);
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
				c = new Carte();
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
    			// On remet le heros selectionne a null pour eviter de dessine la zone de portee du heros de l'ancienne carte
    			herosSelectione = null;
    			c  = Sauvegarde.recupSauvegarde(c);
    			c.nombreSoldatVivant(pj);
    		}
    	});
		
		// Recuperation des clics a la souris
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					clic = new Position(e.getX() / NB_PIX_CASE , e.getY() / NB_PIX_CASE);
					lastClic = new Position(e.getX() / NB_PIX_CASE , e.getY() / NB_PIX_CASE);
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
					// On recupere les clic lorsque la souris est egalement relache
					lastClic = new Position((int)e.getX() / NB_PIX_CASE, (int)e.getY() / NB_PIX_CASE);
					// Si On a un heros de selectionner et que clic actuellement sur autre chose alors on appelle jouerSoldat
					if(lastClic != null && herosSelectione != null)
						c.jouerSoldats(pj);
					
					/* 
					 * Option de jeu suplementaire avec MouseDragged 
					 *	Si le clic est relacher dans la case du heros alors on "memorise" le heros selectionner
					 *	Sinon si le clic est relacher sur un enemis ou sur case de deplacement alors laction est effectuer
					 */
					if(herosSelectione != null && lastClic != null)
						if(lastClic.getX() != herosSelectione.getPosition().getX() && lastClic.getY() != herosSelectione.getPosition().getY())
							herosSelectione = null;
				}
			}
		});
				
		
		/* Affiche les infos des elements survole avec la souris */
		this.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				clicDragged = new Position ((int)e.getX() / NB_PIX_CASE, (int)e.getY() / NB_PIX_CASE);
			}	
			
			public void mouseMoved(MouseEvent e) {
				survol = new Position(e.getX() / NB_PIX_CASE , e.getY() / NB_PIX_CASE);
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
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
				
		// dessine le background avec l'image charger dans IConfig		
		g.drawImage(grass, 0, 0, NB_PIX_CASE * LARGEUR_CARTE_CASE, NB_PIX_CASE * HAUTEUR_CARTE_CASE, null);
		
		this.c.toutDessiner(g);
		
		// Affichage du label dans le menuBar
		this.top.setFont(new Font("Arial", Font.BOLD, 13));
		this.top.setForeground(Color.WHITE);
		this.top.setText("Il reste " + nombreHeros + " Heros et " + nombreMonstre + " Monstres !");
		
		soldatRestant.setText("" + nombreHeros + " Heros VS " + nombreMonstre + " Monstre");
		
		// Affichage du label en bas de la fenetre
		footer.setFont(new Font("Arial", Font.BOLD, 13));
		footer.setForeground(Color.WHITE);
	  
		if(this.elem != null)
	    	footer.setText(" " + this.elem.toString());
				
	    // Affiche les deplacement possible du heros selectionne
	    if(this.herosSelectione != null && this.herosSelectione.aJoue != true) {
	    	this.herosSelectione.dessineSelection(g, this.herosSelectione, clicDragged);
	    	this.herosSelectione.dessineSprite(g,clicDragged);
	    }
	    
	    if(this.herosSelectione == null)
	    	clicDragged = null;   
	 }
}
