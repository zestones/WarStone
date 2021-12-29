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

import javax.swing.JFrame;
import javax.swing.JPanel;

import utile.Boutton;

public interface IMenu {
	
	// Parametre dessin de la fenetre
	JFrame frame = new JFrame("WarStone");	
	
	JPanel panelMenu = new JPanel();
	
	// HAUTEUR et LARGEUR de la Fenetre
	int MENU_LARGEUR = 800;
	int MENU_HAUTEUR = 600;
	
	int LARGEUR_BOUTTON = 160;
	int HAUTEUR_BOUTTON = 40;
	
	int BOUTTON_POSITION_X = MENU_LARGEUR / 2 - LARGEUR_BOUTTON/2;
	int BOUTTON_POSITION_Y = MENU_HAUTEUR / 2 + HAUTEUR_BOUTTON/2;
		
	Boutton newGame = new Boutton(BOUTTON_POSITION_X, 0, LARGEUR_BOUTTON, HAUTEUR_BOUTTON);
	Boutton loadGame = new Boutton(BOUTTON_POSITION_X, 0, LARGEUR_BOUTTON, HAUTEUR_BOUTTON);
	Boutton config = new Boutton(BOUTTON_POSITION_X, 0, LARGEUR_BOUTTON, HAUTEUR_BOUTTON);
	Boutton quit = new Boutton(BOUTTON_POSITION_X, 0, LARGEUR_BOUTTON, HAUTEUR_BOUTTON);
	
	
	String background = "./res/img/background/menu/background.png";
	String backgroundMenu = "./res/img/background/menu/background-menu.png";

	Color COULEUR_BOUTTON  = new Color(0, 89, 154);
	Color COULEUR_BOUTTON_HOVER = new Color(255, 165, 0);
}
