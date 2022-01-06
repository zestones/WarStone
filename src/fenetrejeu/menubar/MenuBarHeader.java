package fenetrejeu.menubar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import carte.Carte;
import fenetrejeu.IFenetre;

public class MenuBarHeader  implements IFenetre {
	private static final long serialVersionUID = 1L;

	public MenuBarHeader() {
		this.initMenuBarHeader();
	}
	
	private void initMenuBarHeader() {
		
		// Creation du menu qui contiendra les bouttons
		menuBar.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 15));
		menuBar.setOpaque(true);
		menuBar.setBackground(COULEUR_MENUBAR);

		menu.setVisible(true);
		menuBar.add(menu);
		
		sauvegarde.setVisible(true);
		menuBar.add(sauvegarde);
		
		recommencer.setVisible(true);
		menuBar.add(recommencer);
		
		if(!Carte.modeConf) {
			finTour.setVisible(true);
			menuBar.add(finTour);
		}
		else {
			jouer.setVisible(true);
			menuBar.add(jouer);
			
			obstacle.setVisible(true);
			menuBar.add(obstacle);
			
			heros.setVisible(true);
			menuBar.add(heros);
		}		
		
		menuBar.add(musicOn); 
        musicOn.setSelected(true);
        
        headerPanel.add(menuBar, BorderLayout.CENTER);
                
        flecheMiniCartePanel.setOpaque(true);
        flecheMiniCartePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
		flecheMiniCartePanel.setBackground(COULEUR_FLECHE_PANEL);
		flecheMiniCartePanel.setPreferredSize(new Dimension(INFOS_PANEL_LARGEUR, MENUBAR_HAUTEUR));
		flecheMiniCartePanel.setBorder(new MatteBorder(0, 2, 0, 2, COULEUR_BORDURE));
		
		// Panel contenant les fleche pour se diriger sur la carte
		JPanel flecheContainer = new JPanel(new BorderLayout());
		flecheContainer.setOpaque(true);
		flecheContainer.setBackground(COULEUR_FLECHE_PANEL);
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
		
		flecheMiniCartePanel.add(flecheContainer);
		headerPanel.add(flecheMiniCartePanel, BorderLayout.EAST);		
	}
	
}
