package wargame;

import java.awt.Color;
import java.awt.Graphics;
import wargame.ISoldat.TypesH;

public class Heros extends Soldat{
    TypesH h;
    String nom;
    Heros(Carte carte, TypesH h, String nom, Position pos){
        super(carte, h.getPoints(), h.getPortee(), h.getPuissance(), h.getTir(), pos);
        this.h = h;
        this.nom = nom;
        carte.plateau[this.pos.getX()][this.pos.getY()] = this;
	}
    public Color changeCouleurs() { return Color.cyan; }
    public void seDessiner(Graphics g) { 
    	g.setColor(COULEUR_HEROS);
    	g.fillRect(this.pos.getX() * NB_PIX_CASE, this.pos.getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE); 
    	g.setColor(COULEUR_TEXTE);
    	g.drawString(this.nom, this.pos.getX() * NB_PIX_CASE + NB_PIX_CASE/2 - g.getFontMetrics().stringWidth(this.nom)/2, this.pos.getY() * NB_PIX_CASE + NB_PIX_CASE/2 + g.getFontMetrics().stringWidth(this.nom)/2);
	}
    public int getPoints() { return this.h.getPoints(); }
    public int getPortee() { return this.h.getPortee(); }
    public int getPuissance() { return this.h.getPuissance(); }
    public int getTir() { return this.h.getTir(); }
    public Position getPosition() {
    	Soldat s = this;
    	return s.getPosition();
	}
}