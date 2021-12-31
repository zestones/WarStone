package fenetrejeu.menubar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import carte.Carte;
import fenetrejeu.IFenetre;

public class MenuBarHeader  implements IFenetre{
	private static final long serialVersionUID = 1L;

	public MenuBarHeader() {
		this.initMenuBarHeader();
	}
	
	private void initMenuBarHeader() {
		
		// Creation du menu qui contiendra les bouttons
		menuBar.setBackground(COULEUR_MENUBAR); 
		menuBar.setOpaque(true);
		menuBar.setPreferredSize(new Dimension(MENUBAR_LARGEUR, MENUBAR_HAUTEUR));
		menuBar.setLayout(new FlowLayout(FlowLayout.LEADING, 10, MENUBAR_HAUTEUR/4));  
		
		menu.setVisible(true);
		menuBar.add(menu);
		System.out.println("MenuBar --> " + Carte.modeConf + "\n");

		if(!Carte.modeConf) {
			finTour.setVisible(true);
			menuBar.add(finTour);		
		}
		else {
			finTour.removeAll();
			finTour.revalidate();
		}
			
		sauvegarde.setVisible(true);
		menuBar.add(sauvegarde);
		
		restart.setVisible(true);
		menuBar.add(restart);
		
        menuBar.add(musicOn); 
        musicOn.setSelected(true);
        
        fleche.setOpaque(true);
		fleche.setBackground(COULEUR_BOUTTON);
		fleche.setPreferredSize(new Dimension(LARGEUR_INFOS_PANEL, MENUBAR_HAUTEUR));
		fleche.setBorder(new MatteBorder(0, 2, 2, 2, COULEUR_BORDURE));
		
		// Panel contenant les fleche pour se diriger sur la carte
		JPanel flecheContainer = new JPanel(new BorderLayout());
		flecheContainer.setOpaque(true);
		flecheContainer.setBackground(COULEUR_MENUBAR);
		flecheContainer.setBorder(new MatteBorder(1, 1, 1, 1, COULEUR_GRILLE));
		flecheContainer.setPreferredSize(new Dimension(MENUBAR_HAUTEUR - MENUBAR_HAUTEUR/4, MENUBAR_HAUTEUR - MENUBAR_HAUTEUR/4));

		// Boutton descend Camera 
		cameraBas.setVisible(true);
		flecheContainer.add(cameraBas, BorderLayout.SOUTH);
		
		// Boutton monte Camera 
		cameraHaut.setVisible(true);
		flecheContainer.add(cameraHaut, BorderLayout.NORTH);
		
		// Boutton gauche Camera 
		cameraGauche.setVisible(true);
		flecheContainer.add(cameraGauche, BorderLayout.WEST);
		
		// Boutton droit Camera 
		cameraDroite.setVisible(true);
		flecheContainer.add(cameraDroite, BorderLayout.EAST);
		
		fleche.add(flecheContainer);
		header.add(fleche);		
	}
	
}
