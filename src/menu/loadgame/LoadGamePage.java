package menu.loadgame;

import java.awt.Dimension;
import java.awt.FlowLayout;
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

public class LoadGamePage extends JPanel implements ISauvegarde, IMenu {
	private static final long serialVersionUID = 1L;
	private static boolean premiereFois = true;
	
	public LoadGamePage() {
		panelLoadGame.setPreferredSize(new Dimension(MENU_LARGEUR, MENU_HAUTEUR));
		
		JPanel saveContainer = new JPanel(new FlowLayout());
		saveContainer.setOpaque(false);	
				
		BufferedImage backgroundImg = null;
		try {
			backgroundImg = ImageIO.read(new File(backgroundMenu));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		JLabel backgroundLabel = new JLabel();
		Image img = backgroundImg.getScaledInstance(MENU_LARGEUR, MENU_HAUTEUR, Image.SCALE_SMOOTH);
		
		backgroundLabel.setIcon(new ImageIcon(img));	
		saveContainer.add(backgroundLabel);		
		
		initListeSauvegarde();
		listeBoutton.clear();
		
		frame.add(deleteSave);
		frame.add(back);
		
		int i = 0;
		Boutton.NOMBRE_BOUTTON = 0;
		for(String save : listeSauvegarde) {
			listeBoutton.add(new Boutton(BOUTTON_POSITION_X, BOUTTON_POSITION_Y, LARGEUR_BOUTTON, HAUTEUR_BOUTTON));
			listeBoutton.get(i).setBouttonText(save.replace(chemin, ""));
			frame.add(listeBoutton.get(i));
			i++;
		}
		
		deleteSave.setBouttonImage("deleteOff");
		deleteSave.unsetHoverBoutton(COULEUR_DELETE);
		
		back.setBouttonText("BACK");
		back.hoverBoutton(COULEUR_BOUTTON_MENU);		
		
		// On creer les evenements lors de la premiere visite de la page uniquement
		if(premiereFois) {
			new LoadGameEvent();		
			premiereFois = false;
		}
		
		new SauvegardeEvent();
		
		panelLoadGame.add(saveContainer);		
		frame.add(panelLoadGame);
	}
	public static void initListeSauvegarde() {
		// On vide la liste
		listeSauvegarde.clear();

		File[] fichiers = new File(chemin).listFiles();

		for(File monFichier : fichiers) {
		    if (monFichier.isFile()) {
		        listeSauvegarde.add(chemin + monFichier.getName());
		    }
		}
	}
}
