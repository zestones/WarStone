package fenetrejeu.configjeu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.MatteBorder;

import carte.Carte;
import fenetrejeu.IFenetre;

/**
 * Class ElementDeposableEvent.
 */
public class ElementDeposableEvent extends ElementDeposable implements IFenetre {
	
	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Heros deposable event.
	 */
	protected static void herosDeposableEvent() {
		// On creer un listener pour chaque label
		// On recupere le type de l'element cliquer a l'aide de l'index de la liste de label
		for(int i = 0; i < listeLabelElement.size(); i++) {
			listeLabelElement.get(i).addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					for(int j = 0; j < listeLabelElement.size(); j++) {
						if(e.getSource() == listeLabelElement.get(j)) {
							herosSelectione = listeHeros.get(j);
							listeLabelElement.get(j).setBorder(new MatteBorder(2, 2, 2, 2, COULEUR_ELEMENT_SELECTIONE));
							index = j;
						}
						else
							listeLabelElement.get(j).setBorder(new MatteBorder(2, 2, 2, 2, COULEUR_GRILLE));
					}
				}	
			});
		}
	}
	
	/**
	 * On creer un listener pour chaque label
	 * On recupere le type de l'element cliquer a l'aide de l'index de la liste de label
	 * 
	 */
	protected static void obstacleDeposableEvent() {
		for(int i = 0; i < listeLabelElement.size(); i++) {
			listeLabelElement.get(i).addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					for(int j = 0; j < listeLabelElement.size(); j++) {
						if(e.getSource() == listeLabelElement.get(j)) {
							obstacleSelectione = listeObstacle.get(j);
							listeLabelElement.get(j).setBorder(new MatteBorder(2, 2, 2, 2, COULEUR_ELEMENT_SELECTIONE));
							index = j;
						}
						else
							listeLabelElement.get(j).setBorder(new MatteBorder(2, 2, 2, 2, COULEUR_GRILLE));
					}
				}	
			});
		}
	}
	
	/**
	 * Deselection event.
	 * 
	 * Deselection de l'element en memoire
	 * 
	 */
	protected static void deselectionEvent() {
		premiereInitEvent = false;
		
		/** Un clique sur le header ou le panel infosElement deselectione l'objet */
		headerPanel.addMouseListener(new MouseAdapter() {	
			public void mousePressed(MouseEvent e) {
				if(Carte.modeConfig) {
					obstacleSelectione = null;
					herosSelectione = null;
					System.out.println("clic");
					if(!listeLabelElement.isEmpty())
						listeLabelElement.get(index).setBorder(new MatteBorder(2, 2, 2, 2, COULEUR_GRILLE));
				}
			}
		});
				
		infosElementPanel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(Carte.modeConfig) {
					obstacleSelectione = null;
					herosSelectione = null;
					if(!listeLabelElement.isEmpty())
						listeLabelElement.get(index).setBorder(new MatteBorder(2, 2, 2, 2, COULEUR_GRILLE));
				}
			}
		});
	}
	
	
}
