package fenetrejeu.menubar;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import fenetrejeu.IFenetre;

/**
 * Class MenuBar.
 */
public class MenuBar implements IFenetre {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancie une nouvelle menu bar.
	 */
	public MenuBar() {
		// Creation de la barre de Menu
		JMenuBar menuBarSecondaire = new JMenuBar();
		menuBarSecondaire.setPreferredSize(new Dimension(LARGEUR_MENUBAR_SECONDAIRE, MENUBAR_SECONDAIRE_HAUTEUR));
		JMenu option = new JMenu("Option");
		
		// Creation d'un Item Menu
		ImageIcon menuIcon = new ImageIcon("./res/img/icon/menu.png");
		Image imageMenu = menuIcon.getImage();
		Image menuImg = imageMenu.getScaledInstance(ICON_MENUBAR_SECONDAIRE_LARGEUR, ICON_MENUBAR_SECONDAIRE_HAUTEUR, Image.SCALE_SMOOTH);
		
		menuIcon = new ImageIcon(menuImg);
		// Ajout de l'icon au menu Item
		JMenuItem menuItem = new JMenuItem("Menu", menuIcon);
		
		menuItem.setToolTipText("Retour au menu");
		menuItem.addActionListener(event -> menu.doClick());
		
		option.add(menuItem);
		
		// Creation d'un Item quitter
		ImageIcon exitIcon = new ImageIcon("./res/img/icon/exit.png");
		Image imageExit = exitIcon.getImage();
		Image exitImg = imageExit.getScaledInstance(ICON_MENUBAR_SECONDAIRE_LARGEUR, ICON_MENUBAR_SECONDAIRE_HAUTEUR, Image.SCALE_SMOOTH);
		
		exitIcon = new ImageIcon(exitImg);
		JMenuItem exitItem = new JMenuItem("Quitter", exitIcon);

		exitItem.setToolTipText("Quitter le Jeu");
		exitItem.addActionListener(event -> System.exit(0));
		
		option.add(exitItem);
		menuBarSecondaire.add(option);
		
		frame.setJMenuBar(menuBarSecondaire);
	}
	
}