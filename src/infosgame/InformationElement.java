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
		
		Image img = e.getImage().getScaledInstance(NB_PIX_CASE, NB_PIX_CASE, Image.SCALE_SMOOTH);
		ImageIcon imgIcon = new ImageIcon(img);
		
		iconLabel.setIcon(imgIcon);
		
		String infos = "<html><font size=\"+2\">  " + e.getType() + "</font><FONT COLOR=RED><br><font>  POS: " + e.getPosition()+"</font></FONT>";
		if(e instanceof Soldat)
			infos += "<br><FONT COLOR=BLUE>  HP: "+((Soldat) e).getPoints() + " / " + ((Soldat) e).getPointsMax() + "</FONT><br><font COLOR=GREEN>  PV: "+((Soldat) e).getPuissance()+"</font></html>";
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
