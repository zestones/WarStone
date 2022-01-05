package fenetrejeu.finjeu;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fenetrejeu.IFenetre;
import menu.MenuEvent;
import menu.MenuJeu;
import musique.Musique;


/**
 * Class FinJeu.
 */
public class FinJeu implements IFenetre {
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Chemin gameOverImg. */
	private static final String gameOverImg = "./res/img/background/menu/youLose.png";
	
	/** Chemin youWinImg. */
	private static final String youWinImg = "./res/img/background/menu/youWin.png";
	
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
		
		JPanel panelFinJeu = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		panelFinJeu.setOpaque(false);	
		
		BufferedImage fondEcranImg = null;
		
		if(nombreHeros == 0) {
			Musique gameOver = new Musique("gameOver.wav");
			fond = gameOverImg;
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
        
        frame.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			retourMenu.unsetHoverBouton(COULEUR_BOUTON_MENU);
    		}	
		});
        
        retourMenu.addMouseMotionListener(new MouseAdapter() {
        	public void mouseMoved(MouseEvent e) {
        		retourMenu.hoverBouton(COULEUR_BOUTON_HOVER_MENU);
    		}	
		});
        
    	retourMenu.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  	
				// On suprime tout le contenu
				panelMenu.removeAll();
				panelMenu.revalidate();
				// On supprime le boutton fin de tour pour qu'il n'aparaisse pas si une config est lancer
				menuBar.remove(finTour);	
				menuBar.remove(jouer);
				// On supprime le panneau que l'on va remplacer
				frame.remove(panelFinJeu);
				removeBoutton();
				// On relance la music du menu
				menuMusic.clip.start();
				// Creation du menu
				MenuJeu.initMenuJeu();
				frame.repaint();
			} 	 
		});  
    	
    	panelMenu.add(panelFinJeu);
    	frame.add(panelMenu);
    	
    	frame.setVisible(true);
    	frame.repaint();
		
	}
	
	/**
	 * Suppresion des bouttons.
	 */
	private void removeBoutton() {
		frame.remove(retourMenu);
		frame.remove(newGame);
		musiqueBouton.removeAll();
		musiqueBouton.revalidate();
		musiqueBouton.setBoutonImage("unmute");
		MenuEvent.estMusicActif = true;
	}
	
}
