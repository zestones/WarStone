package menu;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import carte.Carte;
import fenetrejeu.menubar.MenuBar;

/**
 * Class Menu.
 * 
 * Cree la fenetre de l'application
 * 
 */
public class MenuJeu implements IMenu {
	
	/**
	 * Instancie un nouveau menu jeu.
	 */
	public MenuJeu(){
		/** Creation de la fenetre avec les valeurs maximal de l'ecran */
		frame.setPreferredSize(DIM_MENU);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
					
		/** Initialisation des elements du menu */
		initMenuJeu();
		menuMusic.clip.start();

		/** Creation du boutton music */
        musiqueBouton.setBoutonImage("unmute");
        musiqueBouton.hoverBouton(COULEUR_BOUTON_MENU);
        
        /** Creation d'une menu Bar */
        new MenuBar();
        /** Generation des evenements de cette page */
		new MenuEvent();
		
		frame.pack();
		frame.setVisible(true);  
	}
	
	/**
	 *  Methode separer du contructeur pour permettre a tout moment de recreer 
	 *  le contenu du menu sans generer de nouveau listener.
	 *  
	 */
	public static void initMenuJeu() {
		/** creation du panel Principal */
		panelMenu.setPreferredSize(DIM_MENU);
		panelMenu.setOpaque(false);
			
		/** Arret des music qui ont pu être lance */
		gameMusic.clip.stop();
		configMusic.clip.stop();
			
		/** Lorsqu'on revient sur le menu le mode config est toujours remis a false */
		Carte.modeConf = false;
		
		/** creation d'un label pour deposer l'image */
		BufferedImage fondEcran = null;
		try {
			fondEcran = ImageIO.read(new File(fondEcranLoadPage));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		JLabel fondEcranLabel = new JLabel();
		Image img = fondEcran.getScaledInstance((int) DIM_MENU.getWidth(), (int) DIM_MENU.getHeight(), Image.SCALE_SMOOTH);
		/** Ajout de l'image au label */
		fondEcranLabel.setIcon(new ImageIcon(img));	
		/** Ajout du label au panel */
		panelMenu.add(fondEcranLabel);	
		
		frame.remove(retourMenu);
			
		/** Creation des boutton avec des images */
		newGame.setBoutonImage("new game");
		newGame.hoverBouton(COULEUR_BOUTON_MENU);
        loadGame.setBoutonImage("load game");
        loadGame.hoverBouton(COULEUR_BOUTON_MENU);
        config.setBoutonImage("config");
        config.hoverBouton(COULEUR_BOUTON_MENU);
        quit.setBoutonImage("quit");
        quit.hoverBouton(COULEUR_BOUTON_MENU);
				
		/** Ajout des bouttons du menu a la frame */
		frame.add(musiqueBouton);
		frame.add(newGame); 
        frame.add(loadGame); 
        frame.add(config);
        frame.add(quit); 
        
        frame.add(panelMenu);
	}
	
	/**
	 * main method.
	 *
	 * @param args les arguments
	 */
	public static void main(String[] args) {		
		new MenuJeu();
	}
}