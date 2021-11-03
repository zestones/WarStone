package wargame;

import java.awt.Color;
import java.awt.Graphics;
import wargame.ISoldat.TypesH;

public class Heros extends Soldat{
    TypesH h;
    String nom;
    private Carte carte;
    Heros(Carte carte, TypesH h, String nom, Position pos){
        super(carte, h.getPoints(), h.getPortee(), h.getPuissance(), h.getTir(), pos);
        this.h = h;
        this.nom = nom;
        this.carte = carte;
        carte.plateau[this.pos.getX()][this.pos.getY()] = this;
	}
    public Color changeCouleurs() { return Color.cyan; }
    private void dessinHeros(Graphics g) { 
    	g.setColor(COULEUR_HEROS);
    	g.fillRect(this.pos.getX() * NB_PIX_CASE, this.pos.getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE); 
    	g.setColor(COULEUR_TEXTE);
    	g.drawString(this.nom, this.pos.getX() * NB_PIX_CASE + NB_PIX_CASE/2 - g.getFontMetrics().stringWidth(this.nom)/2, this.pos.getY() * NB_PIX_CASE + NB_PIX_CASE/2 + g.getFontMetrics().stringWidth(this.nom)/2);
    }
    public void seDessiner(Graphics g) {
    	int portee = this.getPortee();
    	for(int i = 0; i <= portee * 2; i++) {
    		for(int j = 0; j <= portee  * 2 ; j++) {
   			
    			Position porteeVisuelle = new Position(this.pos.getX() + i - portee, this.pos.getY() + j - portee);
    			if(porteeVisuelle.estValide() == false)
    				porteeVisuelle.verifPosition();
    			
    			if(this.carte.plateau[porteeVisuelle.getX()][porteeVisuelle.getY()] == null) {
    				g.setColor(COULEUR_VIDE);
    				g.fillRect(porteeVisuelle.getX() * NB_PIX_CASE, porteeVisuelle.getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE); 
    			}
    			
    			g.setColor(COULEUR_GRILLE);
				g.drawRect(porteeVisuelle.getX() * NB_PIX_CASE, porteeVisuelle.getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE); 
    		}
    	}
    	this.dessinHeros(g);
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