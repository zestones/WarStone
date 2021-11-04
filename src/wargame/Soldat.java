package wargame;

import wargame.ISoldat.TypesH;

public abstract class Soldat extends Element implements ISoldat,IConfig{
	private static final long serialVersionUID = 1L;
	
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
    public void seDeplace(Position newPos) {
    	this.carte.plateau[pos.getX()][pos.getY()] = null;
    	pos.setX(newPos.getX());
    	pos.setY(newPos.getY());
    	carte.plateau[pos.getX()][pos.getY()] = this;
    }
}