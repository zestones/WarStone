package fenetrejeu.menubar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicArrowButton;

import fenetrejeu.panneaujeu.IConfig;
import utile.style.Bouton;
import utile.style.CheckBox;

public interface IMenuBar extends IConfig {
	
	JButton finTour = Bouton.setButtonStyle("Fin Tour");
	JButton jouer =  Bouton.setButtonStyle("Jouer"); 
	JButton sauvegarde = Bouton.setButtonStyle("Sauvegarder");   
	JButton recommencer = Bouton.setButtonStyle("Recommencer");
	JButton menu = Bouton.setButtonStyle("Menu");
	JButton obstacle = Bouton.setButtonStyle("Obstacle");
	JButton heros = Bouton.setButtonStyle("Heros");
	
	// MenuBar Principal contenant les bouton
	JPanel menuBar = new JPanel();
	
	JButton cameraBas = new BasicArrowButton(BasicArrowButton.SOUTH);
	JButton cameraHaut = new BasicArrowButton(BasicArrowButton.NORTH);
	JButton cameraGauche = new BasicArrowButton(BasicArrowButton.WEST);
	JButton cameraDroite = new BasicArrowButton(BasicArrowButton.EAST); 
	
	JCheckBox musicOn = CheckBox.setCheckBoxStyle("Music On/Off"); 

	/** Variable du menuBar dans le PanneauJeu */
	final int MENUBAR_LARGEUR = CARTE_LARGEUR;
	int BOUTTON_HAUTEUR = MENUBAR_HAUTEUR/2;
	int BOUTTON_LARGEUR = MENUBAR_LARGEUR/6;
}
