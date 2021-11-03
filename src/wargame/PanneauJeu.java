package wargame;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.awt.Color;

import javax.swing.JPanel;

public class PanneauJeu extends JPanel implements IConfig{
	private static final long serialVersionUID = 1L;
	
	Carte c;
	Position clic = new Position(0, 0);
	Element e = null;
	Graphics g;
	boolean isSelected = false;
	
	PanneauJeu(){
		this.c = new Carte();
		this.EventCatcher();
	}
	public void EventCatcher() {
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				System.out.println("Vous avez cliqué aux positions: x:" + e.getX() / NB_PIX_CASE  + " y:" + e.getY() / NB_PIX_CASE);
				getClic(e.getX() / NB_PIX_CASE, e.getY() / NB_PIX_CASE);
			}
		});
	}
	private void getClic(int posX, int posY) {
		//int OFFSET = 1;
		
		clic.setX(posX);
		clic.setY(posY);
		
		e = this.c.getElement(clic);
		
		if (e instanceof Heros) {
			//repaint((clic.getX() * NB_PIX_CASE) / 2, (clic.getY() * NB_PIX_CASE) / 2 , (NB_PIX_CASE * e.getPortee() + OFFSET) , (NB_PIX_CASE * e.getPortee() + OFFSET) );
			isSelected = true;
		}
	}
	
	public void paintComponent(Graphics g) {
		this.c.toutDessiner(g);	
		repaint(FEN_LARGEUR,FEN_HAUTEUR, (NB_PIX_CASE + 1), (NB_PIX_CASE + 1));
	}
}
