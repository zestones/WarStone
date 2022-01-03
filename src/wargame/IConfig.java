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
	int MENUBAR_SECONDAIRE_HAUTEUR = 20;
	int LARGEUR_MENUBAR_SECONDAIRE = FEN_LARGEUR;
	
	/** Hauteur du header */
	int MENUBAR_HAUTEUR = 65;
	
	/** Definition du nombre de case visible sur la carte */
	int NB_COLONNES_VISIBLES = 16;
	int NB_LIGNES_VISIBLES = 10;	
	
	int FOOTER_HAUTEUR = 35;
	
	/** Dimension d'une case de la carte */
	int TAILLE_CARREAU = Math.min(FEN_LARGEUR / NB_COLONNES_VISIBLES, (FEN_HAUTEUR - FOOTER_HAUTEUR - MENUBAR_HAUTEUR - MENUBAR_SECONDAIRE_HAUTEUR) / NB_LIGNES_VISIBLES);
	
	/** Dimension de la cartz. */
	int CARTE_HAUTEUR = NB_LIGNES_VISIBLES * TAILLE_CARREAU; 
	int CARTE_LARGEUR = NB_COLONNES_VISIBLES * TAILLE_CARREAU;
	
	/** Dimension Total de la carte en Nombre de case */
	int NB_LIGNES = (FEN_HAUTEUR / TAILLE_CARREAU) + 1;
	int NB_COLONNES = (FEN_LARGEUR / TAILLE_CARREAU);

	/** Dimension du panel INFOS */
	int INFOS_PANEL_LARGEUR = FEN_LARGEUR - NB_COLONNES_VISIBLES * TAILLE_CARREAU - 2;
	int INFOS_PANEL_HAUTEUR = CARTE_HAUTEUR + FOOTER_HAUTEUR;
		
	/** Dimension d'une case pour la miniCarte */
	int TAILLE_CARREAU_MINI_CARTE = Math.min((INFOS_PANEL_LARGEUR - INFOS_PANEL_LARGEUR/12) / NB_LIGNES, (INFOS_PANEL_LARGEUR - INFOS_PANEL_LARGEUR/8) / NB_COLONNES);
		
	/** Dimension de la miniCarte */
	int MINI_CARTE_LARGEUR = NB_COLONNES * TAILLE_CARREAU_MINI_CARTE + 6;
	int MINI_CARTE_HAUTEUR = NB_LIGNES * TAILLE_CARREAU_MINI_CARTE + 6;
	
	/** Taille du padding autour de la miniCarte */
	int PADDING_MINI_CARTE_GAUCHE = (INFOS_PANEL_LARGEUR - MINI_CARTE_LARGEUR)/2;
	int PADDING_MINI_CARTE_HAUT = MINI_CARTE_HAUTEUR/16;
	
	/** Hauteur du label decrivant le nombre de soldat restant */
	int ELEMENT_RESTANT_HAUTEUR = TAILLE_CARREAU;
	
	/** Hauteur infos panel. */
	int INFOS_ELEMENT_PANEL_HAUTEUR = INFOS_PANEL_HAUTEUR - ELEMENT_RESTANT_HAUTEUR - MINI_CARTE_HAUTEUR - PADDING_MINI_CARTE_HAUT * 2 - 2;
	
	/** Padding infos panel. */
	int PADDING_INFOS_PANEL = TAILLE_CARREAU_MINI_CARTE;

	/** Dimension de l'icon affichant l'element clique sur la carte */
	int ICON_ELEMENT_HAUTEUR = Math.max(66, INFOS_ELEMENT_PANEL_HAUTEUR/4);
	int ICON_ELEMENT_LARGEUR = ICON_ELEMENT_HAUTEUR;
			/** Dimension du panel affichant les infos de l'element clique */
	int ELEMENT_BODY_LARGEUR = INFOS_PANEL_LARGEUR - PADDING_INFOS_PANEL * 2;
	int ELEMENT_BODY_HAUTEUR = INFOS_ELEMENT_PANEL_HAUTEUR - ICON_ELEMENT_HAUTEUR - PADDING_INFOS_PANEL * 3;
	
	/** Largeur du footer */
	int FOOTER_LARGEUR = FEN_LARGEUR - INFOS_PANEL_LARGEUR;
		
	/** Dimension des icons dans lea barre de menu */
	int ICON_MENUBAR_SECONDAIRE_HAUTEUR = 15;
	int ICON_MENUBAR_SECONDAIRE_LARGEUR = 15;

	// Constante pour centrer la barre de vie
	int PADDING_VIE_CASE_GAUCHE = TAILLE_CARREAU/8;
	int PADDING_VIE_CASE_HAUT = PADDING_VIE_CASE_GAUCHE/2;
		
	// Definir le NB ELEMENT sur la carte
	int NB_HEROS = 5; 
	int NB_MONSTRES = 5; 
	int NB_OBSTACLES = NB_HEROS * 2;
}