package wargame;

import wargame.ISoldat.TypesH;

public abstract class Soldat extends Element implements ISoldat,IConfig{
	private static final long serialVersionUID = 1L;
	
	private final int POINTS_DE_VIE_MAX, PUISSANCE, TIR, PORTEE_VISUELLE;
    private int pointsDeVie;
    public Position pos;
    
    Soldat(Carte carte,int pts, int portee, int puiss, int tir, Position pos) {
        POINTS_DE_VIE_MAX = pointsDeVie = pts;
        PORTEE_VISUELLE = portee; PUISSANCE = puiss; TIR = tir;
        this.pos = pos;
    }
    public void seDeplace() {
    }
    public Position getPosition() { return this.pos; }
}