package wargame;

import wargame.ISoldat.TypesH;

public abstract class Soldat extends Element implements ISoldat,IConfig{
	private final int POINTS_DE_VIE_MAX, PUISSANCE, TIR, PORTEE_VISUELLE;
    private int pointsDeVie;
    public Position pos;
    public Carte carte;
  
    Soldat(Carte carte,int pts, int portee, int puiss, int tir, Position pos) {
    	this.carte = carte;
        POINTS_DE_VIE_MAX = pointsDeVie = pts;
        PORTEE_VISUELLE = portee; PUISSANCE = puiss; TIR = tir;
        this.pos = pos;
    }
    
    public Position getPosition() { return pos; }
    
    /* Met a jours les positions du Soldat */
    public void seDeplace(Position newPos) {
    	this.carte.plateau[pos.getX()][pos.getY()] = null;
    	pos.setX(newPos.getX());
    	pos.setY(newPos.getY());
    	carte.plateau[pos.getX()][pos.getY()] = this;
    }
    
    /* Methode de combat entre Heros & Monstre */
    public void combat(Soldat soldat) {
    	int pH, pM;
    	
     	if (this.getPosition().estVoisine(soldat.getPosition()) == true) {
    		pH = (int) (Math.random() * this.PUISSANCE);
    		pM = (int) (Math.random() * soldat.PUISSANCE);
    	}
    	else {
    		pH = (int) (Math.random() * this.TIR);
    		pM = (int) (Math.random() * soldat.TIR);
    	}
    	
    	soldat.pointsDeVie -= pH;
    	
    	if(soldat.pointsDeVie > 0)
    		this.pointsDeVie -= pM;
    	else 
    		carte.mort(soldat);
    	if(this.pointsDeVie < 0)
    		carte.mort(this);    	
    }
    
    public int getPoints() { return this.pointsDeVie; }
    public int getPortee() { return this.PORTEE_VISUELLE; }
    public int getPuissance() { return this.PUISSANCE; }
    public int getTir() { return this.TIR; }
}