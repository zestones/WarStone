package fenetrejeu.menubar;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.plaf.basic.BasicArrowButton;

import wargame.IConfig;

public interface IMenuBar extends IConfig {
	
	JButton finTour = new JButton("End Turn"); 
	JButton sauvegarde = new JButton("Save");   
	JButton restart = new JButton("ReStart");   
	JButton menu = new JButton("Menu");

	// MenuBar Principal contenant les bouton
	JMenuBar menuBar = new JMenuBar();
	
	JButton cameraBas = new BasicArrowButton(BasicArrowButton.SOUTH);
	JButton cameraHaut = new BasicArrowButton(BasicArrowButton.NORTH);
	JButton cameraGauche = new BasicArrowButton(BasicArrowButton.WEST);
	JButton cameraDroite = new BasicArrowButton(BasicArrowButton.EAST); 

	/** Variable du menuBar dans le PanneauJeu */
	final int MENUBAR_LARGEUR = LARGEUR_CARTE;
	final int BOUTTON_HAUTEUR = MENUBAR_HAUTEUR/2;
	final int BOUTTON_LARGEUR = MENUBAR_LARGEUR/6;
}
