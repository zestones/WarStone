package wargame;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;  

public class FenetreJeu extends JFrame implements IConfig{
	private static final long serialVersionUID = 1L;
	
	FenetreJeu(){
		JFrame fenetre = new JFrame();

	    fenetre.setTitle("WarStone");
	    fenetre.setSize(FEN_LARGEUR,FEN_HAUTEUR);	
	    fenetre.setLocation(POSITION_X, POSITION_Y);
	    
	    JMenuBar menuBar = new JMenuBar();
	    
		menuBar.setOpaque(true);
		menuBar.setBackground(Color.red);
		menuBar.setPreferredSize(new Dimension(MENUBAR_LARGEUR, MENUBAR_HAUTEUR));
		
		JButton tour = new JButton();
		
		menuBar.add(tour);
		tour.setSize(BOUTTON_LARGEUR,BOUTTON_HAUTEUR);
		tour.setVisible(true);
		tour.setText("Fin du tour");
		menuBar.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		// Evenement a effectuer lorsque le bouton est clique
		tour.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				System.out.println("Hello tu a finit ton tour !");
			}  
		});  
		
		fenetre.setJMenuBar(menuBar);

	    JPanel panel = new JPanel();
	    fenetre.setBackground(COULEUR_INCONNU);
	    add(panel, BorderLayout.CENTER);
	    fenetre.setContentPane(new PanneauJeu());               
	    
	    fenetre.setVisible(true);		
	}

	public static void main(String[] args) {
		new FenetreJeu();		
	}
}
