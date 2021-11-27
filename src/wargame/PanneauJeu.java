package wargame;

import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.Point;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


import java.awt.Robot;
import java.awt.Font;

public class PanneauJeu extends JPanel implements IConfig {
	private static final long serialVersionUID = 1L;
	// Variable provisoire qui permet de stocker la couleur des pixels survolé 
	private Color color = null;
	private Carte c;
	private	Position survol;
	public Position clic, lastClic;
	public Heros herosSelectione;
	private	Element elem;
	public int tour ,nombreHeros, nombreMonstre;
	public JButton finTour, sauvegarde, resume, restart;
	private JLabel  top;
	private boolean firstPaint = true;
	
	String nomFichier = "wargame.ser";

	PanneauJeu(){
		this.c = new Carte();
		this.tour = 0;
		this.herosSelectione = null;
		this.elem = null;

		finTour = new JButton("Fin du Tour");   
		finTour.setSize(BOUTTON_LARGEUR, BOUTTON_HAUTEUR);
		finTour.setVisible(true);
        menuBar.add(finTour);
        
        top = new JLabel("Tout la Haut", SwingConstants.CENTER); 
		top.setBackground(COULEUR_MENUBAR);
		top.setOpaque(true); 
		menuBar.add(top);
		
		sauvegarde = new JButton("Save");   
		sauvegarde.setSize(BOUTTON_LARGEUR/2, BOUTTON_HAUTEUR);
		sauvegarde.setVisible(true);
		menuBar.add(sauvegarde);
		
		resume = new JButton("Resume");   
		sauvegarde.setSize(BOUTTON_LARGEUR/2, BOUTTON_HAUTEUR);
		sauvegarde.setVisible(true);
		menuBar.add(resume);
		
		restart = new JButton("ReStart");   
		restart.setSize(BOUTTON_LARGEUR/2, BOUTTON_HAUTEUR);
		restart.setVisible(true);
		menuBar.add(restart);
		
		footer.setBackground(COULEUR_FOOTER);
		footer.setPreferredSize(new Dimension(FOOTER_LARGEUR, FOOTER_HAUTEUR));
		footer.setOpaque(true);

		this.EventCatcher();
		this.nombreSoldatVivant();
	}
	
	public void nombreSoldatVivant() {
		int nbMonstre = 0;
		int nbHeros = 0;
		for(int i = 0; i < LARGEUR_CARTE; i++)
			for(int j = 0; j < HAUTEUR_CARTE; j++) {
				if(c.plateau[i][j] instanceof Heros)
					nbHeros++;
				else if(c.plateau[i][j] instanceof Monstre)
					nbMonstre++;
			}
		nombreMonstre = nbMonstre;
		nombreHeros = nbHeros;
		repaint();
	}
	
	/* Gestion des evenements : souris / boutton */
	public void EventCatcher() {	
		restart.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				c = new Carte();
				tour = 0;
				herosSelectione = null;
				elem = null;
				nombreSoldatVivant();
				repaint();
			}  
		});  
		
		finTour.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				if (tour == 0 )
					tour = 1;
				else 
					tour = 0;
				gameManager();
			}  
		});  
		
		sauvegarde.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			try
    			{   
    				FileOutputStream fichier = new FileOutputStream(nomFichier);
    				ObjectOutputStream sortie = new ObjectOutputStream(fichier);
    				
    				sortie.writeObject(c);
    		
    				sortie.close();
    				fichier.close();    		     
    			}
    			catch(IOException ex)
    			{
    				System.out.println("IOException : " + ex);
    				ex.printStackTrace();
    			}
    		}
    	});
		
		resume.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			// On remet le heros selectionne a null pour eviter de dessine la zone de portee du heros de l'ancienne carte
    			herosSelectione = null;
    			try
    			{   
    				FileInputStream fichier = new FileInputStream(nomFichier);
    				ObjectInputStream in = new ObjectInputStream(fichier);
    				
    				c = (Carte)in.readObject();
    				
    				in.close();
    				fichier.close();
    				nombreSoldatVivant();
    				repaint();    	            
    			}
    			catch(IOException ex)
    			{
    				System.out.println("IOException : " + ex);
    			}    	        
    			catch(ClassNotFoundException ex)
    			{
    				System.out.println("ClassNotFoundException : " + ex);
    			}
    		}
    	});
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				clic = new Position(e.getX() / NB_PIX_CASE , e.getY() / NB_PIX_CASE);
				elem = c.getElement(clic);
				if(clic.estValide() == true) 
					gameManager();
			}
		});
		
		/* Affiche les infos des elements survole avec la souris */
		addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				survol = new Position(e.getX() / NB_PIX_CASE , e.getY() / NB_PIX_CASE);
				survol.verifPosition();
				elem = c.getElement(survol);
			
				// Permet d'eviter afficher les element cacher lorsqu'on les survol (solution provisoire)
				Point p = e.getLocationOnScreen();
				try {
	                Robot r = new Robot();
	                color = r.getPixelColor(p.x, p.y);
	            }
	            catch (Exception evt) {
	            	System.err.println(evt.getMessage());   
	            }
				color = new Color(color.getRed(),color.getGreen(),color.getBlue());
				
				if(color.getRGB() != COULEUR_INCONNU.getRGB()) {
					repaint();
				}
			}
		});
	}
	
	// A modifier
	private void gameManager() {
		c.jouerSoldats(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		// dessine le background avec l'image charger dans IConfig
		/*for(int i = 0; i < LARGEUR_CARTE; i++)
			for(int j = 0; j < HAUTEUR_CARTE; j++) {
				g.drawImage(grass, i * NB_PIX_CASE, j * NB_PIX_CASE,NB_PIX_CASE,NB_PIX_CASE, null);
			}
		*/
		
		this.setBackground(COULEUR_INCONNU);
		
		c.toutDessiner(g);	
		
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
