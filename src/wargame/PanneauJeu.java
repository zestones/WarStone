package wargame;

import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Robot;
import java.awt.Font;

public class PanneauJeu extends JPanel implements IConfig {
	private static final long serialVersionUID = 1L;
	private Color color = null;
	private Carte c;
	private	Position survol;
	public Position clic, lastClic;
	
	private	Element elem;
	public Heros h;
	public boolean isSelected = false;
	public int tour;
	JButton finTour;
	
	PanneauJeu(JButton finTour){
		this.c = new Carte();
		this.tour = 0;
		this.finTour = finTour;
		this.EventCatcher();
	}
	
	/* Récupere les clic de souris */
	public void EventCatcher() {
		
		finTour.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				if (tour == 0 )
					tour = 1;
				else 
					tour = 0;
				gameManager();
			}  
		});  
		
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				clic = new Position(e.getX() / NB_PIX_CASE , e.getY() / NB_PIX_CASE);
				elem = c.getElement(clic);
				if(elem instanceof Soldat) {
					if(elem instanceof Heros)
						System.out.println("Clic Heros : " + elem.toString());
					else
						System.out.println("Clic Monstre : " + elem.toString());
				}
				else if(elem instanceof Obstacle)
					System.out.println("Clic Obstacle : " + elem.toString());
				if(clic.estValide() == true) {
					System.out.println("Clique : " + clic.toString());
					gameManager();
				}
				
				else 
					System.out.println(" Hors piste ! ");
			}
		});
		
		/* Affiche les infos des elements survole avec la souris */
	/*	addMouseMotionListener(new MouseAdapter() {
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
					if(elem instanceof Soldat) {
						if(elem instanceof Heros)
							System.out.println("Survol Heros : " + elem.toString());
						else
							System.out.println("Survol Monstre : " + elem.toString());
					}	
					else if(elem instanceof Obstacle)
						System.out.println("Survol Obstacle : " + elem.toString());
				}
			}
		});*/
	}
	
	private void gameManager() {
		c.jouerSoldats(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(COULEUR_INCONNU);
		c.toutDessiner(g);	
		
		// Message a ecrire dans un Label
		/*Font font = new Font("Courier", Font.BOLD, 20);
	    g.setFont(font);
	    g.setColor(Color.red);          
	    g.drawString("Infos de l'element : " + , 10, 20);   
		*/
	}
}
