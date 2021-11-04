package wargame;

import wargame.ISoldat.TypesH;

public abstract class Soldat extends Element implements ISoldat,IConfig{
	private final int POINTS_DE_VIE_MAX, PUISSANCE, TIR, PORTEE_VISUELLE;
    private int pointsDeVie;
    public Position pos;
    private Carte carte;
  
    Soldat(Carte carte,int pts, int portee, int puiss, int tir, Position pos) {
    	this.carte = carte;
        this.POINTS_DE_VIE_MAX = this.pointsDeVie = pts;
        this.PORTEE_VISUELLE = portee; this.PUISSANCE = puiss; this.TIR = tir;
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
    
    /* Soustraction de point ne marche pas !!!  */
    public void combat(Soldat soldat) {
    	Heros h = (Heros) this; Monstre m = (Monstre) soldat;
    	int pH, pM;
    	
     	System.out.println("====AVANT COMBAT====\n J1 : " + h.getPoints() + " --- J2 : " + m.getPoints());
    	if (this.getPosition().estVoisine(soldat.getPosition()) == true) {
    		pH = (int) (Math.random() * h.getPuissance());
    		pM = (int) (Math.random() * m.getPuissance());
    	}
    	else {
    		pH = (int) (Math.random() * h.getTir());
    		pM = (int) (Math.random() * m.getTir());
    	}
    	
    	m.setPoints(soldat.pointsDeVie - pH);
    	
    	if(m.getPoints() > 0)
    		h.setPoints(this.pointsDeVie - pM);
    	else 
    		carte.mort(soldat);
    	if(h.getPoints() < 0)
    		carte.mort(this);
    	
    	System.out.println("====FIN COMBAT====\n J1 : " + h.getPoints() + " --- J2 : " + soldat.getPoints());	
    	
    	System.out.println("\n pH : " + pM + " --- pM : " + pM);
    }
}