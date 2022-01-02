package menu.loadgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import menu.IMenu;
import menu.MenuJeu;
import utile.Boutton;

public class LoadGameEvent implements ISauvegarde, IMenu {
	public static boolean estDeleteActif = false;
	
	public LoadGameEvent() {
					
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
			
		back.addMouseMotionListener(new MouseAdapter() { 
			public void mouseMoved(MouseEvent e) {
				back.hoverBoutton(COULEUR_BOUTTON_HOVER_MENU);
			}
		});
	
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(estDeleteActif)
					deleteSave.doClick();
				
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
