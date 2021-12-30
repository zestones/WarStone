package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import carte.Carte;
import fenetrejeu.FenetreJeu;
import menu.loadgame.loadGamePage;

public class MenuEvent extends JPanel implements IMenu{
	private static final long serialVersionUID = 1L;
	public static boolean estMusicActif = true;
		
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
		
		musicBoutton.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			musicBoutton.hoverBoutton();
    		}	
		});
		
		frame.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			newGame.unsetHoverBoutton();
    			loadGame.unsetHoverBoutton();
    			config.unsetHoverBoutton();
    			quit.unsetHoverBoutton();
    			musicBoutton.unsetHoverBoutton();
    		}	
		});
		
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(-1);
			}
		});
		
		musicBoutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(estMusicActif) {
					musicBoutton.removeAll();
					musicBoutton.revalidate();
					
					musicBoutton.setBouttonImage("mute");
					menuMusic.clip.stop();
				}
				else {
					musicBoutton.removeAll();
					musicBoutton.revalidate();
					
					musicBoutton.setBouttonImage("unmute");
					menuMusic.clip.start();
				}
				estMusicActif = !estMusicActif;			
			}
		});
		
		loadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// On suprime tout le contenu
				panelMenu.removeAll();
				// On valde les changements
				panelMenu.revalidate();
				// On supprime le panneau que l'on va remplacer
				frame.remove(panelMenu);
				
				removeBoutton();
				new loadGamePage();
				
				frame.repaint();
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
				frame.remove(musicBoutton);
				// Arret de la music du menu
				menuMusic.clip.stop();
				// lancement de la music du jeu
				gameMusic.clip.start();
				// Supression des bouttons 
				removeBoutton();
				// et on cree le paneau du jeu
				new FenetreJeu(new Carte());
				frame.repaint();
        	}			
        });  	
		
	}
	
	private void removeBoutton() {
		frame.remove(newGame);
		frame.remove(backMenu);
		frame.remove(loadGame);
		frame.remove(config);
		frame.remove(quit);
	}
}
