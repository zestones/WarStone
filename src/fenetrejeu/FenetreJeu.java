/********************************************************************
 * 							WarStone								*
 *  -------------------------------------------------------------	*
 * |	 Université Jean-Monnet    L3-Infos 		    2021	 |	*
 *  -------------------------------------------------------------	*
 * 	  BEGGARI ISLEM - CHATAIGNIER ANTOINE - BENGUEZZOU Idriss		*
 * 																	*
 * 														wargame		*
 * ******************************************************************/
package fenetrejeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import carte.Carte;
import fenetrejeu.menubar.MenuBar;
import fenetrejeu.menubar.MenuBarHeader;
import infosgame.InfosElement;
import infosgame.MiniCarte;
import menu.loadgame.LoadGamePage;
import wargame.PanneauJeu;


/**
 * The Class FenetreJeu.
 */
public class FenetreJeu extends JPanel implements IFenetre{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;	
	public static final PanneauJeu panneau = new PanneauJeu(new Carte());
	
	/**
	 * Instantiates a new fenetre jeu.
	 */
	public FenetreJeu(Carte c, boolean conf) {
		
		if(conf) {
			c.setCarteVide();	
			c.listeHeros.clear();
			c.listeMonstres.clear();
			c.removeAllAction();
		}
	
		Carte.modeConf = conf;
		
		panelPrincipal.setPreferredSize(new Dimension(FEN_LARGEUR, FEN_HAUTEUR - HAUTEUR_MENUBAR_SECONDAIRE - 2));
		panelPrincipal.setLayout(new BorderLayout());
		panelPrincipal.setOpaque(false);	   	
		
		/* on retire l'espace cree par le flowLayout */
		header.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		header.setOpaque(true);	    		
             
        // On ajoute le menu a notre HEADER
        header.add(menuBar);
        	
		panel.setLayout(new BorderLayout());
		panel.setOpaque(false);	    
		
		// On charge la liste des sauvegardes 
		LoadGamePage.initListeSauvegarde();
						
		// Creation d'un panel qui contient les infos du jeux
		JPanel infosPanel = new JPanel(new FlowLayout());
		
		/* on retire l'espace cree par le flowLayout entre la miniCarte et infosPanel 
		 * Utilisation du BorderLayout.CENTER impossible car les dimension sont ignore
		 */
		FlowLayout layout = (FlowLayout)infosPanel.getLayout();
		layout.setVgap(0);
		
		infosPanel.setPreferredSize(new Dimension(LARGEUR_INFOS_PANEL, HAUTEUR_INFOS_PANEL));
		infosPanel.setBackground(COULEUR_INFOS_PANEL);

		infosPanel.setOpaque(true);	    	
		infosPanel.setBorder(new MatteBorder(0, 2, 2, 2, COULEUR_BORDURE));
		
		// On cree un conteneur avec FlowLayout pour pouvoir centrer la miniCarte
		JPanel miniCartePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, PADDING_LARGEUR_MINI_CARTE, PADDING_HAUTEUR_MINI_CARTE));

		// On definie les dimensions et la couleurs du background
		miniCartePanel.setPreferredSize(new Dimension(LARGEUR_INFOS_PANEL, HAUTEUR_MINI_CARTE + PADDING_HAUTEUR_MINI_CARTE * 2));
		miniCartePanel.setBackground(COULEUR_EAU);
		miniCartePanel.setBorder(new MatteBorder(0, 2, 0, 2, COULEUR_BORDURE));

		// On centre le panel qui contient la carte dans le conteneur creer juste avant
		carteMiniature.setLayout(new BorderLayout());
		
		// On definie les dimensions
		carteMiniature.setPreferredSize(new Dimension(LARGEUR_MINI_CARTE, HAUTEUR_MINI_CARTE));
		
		// On ajoute la carte au panel
		carteMiniature.add(new MiniCarte(panneau.cam));	
		
		// On Met des bordure autour de la carte
		carteMiniature.setBorder(new MatteBorder(5, 5, 5, 5, COULEUR_MENUBAR));
		
		// On rajoute ce panel au conteneur miniCartePanel
		miniCartePanel.add(carteMiniature);
		
		// On ajoute enfin on conteneur principal
		infosPanel.add(miniCartePanel);
			 
		// On affiche le nombre de soldant restant sur la carte
		JPanel infosSoldatPanel = new JPanel(new FlowLayout());
		
		// On retire l'espace creer par le flow Layout pour que la bordure prenne bien toute la Hauteur du panel
		FlowLayout infosSoldatLayout = (FlowLayout)infosSoldatPanel.getLayout();
		infosSoldatLayout.setVgap(0);
	
		infosSoldatPanel.setBackground(COULEUR_FORET);
		
		// Alignement du text		
		soldatRestant.setHorizontalAlignment(SwingConstants.CENTER);
		
		// On centre le label avec BorderLayou.CENTER
		soldatRestant.setLayout(new BorderLayout());
	
		// On definit la couleur et la taille
		soldatRestant.setPreferredSize(new Dimension(LARGEUR_INFOS_PANEL, HAUTEUR_NB_SOLDAT_VIVANT));
		
		// Couleur du text et Police
		soldatRestant.setForeground(COULEUR_GRILLE);
		soldatRestant.setFont(new Font("Pushster", Font.BOLD, 15));
		
		// On rajoute une La bordure gauche et droite
		soldatRestant.setBorder(new MatteBorder(2, 2, 0, 2, COULEUR_BORDURE));
		
		// On ajoute le label au panel
		infosSoldatPanel.add(soldatRestant);
		
		// On aoute le conteneur dans la panel principal 
		infosPanel.add(infosSoldatPanel);
		
		// Couleur et taille du panel Principal contenant les infos sur les elements
		infosElementPanel.setPreferredSize(new Dimension(LARGEUR_INFOS_PANEL, HAUTEUR_INFOS_PANEL));
		infosElementPanel.setBackground(COULEUR_HEROS);		
		
		// Couleur et taille du header dans ce panel
		infosElementHeader.setPreferredSize(new Dimension(LARGEUR_INFOS_PANEL - PADDING_INFOS_PANEL*2, HAUTEUR_ICON_ELEMENT));
		infosElementPanel.setBorder(new MatteBorder(2, 2, 0, 2, COULEUR_BORDURE));
		
		// panel contenant l'icon
		iconPanel.setBackground(COULEUR_GRILLE);
		iconPanel.setPreferredSize(new Dimension(LARGEUR_ICON_ELEMENT, HAUTEUR_ICON_ELEMENT));
		iconPanel.setBorder(new LineBorder(COULEUR_BORDURE, 2, true));
		
		// Taille du label contenant l'icon
		iconLabel.setPreferredSize(new Dimension(LARGEUR_ICON_ELEMENT, HAUTEUR_ICON_ELEMENT));
		
		// Label contant les infos de l'elements
		iconInfosLabel.setOpaque(true);
		iconInfosLabel.setBackground(COULEUR_HEROS_DEJA_JOUE);

		// On centre le text
		iconInfosLabel.setHorizontalAlignment(SwingConstants.LEFT);
		iconInfosLabel.setFont(new Font("Pushster", Font.BOLD, 15));
		iconInfosLabel.setBorder(new MatteBorder(0, 0, 0, 2, COULEUR_BORDURE));
		
		// On rajoute les elements a leur conteneur parent
		iconPanel.add(iconLabel);
		infosElementHeader.add(iconPanel, BorderLayout.WEST);
		infosElementHeader.add(iconInfosLabel, BorderLayout.CENTER);
		infosElementPanel.add(infosElementHeader, BorderLayout.NORTH);
	
		infosElementBody.setOpaque(true);
		infosElementBody.setLayout(new GridLayout(1, 0));
		infosElementBody.setPreferredSize(new Dimension(LARGEUR_ELEMENT_BODY, NB_PIX_CASE));
		infosElementBody.setBackground(COULEUR_EAU);
		
		if(Carte.modeConf) {
			InfosElement.dessineInfosElementBody();
		}
				
		infosElementPanel.add(infosElementBody, BorderLayout.SOUTH);
		infosPanel.add(infosElementPanel);
		
		// On cree notre panneau 
		if(conf)
			panneau.setPanneauJeuConf(c);
		else
			panneau.setPanneauJeu(c);	
	
		panel.add(panneau, BorderLayout.CENTER);
		
		footer.setBackground(COULEUR_FOOTER);
		footer.setPreferredSize(new Dimension(FOOTER_LARGEUR, FOOTER_HAUTEUR));
		footer.setOpaque(true);
		footer.setFont(new Font("Arial", Font.BOLD, 13));
		footer.setForeground(Color.WHITE);				
		
		panel.add(footer, BorderLayout.SOUTH);
		
		// On ajoute tout les panels a la frame
		panelPrincipal.add(header, BorderLayout.NORTH);
		panelPrincipal.add(infosPanel, BorderLayout.EAST);
		panelPrincipal.add(panel, BorderLayout.CENTER);		
		
		new MenuBarHeader();
		new MenuBar();
		
		frame.add(panelPrincipal);			
	}
	
	public FenetreJeu(Carte c){	
		this(c, false);
	}
	
	public FenetreJeu(){	
		this(new Carte());
	}
}