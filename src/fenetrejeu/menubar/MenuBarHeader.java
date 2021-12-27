package fenetrejeu.menubar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import fenetrejeu.IFenetre;

public class MenuBarHeader  implements IFenetre{
	private static final long serialVersionUID = 1L;

	public MenuBarHeader() {
		this.initMenuBarHeader();
	}
	
	private void initMenuBarHeader() {
		
		finTour.setSize(BOUTTON_LARGEUR, BOUTTON_HAUTEUR);
		finTour.setVisible(true);
        menuBar.add(finTour);
 		
        top.setBackground(COULEUR_MENUBAR);
		top.setOpaque(true); 
		top.setFont(new Font("Arial", Font.BOLD, 13));
		top.setForeground(Color.WHITE);
		menuBar.add(top); 
		
		sauvegarde.setSize(BOUTTON_LARGEUR/2, BOUTTON_HAUTEUR);
		sauvegarde.setVisible(true);
		menuBar.add(sauvegarde);
		
		sauvegarde.setSize(BOUTTON_LARGEUR/2, BOUTTON_HAUTEUR);
		sauvegarde.setVisible(true);
		menuBar.add(reprendre);
		
		restart.setSize(BOUTTON_LARGEUR/2, BOUTTON_HAUTEUR);
		restart.setVisible(true);
		menuBar.add(restart);
		
		JPanel fleche = new JPanel(new BorderLayout());
		
		// Boutton descend Camera 
		cameraBas.setVisible(true);
		fleche.add(cameraBas, BorderLayout.SOUTH);
		
		// Boutton monte Camera 
		cameraHaut.setVisible(true);
		fleche.add(cameraHaut, BorderLayout.NORTH);
		
		// Boutton gauche Camera 
		cameraGauche.setVisible(true);
		fleche.add(cameraGauche, BorderLayout.WEST);
		
		// Boutton droit Camera 
		cameraDroite.setVisible(true);
		fleche.add(cameraDroite, BorderLayout.EAST);
		
		menuBar.add(fleche);
		
	}
	
}
