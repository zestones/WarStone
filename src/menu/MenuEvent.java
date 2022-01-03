package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import carte.Carte;
import fenetrejeu.FenetreJeu;
import menu.loadgame.LoadGamePage;

/**
 * Class MenuEvent.
 * 
 * Cree des listeners pour chaque boutton present sur la page
 * 
 */
public class MenuEvent extends JPanel implements IMenu{
	
	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** boolean indiquant l'etat de la music. */
	public static boolean estMusicActif = true;
		
	/**
	 * Instancie les listeners de la page menu.
	 */
	MenuEvent(){
		this.evenementButton();
	}
		
	/**
	 * listeners des bouttons.
	 */
	private void evenementButton() {
		/** Pour chaque boutton s'il est survoler on change la couleur de fond */
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
		
		/** Si la frame est survoler on change la couleur des boutton */
		frame.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			newGame.unsetHoverBoutton(COULEUR_BOUTTON_MENU);
    			loadGame.unsetHoverBoutton(COULEUR_BOUTTON_MENU);
    			config.unsetHoverBoutton(COULEUR_BOUTTON_MENU);
    			quit.unsetHoverBoutton(COULEUR_BOUTTON_MENU);
    			musicBoutton.unsetHoverBoutton(COULEUR_BOUTTON_MENU);
    		}	
		});
		
		/** Si quit est clique on quitter le jeux */
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(-1);
			}
		});
		
		/** On change l'etat du boutton Music */
		musicBoutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** Si la music est active on la stop et on change l'image */
				if(estMusicActif) {
					musicBoutton.removeAll();
					musicBoutton.revalidate();
					
					musicBoutton.setBouttonImage("mute");
					menuMusic.clip.stop();
				}
				/** Sinon on relance la music et on change l'image */
				else {
					musicBoutton.removeAll();
					musicBoutton.revalidate();
					
					musicBoutton.setBouttonImage("unmute");
					menuMusic.clip.start();
				}
				/** On met a jour la variable */				
				estMusicActif = !estMusicActif;			
			}
		});
		
		/** Boutton geberabt une page contenant les sauvegarde */
		loadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** On nettoie le panel principal */
				panelMenu.removeAll();
				/** On valide les changement */
				panelMenu.revalidate();
				/** On supprime le panneau que l'on va remplacer */
				frame.remove(panelMenu);
				/** Suppression des bouttons cree*/
				removeBoutton();
				/** On creer la page LoadGame */
				new LoadGamePage();
				/** On redessine */
				frame.repaint();
			}
		});
		
		/** Lancement d'une nouvelle partie */
		newGame.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){		
				/** On suprime tout le contenu */
				panelMenu.removeAll();
				/** On valde les changements */
				panelMenu.revalidate();
				/** On supprime le panneau que l'on va remplacer */
				frame.remove(panelMenu);
				/** Suppression du boutton music */
				frame.remove(musicBoutton);
				/** Arret de la music du menu */
				menuMusic.clip.stop();
				/** lancement de la music du jeu */
				gameMusic.clip.start();
				/** Supression des bouttons */
				removeBoutton();
				/** Creation de la fenetre du jeu */
				new FenetreJeu();
				/** On redessine */
				frame.repaint();
        	}			
        });  	
		
		/** Lancement du jeu en mode config */
		config.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){		
				/** On suprime tout le contenu */
				panelMenu.removeAll();
				/** On valde les changements */
				panelMenu.revalidate();
				/** On supprime le panneau que l'on va remplacer */
				frame.remove(panelMenu);
				/** Supression du boutton Music */
				frame.remove(musicBoutton);
				/** Arret de la music du menu */
				menuMusic.clip.stop();
				/** Lancement de la music config */
				configMusic.clip.start();
				/** Supression des bouttons */ 
				removeBoutton();
				/** Creation de la fenetre du jeu en mode config */
				new FenetreJeu(new Carte(), true);
				/** On redessine */
				frame.repaint();
        	}			
        });  	
		
		
	}
	
	/**
	 * Suppression des Bouttons.
	 */
	private void removeBoutton() {
		frame.remove(newGame);
		frame.remove(backMenu);
		frame.remove(loadGame);
		frame.remove(config);
		frame.remove(quit);
	}
}
