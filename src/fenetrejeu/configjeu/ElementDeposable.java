package fenetrejeu.configjeu;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import carte.element.ISoldat.TypesH;
import carte.element.Obstacle.TypeObstacle;
import fenetrejeu.IFenetre;
import fenetrejeu.infosjeu.InfosElement;

public abstract class ElementDeposable implements IFenetre {
	private static final long serialVersionUID = 1L;
	
	/** liste label obstacle. */
	protected static List<JLabel> listeLabelElement = new ArrayList<>();
	
	/** liste obstacle. */
	protected static List<TypeObstacle> listeObstacle = new ArrayList<>();
	
	protected static List<TypesH> listeHeros = new ArrayList<>();
	
	/** obstacle selectione. */
	public static TypeObstacle obstacleSelectione;
	
	public static TypesH herosSelectione;
	
	/** nb element deposer. */
	public static int nbElementDeposer = 0;	
	
	/** index des listes. */
	protected static int index = 0;
	
	protected static boolean premiereInitEvent = true;
	
	/**
	 * Dessine les obstacle que l'on peut deposer sur la carte.
	 */
	public static void dessineObstacleDeposable() {
		// On supprime le contenu des panels 
		removeElementList();
		InfosElement.supprimeInfosElement();
		supprimeLabelDeposable();
				
		// Pour chque objet dans le type enum on recupere son image et on la met dans une liste de label
		// une deuxieme liste est creer pour comparer les label au objet (ROCHER FORET...) 
		for(TypeObstacle o : TypeObstacle.values()) {
			JLabel ObstacleLabel = new JLabel();
			Image img = o.getMiniature().getScaledInstance(DESCRIPTIF_ELEMENT_LARGEUR / TypeObstacle.values().length, DESCRIPTIF_ELEMENT_HAUTEUR / TypeObstacle.values().length - PADDING_ICON_ELEMENT_HAUT, Image.SCALE_SMOOTH);
			ImageIcon imgIcon = new ImageIcon(img);

			ObstacleLabel.setIcon(imgIcon);
			ObstacleLabel.setBorder(new MatteBorder(2, 2, 2, 2, COULEUR_GRILLE));
			ObstacleLabel.setHorizontalAlignment(SwingConstants.CENTER);
			ObstacleLabel.setVerticalAlignment(SwingConstants.CENTER);
			descriptifElementPanel.add(ObstacleLabel, BorderLayout.CENTER);
			listeLabelElement.add(ObstacleLabel);
			listeObstacle.add(o);
		}
		
		ElementDeposableEvent.obstacleDeposableEvent();
		if(premiereInitEvent) ElementDeposableEvent.deselectionEvent();
		
		descriptifElementPanel.repaint();
	}
	
	/**
	 * Dessine les heros deposable.
	 */
	public static void dessineHerosDeposable() {
		// On supprime le contenu des panels 
		InfosElement.supprimeInfosElement();
		supprimeLabelDeposable();
		removeElementList();
		
		// Pour chque objet dans le type enum on recupere son image et on la met dans une liste de label
		// une deuxieme liste est creer pour comparer les label au objet (ROCHER FORET...) 
		for(TypesH h : TypesH.values()) {
			JLabel HerosLabel = new JLabel();
			Image img = h.getMiniature().getScaledInstance(DESCRIPTIF_ELEMENT_LARGEUR / TypesH.values().length, DESCRIPTIF_ELEMENT_HAUTEUR / TypesH.values().length - PADDING_ICON_ELEMENT_HAUT, Image.SCALE_SMOOTH);
			ImageIcon imgIcon = new ImageIcon(img);
			HerosLabel.setIcon(imgIcon);
			
			HerosLabel.setBorder(new MatteBorder(2, 2, 2, 2, COULEUR_GRILLE));
			HerosLabel.setHorizontalAlignment(SwingConstants.CENTER);
			HerosLabel.setVerticalAlignment(SwingConstants.CENTER);
			descriptifElementPanel.add(HerosLabel, BorderLayout.CENTER);
			listeLabelElement.add(HerosLabel);
			listeHeros.add(h);
		}
		
		ElementDeposableEvent.herosDeposableEvent();
		
		if(premiereInitEvent) ElementDeposableEvent.deselectionEvent();

		descriptifElementPanel.repaint();
	}
		
	/**
	 * Supression des listes.
	 */
	public static void removeElementList() {
		// On supprime les listes
		listeLabelElement.clear();
		listeObstacle.clear();
		listeHeros.clear();
		// et on oublie l'obstacle selectione au passage
		herosSelectione = null;
		obstacleSelectione = null;
		index = 0;
	}
	
	/**
	 * Supprime le contenu du panel descriptifElementPanel qui ici contient les elements deposable sur la carte
	 */
	public static void supprimeLabelDeposable() {
		descriptifElementPanel.removeAll();
		descriptifElementPanel.revalidate();
		// On redessine le panel
		descriptifElementPanel.repaint();
	}
	
}
