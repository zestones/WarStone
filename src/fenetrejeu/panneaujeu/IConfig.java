package fenetrejeu.panneaujeu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * Interface IConfig.
 */
public interface IConfig extends java.io.Serializable {	
	
	// Definir le NB ELEMENT sur la carte
	int NB_HEROS = 1; 
	int NB_MONSTRES = 8; 
	int NB_OBSTACLES = NB_HEROS * 2;
		
	/** Couleur des different Panel */
	Color COULEUR_MENUBAR = new Color(70, 71, 71); 
	Color COULEUR_MINI_CARTE_PANEL = new Color(51, 51, 51);
	Color COULEUR_INFOS_PANEL = new Color(51, 51, 51);
	Color COULEUR_INFOS_ELEMENT_LABEL = new Color(234, 184, 150);
	Color COULEUR_DESCRIPTIF_INFOS_PANEL = new Color(84, 84, 84);
	Color COULEUR_ELEMENT_RESTANT = new Color(234, 184, 150);
	Color COULEUR_FOOTER = new Color(41, 41, 41); 
	Color COULEUR_FLECHE_PANEL = new Color(70, 71, 71); 
			
	/** Couleur des Bordure */
	Color COULEUR_BORDUR_MINI_CARTE = new Color(220, 170, 135);
	Color COULEUR_BORDURE = Color.white;
	
	Color COULEUR_TEXTE = Color.BLACK;
		
	/** Couleur de la zone visible sur la miniCarte */
	Color COULEUR_FOCUS_MINI_CARTE = new Color(145, 145, 145, 95);
		
	/** Couleur grille de la carte*/
	Color COULEUR_GRILLE = Color.BLACK; 
	
	/** Couleur des zones autour du heros */
	Color COULEUR_DEPLACEMENT = new Color(75, 25, 75, 100), COULEUR_PORTEE = new Color(20, 25, 25, 100);

	Color COULEUR_ENNEMIS = new Color(255, 10, 20, 50), COULEUR_AMIS = new Color(20,200,10,50); 
	Color COULEUR_HEROS = Color.red, COULEUR_HEROS_DEJA_JOUE = new Color(175, 25, 75, 100);
	
	Color COULEUR_HEROS_TOUR_MONSTRE = new Color(247, 146, 24, 100); 
	Color COULEUR_BARRE_VIE_ROUGE = Color.red, COULEUR_BARRE_VIE_VERT = Color.green;
	Color COULEUR_MONSTRE = new Color(0, 0, 0, 60);
	
	Color COULEUR_FLECHE_DIRECTIONNELLE = new Color(187, 227, 222);
	
	/** Couleur d'un element selectione en mode config */
	Color COULEUR_ELEMENT_SELECTIONE = Color.green;
		
	Image herbe = Toolkit.getDefaultToolkit().getImage("./res/img/background/jeu/grass.png");
	Image terre = Toolkit.getDefaultToolkit().getImage("./res/img/background/jeu/range.jpg");
	Image lave = Toolkit.getDefaultToolkit().getImage("./res/img/background/jeu/lave.jpg");
	Image fleche = Toolkit.getDefaultToolkit().getImage("./res/img/pops/arrow.png");	
	
	
	/** Dimension de la fenetre de jeux */
	Dimension DIM_FEN = Toolkit.getDefaultToolkit().getScreenSize();	

	int FEN_LARGEUR = (int)DIM_FEN.getWidth();
	int FEN_HAUTEUR = (int)DIM_FEN.getHeight();
	
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
	int NB_COLONNES = (FEN_LARGEUR / TAILLE_CARREAU) + 1;

	/** Dimension du panel INFOS */
	int INFOS_PANEL_LARGEUR = FEN_LARGEUR - NB_COLONNES_VISIBLES * TAILLE_CARREAU - 2;
	int INFOS_PANEL_HAUTEUR = CARTE_HAUTEUR + FOOTER_HAUTEUR - 2;
	
	/** Largeur du footer */
	int FOOTER_LARGEUR = (int) (FEN_LARGEUR - INFOS_PANEL_LARGEUR);
		
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
	
	int PADDING_ICON_ELEMENT_HAUT = ICON_ELEMENT_HAUTEUR/10;
	int PADDING_ICON_ELEMENT_GAUCHE = ICON_ELEMENT_LARGEUR/8;
			/** Dimension du panel affichant les infos de l'element clique */
	int DESCRIPTIF_ELEMENT_LARGEUR = INFOS_PANEL_LARGEUR - PADDING_INFOS_PANEL * 2;
	int DESCRIPTIF_ELEMENT_HAUTEUR = INFOS_ELEMENT_PANEL_HAUTEUR - ICON_ELEMENT_HAUTEUR - PADDING_INFOS_PANEL * 3;
			
	/** Dimension des icons dans lea barre de menu */
	int ICON_MENUBAR_SECONDAIRE_HAUTEUR = 15;
	int ICON_MENUBAR_SECONDAIRE_LARGEUR = 15;

	// Constante pour centrer la barre de vie
	int PADDING_VIE_CASE_GAUCHE = TAILLE_CARREAU/8;
	int PADDING_VIE_CASE_HAUT = PADDING_VIE_CASE_GAUCHE/2;	
}