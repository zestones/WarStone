/********************************************************************
 * 							WarStone								*
 *  -------------------------------------------------------------	*
 * |	 Université Jean-Monnet    L3-Infos 		    2021	 |	*
 *  -------------------------------------------------------------	*
 * 	  BEGGARI ISLEM - CHATAIGNIER ANTOINE - BENGUEZZOU Idriss		*
 * 																	*
 * 														wargame		*
 * ******************************************************************/
package wargame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * The Interface IConfig.
 */
public interface IConfig extends java.io.Serializable {	
	
	Color COULEUR_VIDE = Color.white, COULEUR_INCONNU = Color.lightGray;
	Color COULEUR_TEXTE = Color.black, COULEUR_MONSTRE = new Color(0, 0, 0, 60);
	Color COULEUR_HEROS = Color.red, COULEUR_HEROS_DEJA_JOUE = new Color(175, 25, 75, 100);
	Color COULEUR_EAU = Color.blue, COULEUR_FORET = Color.green, COULEUR_ROCHER = Color.gray;
	Color COULEUR_GRILLE = Color.black; 
	Color COULEUR_MENUBAR = Color.gray; 
	Color COULEUR_FOOTER = Color.black;
	Color COULEUR_DEPLACEMENT = new Color(75, 25, 75, 100), COULEUR_PORTEE = new Color(20, 25, 25, 100);
	Color COULEUR_VIE_R = Color.red, COULEUR_VIE_V = Color.green;
	Color COULEUR_ENEMIS = new Color(255, 10, 20, 50), COULEUR_AMIS = new Color(20,200,10,50); 
	Color COULEUR_INFOS_PANEL = new Color(88, 41, 0);
	Color COULEUR_BORDURE = Color.white;
	Color COULEUR_FOCUS = new Color(145, 145, 145, 95);
	Color COULEUR_LAVE = Color.yellow;
	
	Image grass = Toolkit.getDefaultToolkit().getImage("./res/img/background/jeu/grass.png");
	Image range = Toolkit.getDefaultToolkit().getImage("./res/img/background/jeu/range.jpg");
	Image lave = Toolkit.getDefaultToolkit().getImage("./res/img/background/jeu/lave.jpg");
	Image fleche = Toolkit.getDefaultToolkit().getImage("./res/img/pops/arrow.png");	
	
	Dimension taille = Toolkit.getDefaultToolkit().getScreenSize();	

	int HAUTEUR_MENUBAR_SECONDAIRE = 20;

	final int FEN_LARGEUR = (int)taille.getWidth();
	final int FEN_HAUTEUR = (int)taille.getHeight();

	int LARGEUR_CASE_VISIBLE = 15;
	int HAUTEUR_CASE_VISIBLE = 10;	
	
	/** Varibale du footer dans le PanneauJeu */
	final int FOOTER_HAUTEUR = 35;
	final int FOOTER_LARGEUR = FEN_LARGEUR;

	// On definit la largeur comme etant 1/4 de l'ecran de jeu
	int LARGEUR_INFOS_PANEL = FEN_LARGEUR/4;	
	// Hauteur du menuBar
	int MENUBAR_HAUTEUR = 65;
		
	int LARGEUR_MENUBAR_SECONDAIRE = FEN_LARGEUR;
	
	// Taille d'une case de la carte
	int NB_PIX_CASE = Math.min((FEN_LARGEUR - LARGEUR_INFOS_PANEL) / LARGEUR_CASE_VISIBLE, (FEN_HAUTEUR - FOOTER_HAUTEUR - MENUBAR_HAUTEUR) / HAUTEUR_CASE_VISIBLE);
	
	// Constante pour centrer la barre de vie
	int PADDING_VIE_CASE_LARGEUR = NB_PIX_CASE/8;
	
	int PADDING_VIE_CASE = PADDING_VIE_CASE_LARGEUR/2;
		
	// HAUTEUR et LARGEUR de la carte en NB DE CASE 
	// definit la taille en dehors de la fenetre si trop de case
	int HAUTEUR_CARTE_CASE = (FEN_HAUTEUR / NB_PIX_CASE) + 1;
	
	/** largeur carte en nombre de case. */
	int LARGEUR_CARTE_CASE = (FEN_LARGEUR / NB_PIX_CASE);
	
	
	// Hauteur pour le label NB_SOLDAT_RESTANT
	int HAUTEUR_NB_SOLDAT_VIVANT = LARGEUR_INFOS_PANEL/6;
	
	int PADDING_LARGEUR_MINI_CARTE = LARGEUR_INFOS_PANEL/16;
	int PADDING_HAUTEUR_MINI_CARTE = PADDING_LARGEUR_MINI_CARTE;

	// Taille d'une case dans la miniCarte
	int MINI_NB_PIX_CASE = Math.min((LARGEUR_INFOS_PANEL - LARGEUR_INFOS_PANEL/12) / HAUTEUR_CARTE_CASE, (LARGEUR_INFOS_PANEL - LARGEUR_INFOS_PANEL/8) / LARGEUR_CARTE_CASE);
	
	/** The largeur mini carte. */
	// Definit la largeur de la minicarte + TAILLE_BORDURE
	int LARGEUR_MINI_CARTE = LARGEUR_CARTE_CASE * MINI_NB_PIX_CASE + 6;
	
	/** The hauteur mini carte. */
	// HAUTEUR de la miniCarte + TAILLE_BORDURE
	int HAUTEUR_MINI_CARTE = HAUTEUR_CARTE_CASE * MINI_NB_PIX_CASE + 6;
		
	/** The hauteur carte. */
	int HAUTEUR_CARTE = FEN_HAUTEUR - HAUTEUR_MENUBAR_SECONDAIRE - 2 - MENUBAR_HAUTEUR; 
	
	/** The padding infos panel. */
	int PADDING_INFOS_PANEL = MINI_NB_PIX_CASE;

	/**hauteur infos panel. */
	int HAUTEUR_INFOS_PANEL = HAUTEUR_CARTE - HAUTEUR_MINI_CARTE - HAUTEUR_NB_SOLDAT_VIVANT - PADDING_INFOS_PANEL * 3 + 2;

	/** The largeur carte. */
	// HAUTEUR et LARGEUR de la carte
	int LARGEUR_CARTE = FEN_LARGEUR - LARGEUR_INFOS_PANEL;
		/** The hauteur icon menu. */
	// HAUTEUR ET LARGEUR des icon dans le menuSecondaire
	int HAUTEUR_ICON_MENU = 15;
	
	/** The largeur icon menu. */
	int LARGEUR_ICON_MENU = 15;
	
	/** The hauteur icon element. */
	// HAUTEUR et LARGEUR des icon cliquer sur la cart
	int HAUTEUR_ICON_ELEMENT = LARGEUR_INFOS_PANEL/3;
	
	/** The largeur icon element. */
	int LARGEUR_ICON_ELEMENT = HAUTEUR_ICON_ELEMENT;
	
	// Dimension du panel infosElementBody
	int LARGEUR_ELEMENT_BODY = LARGEUR_INFOS_PANEL - PADDING_INFOS_PANEL * 2;
	int HAUTEUR_ELEMENT_BODY = HAUTEUR_INFOS_PANEL - HAUTEUR_ICON_ELEMENT - HAUTEUR_NB_SOLDAT_VIVANT - PADDING_INFOS_PANEL*2;
	
		
	// Definir le NB ELEMENT sur la carte
	int NB_HEROS = 5; 
	int NB_MONSTRES = 5; 
	int NB_OBSTACLES = NB_HEROS * 2;
	
}