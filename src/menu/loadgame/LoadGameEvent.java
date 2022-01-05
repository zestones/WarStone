package menu.loadgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import menu.IMenu;
import menu.MenuJeu;
import utile.Bouton;

/**
 * Class LoadGameEvent.
 *
 * Cree les lsiteners pour le boutton back et delete 
 *
 */
public class LoadGameEvent implements ISauvegarde, IMenu {
	
	/** boolean indiquant l'etat du boutton de supression. */
	public static boolean estDeleteActif = false;
	
	/**
	 * Instacie les listeners de la page loadGame.
	 */
	public LoadGameEvent() {
					
		/** Boutton delete */
		supprimeSauvegarde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** Supression de son contenu */
				supprimeSauvegarde.removeAll();
				supprimeSauvegarde.revalidate();
				
				/** Mise a jour de la variable */
				estDeleteActif = !estDeleteActif;		
				
				/** Dessin de la poubelle en fonction de son etat (Actif / Inactif) */
				if(estDeleteActif)
					supprimeSauvegarde.setBoutonImage("deleteOn");
				else 
					supprimeSauvegarde.setBoutonImage("deleteOff");
			}
			
		});
			
		/** Boutton retour Mouvement Souris*/
		retour.addMouseMotionListener(new MouseAdapter() { 
			public void mouseMoved(MouseEvent e) {
				/** Si le boutton est survole on change sa couleur */
				retour.hoverBouton(COULEUR_BOUTON_HOVER_MENU);
			}
		});
		
		/** Boutton retour Clique*/
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/** On change l'etat du boutton delete si il est actif */
				if(estDeleteActif)
					supprimeSauvegarde.doClick();
				
				/** On suprime tout le contenu */
				panelLoadGame.removeAll();
				/** On valde les changements */
				panelLoadGame.revalidate();			
				/** On supprime le panneau que l'on va remplacer */
				frame.remove(panelLoadGame);
				/** Supression des bouttons */
				removeBouton();	
				/** Rechargement de la page de menu */
				MenuJeu.initMenuJeu();
				/** On redessine */
				frame.repaint();
			}
		});
	}
	
	/**
	 * Supression des bouttons.
	 */
	private void removeBouton() {
		/** Suppression de tout les bouttons de sauvegarde*/
		for(Bouton save : listeBoutton)
			frame.remove(save);
		
		frame.remove(retour);
		frame.remove(supprimeSauvegarde);
	}
}
