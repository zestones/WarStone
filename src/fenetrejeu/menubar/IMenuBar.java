package fenetrejeu.menubar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicArrowButton;

import utile.Bouton;
import wargame.IConfig;

public interface IMenuBar extends IConfig {
	
	JButton finTour = Bouton.setBoutonStyle("Fin Tour");; 
	JButton jouer =  Bouton.setBoutonStyle("Jouer"); 
	JButton sauvegarde = Bouton.setBoutonStyle("Sauvegarder");   
	JButton recommencer = Bouton.setBoutonStyle("Recommencer");;   
	JButton menu = Bouton.setBoutonStyle("Menu");;

	// MenuBar Principal contenant les bouton
	JPanel menuBar = new JPanel();
	
	JButton cameraBas = new BasicArrowButton(BasicArrowButton.SOUTH);
	JButton cameraHaut = new BasicArrowButton(BasicArrowButton.NORTH);
	JButton cameraGauche = new BasicArrowButton(BasicArrowButton.WEST);
	JButton cameraDroite = new BasicArrowButton(BasicArrowButton.EAST); 
	
	JCheckBox musicOn = new JCheckBox("Music On/Off"); 

	/** Variable du menuBar dans le PanneauJeu */
	final int MENUBAR_LARGEUR = CARTE_LARGEUR;
	int BOUTTON_HAUTEUR = MENUBAR_HAUTEUR/2;
	int BOUTTON_LARGEUR = MENUBAR_LARGEUR/6;
}
