package fenetrejeu.menubar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicArrowButton;

import utile.Boutton;
import wargame.IConfig;

public interface IMenuBar extends IConfig {
	
	JButton finTour = Boutton.setBouttonStyle("Fin Tour");; 
	JButton play =  Boutton.setBouttonStyle("play"); 
	JButton sauvegarde = Boutton.setBouttonStyle("Save");   
	JButton restart = Boutton.setBouttonStyle("ReStart");;   
	JButton menu = Boutton.setBouttonStyle("Menu");;

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
