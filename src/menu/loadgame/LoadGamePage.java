package menu.loadgame;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import menu.IMenu;
import utile.Boutton;

/**
 * Class LoadGamePage.
 * 
 * Cree la page contenant les sauvegardes
 * 
 */
public class LoadGamePage extends JPanel implements ISauvegarde, IMenu {
	
	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Boolean permettant de savoir si la cette page a deja ete cree. */
	private static boolean premiereFois = true;
	
	/**
	 * Instancie une nouvelle page loadGame.
	 */
	public LoadGamePage() {
		/** Creation d'un panel principal */
		panelLoadGame.setPreferredSize(new Dimension(MENU_LARGEUR, MENU_HAUTEUR));
		
	
		BufferedImage backgroundImg = null;
		try {
			backgroundImg = ImageIO.read(new File(backgroundMenu));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		/** Creation d'un label pour deposer l'image */		
		JLabel backgroundLabel = new JLabel();
		Image img = backgroundImg.getScaledInstance(MENU_LARGEUR, MENU_HAUTEUR, Image.SCALE_SMOOTH);
		
		/** Ajout de l'image au label */
		backgroundLabel.setIcon(new ImageIcon(img));	
		/** Ajout du label au panel */
		panelLoadGame.add(backgroundLabel);		
		
		/** initialisation de la liste des sauvegardes */
		initListeSauvegarde();
		/** Supression de la liste des bouttons */
		listeBoutton.clear();
		
		/** Ajout des bouttons a la frame */
		frame.add(deleteSave);
		frame.add(back);
		
		int i = 0;
		/** Nombre de boutton est repasser a 0 pour avoir la position y calculer dans l'interface */
		Boutton.NOMBRE_BOUTTON = 0;
		/** Pour chaque sauvegarde on creer un boutton que l'on met dans une liste de bouttons */
		for(String save : listeSauvegarde) {
			listeBoutton.add(new Boutton(BOUTTON_POSITION_X, BOUTTON_POSITION_Y, LARGEUR_BOUTTON, HAUTEUR_BOUTTON));
			listeBoutton.get(i).setBouttonText(save.replace(chemin, ""));
			frame.add(listeBoutton.get(i));
			i++;
		}
		
		/** Ajout d'une image / couleur au boutton */
		deleteSave.setBouttonImage("deleteOff");
		deleteSave.unsetHoverBoutton(COULEUR_DELETE);
		/** Ajout de text / couleur au boutton  */
		back.setBouttonText("BACK");
		back.hoverBoutton(COULEUR_BOUTTON_MENU);		
		
		/** Creation des listeners des bouttons delete et back une seule fois */
		if(premiereFois) {
			new LoadGameEvent();		
			premiereFois = false;
		}
		/**
		 *  Creation des listeners des bouttons de sauvegarde a chaque fois que l'on va sur cette page
		 *  la liste est supprime et recree il faut donc recree les listeners des bouttons
		 */
		new SauvegardeEvent();
		
		frame.add(panelLoadGame);
	}
	
	/**
	 * Inits the liste sauvegarde.
	 */
	public static void initListeSauvegarde() {
		/** On vide la liste avant de la remplir */
		listeSauvegarde.clear();

		File[] fichiers = new File(chemin).listFiles();
		
		/** On ajoute chaque fichiers trouver dans le dossier a la liste de sauvegarde */
		for(File monFichier : fichiers) {
		    if (monFichier.isFile()) {
		        listeSauvegarde.add(chemin + monFichier.getName());
		    }
		}
	}
}
