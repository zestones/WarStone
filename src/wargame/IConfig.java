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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * The Interface IConfig.
 */
public interface IConfig extends java.io.Serializable {	
	
	/** The nb heros. */
	// Definir le NB ELEMENT sur la carte
	int NB_HEROS = 5; 
	/** The nb monstres. */
	int NB_MONSTRES = 6; 
	/** The nb obstacles. */
	int NB_OBSTACLES = 4;

	/** The couleur inconnu. */
	// Parametre de dessin 
	Color COULEUR_VIDE = Color.white, COULEUR_INCONNU = Color.lightGray;
	
	/** The couleur monstre. */
	Color COULEUR_TEXTE = Color.black, COULEUR_MONSTRE = new Color(0, 0, 0, 60);
	
	/** The couleur heros deja joue. */
	Color COULEUR_HEROS = Color.red, COULEUR_HEROS_DEJA_JOUE = new Color(175, 25, 75, 100);
	
	/** The couleur rocher. */
	Color COULEUR_EAU = Color.blue, COULEUR_FORET = Color.green, COULEUR_ROCHER = Color.gray;
	
	/** The couleur grille. */
	Color COULEUR_GRILLE = Color.black; 
	/** The couleur menubar. */
	Color COULEUR_MENUBAR = Color.gray; 
	/** The couleur footer. */
	Color COULEUR_FOOTER = Color.black;
	
	/** The couleur portee. */
	Color COULEUR_DEPLACEMENT = new Color(75, 25, 75, 100), COULEUR_PORTEE = new Color(20, 25, 25, 100);
	
	/** The couleur vie v. */
	Color COULEUR_VIE_R = Color.red, COULEUR_VIE_V = Color.green;
	
	/** The couleur amis. */
	Color COULEUR_ENEMIS = new Color(255, 10, 20, 50), COULEUR_AMIS = new Color(20,200,10,50); 
	
	/** The couleur infos panel. */
	Color COULEUR_INFOS_PANEL = new Color(88, 41, 0);
	
	/** The couleur bordure. */
	Color COULEUR_BORDURE = Color.white;
	
	/** The couleur focus. */
	Color COULEUR_FOCUS = new Color(145, 145, 145, 95);
	
	/** The grass. */
	// Les images 
	Image grass = Toolkit.getDefaultToolkit().getImage("./res/img/background/jeu/grass.png");
	
	/** The range. */
	Image range = Toolkit.getDefaultToolkit().getImage("./res/img/background/jeu/range.jpg");
	
	/** The fleche. */
	Image fleche = Toolkit.getDefaultToolkit().getImage("./res/img/pops/arrow.png");
	
	Dimension taille = Toolkit.getDefaultToolkit().getScreenSize();	
//	(int)taille.getWidth();
//	(int)taille.getHeight();
	
	/** The fen largeur. */
	// HAUTEUR et LARGEUR de la Fenetre
	final int FEN_LARGEUR = 1000;
	
	/** The fen hauteur. */
	final int FEN_HAUTEUR = 800;

	/** The position x. */
	int POSITION_X = 100; 
	/** The position y. */
	int POSITION_Y = 50; 

	/** The nb pix case. */
	// Taille d'une case de la carte
	int NB_PIX_CASE = 100;
	
	/** The padding vie case largeur. */
	// Constante pour centrer la barre de vie
	int PADDING_VIE_CASE_LARGEUR = NB_PIX_CASE/8;
	
	/** The padding vie case. */
	int PADDING_VIE_CASE = PADDING_VIE_CASE_LARGEUR/2;
		
	/** The menubar hauteur. */
	/* Variable Menu */
	final int MENUBAR_HAUTEUR = 65;
	
	/** The menubar largeur. */
	final int MENUBAR_LARGEUR = FEN_LARGEUR;
	
	/** The footer hauteur. */
	/* Variable footer */
	final int FOOTER_HAUTEUR = 30;
	
	/** The footer largeur. */
	final int FOOTER_LARGEUR = FEN_LARGEUR;
	
	// HAUTEUR et LARGEUR de la carte en NB DE CASE 
	/** The hauteur carte case. */
	// definit la taille en dehors de la fenetre si trop de case
	int HAUTEUR_CARTE_CASE = (FEN_HAUTEUR / NB_PIX_CASE) + 1;
	
	/** The largeur carte case. */
	int LARGEUR_CARTE_CASE = (FEN_LARGEUR / NB_PIX_CASE);
	
	/** The largeur infos panel. */
	// On definit la largeur comme etant 1/4 de l'ecran de jeu
	int LARGEUR_INFOS_PANEL = NB_PIX_CASE * LARGEUR_CARTE_CASE/4;	
	
	/** The hauteur nb soldat vivant. */
	// Hauteur pour le label NB_SOLDAT_RESTANT
	int HAUTEUR_NB_SOLDAT_VIVANT = LARGEUR_INFOS_PANEL/6;
	
	int PADDING_LARGEUR_MINI_CARTE = LARGEUR_INFOS_PANEL/16;
	int PADDING_HAUTEUR_MINI_CARTE = PADDING_LARGEUR_MINI_CARTE;
	/** The mini nb pix case. */
	// Taille d'une case dans la miniCarte
	int MINI_NB_PIX_CASE = Math.min((LARGEUR_INFOS_PANEL - LARGEUR_INFOS_PANEL/12) / HAUTEUR_CARTE_CASE, (LARGEUR_INFOS_PANEL - LARGEUR_INFOS_PANEL/8) / LARGEUR_CARTE_CASE);
	
	/** The largeur mini carte. */
	// Definit la largeur de la minicarte + TAILLE_BORDURE
	int LARGEUR_MINI_CARTE = LARGEUR_CARTE_CASE * MINI_NB_PIX_CASE + 6;
	
	/** The hauteur mini carte. */
	// HAUTEUR de la miniCarte + TAILLE_BORDURE
	int HAUTEUR_MINI_CARTE = HAUTEUR_CARTE_CASE * MINI_NB_PIX_CASE + 6;
		
	/** The hauteur infos panel. */
	// la hauteur = HAUTEUR_FEN - (HAUTEUR_MINI_CARTE - PADDING) - HAUTEUR_NB_SOLDAT_VIE - HAUTEUR_BORDURE
	int HAUTEUR_INFOS_PANEL = FEN_HAUTEUR - HAUTEUR_MINI_CARTE - LARGEUR_INFOS_PANEL/4 - HAUTEUR_NB_SOLDAT_VIVANT - 2;
		
	/** The padding infos panel. */
	int PADDING_INFOS_PANEL = MINI_NB_PIX_CASE;

	/** The largeur carte. */
	// HAUTEUR et LARGEUR de la carte
	int LARGEUR_CARTE = FEN_LARGEUR - LARGEUR_INFOS_PANEL;
	
	/** The hauteur carte. */
	int HAUTEUR_CARTE = FEN_HAUTEUR; 

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
	
	/** The boutton hauteur. */
	/* Variable du Boutton */
	final int BOUTTON_HAUTEUR = MENUBAR_HAUTEUR/2;
	
	/** The boutton largeur. */
	final int BOUTTON_LARGEUR = MENUBAR_LARGEUR/6;
	
	/** The largeur case visible. */
	int LARGEUR_CASE_VISIBLE = (LARGEUR_CARTE / NB_PIX_CASE);
	
	/** The hauteur case visible. */
	int HAUTEUR_CASE_VISIBLE = (HAUTEUR_CARTE / NB_PIX_CASE);
	
	/** The Constant frame. */
	// Parametre dessin de la fenetre
	public static final JFrame frame = new JFrame("WarStone");	
	
	/** The Constant menuBar. */
	// MenuBar Principal contenant les bouton
	public static final JMenuBar menuBar = new JMenuBar();
	
	/** The Constant footer. */
	public static final JLabel footer = new JLabel("", SwingConstants.CENTER);	
	
	/** The icon infos label. */
	// infos sur les elements clique
	public JLabel iconInfosLabel = new JLabel();
	
	/** The icon label. */
	// Image pour les elements clique
	public JLabel iconLabel = new JLabel();
	
	/** The icon panel. */
	// Conteneur de l'img de l'element clique
	public JPanel iconPanel = new JPanel();
	
	/** The infos element header. */
	// Panel qui contient les l'icon et les infos sur l'element cliquer
	JPanel infosElementHeader = new JPanel(new BorderLayout());
	
	/** The infos element panel. */
	// Conteneur principal des infos sur les elements
	public JPanel infosElementPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, PADDING_INFOS_PANEL, PADDING_INFOS_PANEL));

	/** The soldat restant. */
	// Label pour Nombre de soldat restant
	JLabel soldatRestant = new JLabel();	
	
	/** The carte miniature. */
	// Panel contenant la carte miniature
	JPanel carteMiniature = new JPanel();

}