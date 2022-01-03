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
	Color COULEUR_MENUBAR = new Color(0, 70 ,85, 38); 
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
	
	/** Dimension de la fenetre de jeux */
	int FEN_LARGEUR = (int)taille.getWidth();
	int FEN_HAUTEUR = (int)taille.getHeight();
		
	/** Dimension de la barre de menu */
	int HAUTEUR_MENUBAR_SECONDAIRE = 20;
	int LARGEUR_MENUBAR_SECONDAIRE = FEN_LARGEUR;
	
	/** Hauteur du header */
	int MENUBAR_HAUTEUR = 65;
	
	/** Definition du nombre de case visible sur la carte */
	int LARGEUR_CASE_VISIBLE = 16;
	int HAUTEUR_CASE_VISIBLE = 10;	
	
	int FOOTER_HAUTEUR = 35;
	
	/** Dimension d'une case de la carte */
	int NB_PIX_CASE = Math.min(FEN_LARGEUR / LARGEUR_CASE_VISIBLE, (FEN_HAUTEUR - FOOTER_HAUTEUR - MENUBAR_HAUTEUR - HAUTEUR_MENUBAR_SECONDAIRE) / HAUTEUR_CASE_VISIBLE);
	
	/** Dimension de la cartz. */
	int HAUTEUR_CARTE = HAUTEUR_CASE_VISIBLE * NB_PIX_CASE; 
	int LARGEUR_CARTE = LARGEUR_CASE_VISIBLE * NB_PIX_CASE;	
	/** Dimension Total de la carte en Nombre de case */
	int HAUTEUR_CARTE_CASE = (FEN_HAUTEUR / NB_PIX_CASE) + 1;
	int LARGEUR_CARTE_CASE = (FEN_LARGEUR / NB_PIX_CASE);

	/** Dimension du panel INFOS */
	int LARGEUR_INFOS_PANEL = FEN_LARGEUR - LARGEUR_CASE_VISIBLE * NB_PIX_CASE - 2;
	int HAUTEUR_INFOS_PANEL = HAUTEUR_CARTE + FOOTER_HAUTEUR;
		
	/** Dimension d'une case pour la miniCarte */
	int MINI_NB_PIX_CASE = Math.min((LARGEUR_INFOS_PANEL - LARGEUR_INFOS_PANEL/12) / HAUTEUR_CARTE_CASE, (LARGEUR_INFOS_PANEL - LARGEUR_INFOS_PANEL/8) / LARGEUR_CARTE_CASE);
		
	/** Dimension de la miniCarte */
	int LARGEUR_MINI_CARTE = LARGEUR_CARTE_CASE * MINI_NB_PIX_CASE + 6;
	int HAUTEUR_MINI_CARTE = HAUTEUR_CARTE_CASE * MINI_NB_PIX_CASE + 6;
	
	/** Taille du padding autour de la miniCarte */
	int PADDING_LARGEUR_MINI_CARTE = (LARGEUR_INFOS_PANEL - LARGEUR_MINI_CARTE)/2;
	int PADDING_HAUTEUR_MINI_CARTE = HAUTEUR_MINI_CARTE/16;
	
	/** Hauteur du label decrivant le nombre de soldat restant */
	int HAUTEUR_NB_SOLDAT_VIVANT = NB_PIX_CASE;
	
	/** Hauteur infos panel. */
	int HAUTEUR_INFOS_ELEMENT_PANEL = HAUTEUR_INFOS_PANEL - HAUTEUR_NB_SOLDAT_VIVANT - HAUTEUR_MINI_CARTE - PADDING_HAUTEUR_MINI_CARTE * 2 - 2;
	
	/** Padding infos panel. */
	int PADDING_INFOS_PANEL = MINI_NB_PIX_CASE;

	/** Dimension de l'icon affichant l'element clique sur la carte */
	int HAUTEUR_ICON_ELEMENT = Math.max(66, HAUTEUR_INFOS_ELEMENT_PANEL/4);
	int LARGEUR_ICON_ELEMENT = HAUTEUR_ICON_ELEMENT;
			/** Dimension du panel affichant les infos de l'element clique */
	int LARGEUR_ELEMENT_BODY = LARGEUR_INFOS_PANEL - PADDING_INFOS_PANEL * 2;
	int HAUTEUR_ELEMENT_BODY = HAUTEUR_INFOS_ELEMENT_PANEL - HAUTEUR_ICON_ELEMENT - PADDING_INFOS_PANEL * 3;
	
	/** Largeur du footer */
	int FOOTER_LARGEUR = FEN_LARGEUR - LARGEUR_INFOS_PANEL;
		
	/** Dimension des icons dans lea barre de menu */
	int HAUTEUR_ICON_MENU = 15;
	int LARGEUR_ICON_MENU = 15;

	// Constante pour centrer la barre de vie
	int PADDING_VIE_CASE_LARGEUR = NB_PIX_CASE/8;
	int PADDING_VIE_CASE = PADDING_VIE_CASE_LARGEUR/2;
		
	// Definir le NB ELEMENT sur la carte
	int NB_HEROS = 5; 
	int NB_MONSTRES = 5; 
	int NB_OBSTACLES = NB_HEROS * 2;
}