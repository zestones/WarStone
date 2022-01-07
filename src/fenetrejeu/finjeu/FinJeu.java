package fenetrejeu.finjeu;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import fenetrejeu.IFenetre;
import musique.Musique;


/**
 * Class FinJeu.
 */
public class FinJeu implements IFenetre, IFinJeu {
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	protected static boolean eventDejaCree = false;
	
	/**
	 * Instancie une nouvelle page fin jeu.
	 *
	 * @param nombreHeros 
	 * @param nombreMonstre 
	 */
	public FinJeu(int nombreHeros, int nombreMonstre) {		
		String fond;
		gameMusic.clip.stop();
		// On suprime tout le contenu
		panelPrincipal.removeAll();
		panelPrincipal.revalidate();
		
		headerPanel.removeAll();
		flecheMiniCartePanel.removeAll();
		// On supprime le panneau que l'on va remplacer
		frame.remove(panelPrincipal);
		
		panelFinJeu.setOpaque(false);	
		
		BufferedImage fondEcranImg = null;
		
		// Chargement de la bande son et du fond ecran en fonction que l'on a gagne ou perdu
		if(nombreHeros == 0) {
			Musique gameOver = new Musique("gameOver.wav");
			fond = youLooseImg;
			gameOver.clip.start();
			gameOver.clip.loop(0);
		}
		else {
			Musique youWin = new Musique("youWin.wav");
			youWin.clip.start();
			youWin.clip.loop(0);
			fond = youWinImg;
		}
		
		try {
			fondEcranImg = ImageIO.read(new File(fond));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		JLabel fondEcranLabel = new JLabel();
		Image img = fondEcranImg.getScaledInstance(FEN_LARGEUR, FEN_HAUTEUR, Image.SCALE_SMOOTH);
		
		fondEcranLabel.setIcon(new ImageIcon(img));	
		panelFinJeu.add(fondEcranLabel);	
						
		frame.add(retourMenu); 
        frame.add(newGame); 
          
        newGame.setBoutonImage("new game");      
        retourMenu.setBoutonImage("menu");      
       
        // Si les listeners on deja ete cree on ne le recree pas
        if(!eventDejaCree)
        	new FinJeuEvent();	
        
    	panelMenu.add(panelFinJeu);
    	frame.add(panelMenu);
    	
    	frame.setVisible(true);
    	frame.repaint();
	}
}
