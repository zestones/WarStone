package fenetrejeu.infosjeu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import carte.Carte;
import carte.element.Element;
import carte.element.Heros;
import carte.element.Soldat;
import carte.element.ISoldat.TypesH;
import carte.element.Obstacle.TypeObstacle;
import fenetrejeu.IFenetre;

/**
 * Class InformationElement.
 */
public abstract class InfosElement implements IFenetre {
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	 
	/** liste label obstacle. */
	private static List<JLabel> listeLabelElement = new ArrayList<>();
	
	/** liste obstacle. */
	private static List<TypeObstacle> listeObstacle = new ArrayList<>();
	
	private static List<TypesH> listeHeros = new ArrayList<>();
	
	/** obstacle selectione. */
	public static TypeObstacle obstacleSelectione;
	
	public static TypesH herosSelectione;
	
	/** nb element deposer. */
	public static int nbElementDeposer = 0;
	
	/** index des listes. */
	private static int index = 0;
	
	private static boolean premiereInit = true;
	
	/**
	 * Dessine infos element.
	 *
	 * @param e 
	 */
	public static void dessineInfosElement(Element e) {
		/** on nettoie le panel et on verifie qu'un element a ete cliquer */
		supprimeInfosElement();	
		if(e == null) return;
		
		/** si nous ne somme pas en mode creatif on affiche pas les infos des obstacles */
		if(!Carte.modeConf) {
			descriptifElementPanel.removeAll();
			descriptifElementPanel.revalidate();
			dessineDescriptifElement(e);
		}	
		
		Image img = e.getImage().getScaledInstance(ICON_ELEMENT_LARGEUR, ICON_ELEMENT_HAUTEUR, Image.SCALE_SMOOTH);
		ImageIcon imgIcon = new ImageIcon(img);
		
		infosIconLabel.setIcon(imgIcon);
		
		String infos = "<html>";
		
		if(e instanceof Soldat) {
			if(e instanceof Heros)
				infos += "<center><FONT COLOR = #901900 size=\"+1\">HEROS</FONT><center>";
			else 
				infos += "<center><FONT COLOR = #901900 size=\"+1\">MONSTRE</FONT><center>";
		
			infos += "<br><br><FONT COLOR = #1B8100>  POINTS VIE : " + ((Soldat) e).getPoints() + " / " + ((Soldat) e).getPointsMax() + "</FONT>"
					+ "<br> <FONT COLOR = #B00000>  PUISSANCE : " + ((Soldat) e).getPuissance() + "</FONT>"
					+ "<br> <FONT COLOR = #3D0074> PORTEE : " + ((Soldat) e).getPortee() + "</FONT>"
					+ "<br>	<FONT COLOR = #B00000> TIR : " + ((Soldat) e).getTir() + "</FONT>" 
					;
		}
		else 
			infos += "<center><FONT COLOR = BLACK size=\"+3\">" + e.getType() + "</FONT><center>";
		
		infos += "</html>";
		
		InfosElementLabel.setText(infos);
		InfosElementLabel.setHorizontalAlignment(SwingConstants.HORIZONTAL);

		infosIconPanel.add(infosIconLabel);
		
		infosElementHeader.add(infosIconPanel, BorderLayout.WEST);
		infosElementHeader.add(InfosElementLabel, BorderLayout.CENTER);
		infosElementPanel.add(infosElementHeader, BorderLayout.NORTH);
		infosElementPanel.add(descriptifElementPanel, BorderLayout.SOUTH);
		infosElementPanel.repaint();
	}
	
	/**
	 * Dessine infos sup elements.
	 *
	 * @param e
	 */
	public static void dessineDescriptifElement(Element e) {
		JLabel typeLabel = new JLabel();
		String titre = "<html><FONT text-shadow: 3px 2px COLOR = #B0A2BD><center> " + e.getType() + "</center></FONT></html>";
		typeLabel.setText(titre);
		typeLabel.setFont(new Font("Pushster", Font.BOLD, 30));
		typeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		typeLabel.setVerticalAlignment(SwingConstants.CENTER);
		
		typeLabel.setPreferredSize(new Dimension(DESCRIPTIF_ELEMENT_LARGEUR, DESCRIPTIF_ELEMENT_HAUTEUR/5));
		descriptifElementPanel.add(typeLabel, BorderLayout.NORTH);

		JLabel infosLabel = new JLabel();	
		String infos = "<html><FONT COLOR = #BFB7A6 size=\"+1\"><center>"  + e.getHistoire() + "</center></FONT><html>";
			
		infosLabel.setText(infos);
		infosLabel.setFont(new Font("Pushster", Font.BOLD, 15));
		
		infosLabel.setVerticalAlignment(JLabel.NORTH);

		descriptifElementPanel.add(infosLabel);
		descriptifElementPanel.repaint();
	}
	
	/**
	 * Dessine infos element body.
	 */
	public static void dessineObstacleDeposable() {
		// On supprime le contenu des panels 
		removeElementList();
		supprimeInfosElement();
		supprimeLabelDeposable();
				
		// Pour chque objet dans le type enum on recupere son image et on la met dans une liste de label
		// une deuxieme liste est creer pour comparer les label au objet (ROCHER FORET...) 
		for(TypeObstacle o : TypeObstacle.values()) {
			JLabel ObstacleLabel = new JLabel();
			Image img = o.getMiniature().getScaledInstance(INFOS_PANEL_LARGEUR / TypeObstacle.values().length, TAILLE_CARREAU, Image.SCALE_SMOOTH);
			ImageIcon imgIcon = new ImageIcon(img);
			ObstacleLabel.setIcon(imgIcon);
			ObstacleLabel.setBorder(new MatteBorder(2, 2, 2, 2, COULEUR_GRILLE));
			ObstacleLabel.setHorizontalAlignment(SwingConstants.CENTER);
			ObstacleLabel.setVerticalAlignment(SwingConstants.CENTER);
			descriptifElementPanel.add(ObstacleLabel, BorderLayout.CENTER);
			listeLabelElement.add(ObstacleLabel);
			listeObstacle.add(o);
		}
		
		obstacleDeposableEvent();
		deselectionEvent();
		
		descriptifElementPanel.repaint();
	}
	
	/**
	 * Dessine infos element body.
	 */
	public static void dessineHerosDeposable() {
		// On supprime le contenu des panels 
		supprimeInfosElement();
		supprimeLabelDeposable();
		removeElementList();
		
		// Pour chque objet dans le type enum on recupere son image et on la met dans une liste de label
		// une deuxieme liste est creer pour comparer les label au objet (ROCHER FORET...) 
		for(TypesH h : TypesH.values()) {
			JLabel HerosLabel = new JLabel();
			Image img = h.getMiniature().getScaledInstance(INFOS_PANEL_LARGEUR / TypesH.values().length, TAILLE_CARREAU, Image.SCALE_SMOOTH);
			ImageIcon imgIcon = new ImageIcon(img);
			HerosLabel.setIcon(imgIcon);
			HerosLabel.setBorder(new MatteBorder(2, 2, 2, 2, COULEUR_GRILLE));
			HerosLabel.setHorizontalAlignment(SwingConstants.CENTER);
			HerosLabel.setVerticalAlignment(SwingConstants.CENTER);
			descriptifElementPanel.add(HerosLabel, BorderLayout.CENTER);
			listeLabelElement.add(HerosLabel);
			listeHeros.add(h);
		}
		
		herosDeposableEvent();
		
		if(premiereInit) deselectionEvent();

		descriptifElementPanel.repaint();
	}
	
	
	private static void herosDeposableEvent() {
		// On creer un listener pour chaque label
		// On recupere le type de l'element cliquer a l'aide de l'index de la liste de label
		for(int i = 0; i < listeLabelElement.size(); i++) {
			listeLabelElement.get(i).addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					for(int j = 0; j < listeLabelElement.size(); j++) {
						if(e.getSource() == listeLabelElement.get(j)) {
							herosSelectione = listeHeros.get(j);
							listeLabelElement.get(j).setBorder(new MatteBorder(2, 2, 2, 2, COULEUR_ELEMENT_SELECTIONE));
							index = j;
						}
						else
							listeLabelElement.get(j).setBorder(new MatteBorder(2, 2, 2, 2, COULEUR_GRILLE));
					}
				}	
			});
		}
	}
	
	
	
	/**
	 * Dessin element mode config.
	 */
	private static void obstacleDeposableEvent() {
		// On creer un listener pour chaque label
		// On recupere le type de l'element cliquer a l'aide de l'index de la liste de label
		for(int i = 0; i < listeLabelElement.size(); i++) {
			listeLabelElement.get(i).addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					for(int j = 0; j < listeLabelElement.size(); j++) {
						if(e.getSource() == listeLabelElement.get(j)) {
							obstacleSelectione = listeObstacle.get(j);
							listeLabelElement.get(j).setBorder(new MatteBorder(2, 2, 2, 2, COULEUR_ELEMENT_SELECTIONE));
							index = j;
						}
						else
							listeLabelElement.get(j).setBorder(new MatteBorder(2, 2, 2, 2, COULEUR_GRILLE));
					}
				}	
			});
		}
	}
	
	
	

	private static void deselectionEvent() {
		premiereInit = false;
		
		/** Un clique sur le header ou le panel infosElement deselectione l'objet */
		headerPanel.addMouseListener(new MouseAdapter() {	
			public void mousePressed(MouseEvent e) {
				if(Carte.modeConf) {
					obstacleSelectione = null;
					herosSelectione = null;
					if(!listeLabelElement.isEmpty())
						listeLabelElement.get(index).setBorder(new MatteBorder(2, 2, 2, 2, COULEUR_GRILLE));
				}
			}
		});
				
		infosElementPanel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(Carte.modeConf) {
					obstacleSelectione = null;
					herosSelectione = null;
					if(!listeLabelElement.isEmpty())
						listeLabelElement.get(index).setBorder(new MatteBorder(2, 2, 2, 2, COULEUR_GRILLE));
				}
			}
		});
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
	 * Supprime les infos du panel
	 */
	public static void supprimeInfosElement() {
		// Suppression de l'icon
		infosIconPanel.removeAll();
		infosIconPanel.revalidate();
		
		// On redefinie ses dimensions
		infosIconPanel.setPreferredSize(new Dimension(ICON_ELEMENT_LARGEUR, ICON_ELEMENT_HAUTEUR));
		InfosElementLabel.setText("");
		// On redessine le panel
		infosElementHeader.repaint();
		if(!Carte.modeConf)
			supprimeLabelDeposable();
	}	
	
	/**
	 * Supprime le contenu du panel descriptifElementPanel qui ici contient les elements deposable sur la carte
	 */
	public static void supprimeLabelDeposable() {
		// On supprime aussi les infos supp si nous ne somme pas en mode config
		descriptifElementPanel.removeAll();
		descriptifElementPanel.revalidate();
		// On redessine le panel
		descriptifElementPanel.repaint();
	}
	
}
