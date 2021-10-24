package wargame;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;


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
	Carte carte;
	
	private void initFenetre() {
		this.fenetre = new JFrame("WarStone");
		this.fenetre.setSize(FEN_LARGEUR, FEN_HAUTEUR);
		
		this.fenetre.setLocationRelativeTo(null);
		
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.fenetre.setVisible(true);
	}
	
	public FenetreJeu() {
		this.initFenetre();
		this.carte = new Carte(this.NB_COLONNES, this.NB_LIGNES, this.TAILLE_TUILE);
		this.fenetre.add(this.carte);
	}
	
	public static void main(String[] args) {
		new FenetreJeu();
    }
}
