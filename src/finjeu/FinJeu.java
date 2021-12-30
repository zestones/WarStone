package finjeu;

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
import music.SoundLauncher;


public class FinJeu implements IFenetre {
	private static final long serialVersionUID = 1L;
	
	private static final String gameOverImg = background;
	private static final String youWinImg = background;
	
	public FinJeu(int nombreHeros, int nombreMonstre) {		
		String fond;
		gameMusic.clip.stop();
		// On suprime tout le contenu
		panelPrincipal.removeAll();
		panelPrincipal.revalidate();
		
		header.removeAll();
		fleche.removeAll();
		// On supprime le panneau que l'on va remplacer
		frame.remove(panelPrincipal);
		
		JPanel panelOver = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		panelOver.setOpaque(false);	
		
		BufferedImage backgroundImg = null;
		
		if(nombreHeros == 0) {
			SoundLauncher gameOver = new SoundLauncher("gameOver.wav");
			fond = gameOverImg;
			gameOver.clip.start();
			gameOver.clip.loop(0);
		}
		else {
			SoundLauncher youWin = new SoundLauncher("youWin.wav");
			youWin.clip.start();
			youWin.clip.loop(0);
			fond = youWinImg;
		}
		
		try {
			backgroundImg = ImageIO.read(new File(fond));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		JLabel backgroundLabel = new JLabel();
		Image img = backgroundImg.getScaledInstance(FEN_LARGEUR, FEN_HAUTEUR, Image.SCALE_SMOOTH);
		
		backgroundLabel.setIcon(new ImageIcon(img));	
		panelOver.add(backgroundLabel);	
						
		frame.add(backMenu); 
        frame.add(newGame); 
          
        newGame.setBouttonImage("new game");      
        backMenu.setBouttonImage("menu");      
        
        frame.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			backMenu.unsetHoverBoutton();
    		}	
		});
        
        backMenu.addMouseMotionListener(new MouseAdapter() {
        	public void mouseMoved(MouseEvent e) {
        		backMenu.hoverBoutton();
    		}	
		});
        
    	backMenu.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  	
				// On suprime tout le contenu
				panelMenu.removeAll();
				panelMenu.revalidate();
				
				// On supprime le panneau que l'on va remplacer
				frame.remove(panelOver);
				removeBoutton();
				
				musicBoutton.removeAll();
				musicBoutton.revalidate();
				musicBoutton.setBouttonImage("unmute");
				MenuEvent.estMusicActif = true;
				menuMusic.clip.start();
				// Creation du menu
				MenuJeu.initMenuJeu();
				frame.repaint();
			} 	 
		});  
    	
    	panelMenu.add(panelOver);
    	frame.add(panelMenu);
    	
    	frame.setVisible(true);
    	frame.repaint();
		
	}
	
	private void removeBoutton() {
		frame.remove(backMenu);
		frame.remove(newGame);
	}
	
}
