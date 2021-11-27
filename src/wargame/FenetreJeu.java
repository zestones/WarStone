package wargame;

import java.awt.*;
import javax.swing.*;

public class FenetreJeu extends JFrame implements IConfig{
	private static final long serialVersionUID = 1L;
	
	FenetreJeu(){
		// Creer la JFrame
		JFrame frame = new JFrame("WarStone");	
		frame.setSize(FEN_LARGEUR, FEN_HAUTEUR);	
		frame.setLocation(POSITION_X, POSITION_Y);
		
		menuBar.setOpaque(true);
		menuBar.setBackground(COULEUR_MENUBAR); 
		menuBar.setPreferredSize(new Dimension(MENUBAR_LARGEUR, MENUBAR_HAUTEUR));
		
		frame.setBackground(COULEUR_INCONNU);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(FEN_LARGEUR, FEN_HAUTEUR));

		panel.setLayout(new BorderLayout());
		panel.setOpaque(true);	    
	
		panel.add(footer, BorderLayout.SOUTH);		
		
        
		frame.setJMenuBar(menuBar);
		
		panel.add(new PanneauJeu());	
		frame.getContentPane().add(panel);
        frame.add(panel);
        
        menuBar.setLayout(new FlowLayout(FlowLayout.CENTER));        
        
        pack();
		frame.setVisible(true); 	
	}

	public static void main(String[] args) {
		new FenetreJeu();
	}
}