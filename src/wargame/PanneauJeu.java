package wargame;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class PanneauJeu extends JPanel implements IConfig {
	private static final long serialVersionUID = 1L;

	private JButton sauvegarde, reprendre, restart;
	public int tour, nombreHeros, nombreMonstre;
	public Position clic, lastClic;
	public Heros herosSelectione;
	private	Position survol;
	public JButton finTour;
	private	Element elem;
	private JLabel  top; 
	private Carte c;
		
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
		IConfig.spriteEngine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				clic = new Position(e.getX() / NB_PIX_CASE , e.getY() / NB_PIX_CASE);
				elem = c.getElement(clic);
				if(clic.estValide() == true) 
					c.jouerSoldats(pj);
			}
		});
		
		/* Affiche les infos des elements survole avec la souris */
		this.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				survol = new Position(e.getX() / NB_PIX_CASE , e.getY() / NB_PIX_CASE);
				survol.verifPosition();
				elem = c.getElement(survol);
				// Ajouter un moyen de ne pas afficher les elements cache
			}
		});
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		// dessine le background avec l'image charger dans IConfig
		for(int i = 0; i < LARGEUR_CARTE; i++)
			for(int j = 0; j < HAUTEUR_CARTE; j++) {
				g.drawImage(grass, i * NB_PIX_CASE, j * NB_PIX_CASE,NB_PIX_CASE,NB_PIX_CASE, null);
			}
		
		this.c.toutDessiner(g);
		
		// Affichage du laben dans le menuBar
		this.top.setFont(new Font("Arial", Font.BOLD, 13));
		this.top.setForeground(Color.WHITE);
		this.top.setText("Il reste " + nombreHeros + " Heros et " + nombreMonstre + " Monstres !");
		 
		// Affichage du label en bas de la fenetre
		footer.setFont(new Font("Arial", Font.BOLD, 13));
		footer.setForeground(Color.WHITE);
	    if(this.elem != null)
	    	footer.setText(" " + this.elem.toString());
	   
	    // Affiche les deplacement possible du heros selectionne
	   if(this.herosSelectione != null && this.herosSelectione.aJoue != true) 
		   this.herosSelectione.dessineSelection(g);
	   
	}
}
