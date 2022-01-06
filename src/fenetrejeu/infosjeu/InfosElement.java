package fenetrejeu.infosjeu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import carte.Carte;
import carte.element.Element;
import carte.element.Heros;
import carte.element.Soldat;
import fenetrejeu.IFenetre;
import fenetrejeu.configjeu.ElementDeposable;

/**
 * Class InformationElement.
 */
public class InfosElement implements IFenetre {
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
		
	/**
	 * Dessine infos element.
	 *
	 * @param e 
	 */
	public static void dessineInfosElement(Element e) {
		/** on nettoie le panel et on verifie qu'un element a ete cliquer */
		supprimeInfosElement();	
		if(e == null) return;
		
		/** si nous ne somme pas en mode config on affiche le descriptif des elements */
		if(!Carte.modeConfig) {
			descriptifElementPanel.removeAll();
			descriptifElementPanel.revalidate();
			dessineDescriptifElement(e);
		}	
		
		// On ajoute la Miniature de l'element clique
		Image img = e.getMiniature().getScaledInstance(ICON_ELEMENT_LARGEUR - PADDING_ICON_ELEMENT_GAUCHE, ICON_ELEMENT_HAUTEUR - PADDING_ICON_ELEMENT_HAUT, Image.SCALE_SMOOTH);
		ImageIcon imgIcon = new ImageIcon(img);
		
		infosIconLabel.setIcon(imgIcon);
		
		infosIconLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infosIconLabel.setVerticalAlignment(SwingConstants.TOP);
		
		// Et on ajoute les infos de l'element
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
	 * Dessine descriptif des elements.
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
		// On supprime aussi les infos supp si nous ne somme pas en mode config
		if(!Carte.modeConfig)
			ElementDeposable.supprimeLabelDeposable();
	}		
}
