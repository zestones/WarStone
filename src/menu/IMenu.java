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
	SoundLauncher configMusic = new SoundLauncher("config_music.wav");

	JPanel panelMenu = new JPanel();
	
	Dimension taille = Toolkit.getDefaultToolkit().getScreenSize();	
	
	final int MENU_LARGEUR = (int)taille.getWidth();
	final int MENU_HAUTEUR = (int)taille.getHeight();
		
	// Dimension des boutton du menu
	int LARGEUR_BOUTTON = MENU_LARGEUR/4;
	
	// Position des bouttons
	int BOUTTON_POSITION_X = MENU_LARGEUR / 2 - LARGEUR_BOUTTON/2;
	
	// On recupere la hauteur libre pour placer 4 boutton dans la moitier de l'ecran
	int HAUTEUR = (MENU_LARGEUR / 2) / 8;
	int HAUTEUR_BOUTTON = HAUTEUR -  HAUTEUR / 4;
	
	int BOUTTON_POSITION_Y = MENU_HAUTEUR / 2 + HAUTEUR_BOUTTON/2;

	// la POS_Y se met a jour en fonction du nombre de boutton cree
	Boutton newGame = new Boutton(BOUTTON_POSITION_X, BOUTTON_POSITION_Y, LARGEUR_BOUTTON, HAUTEUR_BOUTTON);
	Boutton loadGame = new Boutton(BOUTTON_POSITION_X, BOUTTON_POSITION_Y, LARGEUR_BOUTTON, HAUTEUR_BOUTTON);
	Boutton config = new Boutton(BOUTTON_POSITION_X, BOUTTON_POSITION_Y, LARGEUR_BOUTTON, HAUTEUR_BOUTTON);
	Boutton quit = new Boutton(BOUTTON_POSITION_X, BOUTTON_POSITION_Y, LARGEUR_BOUTTON, HAUTEUR_BOUTTON);
	
	Boutton back = new Boutton(LARGEUR_BOUTTON/4, MENU_HAUTEUR - HAUTEUR_BOUTTON - HAUTEUR_BOUTTON/4, LARGEUR_BOUTTON/2, HAUTEUR_BOUTTON, false);
	Boutton backMenu = new Boutton(BOUTTON_POSITION_X, BOUTTON_POSITION_Y, LARGEUR_BOUTTON, HAUTEUR_BOUTTON);

	// Variable du boutton de la music
	int LARGEUR_MUSIC_BOUTTON = LARGEUR_BOUTTON/4;
	int HAUTEUR_MUSIC_BOUTTON = HAUTEUR_BOUTTON;
	int BOUTTON_MUSIC_POSITION_X = MENU_LARGEUR - LARGEUR_MUSIC_BOUTTON - LARGEUR_MUSIC_BOUTTON/4;
	int BOUTTON_MUSIC_POSITION_Y = HAUTEUR_MUSIC_BOUTTON/2;

	// On creer notre boutton
	Boutton musicBoutton = new Boutton(BOUTTON_MUSIC_POSITION_X, BOUTTON_MUSIC_POSITION_Y, LARGEUR_MUSIC_BOUTTON, HAUTEUR_MUSIC_BOUTTON, false);
	
	// Variable du boutton delete (pour les sauvegarde)
	int LARGEUR_DELETE_BOUTTON = LARGEUR_MUSIC_BOUTTON;
	int HAUTEUR_DELETE_BOUTTON = HAUTEUR_MUSIC_BOUTTON;
	int BOUTTON_DELETE_POSITION_X = BOUTTON_MUSIC_POSITION_X;
	int BOUTTON_DELETE_POSITION_Y = BOUTTON_MUSIC_POSITION_Y + BOUTTON_MUSIC_POSITION_Y * 2 + HAUTEUR_DELETE_BOUTTON/4;
	// Creation du boutton
	Boutton deleteSave = new Boutton(BOUTTON_DELETE_POSITION_X, BOUTTON_DELETE_POSITION_Y, LARGEUR_DELETE_BOUTTON, HAUTEUR_DELETE_BOUTTON, false);	
	
	String background = "./res/img/background/menu/background.png";
	String backgroundMenu = "./res/img/background/menu/background-menu.png";

	Color COULEUR_BOUTTON_MENU  = new Color(0, 89, 154);
	Color COULEUR_BOUTTON_HOVER_MENU = new Color(255, 165, 0);
	Color COULEUR_DELETE = new Color(176, 176, 176);
}
