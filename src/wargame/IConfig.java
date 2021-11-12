package wargame;

import java.awt.Color;

public interface IConfig {
	int LARGEUR_CARTE = 25; int HAUTEUR_CARTE = 15; // en nombre de cases
	int NB_PIX_CASE = 20;
	int POSITION_X = 100; int POSITION_Y = 50; // Position de la fenêtre
	int NB_HEROS = 6; int NB_MONSTRES = 15; int NB_OBSTACLES = 20;
	
	Color COULEUR_VIDE = Color.white, COULEUR_INCONNU = Color.lightGray;
	Color COULEUR_TEXTE = Color.black, COULEUR_MONSTRES = Color.black;
	Color COULEUR_HEROS = Color.red, COULEUR_HEROS_DEJA_JOUE = Color.pink;
	Color COULEUR_EAU = Color.blue, COULEUR_FORET = Color.green, COULEUR_ROCHER = Color.gray;
	Color COULEUR_GRILLE = Color.black; Color COULEUR_MENUBAR = Color.white;
	
	final int MAX_FEN_LARGEUR = 500;
	final int MAX_FEN_HAUTEUR = 500;
	final int PADDING_BAS = 41;
	final int PADDING_DROIT = 18;

	/* Variables calculées */
	final int FEN_LARGEUR = LARGEUR_CARTE * NB_PIX_CASE + PADDING_DROIT;
	/* Variable Menu */
	final int MENUBAR_HAUTEUR = 55;
	final int MENUBAR_LARGEUR = FEN_LARGEUR;
	
	final int FEN_HAUTEUR = HAUTEUR_CARTE * NB_PIX_CASE + PADDING_BAS + MENUBAR_HAUTEUR ;
	
	/* Variable du Boutton */
	final int BOUTTON_HAUTEUR = MENUBAR_HAUTEUR/2;
	final int BOUTTON_LARGEUR = MENUBAR_LARGEUR/6;
			

}