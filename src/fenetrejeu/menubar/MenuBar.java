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

		ImageIcon exitIcon = new ImageIcon("./res/icon/exit.png");
		Image image = exitIcon.getImage();
		Image newimg = image.getScaledInstance(LARGEUR_ICON_MENU, HAUTEUR_ICON_MENU, java.awt.Image.SCALE_SMOOTH);
		exitIcon = new ImageIcon(newimg);

		JMenu option = new JMenu("Option");
		// Marche pas setFocusable ??
		option.setMnemonic(KeyEvent.VK_F);

		JMenuItem menuItem = new JMenuItem("Quitter", exitIcon);
		menuItem.setMnemonic(KeyEvent.VK_E);
		menuItem.setToolTipText("Quitter le Jeu");
		menuItem.addActionListener(event -> System.exit(0));

		option.add(menuItem);

		menuBarSecondaire.add(option);

		frame.setJMenuBar(menuBarSecondaire);
	}
}