package fenetrejeu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import fenetrejeu.menubar.IMenuBar;
import menu.IMenu;

public interface IFenetre extends IMenuBar, IMenu{

	/** panelPrincipal de la fenetre */
	JPanel panelPrincipal = new JPanel();

	/** Creation d'un panel contenant la partie haute de la fenetre (boutton, fleche..) */
	JPanel headerPanel = new JPanel();	
	
	/** panel contenant les fleches pour ce diriger sur la carte */
	JPanel flecheMiniCartePanel = new JPanel();
	
	/** Creation du panel principal qui contient la carte */
	JPanel cartePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
	
	/** Panel contenant la carte miniature */
	JPanel carteMiniaturePanel = new JPanel();
	
	/** Conteneur principal des infos sur les elements */
	JPanel infosElementPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, PADDING_INFOS_PANEL, PADDING_INFOS_PANEL));
	
	/** infos sur les elements clique */
	JLabel InfosElementLabel = new JLabel();

	/** Conteneur du label contentant l'img de l'element clique */
	JPanel infosIconPanel = new JPanel();

	/** Label contenant l'img de l'elements cliquer */
	JLabel infosIconLabel = new JLabel();
		
	/** Panel qui contient les l'icon et les infos (a droite de l'icon) sur l'element cliquer */
	JPanel infosElementHeader = new JPanel(new BorderLayout());
		
	/** Contient une description des elements cliquer en mode jeux
	 *	Ou les elements deposable sur la carte en mode config
	 */
	JPanel descriptifElementPanel = new JPanel();
	
	/** Label pour Nombre de soldat restant */
	JLabel elementRestantLabel = new JLabel();	
		
	/** Label affichant les infos des elements survole */
	JLabel footerLabel = new JLabel("", SwingConstants.CENTER);	

}
