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
public class MenuEvent extends JPanel implements IMenu {
	
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
    			newGame.hoverBouton(COULEUR_BOUTON_HOVER_MENU);
    		}	
		});
		
		loadGame.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			loadGame.hoverBouton(COULEUR_BOUTON_HOVER_MENU);
    		}	
		});
		
		config.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				config.hoverBouton(COULEUR_BOUTON_HOVER_MENU);
    		}	
		});
		
		quit.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			quit.hoverBouton(COULEUR_BOUTON_HOVER_MENU);
    		}	
		});
		
		/** Si la frame est survoler on change la couleur des boutton */
		frame.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			newGame.unsetHoverBouton(COULEUR_BOUTON_MENU);
    			loadGame.unsetHoverBouton(COULEUR_BOUTON_MENU);
    			config.unsetHoverBouton(COULEUR_BOUTON_MENU);
    			quit.unsetHoverBouton(COULEUR_BOUTON_MENU);
    			musiqueBouton.unsetHoverBouton(COULEUR_BOUTON_MENU);
    		}	
		});
		
		/** Si quit est clique on quitter le jeux */
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(-1);
			}
		});
		
		/** On change l'etat du boutton Music */
		musiqueBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** Si la music est active on la stop et on change l'image */
				if(estMusicActif) {
					musiqueBouton.removeAll();
					musiqueBouton.revalidate();
					
					musiqueBouton.setBoutonImage("mute");
					menuMusic.clip.stop();
				}
				/** Sinon on relance la music et on change l'image */
				else {
					musiqueBouton.removeAll();
					musiqueBouton.revalidate();
					
					musiqueBouton.setBoutonImage("unmute");
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
				frame.remove(musiqueBouton);
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
				frame.remove(musiqueBouton);
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
		frame.remove(retourMenu);
		frame.remove(loadGame);
		frame.remove(config);
		frame.remove(quit);
	}
}
