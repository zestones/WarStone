package fenetrejeu.menubar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;

import wargame.IConfig;

public interface IMenuBar extends IConfig {
	
	JButton finTour = new JButton("End Turn"); 
	JLabel top = new JLabel("", SwingConstants.CENTER); 
	JButton sauvegarde = new JButton("Save");   
	JButton reprendre = new JButton("Resume");   
	JButton restart = new JButton("ReStart");   

	JButton cameraBas = new BasicArrowButton(BasicArrowButton.SOUTH);
	JButton cameraHaut = new BasicArrowButton(BasicArrowButton.NORTH);
	JButton cameraGauche = new BasicArrowButton(BasicArrowButton.WEST);
	JButton cameraDroite = new BasicArrowButton(BasicArrowButton.EAST); 

	/** Variable du menuBar dans le PanneauJeu */
	final int MENUBAR_HAUTEUR = 65;
	final int MENUBAR_LARGEUR = FEN_LARGEUR;
	final int BOUTTON_HAUTEUR = MENUBAR_HAUTEUR/2;
	final int BOUTTON_LARGEUR = MENUBAR_LARGEUR/6;
	
	/** Varibale du footer dans le PanneauJeu */
	final int FOOTER_HAUTEUR = 30;
	final int FOOTER_LARGEUR = FEN_LARGEUR;

}
