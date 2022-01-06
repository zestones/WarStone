package fenetrejeu.finjeu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import fenetrejeu.IFenetre;
import menu.MenuEvent;
import menu.MenuJeu;

/**
 * Class FinJeuEvent.
 */
public class FinJeuEvent implements IFenetre, IFinJeu {
	
	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancie les evenement de la page fin jeu.
	 */
	public FinJeuEvent() {
		FinJeu.eventDejaCree = true; 
		
		frame.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				retourMenu.unsetHoverBouton(COULEUR_BOUTON_MENU);
    		}	
		});
        
        retourMenu.addMouseMotionListener(new MouseAdapter() {
        	public void mouseMoved(MouseEvent e) {
        		retourMenu.hoverBouton(COULEUR_BOUTON_HOVER_MENU);
    		}	
		});
        
    	retourMenu.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				// On suprime tout le contenu
				panelMenu.removeAll();
				panelMenu.revalidate();		
				// On supprime le boutton fin de tour pour qu'il n'aparaisse pas si une config est lancer
				menuBar.remove(finTour);	
				menuBar.remove(jouer);
				// On supprime le panneau que l'on va remplacer
				frame.remove(panelMenu);
				
				panelFinJeu.removeAll();
				panelFinJeu.revalidate();
				
				frame.remove(panelFinJeu);
				
				removeBoutton();
				// On relance la music du menu
				menuMusic.clip.start();
				// Creation du menu
				MenuJeu.initMenuJeu();
				frame.repaint();
			} 	 
		});  	
	}
	
	/**
	 * Suppresion des bouttons.
	 */
	private void removeBoutton() {
		frame.remove(retourMenu);
		frame.remove(newGame);
		musiqueBouton.removeAll();
		musiqueBouton.revalidate();
		musiqueBouton.setBoutonImage("unmute");
		MenuEvent.estMusicActif = true;
	}
}	
