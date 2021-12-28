package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import fenetrejeu.FenetreJeu;

public class MenuEvent extends JPanel implements IMenu{
	private static final long serialVersionUID = 1L;
	
	MenuEvent(){
		this.evenementButton();
	}
		
	private void evenementButton() {
		newGame.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			newGame.hoverBoutton();
    		}	
		});
		
		loadGame.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			loadGame.hoverBoutton();
    		}	
		});
		
		config.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				config.hoverBoutton();
    		}	
		});
		
		quit.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			quit.hoverBoutton();
    		}	
		});
		
		frame.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			newGame.unsetHoverBoutton();
    			loadGame.unsetHoverBoutton();
    			config.unsetHoverBoutton();
    			quit.unsetHoverBoutton();
    		}	
		});
		
		
		newGame.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){			
				// On suprime tout le contenu
				panelMenu.removeAll();
				// On valde les changements
				panelMenu.revalidate();
				// On supprime le panneau que l'on va remplacer
				frame.remove(panelMenu);
				// Supression des bouttons 
				removeBoutton();
				// On resize la fenetre pour la mettre en pleine ecran
//				frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				// et on cree le paneau du jeu
				new FenetreJeu();
				frame.repaint();
        	}			
        });  	
		
	}
	
	private void removeBoutton() {
		frame.remove(newGame);
		frame.remove(loadGame);
		frame.remove(config);
		frame.remove(quit);
	}
}
