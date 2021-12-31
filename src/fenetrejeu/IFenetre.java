package fenetrejeu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import fenetrejeu.menubar.IMenuBar;
import menu.IMenu;

public interface IFenetre extends IMenuBar, IMenu{

	// panelPrincipal de la fenetre
	JPanel panelPrincipal = new JPanel(new BorderLayout());
	
	// Creation du panel principal qui contient la carte
	JPanel panel = new JPanel();

	// Creation d'un panel dans lequel on va mettre notre menu
	JPanel header = new JPanel();	

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

	JPanel infosElementBody = new JPanel();
	
	// Label pour Nombre de soldat restant
	JLabel soldatRestant = new JLabel();	
	
	// panel contenant les fleches pour ce diriger sur la carte
	JPanel fleche = new JPanel();
	// Position de la fenetre
	int POSITION_X = 100; int POSITION_Y = 50; 
}
