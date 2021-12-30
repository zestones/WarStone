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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import music.SoundLauncher;
import utile.Boutton;

public interface IMenu {
	
	// Parametre dessin de la fenetre
	JFrame frame = new JFrame("WarStone");	

	SoundLauncher menuMusic = new SoundLauncher("menu_music.wav");
	SoundLauncher gameMusic = new SoundLauncher("game_music.wav");
	
	JPanel panelMenu = new JPanel();
	
	
	Dimension taille = Toolkit.getDefaultToolkit().getScreenSize();	
	
	final int MENU_LARGEUR = (int)taille.getWidth();
	final int MENU_HAUTEUR = (int)taille.getHeight();
		
	int LARGEUR_BOUTTON = 511;
	int HAUTEUR_BOUTTON = 70;
	
	int BOUTTON_POSITION_X = MENU_LARGEUR / 2 - LARGEUR_BOUTTON/2;
	int BOUTTON_POSITION_Y = MENU_HAUTEUR / 2 + HAUTEUR_BOUTTON/2;
		
	Boutton newGame = new Boutton(BOUTTON_POSITION_X, BOUTTON_POSITION_Y, LARGEUR_BOUTTON, HAUTEUR_BOUTTON);
	Boutton loadGame = new Boutton(BOUTTON_POSITION_X, BOUTTON_POSITION_Y, LARGEUR_BOUTTON, HAUTEUR_BOUTTON);
	Boutton config = new Boutton(BOUTTON_POSITION_X, BOUTTON_POSITION_Y, LARGEUR_BOUTTON, HAUTEUR_BOUTTON);
	Boutton quit = new Boutton(BOUTTON_POSITION_X, BOUTTON_POSITION_Y, LARGEUR_BOUTTON, HAUTEUR_BOUTTON);
	Boutton back = new Boutton(BOUTTON_POSITION_X - BOUTTON_POSITION_X + LARGEUR_BOUTTON/4, BOUTTON_POSITION_Y, LARGEUR_BOUTTON/2, HAUTEUR_BOUTTON);
	
	Boutton backMenu = new Boutton(BOUTTON_POSITION_X, BOUTTON_POSITION_Y / 2, LARGEUR_BOUTTON, HAUTEUR_BOUTTON);
	
	
	String background = "./res/img/background/menu/background.png";
	String backgroundMenu = "./res/img/background/menu/background-menu.png";

	Color COULEUR_BOUTTON  = new Color(0, 89, 154);
	Color COULEUR_BOUTTON_HOVER = new Color(255, 165, 0);
}
