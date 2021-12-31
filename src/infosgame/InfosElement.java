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
	/**
	 * Dessine infos element.
	 *
	 * @param e the e
	 */
	public static void dessineInfosElement(Element e) {
		if(e == null) {
			supprimeInfos();
			return;
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
	
	public static void dessineInfosElementBody() {
		
		for(TypeObstacle o : TypeObstacle.values()) {
			JLabel ObstacleLabel = new JLabel();
			Image img = o.getImage().getScaledInstance(NB_PIX_CASE, NB_PIX_CASE, Image.SCALE_SMOOTH);
			ImageIcon imgIcon = new ImageIcon(img);
			ObstacleLabel.setIcon(imgIcon);
			infosElementBody.add(ObstacleLabel, BorderLayout.CENTER);
			listeLabelObstacle.add(ObstacleLabel);
			listeObstacle.add(o);
		}
		
		for(int i = 0; i < listeLabelObstacle.size(); i++) {
			listeLabelObstacle.get(i).addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					for(int j = 0; j < listeLabelObstacle.size(); j++)
						if(e.getSource() == listeLabelObstacle.get(j)) {
							obstacleSelectione = listeObstacle.get(j);
							System.out.println("Obstacle ; " + obstacleSelectione);
						}
				}	
			});
		}
		
		infosElementBody.repaint();
	}
	
	private static void supprimeInfos() {
		iconPanel.removeAll();
		iconPanel.revalidate();
		iconPanel.setPreferredSize(new Dimension(LARGEUR_ICON_ELEMENT, HAUTEUR_ICON_ELEMENT));
		iconInfosLabel.setText("");
		infosElementHeader.repaint();
	}
}
