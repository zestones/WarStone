package wargame;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.awt.Color;

import javax.swing.JPanel;
import java.awt.MouseInfo;

public class PanneauJeu extends JPanel implements IConfig{
	private static final long serialVersionUID = 1L;
	
	Carte c;
	Position clic, survol, lastClic;
	Element elem;
	Graphics g;
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
						System.out.println("Survol Heros : " + elem.toString());
					else
						System.out.println("Survol Monstre : " + elem.toString());
				}
				if(clic.estValide() == true) {
					System.out.println("Clique : " + clic.toString());
					gameManager();
				}
				
				else 
					System.out.println(" Hors piste ! ");
			}
		});
		/*addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				survol = new Position(e.getX() / NB_PIX_CASE , e.getY() / NB_PIX_CASE);
				survol.verifPosition();
				elem = c.getElement(survol);
				if(elem instanceof Soldat) {
					if(elem instanceof Heros)
						System.out.println("Survol Heros : " + elem.toString());
					else
						System.out.println("Survol Monstre : " + elem.toString());
				}	
				else if(elem instanceof Obstacle)
					System.out.println("Survol Obstacle : " + elem.toString());
			}
		});*/
	}
	private void gameManager() {
		int OFFSET = 1;
		Element e = c.getElement(clic);
		if(e instanceof Heros) {
			isSelected = true;
			lastClic = new Position(clic.getX(), clic.getY());
		}
		if( (clic.getX() == lastClic.getX() && clic.getY() == lastClic.getY()) == false)
			if( (c.actionHeros(lastClic, clic)) == true ) {
		        repaint(clic.getX() * NB_PIX_CASE, clic.getY() * NB_PIX_CASE, (NB_PIX_CASE + OFFSET), (NB_PIX_CASE + OFFSET));
		        repaint(lastClic.getX() * NB_PIX_CASE, lastClic.getY() * NB_PIX_CASE, (NB_PIX_CASE + OFFSET), (NB_PIX_CASE + OFFSET));
		        }
		
		
	}
	
	public void paintComponent(Graphics g) {
		this.c.toutDessiner(g);	
		repaint(FEN_LARGEUR,FEN_HAUTEUR, (NB_PIX_CASE + 1), (NB_PIX_CASE + 1));
	}
}
