package menu.loadgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import fenetrejeu.FenetreJeu;
import menu.IMenu;
import menu.MenuJeu;
import utile.Boutton;

public class loadGameEvent implements ISauvegarde, IMenu {
	
	public loadGameEvent() {
		
		for(int i = 0; i < listeBoutton.size(); i++) {
			listeBoutton.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// On suprime tout le contenu
					panelLoadGame.removeAll();
					// On valde les changements
					panelLoadGame.revalidate();
					// On supprime le panneau que l'on va remplacer
					frame.remove(panelLoadGame);
					// Supression des bouttons 
					removeBouton();
					
					frame.remove(musicBoutton);
										
					// et on cree le paneau du jeu		
					for(int j = 0; j < listeBoutton.size(); j++)
						if(e.getSource() == listeBoutton.get(j)) {
							// Arret de la music du menu
							menuMusic.clip.stop();
							// lancement de la music du jeu
							gameMusic.clip.start();
							// creation de la carte avec les donnees recupere
							new FenetreJeu(Sauvegarde.recupSauvegarde(j));						
							frame.repaint();
							break;
						}
				}
			});
		}
	
		for(int i = 0; i < listeBoutton.size(); i++) {
			listeBoutton.get(i).addMouseMotionListener(new MouseAdapter() {
				public void mouseMoved(MouseEvent e) {
					for(int j = 0; j < listeBoutton.size(); j++)
						if(e.getSource() == listeBoutton.get(j)) {
							listeBoutton.get(j).hoverBoutton(COULEUR_BOUTTON_HOVER_MENU);
						}
				}	
			});
		}
		
		back.addMouseMotionListener(new MouseAdapter() { 
			public void mouseMoved(MouseEvent e) {
				back.hoverBoutton(COULEUR_BOUTTON_HOVER_MENU);
			}
		});
		
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// On suprime tout le contenu
				panelLoadGame.removeAll();
				// On valde les changements
				panelLoadGame.revalidate();			
				// On supprime le panneau que l'on va remplacer
				frame.remove(panelLoadGame);
				// Supression des bouttons 
				removeBouton();	
				
				MenuJeu.initMenuJeu();
				frame.repaint();
			}
			
		});
		
		frame.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				for(int j = 0; j < listeBoutton.size(); j++) {
					listeBoutton.get(j).unsetHoverBoutton(COULEUR_BOUTTON_MENU);
				}
				back.unsetHoverBoutton(COULEUR_BOUTTON_MENU);
			}
		});
	}
		
	
	private void removeBouton() {
			for(Boutton save : listeBoutton)
				frame.remove(save);
			frame.remove(back);
	}
}
