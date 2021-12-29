package menu.menupage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
							System.out.println(" i -----> " + j);
							new FenetreJeu(Sauvegarde.recupSauvegarde(j));						
							frame.repaint();
							break;
						}
				}
			});
		}
	}
	
	private void removeBouton() {
			for(Boutton save : listeBoutton)
				frame.remove(save);
	}
}
