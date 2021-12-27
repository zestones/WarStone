package fenetrejeu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import fenetrejeu.menubar.IMenuBar;

public interface IFenetre extends IMenuBar{
	
	// Parametre dessin de la fenetre
	public static final JFrame frame = new JFrame("WarStone");	
	
	// MenuBar Principal contenant les bouton
	public static final JMenuBar menuBar = new JMenuBar();
	
	public static final JLabel footer = new JLabel("", SwingConstants.CENTER);	
	
	// infos sur les elements clique
	public JLabel iconInfosLabel = new JLabel();
	
	// Image pour les elements clique
	public JLabel iconLabel = new JLabel();
	
	// Conteneur de l'img de l'element clique
	public JPanel iconPanel = new JPanel();
	
	// Panel qui contient les l'icon et les infos sur l'element cliquer
	JPanel infosElementHeader = new JPanel(new BorderLayout());
	
	// Conteneur principal des infos sur les elements
	public JPanel infosElementPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, PADDING_INFOS_PANEL, PADDING_INFOS_PANEL));

	// Label pour Nombre de soldat restant
	JLabel soldatRestant = new JLabel();	
	
	// Panel contenant la carte miniature
	JPanel carteMiniature = new JPanel();
	
	// Position de la fenetre
	int POSITION_X = 100; int POSITION_Y = 50; 
}
