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

public interface IConfig extends java.io.Serializable {	
	
	/* A changer pour mettre en pleine ecran */
	Dimension taille = Toolkit.getDefaultToolkit().getScreenSize();
	
//	int LARGEUR_CARTE = (int)taille.getWidth();
//	int HAUTEUR_CARTE = (int)taille.getHeight();
	
	int LARGEUR_CARTE = 1200;
	int HAUTEUR_CARTE = 800;
	
	int NB_PIX_CASE = 100;

	int HAUTEUR_ICON = 15;
	int LARGEUR_ICON = 15;
	
	
	int POSITION_X = 100; int POSITION_Y = 50; // Position de la fenêtre
	int NB_HEROS = 10; int NB_MONSTRES = 2; int NB_OBSTACLES = 20;

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
	// Les images 
	Image grass = Toolkit.getDefaultToolkit().getImage("./res/img/background/grass.png");
	Image range = Toolkit.getDefaultToolkit().getImage("./res/img/background/range.jpg");
	Image fleche = Toolkit.getDefaultToolkit().getImage("./res/img/pops/arrow.png");
	
	// Constante pour centrer la barre de vie
	int PADDING_VIE_CASE_LARGEUR = NB_PIX_CASE/8;
	int PADDING_VIE_CASE = PADDING_VIE_CASE_LARGEUR/2;
	
	final int MAX_FEN_LARGEUR = 500;
	final int MAX_FEN_HAUTEUR = 500;
	final int PADDING_BAS = 41;
	final int PADDING_DROIT = 18;

	/* Variables calculées */
	final int FEN_LARGEUR = LARGEUR_CARTE;
	
	/* Variable Menu */
	final int MENUBAR_HAUTEUR = 35;
	final int MENUBAR_LARGEUR = FEN_LARGEUR;
	
	/* Variable footer */
	final int FOOTER_HAUTEUR = 30;
	final int FOOTER_LARGEUR = FEN_LARGEUR;
	
	final int FEN_HAUTEUR = HAUTEUR_CARTE - FOOTER_HAUTEUR;
	
	int HAUTEUR_CARTE_CASE = (FEN_HAUTEUR / NB_PIX_CASE) + 1;
	int LARGEUR_CARTE_CASE = FEN_LARGEUR / NB_PIX_CASE;

	int LARGEUR_INFOS_PANEL = NB_PIX_CASE * (FEN_LARGEUR / NB_PIX_CASE)/4;	
	int HAUTEUR_INFOS_PANEL = FEN_HAUTEUR ;
	
	int LARGEUR_MINI_CARTE = LARGEUR_INFOS_PANEL - LARGEUR_INFOS_PANEL/4;
	int HAUTEUR_MINI_CARTE = NB_PIX_CASE * 2;
	
	int MINI_NB_PIX_CASE = NB_PIX_CASE/3;
	
	/* Variable du Boutton */
	final int BOUTTON_HAUTEUR = MENUBAR_HAUTEUR/2;
	final int BOUTTON_LARGEUR = MENUBAR_LARGEUR/6;	




	// Element de la fenetre partager entre la class PanneauJeu & FenetreJeu
	public static final JFrame frame = new JFrame("WarStone");	
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
	public JPanel infosElementPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, MINI_NB_PIX_CASE, MINI_NB_PIX_CASE));

	// Label pour Nombre de soldat restant
	JLabel soldatRestant = new JLabel();	
	// Panel contenant la carte miniature
	JPanel carteMiniature = new JPanel();
		


}