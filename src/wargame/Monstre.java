package wargame;

import java.awt.Color;
import java.awt.Graphics;

import wargame.ISoldat.TypesH;

public class Monstre extends Soldat {
    TypesM m;
    String nom;
    Monstre(Carte carte, TypesM m, String nom,Position pos){
        super(carte, m.getPoints(), m.getPortee(), m.getPuissance(), m.getTir(), pos);
        this.m = m;
        this.nom = nom;
        carte.plateau[this.pos.getX()][this.pos.getY()] = this;
    }
    
    /* Dessin du Monstre sur la carte */
    public void seDessiner(Graphics g) { 
    	int posX = this.pos.getX() * NB_PIX_CASE + NB_PIX_CASE/2 - g.getFontMetrics().stringWidth(this.nom)/2;
    	int posY = this.pos.getY() * NB_PIX_CASE + NB_PIX_CASE/2 + g.getFontMetrics().stringWidth(this.nom)/2;
    	
    	g.setColor(COULEUR_MONSTRES);
    	g.fillRect(this.pos.getX() * NB_PIX_CASE, this.pos.getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE); 
    	g.setColor(Color.white);
    	g.drawString(this.nom, posX, posY);
	}
    
    // Deplacer dans Soldat
    /*public int getPortee() { return this.m.getPortee(); }
    public int getPuissance() { return this.m.getPuissance(); }
    public int getTir() { return this.m.getTir(); }
     */
}