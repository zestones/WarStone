package wargame;

import java.awt.*;
import javax.swing.*;

public class FenetreJeu extends JFrame implements IConfig{
	private static final long serialVersionUID = 1L;
	
	FenetreJeu(){
		// Creer la JFrame
		JFrame frame = new JFrame("WarStone");
		setTitle("WarStone");
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setOpaque(true);
		menuBar.setBackground(new Color(200, 225, 150)); // ou Color.GREEN
		menuBar.setPreferredSize(new Dimension(MENUBAR_LARGEUR, MENUBAR_HAUTEUR));
		
		JPanel panel = new JPanel();
		setBackground(COULEUR_INCONNU);
		add(panel, BorderLayout.CENTER);
		
		
		setSize(FEN_LARGEUR,FEN_HAUTEUR);	
		setLocation(POSITION_X, POSITION_Y);
		
		setJMenuBar(menuBar);
		
		// Affichage
		frame.pack(); 
		setVisible(true);	
	}

	public static void main(String[] args) {
		FenetreJeu fenetre = new FenetreJeu();
		
		PanneauJeu panneau = new PanneauJeu();
		fenetre.setContentPane(panneau) ;
		
	}
}
