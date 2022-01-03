package menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import music.SoundLauncher;
import utile.Boutton;

/**
 * Interface IMenu.
 * 
 */
public interface IMenu {
	
	/**  Fenetre du jeu. */
	JFrame frame = new JFrame("WarStone");	
	
	/**  Music. */
	SoundLauncher menuMusic = new SoundLauncher("menu_music.wav");
	
	/** game music. */
	SoundLauncher gameMusic = new SoundLauncher("game_music.wav");
	
	/** config music. */
	SoundLauncher configMusic = new SoundLauncher("config_music.wav");
	
	/**  panel principal du menu. */
	JPanel panelMenu = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
	
	/**  recuperation de la taille de l'ecran. */
	Dimension taille = Toolkit.getDefaultToolkit().getScreenSize();	
	
	/**  Dimension de la fenetre du menu. */
	int MENU_LARGEUR = (int)taille.getWidth();
	
	/** The menu hauteur. */
	int MENU_HAUTEUR = (int)taille.getHeight();
	
	/**  Largeur des boutton. */
	int LARGEUR_BOUTTON = MENU_LARGEUR/4;
	
	/** Position des boutton. */
	int BOUTTON_POSITION_X = MENU_LARGEUR / 2 - LARGEUR_BOUTTON/2;
	
	/** On recupere la hauteur libre pour placer 4 boutton dans la moitier de l'ecran */
	int HAUTEUR = (MENU_LARGEUR / 2) / 8;
	
	/** Hauteur boutton. */
	int HAUTEUR_BOUTTON = HAUTEUR -  HAUTEUR / 4;
	
	/** Position Y du boutton.
	 *  la POS_Y se met a jour en fonction du nombre de boutton cree
	 */
	int BOUTTON_POSITION_Y = MENU_HAUTEUR / 2 + HAUTEUR_BOUTTON/2;

	/** Les boutttons du menu */
	Boutton newGame = new Boutton(BOUTTON_POSITION_X, BOUTTON_POSITION_Y, LARGEUR_BOUTTON, HAUTEUR_BOUTTON);
	Boutton loadGame = new Boutton(BOUTTON_POSITION_X, BOUTTON_POSITION_Y, LARGEUR_BOUTTON, HAUTEUR_BOUTTON);
	Boutton config = new Boutton(BOUTTON_POSITION_X, BOUTTON_POSITION_Y, LARGEUR_BOUTTON, HAUTEUR_BOUTTON);
	Boutton quit = new Boutton(BOUTTON_POSITION_X, BOUTTON_POSITION_Y, LARGEUR_BOUTTON, HAUTEUR_BOUTTON);
	Boutton back = new Boutton(LARGEUR_BOUTTON/4, MENU_HAUTEUR - HAUTEUR_BOUTTON *2, LARGEUR_BOUTTON/2, HAUTEUR_BOUTTON, false);
	Boutton backMenu = new Boutton(BOUTTON_POSITION_X, BOUTTON_POSITION_Y, LARGEUR_BOUTTON, HAUTEUR_BOUTTON);

	/** Dimension du boutton de la music */
	int LARGEUR_MUSIC_BOUTTON = LARGEUR_BOUTTON/4;
	int HAUTEUR_MUSIC_BOUTTON = HAUTEUR_BOUTTON;
	
	/** Position du boutton de la music. */
	int BOUTTON_MUSIC_POSITION_X = MENU_LARGEUR - LARGEUR_MUSIC_BOUTTON - LARGEUR_MUSIC_BOUTTON/4;
	int BOUTTON_MUSIC_POSITION_Y = HAUTEUR_MUSIC_BOUTTON/2;
	
	/** Creation du boutton music */
	Boutton musicBoutton = new Boutton(BOUTTON_MUSIC_POSITION_X, BOUTTON_MUSIC_POSITION_Y, LARGEUR_MUSIC_BOUTTON, HAUTEUR_MUSIC_BOUTTON, false);
	
	/** Dimension du boutton de la poubelle (supression des sauvegarde) */
	int LARGEUR_DELETE_BOUTTON = LARGEUR_MUSIC_BOUTTON;
	int HAUTEUR_DELETE_BOUTTON = HAUTEUR_MUSIC_BOUTTON;
		
	/** Position du boutton de la poubelle */
	int BOUTTON_DELETE_POSITION_X = BOUTTON_MUSIC_POSITION_X;
	int BOUTTON_DELETE_POSITION_Y = BOUTTON_MUSIC_POSITION_Y + BOUTTON_MUSIC_POSITION_Y * 2 + HAUTEUR_DELETE_BOUTTON/4;
	
	/** Creation du boutton de supression */
	Boutton deleteSave = new Boutton(BOUTTON_DELETE_POSITION_X, BOUTTON_DELETE_POSITION_Y, LARGEUR_DELETE_BOUTTON, HAUTEUR_DELETE_BOUTTON, false);	
	
	/** Images de fond du menu */
	String background = "./res/img/background/menu/background.png";
	String backgroundMenu = "./res/img/background/menu/background-menu.png";
	
	/** Couleur des bouttons du menu */
	Color COULEUR_BOUTTON_MENU  = new Color(0, 89, 154);
	Color COULEUR_BOUTTON_HOVER_MENU = new Color(255, 165, 0);

	/** Couleur du boutton de supression des sauvegarde */
	Color COULEUR_DELETE = new Color(176, 176, 176);
}
