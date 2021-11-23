package wargame;

import java.awt.*;
import javax.swing.*;

public class FenetreJeu extends JFrame implements IConfig{
	private static final long serialVersionUID = 1L;
	public JLabel footer;
	FenetreJeu(){
		// Creer la JFrame
		JFrame frame = new JFrame("WarStone");	
		frame.setSize(FEN_LARGEUR, FEN_HAUTEUR);	
		frame.setLocation(POSITION_X, POSITION_Y);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setOpaque(true);
		menuBar.setBackground(COULEUR_MENUBAR); 
		menuBar.setPreferredSize(new Dimension(MENUBAR_LARGEUR, MENUBAR_HAUTEUR));
		
		JLabel label = new JLabel("Tout la Haut", SwingConstants.CENTER); 
		label.setBackground(COULEUR_MENUBAR);
		label.setOpaque(true); 
		
		frame.setBackground(COULEUR_INCONNU);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(FEN_LARGEUR, FEN_HAUTEUR));

		panel.setLayout(new BorderLayout());
		panel.setOpaque(true);	    
	
		JLabel footer = new JLabel("", SwingConstants.CENTER); 
		panel.add(footer, BorderLayout.SOUTH);
		footer.setBackground(COULEUR_FOOTER);
		footer.setPreferredSize(new Dimension(FOOTER_LARGEUR, FOOTER_HAUTEUR));
		footer.setOpaque(true); //Test background
	
		frame.setJMenuBar(menuBar);
		
		JButton finTour = new JButton("Fin du Tour");   
		finTour.setSize(BOUTTON_LARGEUR, BOUTTON_HAUTEUR);
		finTour.setVisible(true);
		
		panel.add(new PanneauJeu(finTour, footer, label));	
		frame.getContentPane().add(panel);
        frame.add(panel);
        
       
        menuBar.add(finTour);
        menuBar.add(label);
        menuBar.setLayout(new FlowLayout(FlowLayout.CENTER));        
        
        pack();
		frame.setVisible(true); 	
	}

	public static void main(String[] args) {
		new FenetreJeu();
	}
}