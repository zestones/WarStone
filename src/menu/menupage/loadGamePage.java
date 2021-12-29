package menu.menupage;

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

public class loadGamePage extends JPanel implements ISauvegarde, IMenu {
	private static final long serialVersionUID = 1L;
	
	public loadGamePage() {
		
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
		
		File[] fichiers = new File(chemin).listFiles();

		for(File monFichier : fichiers) {
		    if (monFichier.isFile()) {
		        listeSauvegarde.add(chemin + monFichier.getName());
		    }
		}
		
		int i = 0;
		Boutton.NOMBRE_BOUTTON = 0;
		for(String save : listeSauvegarde) {
			listeBoutton.add(new Boutton(BOUTTON_POSITION_X - BOUTTON_POSITION_X/4, 0, LARGEUR_BOUTTON*2, HAUTEUR_BOUTTON));
			listeBoutton.get(i).setBouttonText(save.replace(chemin, ""));
			frame.add(listeBoutton.get(i));
			i++;
		}
		
		new loadGameEvent();
		
		panelLoadGame.add(saveContainer);		
		frame.add(panelLoadGame);
	}
}
