package fenetrejeu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import fenetrejeu.menubar.IMenuBar;
import menu.IMenu;

public interface IFenetre extends IMenuBar, IMenu{
	
	// Creation du panel principal qui contient la carte
	JPanel panel = new JPanel();

	// MenuBar Principal contenant les bouton
	JMenuBar menuBar = new JMenuBar();
	
	JLabel footer = new JLabel("", SwingConstants.CENTER);	
	// Panel contenant la carte miniature
	JPanel carteMiniature = new JPanel();
	
	// infos sur les elements clique
	JLabel iconInfosLabel = new JLabel();
	
	// Image pour les elements clique
	JLabel iconLabel = new JLabel();
	
	// Conteneur de l'img de l'element clique
	JPanel iconPanel = new JPanel();
	
	// Panel qui contient les l'icon et les infos sur l'element cliquer
	JPanel infosElementHeader = new JPanel(new BorderLayout());
	
	// Conteneur principal des infos sur les elements
	JPanel infosElementPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, PADDING_INFOS_PANEL, PADDING_INFOS_PANEL));

	// Label pour Nombre de soldat restant
	JLabel soldatRestant = new JLabel();	
		
	// Position de la fenetre
	int POSITION_X = 100; int POSITION_Y = 50; 
}
