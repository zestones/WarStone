/********************************************************************
 * 							WarStone								*
 *  -------------------------------------------------------------	*
 * |	 Université Jean-Monnet    L3-Infos 		    2021	 |	*
 *  -------------------------------------------------------------	*
 * 	  BEGGARI ISLEM - CHATAIGNIER ANTOINE - BENGUEZZOU Idriss		*
 * 																	*
 * 														wargame		*
 * ******************************************************************/
package fenetrejeu.menubar;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import fenetrejeu.IFenetre;

/**
 * The Class MenuBar.
 */
public class MenuBar implements IFenetre {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new menu bar.
	 */
	public MenuBar() {
		JMenuBar menuBarSecondaire = new JMenuBar();
		menuBarSecondaire.setPreferredSize(new Dimension(LARGEUR_MENUBAR_SECONDAIRE, MENUBAR_SECONDAIRE_HAUTEUR));
		JMenu option = new JMenu("Option");
		
		//ImageIcon menuIcon = new ImageIcon("./res/img/icon/menu.png");
		JMenuItem menuItem = new JMenuItem("Menu");
		
		//Image imageMenu = menuIcon.getImage();
		//Image menuImg = imageMenu.getScaledInstance(LARGEUR_ICON_MENU, HAUTEUR_ICON_MENU, java.awt.Image.SCALE_SMOOTH);
		
		//menuIcon = new ImageIcon(menuImg);
		
		menuItem.setToolTipText("Retour au menu");
		menuItem.addActionListener(event -> menu.doClick());
		option.add(menuItem);
		
		
		ImageIcon exitIcon = new ImageIcon("./res/img/icon/exit.png");
	
		Image imageExit = exitIcon.getImage();
		Image exitImg = imageExit.getScaledInstance(ICON_MENUBAR_SECONDAIRE_LARGEUR, ICON_MENUBAR_SECONDAIRE_HAUTEUR, java.awt.Image.SCALE_SMOOTH);
		
		exitIcon = new ImageIcon(exitImg);
		// Marche pas setFocusable ??
		option.setMnemonic(KeyEvent.VK_F);

		JMenuItem exitItem = new JMenuItem("Quitter", exitIcon);
		exitItem.setMnemonic(KeyEvent.VK_E);
		exitItem.setToolTipText("Quitter le Jeu");
		exitItem.addActionListener(event -> System.exit(0));
		
		option.add(exitItem);
		menuBarSecondaire.add(option);
		
		
		frame.setJMenuBar(menuBarSecondaire);
	}
	
}