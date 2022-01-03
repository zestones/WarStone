package menu;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
		frame.setPreferredSize(new Dimension(MENU_LARGEUR, MENU_HAUTEUR));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
					
		/** Initialisation des elements du menu */
		initMenuJeu();
		menuMusic.clip.start();

		/** Creation du boutton music */
        musicBoutton.setBouttonImage("unmute");
        musicBoutton.hoverBoutton(COULEUR_BOUTTON_MENU);
        
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
		panelMenu.setPreferredSize(new Dimension(MENU_LARGEUR, MENU_HAUTEUR));
		panelMenu.setOpaque(false);
			
		/** Arret des music qui ont pu être lance */
		gameMusic.clip.stop();
		configMusic.clip.stop();
			
		/** creation d'un label pour deposer l'image */
		BufferedImage backgroundImg = null;
		try {
			backgroundImg = ImageIO.read(new File(background));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		JLabel backgroundLabel = new JLabel();
		Image img = backgroundImg.getScaledInstance(MENU_LARGEUR, MENU_HAUTEUR, Image.SCALE_SMOOTH);
		/** Ajout de l'image au label */
		backgroundLabel.setIcon(new ImageIcon(img));	
		/** Ajout du label au panel */
		panelMenu.add(backgroundLabel);	
		
		frame.remove(backMenu);
			
		/** Creation des boutton avec des images */
		newGame.setBouttonImage("new game");
		newGame.hoverBoutton(COULEUR_BOUTTON_MENU);
        loadGame.setBouttonImage("load game");
        loadGame.hoverBoutton(COULEUR_BOUTTON_MENU);
        config.setBouttonImage("config");
        config.hoverBoutton(COULEUR_BOUTTON_MENU);
        quit.setBouttonImage("quit");
        quit.hoverBoutton(COULEUR_BOUTTON_MENU);
				
		/** Ajout des bouttons du menu a la frame */
		frame.add(musicBoutton);
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