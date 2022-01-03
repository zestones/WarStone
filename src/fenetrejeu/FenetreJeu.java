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
		
		/** Si le joueur lance une partie en mode config on nettoie la carte */
		if(conf) {
			/** On vide la carte */
			c.setCarteVide();	
			/**Nettoie les listes de soldat */
			c.listeHeros.clear();
			c.listeMonstres.clear();
			/** Supprime toute les actions des listes */
			c.removeAllAction();
		}
		
		/** On change le mode de jeux en fonction du choix du joueur */
		Carte.modeConf = conf;
		
		/** On initialise le panel principal */
		panelPrincipal.setPreferredSize(new Dimension(FEN_LARGEUR, FEN_HAUTEUR));
		panelPrincipal.setLayout(new BorderLayout());
		panelPrincipal.setOpaque(false);	   	
		
		header.setLayout(new BorderLayout());
		header.setOpaque(false);	    	
	
		panel.setLayout(new BorderLayout());
		panel.setOpaque(false);	    
		
		/** On charge la liste des sauvegardes */
		LoadGamePage.initListeSauvegarde();
						
		/** Creation d'un panel qui contient les infos du jeux */
		JPanel infosPanel = new JPanel(new FlowLayout());
		
		/* on retire l'espace cree par le flowLayout entre la miniCarte et infosPanel 
		 * Utilisation du BorderLayout.CENTER impossible car les dimension sont ignore
		 */
		FlowLayout layout = (FlowLayout)infosPanel.getLayout();
		layout.setVgap(0);
		
		/** infosPanel est le panel contenant les infos sur la partie en cours (miniCarte, nb Soldat Vivant...) */
		infosPanel.setPreferredSize(new Dimension(INFOS_PANEL_LARGEUR, INFOS_PANEL_HAUTEUR));
		infosPanel.setBackground(COULEUR_INFOS_PANEL);

		infosPanel.setOpaque(true);	    	
		infosPanel.setBorder(new MatteBorder(0, 2, 2, 2, COULEUR_BORDURE));
		
		/** On cree un conteneur avec FlowLayout pour pouvoir centrer la miniCarte */
		JPanel miniCartePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, PADDING_MINI_CARTE_GAUCHE, PADDING_MINI_CARTE_HAUT));

		/** On definie les dimensions et la couleurs du background */
		miniCartePanel.setPreferredSize(new Dimension(INFOS_PANEL_LARGEUR, MINI_CARTE_HAUTEUR + PADDING_MINI_CARTE_HAUT * 2));
		miniCartePanel.setBackground(COULEUR_EAU);
		miniCartePanel.setBorder(new MatteBorder(0, 2, 0, 2, COULEUR_BORDURE));

		/** On centre le panel qui contient la carte dans le conteneur creer juste avant */
		carteMiniature.setLayout(new BorderLayout());
		/** On definie les dimensions */
		carteMiniature.setPreferredSize(new Dimension(MINI_CARTE_LARGEUR, MINI_CARTE_HAUTEUR));
		
		// On ajoute la carte au panel
		carteMiniature.add(new MiniCarte(panneau.cam));	
		
		// On Met des bordure autour de la carte
		carteMiniature.setBorder(new MatteBorder(5, 5, 5, 5, COULEUR_MENUBAR));
		
		/** On rajoute ce panel au conteneur miniCartePanel */
		miniCartePanel.add(carteMiniature);
		
		/** On ajoute enfin on conteneur principal */
		infosPanel.add(miniCartePanel);
			 
		/** On affiche le nombre de soldant restant sur la carte */
		JPanel infosSoldatPanel = new JPanel(new FlowLayout());
		
		/** On retire l'espace creer par le flow Layout pour que la bordure prenne bien toute la Hauteur du panel */
		FlowLayout infosSoldatLayout = (FlowLayout)infosSoldatPanel.getLayout();
		infosSoldatLayout.setVgap(0);
	
		infosSoldatPanel.setBackground(COULEUR_FORET);
		
		/** Alignement du text */		
		soldatRestant.setHorizontalAlignment(SwingConstants.CENTER);
		
		/** On centre le label avec BorderLayou.CENTER */
		soldatRestant.setLayout(new BorderLayout());
	
		/** On definit la couleur et la taille */
		soldatRestant.setPreferredSize(new Dimension(INFOS_PANEL_LARGEUR, ELEMENT_RESTANT_HAUTEUR));
		
		/** Couleur du text et Police */
		soldatRestant.setForeground(COULEUR_GRILLE);
		soldatRestant.setFont(new Font("Pushster", Font.BOLD, 15));
		soldatRestant.setBorder(new MatteBorder(2, 2, 0, 2, COULEUR_BORDURE));
		infosSoldatPanel.add(soldatRestant);
		
		infosPanel.add(infosSoldatPanel);
		
		infosElementPanel.setPreferredSize(new Dimension(INFOS_PANEL_LARGEUR, INFOS_ELEMENT_PANEL_HAUTEUR));
		infosElementPanel.setBackground(COULEUR_HEROS);		
		
		// Couleur et taille du header dans ce panel
		infosElementHeader.setPreferredSize(new Dimension(INFOS_PANEL_LARGEUR - PADDING_INFOS_PANEL*2, ICON_ELEMENT_HAUTEUR));
		infosElementPanel.setBorder(new MatteBorder(2, 2, 0, 2, COULEUR_BORDURE));
		
		// panel contenant l'icon
		iconPanel.setBackground(COULEUR_GRILLE);
		iconPanel.setPreferredSize(new Dimension(ICON_ELEMENT_LARGEUR, ICON_ELEMENT_HAUTEUR));
		iconPanel.setBorder(new LineBorder(COULEUR_BORDURE, 2, true));
		
		// Taille du label contenant l'icon
		iconLabel.setPreferredSize(new Dimension(ICON_ELEMENT_LARGEUR, ICON_ELEMENT_HAUTEUR));
		
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
		infosElementBody.setBackground(COULEUR_EAU);
		
		// En mode config on affiche les elements deposable
		if(Carte.modeConf) {
			infosElementBody.setPreferredSize(new Dimension(ELEMENT_BODY_LARGEUR, TAILLE_CARREAU));
			InfosElement.dessineInfosElementBody();
		}
		/** Sinon on affiche le panel avec le detail des elements clique */
		else
			infosElementBody.setPreferredSize(new Dimension(ELEMENT_BODY_LARGEUR, ELEMENT_BODY_HAUTEUR));
		
		infosElementPanel.add(infosElementBody, BorderLayout.SOUTH);
		infosPanel.add(infosElementPanel);
		
		/** le panneau existe deja on le modifie */
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