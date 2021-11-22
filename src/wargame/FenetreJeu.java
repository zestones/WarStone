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
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setOpaque(true);
		menuBar.setBackground(COULEUR_MENUBAR); // ou Color.GREEN
		menuBar.setPreferredSize(new Dimension(MENUBAR_LARGEUR, MENUBAR_HAUTEUR));
		
		frame.setBackground(COULEUR_INCONNU);
		
		JPanel panel = new JPanel();
		panel = new JPanel();
		panel.setOpaque(true);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setJMenuBar(menuBar);
		
		JButton finTour = new JButton("Fin du Tour");   
		finTour.setSize(BOUTTON_LARGEUR, BOUTTON_HAUTEUR);
		finTour.setVisible(true);
		
		
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
      
        frame.setContentPane(new PanneauJeu(finTour));	
        menuBar.add(finTour);
        menuBar.setLayout(new FlowLayout(FlowLayout.CENTER));
       
        pack();
		frame.setVisible(true); 
	}

	public static void main(String[] args) {
		new FenetreJeu();
	}
}