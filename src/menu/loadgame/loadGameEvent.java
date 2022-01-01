package menu.loadgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.SwingUtilities;

import fenetrejeu.FenetreJeu;
import menu.IMenu;
import menu.MenuJeu;
import utile.Boutton;

public class loadGameEvent implements ISauvegarde, IMenu {
	public static boolean estDeleteActif = false;
	
	public loadGameEvent() {
	
		frame.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				back.unsetHoverBoutton(COULEUR_BOUTTON_MENU);
				
				for(Boutton boutton : listeBoutton) {
					boutton.unsetHoverBoutton(COULEUR_BOUTTON_MENU);
				}
				frame.repaint();
			}
		});
		
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
						if(e.getSource() == listeBoutton.get(j) && !estDeleteActif) {
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
		
		deleteSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteSave.removeAll();
				deleteSave.revalidate();
				
				estDeleteActif = !estDeleteActif;		
				
				if(estDeleteActif)
					deleteSave.setBouttonImage("deleteOn");
				else 
					deleteSave.setBouttonImage("deleteOff");
			}
			
		});
	
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
					if(SwingUtilities.isLeftMouseButton(e) && estDeleteActif) {
						for(int j = 0; j < listeBoutton.size(); j++)
							if(e.getSource() == listeBoutton.get(j)) {
								System.out.println("\n....." + listeSauvegarde.get(j));
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
									new loadGamePage();
									
									frame.repaint();							
									break;
								}
							}
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
	}
	
	private void removeBouton() {
			for(Boutton save : listeBoutton)
				frame.remove(save);
			
			frame.remove(back);
			frame.remove(deleteSave);
	}
}
