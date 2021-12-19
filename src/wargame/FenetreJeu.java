package wargame;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.BorderLayout;

public class FenetreJeu extends JFrame implements IConfig{
	private static final long serialVersionUID = 1L;
	
	FenetreJeu(){
		frame.setSize(FEN_LARGEUR, FEN_HAUTEUR);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Pour mettre la fenetre en pleine ecran
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(false);
		
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