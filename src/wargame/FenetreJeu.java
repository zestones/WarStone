package wargame;

import javax.swing.*;

public class FenetreJeu {
	/* Variables prédéfinies */
	final int NB_COLONNES = 50;
	final int NB_LIGNES = 50;
	final int MAX_FEN_LARGEUR = 500;
	final int MAX_FEN_HAUTEUR = 500;
	final int PADDING_BAS = 41;
	final int PADDING_DROIT = 18;
	
	/* Variables calculées */
	final int TAILLE_TUILE = Math.min(MAX_FEN_HAUTEUR / NB_COLONNES, MAX_FEN_LARGEUR / NB_LIGNES);
	final int FEN_LARGEUR = NB_COLONNES * TAILLE_TUILE + PADDING_DROIT;
	final int FEN_HAUTEUR = NB_LIGNES * TAILLE_TUILE + PADDING_BAS;
	
	/* Autre variables */
	JFrame fenetre;
	
	private void initFenetre() {
		fenetre = new JFrame("WarStone");
		fenetre.setSize(FEN_LARGEUR, FEN_HAUTEUR);
		
		fenetre.setLocationRelativeTo(null);
		
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);
	}
	
	public FenetreJeu() {
		this.initFenetre();
		JPanel panneauPrincipal = new JPanel();
		panneauPrincipal.setLayout(new BoxLayout(panneauPrincipal, BoxLayout.Y_AXIS));
		
		Element c = new Element();
		c.setLocation(600, 10);
        c.setSize(50, 50);
        panneauPrincipal.add(c);
		Element e = new Element();
		e.setLocation(220, 10);
        e.setSize(50, 50);
        panneauPrincipal.add(e);
        
        fenetre.add(panneauPrincipal);
	}
	
	public static void main(String[] args) {
		new FenetreJeu();
    }
}
