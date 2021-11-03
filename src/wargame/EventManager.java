package wargame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class EventManager extends JPanel {
private static final long serialVersionUID = 1L;
	Position position = new Position(0, 0);
	private int hauteur = 20;
	private int largeur = 20;
	
	public EventManager() {
		setBorder(BorderFactory.createLineBorder(Color.black));
	    
	    addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	        	System.out.println("Vous avez cliqué aux positions: x:" + e.getX() + " y:" + e.getY());
	        	//deplace(e.getX(), e.getY());
	        }
	    });
	
	   /*addMouseMotionListener(new MouseAdapter() {
	        public void mouseDragged(MouseEvent e) {
	        	System.out.println("Vous avez déplacé la souris aux positions: x:" + e.getX() + " y:" + e.getY());
	        	deplace(e.getX(), e.getY());
	        }
	    });
	    */
	}
	
	/*
	private void deplace(int x, int y) {
	    int OFFSET = 1;
	    if ((position.getX()!=x) || (position.getY()!=y)) {
	        repaint(position.getX(), position.getY(), (largeur + OFFSET), (hauteur + OFFSET));
	        position.setX(x);
	        position.setY(y);
	        repaint(position.getX(), position.getY(), (largeur + OFFSET), (hauteur + OFFSET));
	    } 
	}
	*/
	
	/*public Dimension getPreferredSize() {
	    return new Dimension(hauteur, largeur);
	}*/
	
	
	
}
