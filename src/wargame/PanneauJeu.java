package wargame;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.Color;

import javax.swing.JPanel;
import java.awt.Robot;

public class PanneauJeu extends JPanel implements IConfig {
	private static final long serialVersionUID = 1L;
	Color color = null;
	Carte c;
	Position clic, survol, lastClic;
	Element elem;
	Heros h;
	boolean isSelected = false;
	
	PanneauJeu(){
		this.c = new Carte();
		this.EventCatcher();
	}
	
	/* Récupere les clic de souris */
	public void EventCatcher() {
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
				
				if(color.getRGB() != COULEUR_INCONNU.getRGB()) 
				{
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
		});
	}
	
	private void gameManager() {
		Element e = c.getElement(clic);
		if(e instanceof Heros) {
			isSelected = true;
			lastClic = new Position(clic.getX(), clic.getY());
			h = (Heros) e;
		}
		if (lastClic == null)
			return;
		if( (clic.getX() == lastClic.getX() && clic.getY() == lastClic.getY()) == false) {
			if( (c.actionHeros(lastClic, clic)) == true ) 
	    		repaint();
		}
	}
	
	public void paintComponent(Graphics g) {
		c.toutDessiner(g);	
	}
}
