package infosgame;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import element.Element;
import element.Soldat;
import wargame.IConfig;

public abstract class InformationElement extends JPanel implements IConfig {
	private static final long serialVersionUID = 1L;
	 
	public void dessineInfosElement(Element e) {
		
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

		frame.repaint();
	}
}
