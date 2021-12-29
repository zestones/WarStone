/********************************************************************
 * 							WarStone								*
 *  -------------------------------------------------------------	*
 * |	 Université Jean-Monnet    L3-Infos 		    2021	 |	*
 *  -------------------------------------------------------------	*
 * 	  BEGGARI ISLEM - CHATAIGNIER ANTOINE - BENGUEZZOU Idriss		*
 * 																	*
 * 														menu		*
 * ******************************************************************/
package menu;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fenetrejeu.menubar.MenuBar;

/**
 * The Class Menu.
 */
public class MenuJeu implements IMenu {
	
	public MenuJeu(){
		
		frame.setSize(MENU_LARGEUR, MENU_HAUTEUR);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
			
		initMenuJeu();
		
		// On creer notre bar de menu secondaire
		new MenuBar();
		new MenuEvent();
		
		frame.pack();
		frame.setVisible(true);  
	}
	
	public static void initMenuJeu() {
		panelMenu.setPreferredSize(new Dimension(MENU_LARGEUR, MENU_HAUTEUR));
		gameMusic.clip.stop();
		menuMusic.clip.start();
		
		JPanel menuContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		menuContainer.setOpaque(false);	
				
		BufferedImage backgroundImg = null;
		try {
			backgroundImg = ImageIO.read(new File(background));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		JLabel backgroundLabel = new JLabel();
		Image img = backgroundImg.getScaledInstance(MENU_LARGEUR, MENU_HAUTEUR, Image.SCALE_SMOOTH);
		
		backgroundLabel.setIcon(new ImageIcon(img));	
		menuContainer.add(backgroundLabel);	
				
		frame.add(newGame); 
        frame.add(loadGame); 
        frame.add(config);
        frame.add(quit); 
        
        newGame.setBouttonImage("new game");
        loadGame.setBouttonImage("load game");
        config.setBouttonImage("config");
        quit.setBouttonImage("quit");
		
		panelMenu.add(menuContainer);		
		
		frame.add(panelMenu);
	}
	
	public static void main(String[] args) {		
		new MenuJeu();
	}
}