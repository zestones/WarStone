package wargame;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar implements IConfig{

	private static final long serialVersionUID = 1L;

	public MenuBar() {		
		JMenuBar menuBarSecondaire = new JMenuBar();
		
		ImageIcon exitIcon = new ImageIcon("./res/icon/exit.png"); 
		Image image = exitIcon.getImage(); 
		Image newimg = image.getScaledInstance(LARGEUR_ICON, HAUTEUR_ICON,  java.awt.Image.SCALE_SMOOTH); 
		exitIcon = new ImageIcon(newimg); 
		
		
		
        JMenu option = new JMenu("Option");
        // Marche pas setFocusable ??
        option.setMnemonic(KeyEvent.VK_F);

        JMenuItem menuItem = new JMenuItem("Quitter", exitIcon);
        menuItem.setMnemonic(KeyEvent.VK_E);
        menuItem.setToolTipText("Quitter le Jeu");
        menuItem.addActionListener((event) -> System.exit(0));

        option.add(menuItem);
              
        menuBarSecondaire.add(option);
        
        frame.setJMenuBar(menuBarSecondaire);
    }
}