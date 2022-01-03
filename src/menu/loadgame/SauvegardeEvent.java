package menu.loadgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import fenetrejeu.FenetreJeu;
import menu.IMenu;
import utile.Boutton;

/**
 * 
 * Class SauvegardeEvent.
 * 
 * Cree les listeners pour chaque sauvegarde
 * 
 */
public class SauvegardeEvent implements ISauvegarde, IMenu {

	/**
	 * Instancie les listeners des sauvegardes.
	 */
	public SauvegardeEvent() {
		
		/** Pour chaque sauvegarde on creer un listener : Boutton clique */
		for(Boutton boutton : listeBoutton) {
			boutton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					/** On suprime tout le contenu */
					panelLoadGame.removeAll();
					/** On valde les changements */
					panelLoadGame.revalidate();
					/** On supprime le panneau que l'on va remplacer */
					frame.remove(panelLoadGame);
					/** Supression des bouttons */
					removeBouton();
						
					/** On recherche qu'elle boutton a ete cliquer */
					for(int j = 0; j < listeBoutton.size(); j++) {
						/** Si nous ne somme oas entrain de suprimer On charge la partie */
						if(e.getSource() == listeBoutton.get(j)) 
							if(!LoadGameEvent.estDeleteActif) {
								/** Supression du boutton music */
								frame.remove(musicBoutton);
								/** Arret de la music du menu */
								menuMusic.clip.stop();
								/** lancement de la music du jeu */
								gameMusic.clip.start();
								/** Creation de la fenetre Jeu a partir de la sauvegarde */
								new FenetreJeu(Sauvegarde.recupSauvegarde(j));						
								frame.repaint();							
								break;
							}
							/** Sinon on supprime la sauvegarde */
							else {
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
		
		/** Boutton sauvegarde : Mouvement Souris */
		for(Boutton boutton : listeBoutton) {
			boutton.addMouseMotionListener(new MouseAdapter() {
				public void mouseMoved(MouseEvent e) {
					/** On cherche sur quelle boutton la souris a ete deplace */
					for(int j = 0; j < listeBoutton.size(); j++)
						if(e.getSource() == listeBoutton.get(j)) {
							/** On change la couleur du boutton */
							listeBoutton.get(j).hoverBoutton(COULEUR_BOUTTON_HOVER_MENU);
							break;
						}
				}	
			});
		}
		
		/** Pour tout les bouttons on change la couleur lorsque la souris est deplacer sur la frame */
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
	
	/**
	 * Suppression des bouttons.
	 */
	private void removeBouton() {
		for(Boutton save : listeBoutton)
			frame.remove(save);
		
		frame.remove(back);
		frame.remove(deleteSave);
}
}
