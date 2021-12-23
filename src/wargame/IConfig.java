package wargame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public interface IConfig extends java.io.Serializable {	
	
	// Definir le NB ELEMENT sur la carte
	int NB_HEROS = 1; int NB_MONSTRES = 6; int NB_OBSTACLES = 3;

	// Parametre de dessin 
	Color COULEUR_VIDE = Color.white, COULEUR_INCONNU = Color.lightGray;
	Color COULEUR_TEXTE = Color.black, COULEUR_MONSTRE = new Color(0, 0, 0, 60);
	Color COULEUR_HEROS = Color.red, COULEUR_HEROS_DEJA_JOUE = new Color(175, 25, 75, 100);
	Color COULEUR_EAU = Color.blue, COULEUR_FORET = Color.green, COULEUR_ROCHER = Color.gray;
	Color COULEUR_GRILLE = Color.black; Color COULEUR_MENUBAR = Color.gray; Color COULEUR_FOOTER = Color.black;
	Color COULEUR_DEPLACEMENT = new Color(75, 25, 75, 100), COULEUR_PORTEE = new Color(20, 25, 25, 100);
	Color COULEUR_VIE_R = Color.red, COULEUR_VIE_V = Color.green;
	Color COULEUR_ENEMIS = new Color(255, 10, 20, 50), COULEUR_AMIS = new Color(20,200,10,50); 
	Color COULEUR_INFOS_PANEL = new Color(88, 41, 0);
	Color COULEUR_BORDURE = Color.white;
	Color COULEUR_FOCUS = new Color(145, 145, 145, 95);
	// Les images 
	Image grass = Toolkit.getDefaultToolkit().getImage("./res/img/background/grass.png");
	Image range = Toolkit.getDefaultToolkit().getImage("./res/img/background/range.jpg");
	Image fleche = Toolkit.getDefaultToolkit().getImage("./res/img/pops/arrow.png");
	
	
//	Dimension taille = Toolkit.getDefaultToolkit().getScreenSize();	
//	(int)taille.getWidth();
//  (int)taille.getHeight();

	// HAUTEUR et LARGEUR de la Fenetre
	final int FEN_LARGEUR = 800;
	final int FEN_HAUTEUR = 600;

	int POSITION_X = 100; int POSITION_Y = 50; 

	// Taille d'une case de la carte
	int NB_PIX_CASE = 100;
	
	// Constante pour centrer la barre de vie
	int PADDING_VIE_CASE_LARGEUR = NB_PIX_CASE/8;
	int PADDING_VIE_CASE = PADDING_VIE_CASE_LARGEUR/2;
		
	
	/* Variable Menu */
	final int MENUBAR_HAUTEUR = 65;
	final int MENUBAR_LARGEUR = FEN_LARGEUR;
	
	/* Variable footer */
	final int FOOTER_HAUTEUR = 30;
	final int FOOTER_LARGEUR = FEN_LARGEUR;
	
	// HAUTEUR et LARGEUR de la carte en NB DE CASE 
	// definit la taille en dehors de la fenetre si trop de case
	int HAUTEUR_CARTE_CASE = (FEN_HAUTEUR / NB_PIX_CASE) + 1;
	int LARGEUR_CARTE_CASE = (FEN_LARGEUR / NB_PIX_CASE);
	
	// On definit la largeur comme etant 1/4 de l'ecran de jeu
	int LARGEUR_INFOS_PANEL = NB_PIX_CASE * (FEN_LARGEUR / NB_PIX_CASE)/4;	
	
	// Hauteur pour le label NB_SOLDAT_RESTANT
	int HAUTEUR_NB_SOLDAT_VIVANT = LARGEUR_INFOS_PANEL/6;
	
	// Taille d'une case dans la miniCarte
	int MINI_NB_PIX_CASE = Math.min((LARGEUR_INFOS_PANEL - LARGEUR_INFOS_PANEL/6) / HAUTEUR_CARTE_CASE, (LARGEUR_INFOS_PANEL - LARGEUR_INFOS_PANEL/4) / LARGEUR_CARTE_CASE);
	
	// Definit la largeur de la minicarte + TAILLE_BORDURE
	int LARGEUR_MINI_CARTE = LARGEUR_CARTE_CASE * MINI_NB_PIX_CASE + 6;
	
	// HAUTEUR de la miniCarte + TAILLE_BORDURE
	int HAUTEUR_MINI_CARTE = HAUTEUR_CARTE_CASE * MINI_NB_PIX_CASE + 6;
		
	// la hauteur = HAUTEUR_FEN - (HAUTEUR_MINI_CARTE - PADDING) - HAUTEUR_NB_SOLDAT_VIE - HAUTEUR_BORDURE
	int HAUTEUR_INFOS_PANEL = FEN_HAUTEUR - HAUTEUR_MINI_CARTE - LARGEUR_INFOS_PANEL/4 - HAUTEUR_NB_SOLDAT_VIVANT - 2;
		
	int PADDING_INFOS_PANEL = MINI_NB_PIX_CASE;

	// HAUTEUR et LARGEUR de la carte
	int LARGEUR_CARTE = FEN_LARGEUR - LARGEUR_INFOS_PANEL;
	int HAUTEUR_CARTE = FEN_HAUTEUR; 

	// HAUTEUR ET LARGEUR des icon dans le menuSecondaire
	int HAUTEUR_ICON_MENU = 15;
	int LARGEUR_ICON_MENU = 15;
	
	// HAUTEUR et LARGEUR des icon cliquer sur la cart
	int HAUTEUR_ICON_ELEMENT = LARGEUR_INFOS_PANEL/3;
	int LARGEUR_ICON_ELEMENT = HAUTEUR_ICON_ELEMENT;
	/* Variable du Boutton */
	final int BOUTTON_HAUTEUR = MENUBAR_HAUTEUR/2;
	final int BOUTTON_LARGEUR = MENUBAR_LARGEUR/6;
	
	int LARGEUR_CASE_VISIBLE = (LARGEUR_CARTE / NB_PIX_CASE);
	int HAUTEUR_CASE_VISIBLE = (HAUTEUR_CARTE / NB_PIX_CASE);
	
	// Parametre dessin de la fenetre
	public static final JFrame frame = new JFrame("WarStone");	
	// MenuBar Principal contenant les bouton
	public static final JMenuBar menuBar = new JMenuBar();
	public static final JLabel footer = new JLabel("", SwingConstants.CENTER);	
	// infos sur les elements clique
	public JLabel iconInfosLabel = new JLabel();
	// Image pour les elements clique
	public JLabel iconLabel = new JLabel();
	// Conteneur de l'img de l'element clique
	public JPanel iconPanel = new JPanel();
	// Panel qui contient les l'icon et les infos sur l'element cliquer
	JPanel infosElementHeader = new JPanel(new BorderLayout());
	// Conteneur principal des infos sur les elements
	public JPanel infosElementPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, PADDING_INFOS_PANEL, PADDING_INFOS_PANEL));

	// Label pour Nombre de soldat restant
	JLabel soldatRestant = new JLabel();	
	// Panel contenant la carte miniature
	JPanel carteMiniature = new JPanel();

}