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

import javax.swing.ImageIcon;

import element.Element;
import element.Soldat;
import fenetrejeu.IFenetre;

/**
 * The Class InformationElement.
 *
 * @author pc
 */
public abstract class InformationElement implements IFenetre {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	 
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

		infosElementPanel.repaint();
	}
	
	private static void supprimeInfos() {
		iconPanel.removeAll();
		iconPanel.revalidate();
		iconPanel.setPreferredSize(new Dimension(LARGEUR_ICON_ELEMENT, HAUTEUR_ICON_ELEMENT));
		iconInfosLabel.setText("");
		infosElementHeader.repaint();
	}
}
