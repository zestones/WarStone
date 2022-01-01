package menu.loadgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.SwingUtilities;

import fenetrejeu.FenetreJeu;
import menu.IMenu;
import utile.Boutton;

public class SauvegardeEvent implements ISauvegarde, IMenu {

	public SauvegardeEvent() {
		for(Boutton boutton : listeBoutton) {
			boutton.addActionListener(new ActionListener() {
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
						if(e.getSource() == listeBoutton.get(j) && !LoadGameEvent.estDeleteActif) {
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
		
		
		for(Boutton boutton : listeBoutton) {
			boutton.addMouseMotionListener(new MouseAdapter() {
				public void mouseMoved(MouseEvent e) {
					for(int j = 0; j < listeBoutton.size(); j++)
						if(e.getSource() == listeBoutton.get(j)) {
							listeBoutton.get(j).hoverBoutton(COULEUR_BOUTTON_HOVER_MENU);
							break;
						}
				}	
			});
		}
		
		for(Boutton boutton : listeBoutton) {
			boutton.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if(SwingUtilities.isLeftMouseButton(e) && LoadGameEvent.estDeleteActif) {
						for(int j = 0; j < listeBoutton.size(); j++)
							if(e.getSource() == listeBoutton.get(j)) {
								File file = new File(listeSauvegarde.get(j)); 
								if(file.delete()){
									frame.remove(listeBoutton.get(j));
									listeBoutton.remove(j);
									// On suprime tout le contenu
									panelLoadGame.removeAll();
									// On valde les changements
									panelLoadGame.revalidate();			
									// On supprime le panneau que l'on va remplacer
									frame.remove(panelLoadGame);
									// Supression des bouttons 
									removeBouton();						
									// On recharge la page							
									new LoadGamePage();
									
									frame.repaint();							
									break;
								}
							}
					}
				}
			});
		}
		
		frame.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				back.unsetHoverBoutton(COULEUR_BOUTTON_MENU);
				
				for(Boutton boutton : listeBoutton) {
					boutton.unsetHoverBoutton(COULEUR_BOUTTON_MENU);
				}
				
				frame.repaint();
			}
		});
		
		
	}
	
	private void removeBouton() {
		for(Boutton save : listeBoutton)
			frame.remove(save);
		
		frame.remove(back);
		frame.remove(deleteSave);
}
}
