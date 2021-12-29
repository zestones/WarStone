package menu.loadgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import fenetrejeu.FenetreJeu;
import menu.IMenu;
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
					// et on cree le paneau du jeu		
					for(int j = 0; j < listeBoutton.size(); j++)
						if(e.getSource() == listeBoutton.get(j)) {
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
							listeBoutton.get(j).hoverBoutton();
						}
				}	
			});
		}
		
		
		frame.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				for(int j = 0; j < listeBoutton.size(); j++) {
					listeBoutton.get(j).unsetHoverBoutton();
				}
			}
		});
	}
		
	
	private void removeBouton() {
			for(Boutton save : listeBoutton)
				frame.remove(save);
	}
}
