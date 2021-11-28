package wargame;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Image;
import java.awt.Toolkit;

public interface IConfig extends java.io.Serializable {
	
	// Element de la fenetre partager entre la class PanneauJeu & FenetreJeu
	public static final JFrame frame = new JFrame("WarStone");	
	public static final JMenuBar menuBar = new JMenuBar();
	public static final JLabel footer = new JLabel("", SwingConstants.CENTER);
	public static final JPanel panel = new JPanel();	
	
	int LARGEUR_CARTE = 20; int HAUTEUR_CARTE = 10; // en nombre de cases
	int NB_PIX_CASE = 75;
	int POSITION_X = 100; int POSITION_Y = 50; // Position de la fenêtre
	int NB_HEROS = 6; int NB_MONSTRES = 15; int NB_OBSTACLES = 20;
	
	
	// Parametre de dessin 
	Color COULEUR_VIDE = Color.white, COULEUR_INCONNU = Color.lightGray;
	Color COULEUR_TEXTE = Color.black, COULEUR_MONSTRES = Color.black;
	Color COULEUR_HEROS = Color.red, COULEUR_HEROS_DEJA_JOUE = new Color(175, 25, 75, 100);
	Color COULEUR_EAU = Color.blue, COULEUR_FORET = Color.green, COULEUR_ROCHER = Color.gray;
	Color COULEUR_GRILLE = Color.black; Color COULEUR_MENUBAR = Color.gray; Color COULEUR_FOOTER = Color.black;
	Color COULEUR_DEPLACEMENT = new Color(75, 25, 75, 100); Color COULEUR_PORTEE = new Color(75, 125, 75, 100);
	
	// Les images 
	Image grass = Toolkit.getDefaultToolkit().getImage("./res/img/background/grass.png");
	Image range = Toolkit.getDefaultToolkit().getImage("./res/img/background/range.jpg");
	
	// Chemin des images pour les Heros
	String humain = "./res/img/soldat/heros/humain.png";
	String nain = "./res/img/soldat/heros/nain.png";
	String elf = "./res/img/soldat/heros/elf.png";
	String hobbit = "./res/img/soldat/heros/hobbit.png";
	 
	String sprite = "./res/img/soldat/heros/tank_sprite.png";
	public SpriteEngine spriteEngine = new SpriteEngine(33);
	
	int PADDING_SPRITE = NB_PIX_CASE/2;
	
	final int MAX_FEN_LARGEUR = 500;
	final int MAX_FEN_HAUTEUR = 500;
	final int PADDING_BAS = 41;
	final int PADDING_DROIT = 18;

	/* Variables calculées */
	final int FEN_LARGEUR = LARGEUR_CARTE * NB_PIX_CASE + PADDING_DROIT;
	
	/* Variable Menu */
	final int MENUBAR_HAUTEUR = 35;
	final int MENUBAR_LARGEUR = FEN_LARGEUR;
	
	/* Variable footer */
	final int FOOTER_HAUTEUR = 25;
	final int FOOTER_LARGEUR = FEN_LARGEUR;
	final int FEN_HAUTEUR = HAUTEUR_CARTE * NB_PIX_CASE + PADDING_BAS + MENUBAR_HAUTEUR + FOOTER_HAUTEUR;
	
	
	/* Variable du Boutton */
	final int BOUTTON_HAUTEUR = MENUBAR_HAUTEUR/2;
	final int BOUTTON_LARGEUR = MENUBAR_LARGEUR/6;
			

}