/********************************************************************
 * 							WarStone								*
 *  -------------------------------------------------------------	*
 * |	 Université Jean-Monnet    L3-Infos 		    2021	 |	*
 *  -------------------------------------------------------------	*
 * 	  BEGGARI ISLEM - CHATAIGNIER ANTOINE - BENGUEZZOU Idriss		*
 * 																	*
 * 														infosgame	*
 * ******************************************************************/
package infosgame;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
 * The Class InformationElement.
 *
 * @author pc
 */
public abstract class InfosElement implements IFenetre {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	 
	private static List<JLabel> listeLabelObstacle = new ArrayList<>();
	private static List<TypeObstacle> listeObstacle = new ArrayList<>();
	public static TypeObstacle obstacleSelectione;
	private static int index = 0;
	
	/**
	 * Dessine infos element.
	 *
	 * @param e the e
	 */
	public static void dessineInfosElement(Element e) {
		// On verifie qu'un element a ete cliquer sinon on nettoie le panel
		if(e == null) {
			supprimeInfos();			
			return;
		}
		// si nous ne somme pas en mode creatif on affiche pas les infos des obstacles
		if(!Carte.modeConf) {
			infosElementBody.removeAll();
			infosElementBody.revalidate();
			dessineInfosSupElements(e);
		}	
		
		Image img = e.getImage().getScaledInstance(LARGEUR_ICON_ELEMENT, HAUTEUR_ICON_ELEMENT, Image.SCALE_SMOOTH);
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
	
	private static void dessineInfosSupElements(Element e) {
		JLabel infosLabel = new JLabel();
		
		String infos = "<html><font size=\"+1\">  " + e.getType() + "</font><FONT COLOR=RED><br><font size=\"-1\">  POS: " + e.getPosition()+"</font></FONT>";
		infosLabel.setText(infos);
		infosElementBody.add(infosLabel);
		infosElementBody.repaint();
	}
	
	public static void dessineInfosElementBody() {
		// On supprime le contenu des panels 
		supprimeInfos();
		// Pour chque objet dans le type enum on recupere son image et on la met dans une liste de label
		// une deuxieme liste est creer pour comparer les label au objet (ROCHER FORET...) 
		for(TypeObstacle o : TypeObstacle.values()) {
			JLabel ObstacleLabel = new JLabel();
			Image img = o.getImage().getScaledInstance(LARGEUR_INFOS_PANEL / TypeObstacle.values().length, NB_PIX_CASE, Image.SCALE_SMOOTH);
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
	
	public static void removeObstacleList() {
		// On supprime les listes
		listeLabelObstacle.clear();
		listeObstacle.clear();
		// et on oublie l'obstacle selectione au passage
		obstacleSelectione = null;
	}
	
	private static void supprimeInfos() {
		// Suppression de l'icon
		iconPanel.removeAll();
		iconPanel.revalidate();
		// On redefinie ses dimensions
		iconPanel.setPreferredSize(new Dimension(LARGEUR_ICON_ELEMENT, HAUTEUR_ICON_ELEMENT));
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
