package menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import musique.Musique;
import utile.style.Bouton;

/**
 * Interface IMenu.
 * 
 */
public interface IMenu {
	
	/**  Fenetre du jeu. */
	JFrame frame = new JFrame("WarStone");	
	
	/**  Music. */
	Musique menuMusic = new Musique("menu_music.wav");
	
	/** game music. */
	Musique gameMusic = new Musique("game_music.wav");
	
	/** config music. */
	Musique configMusic = new Musique("config_music.wav");
	
	/** panel principal du menu. */
	JPanel panelMenu = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
	
	/**  recuperation de la taille de l'ecran. */
	Dimension DIM_MENU = Toolkit.getDefaultToolkit().getScreenSize();	
	
	/**  Largeur des boutton. */
	int BOUTON_LARGEUR = (int) (DIM_MENU.getWidth()/4);
	
	/** Position des boutton. */
	int BOUTTON_POSITION_X = (int) (DIM_MENU.getWidth() / 2 - BOUTON_LARGEUR/2);
	
	/** On recupere la hauteur libre pour placer 4 boutton dans la moitier de l'ecran */
	int HAUTEUR = (int) ((DIM_MENU.getWidth() / 2) / 8);
	
	/** Hauteur boutton. */
	int BOUTON_HAUTEUR = HAUTEUR - HAUTEUR / 4;
	
	/** Position Y du boutton.
	 *  la POS_Y se met a jour en fonction du nombre de boutton cree
	 */
	int BOUTTON_POSITION_Y = (int) (DIM_MENU.getHeight() / 2 + BOUTON_HAUTEUR/2);

	/** Les boutttons du menu */
	Bouton newGame = new Bouton(BOUTTON_POSITION_X, BOUTTON_POSITION_Y, BOUTON_LARGEUR, BOUTON_HAUTEUR, true);
	Bouton loadGame = new Bouton(BOUTTON_POSITION_X, BOUTTON_POSITION_Y, BOUTON_LARGEUR, BOUTON_HAUTEUR, true);
	Bouton config = new Bouton(BOUTTON_POSITION_X, BOUTTON_POSITION_Y, BOUTON_LARGEUR, BOUTON_HAUTEUR, true);
	Bouton quit = new Bouton(BOUTTON_POSITION_X, BOUTTON_POSITION_Y, BOUTON_LARGEUR, BOUTON_HAUTEUR, true);
	Bouton retour = new Bouton(BOUTON_LARGEUR/4, (int) (DIM_MENU.getHeight() - BOUTON_HAUTEUR *2), BOUTON_LARGEUR/2, BOUTON_HAUTEUR, false);
	Bouton retourMenu = new Bouton(BOUTTON_POSITION_X, BOUTTON_POSITION_Y + BOUTON_HAUTEUR * 2, BOUTON_LARGEUR, BOUTON_HAUTEUR, false);

	/** Dimension du boutton de la music */
	int MUSIQUE_BOUTON_LARGEUR = BOUTON_LARGEUR/4;
	int MUSIQUE_BOUTON_HAUTEUR = BOUTON_HAUTEUR;
	
	/** Position du boutton de la music. */
	int MUSIQUE_BOUTON_POSITION_X = (int) (DIM_MENU.getWidth() - MUSIQUE_BOUTON_LARGEUR - MUSIQUE_BOUTON_LARGEUR/4);
	int MUSIQUE_BOUTON_POSITION_Y = MUSIQUE_BOUTON_HAUTEUR/2;
	
	/** Creation du boutton music */
	Bouton musiqueBouton = new Bouton(MUSIQUE_BOUTON_POSITION_X, MUSIQUE_BOUTON_POSITION_Y, MUSIQUE_BOUTON_LARGEUR, MUSIQUE_BOUTON_HAUTEUR, false);
	
	/** Dimension du boutton de la poubelle (supression des sauvegarde) */
	int SUPPRIME_BOUTTON_LARGEUR = MUSIQUE_BOUTON_LARGEUR;
	int SUPPRIME_BOUTON_HAUTEUR = MUSIQUE_BOUTON_HAUTEUR;
		
	/** Position du boutton de la poubelle */
	int SUPPRIME_BOUTON_POSITION_X = MUSIQUE_BOUTON_POSITION_X;
	int SUPPRIME_BOUTON_POSITION_Y = MUSIQUE_BOUTON_POSITION_Y + MUSIQUE_BOUTON_POSITION_Y * 2 + SUPPRIME_BOUTON_HAUTEUR/4;
	
	/** Creation du boutton de supression */
	Bouton supprimeSauvegarde = new Bouton(SUPPRIME_BOUTON_POSITION_X, SUPPRIME_BOUTON_POSITION_Y, SUPPRIME_BOUTTON_LARGEUR, SUPPRIME_BOUTON_HAUTEUR, false);	
	
	/** Images de fond du menu */
	String fondEcranLoadPage = "./res/img/background/menu/background.png";
	String fondEcranMenu = "./res/img/background/menu/background-menu.png";
	
	/** Couleur des bouttons du menu */
	Color COULEUR_BOUTON_MENU  = new Color(0, 89, 154);
	Color COULEUR_BOUTON_HOVER_MENU = new Color(255, 165, 0);

	/** Couleur du boutton de supression des sauvegarde */
	Color COULEUR_SUPPRIME = new Color(176, 176, 176);
	
	Font BUTTON_FONT = new Font("Noto Serif", Font.BOLD, 13);
}
