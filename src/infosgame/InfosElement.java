package infosgame;

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
import javax.swing.border.MatteBorder;

import carte.Carte;
import element.Element;
import element.Obstacle.TypeObstacle;
import element.Soldat;
import fenetrejeu.IFenetre;

/**
 * Class InformationElement.
 */
public abstract class InfosElement implements IFenetre {
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	 
	/** liste label obstacle. */
	private static List<JLabel> listeLabelObstacle = new ArrayList<>();
	
	/** liste obstacle. */
	private static List<TypeObstacle> listeObstacle = new ArrayList<>();
	
	/** obstacle selectione. */
	public static TypeObstacle obstacleSelectione;
	
	/** nb element deposer. */
	public static int nbElementDeposer = 0;
	
	/** index des listes. */
	private static int index = 0;
	
	/**
	 * Dessine infos element.
	 *
	 * @param e 
	 */
	public static void dessineInfosElement(Element e) {
		/** On verifie qu'un element a ete cliquer sinon on nettoie le panel */
		if(e == null) {
			supprimeInfos();			
			return;
		}
		/** si nous ne somme pas en mode creatif on affiche pas les infos des obstacles */
		if(!Carte.modeConf) {
			infosElementBody.removeAll();
			infosElementBody.revalidate();
			dessineInfosSupElements(e);
		}	
		
		Image img = e.getImage().getScaledInstance(ICON_ELEMENT_LARGEUR, ICON_ELEMENT_HAUTEUR, Image.SCALE_SMOOTH);
		ImageIcon imgIcon = new ImageIcon(img);
		
		iconLabel.setIcon(imgIcon);
		
		String infos = "<html><font size=\"+1\">  " + e.getType() + "</font><FONT COLOR=RED><br><font size=\"-1\">  POS: " + e.getPosition()+"</font></FONT>";
		
		if(e instanceof Soldat)
			infos += "<br><FONT COLOR=BLUE size=\"-1\">  HP: "+((Soldat) e).getPoints() + " / " + ((Soldat) e).getPointsMax() + "</FONT><br><font COLOR=GREEN size = \"-1\">  PV: "+((Soldat) e).getPuissance()+"</font></html>";
		else
			infos += "</html>";
		
		iconInfosLabel.setText(infos);
	
		iconPanel.add(iconLabel);
		
		infosElementHeader.add(iconPanel, BorderLayout.WEST);
		infosElementHeader.add(iconInfosLabel, BorderLayout.CENTER);
		infosElementPanel.add(infosElementHeader, BorderLayout.NORTH);
		infosElementPanel.add(infosElementBody, BorderLayout.SOUTH);
		infosElementPanel.repaint();
	}
	
	/**
	 * Dessine infos sup elements.
	 *
	 * @param e
	 */
	private static void dessineInfosSupElements(Element e) {
		JLabel infosLabel = new JLabel();
		
		String infos = "<html><font COLOR=RED text-shadow: 3px 2px #000 size=\"+4\" > <center> " + e.getType() + "</center></font><br><font size=\"+1\"><center>" +e.getHistoire() + "</center></font>";
		
		infosLabel.setText(infos);
		infosLabel.setFont(new Font("Pushster", Font.BOLD, 15));
		infosLabel.setBorder(new MatteBorder(0, 0, 0, 2, COULEUR_BORDURE));
		infosElementBody.add(infosLabel);
		infosElementBody.repaint();
	}
	
	/**
	 * Dessine infos element body.
	 */
	public static void dessineInfosElementBody() {
		// On supprime le contenu des panels 
		supprimeInfos();
		// Pour chque objet dans le type enum on recupere son image et on la met dans une liste de label
		// une deuxieme liste est creer pour comparer les label au objet (ROCHER FORET...) 
		for(TypeObstacle o : TypeObstacle.values()) {
			JLabel ObstacleLabel = new JLabel();
			Image img = o.getImage().getScaledInstance(INFOS_PANEL_LARGEUR / TypeObstacle.values().length, TAILLE_CARREAU, Image.SCALE_SMOOTH);
			ImageIcon imgIcon = new ImageIcon(img);
			ObstacleLabel.setIcon(imgIcon);
			ObstacleLabel.setBorder(new MatteBorder(2, 2, 2, 2, COULEUR_GRILLE));
			infosElementBody.add(ObstacleLabel, BorderLayout.CENTER);
			listeLabelObstacle.add(ObstacleLabel);
			listeObstacle.add(o);
		}
		
		dessinElementModeConfig();
			
		System.out.println("dessin liste");
		infosElementBody.repaint();
	}
	
	/**
	 * Dessin element mode config.
	 */
	private static void dessinElementModeConfig() {
		// On creer un listener pour chaque label
		// On recupere le type de l'element cliquer a l'aide de l'index de la liste de label
		for(int i = 0; i < listeLabelObstacle.size(); i++) {
			listeLabelObstacle.get(i).addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					for(int j = 0; j < listeLabelObstacle.size(); j++) {
						if(e.getSource() == listeLabelObstacle.get(j)) {
							obstacleSelectione = listeObstacle.get(j);
							listeLabelObstacle.get(j).setBorder(new MatteBorder(2, 2, 2, 2, COULEUR_FORET));
							index = j;
						}
						else
							listeLabelObstacle.get(j).setBorder(new MatteBorder(2, 2, 2, 2, COULEUR_GRILLE));
					}
				}	
			});
		}
		
		/** Un clique sur le header ou le panel infosElement deselectione l'objet */
		header.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(Carte.modeConf) {
					obstacleSelectione = null;
					listeLabelObstacle.get(index).setBorder(new MatteBorder(2, 2, 2, 2, COULEUR_GRILLE));
				}
			}
		});
				
		infosElementPanel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(Carte.modeConf) {
					obstacleSelectione = null;
					listeLabelObstacle.get(index).setBorder(new MatteBorder(2, 2, 2, 2, COULEUR_GRILLE));
				}
			}
		});
	}
	
	/**
	 * Supression des listes.
	 */
	public static void removeObstacleList() {
		// On supprime les listes
		listeLabelObstacle.clear();
		listeObstacle.clear();
		// et on oublie l'obstacle selectione au passage
		obstacleSelectione = null;
	}
	
	/**
	 * Supprime les infos du panel
	 */
	private static void supprimeInfos() {
		// Suppression de l'icon
		iconPanel.removeAll();
		iconPanel.revalidate();
		// On redefinie ses dimensions
		iconPanel.setPreferredSize(new Dimension(ICON_ELEMENT_LARGEUR, ICON_ELEMENT_HAUTEUR));
		iconInfosLabel.setText("");
		// On redessine le panel
		infosElementHeader.repaint();
		// On supprime aussi les infos supp si nous ne somme pas en mode config
		if(!Carte.modeConf) {
			infosElementBody.removeAll();
			infosElementBody.revalidate();
			// On redessine le panel
			infosElementBody.repaint();
		}
	}
}
