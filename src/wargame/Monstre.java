package wargame;

import java.awt.Color;
import java.awt.Graphics;

import wargame.ISoldat.TypesH;

public class Monstre extends Soldat {
    TypesM m;
    String nom;
    int pointsDeVie;
    Monstre(Carte carte, TypesM m, String nom,Position pos){
        super(carte, m.getPoints(), m.getPortee(), m.getPuissance(), m.getTir(), pos);
        this.m = m;
        this.nom = nom;
        this.pointsDeVie = m.getPoints();
        carte.plateau[this.pos.getX()][this.pos.getY()] = this;
    }
    
    /* Dessin du Monstre sur la carte */
    public void seDessiner(Graphics g) { 
    	g.setColor(COULEUR_MONSTRES);
    	g.fillRect(this.pos.getX() * NB_PIX_CASE, this.pos.getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE); 
    	g.setColor(Color.white);
    	g.drawString(this.nom, this.pos.getX() * NB_PIX_CASE + NB_PIX_CASE/2 - g.getFontMetrics().stringWidth(this.nom)/2, this.pos.getY() * NB_PIX_CASE + NB_PIX_CASE/2 + g.getFontMetrics().stringWidth(this.nom)/2);
	}
    
    public void setPoints(int p) { this.pointsDeVie = p; }
    public int getPoints() { return this.m.getPoints(); }
    public int getPortee() { return this.m.getPortee(); }
    public int getPuissance() { return this.m.getPuissance(); }
    public int getTir() { return this.m.getTir(); }
 
}