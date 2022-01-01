package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import carte.Carte;
import fenetrejeu.FenetreJeu;
import menu.loadgame.LoadGamePage;

public class MenuEvent extends JPanel implements IMenu{
	private static final long serialVersionUID = 1L;
	public static boolean estMusicActif = true;
		
	MenuEvent(){
		this.evenementButton();
	}
		
	private void evenementButton() {
		newGame.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			newGame.hoverBoutton(COULEUR_BOUTTON_HOVER_MENU);
    		}	
		});
		
		loadGame.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			loadGame.hoverBoutton(COULEUR_BOUTTON_HOVER_MENU);
    		}	
		});
		
		config.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				config.hoverBoutton(COULEUR_BOUTTON_HOVER_MENU);
    		}	
		});
		
		quit.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			quit.hoverBoutton(COULEUR_BOUTTON_HOVER_MENU);
    		}	
		});
		
		musicBoutton.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			musicBoutton.hoverBoutton(COULEUR_BOUTTON_HOVER_MENU);
    		}	
		});
		
		frame.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			newGame.unsetHoverBoutton(COULEUR_BOUTTON_MENU);
    			loadGame.unsetHoverBoutton(COULEUR_BOUTTON_MENU);
    			config.unsetHoverBoutton(COULEUR_BOUTTON_MENU);
    			quit.unsetHoverBoutton(COULEUR_BOUTTON_MENU);
    			musicBoutton.unsetHoverBoutton(COULEUR_BOUTTON_MENU);
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
				new LoadGamePage();
				
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
		
		config.addActionListener(new ActionListener(){  
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
				configMusic.clip.start();
				// Supression des bouttons 
				removeBoutton();
				// et on cree le paneau du jeu
				new FenetreJeu(new Carte(), true);
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
