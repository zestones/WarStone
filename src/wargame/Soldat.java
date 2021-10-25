package wargame;

import wargame.ISoldat.TypesH;

public abstract class Soldat extends Element implements ISoldat{
    private final int POINTS_DE_VIE_MAX, PUISSANCE, TIR, PORTEE_VISUELLE;
    private int pointsDeVie;
    private Position pos;
    
    Soldat(int pts, int portee, int puiss, int tir, Position pos) {
        POINTS_DE_VIE_MAX = pointsDeVie = pts;
        PORTEE_VISUELLE = portee; PUISSANCE = puiss; TIR = tir;
        this.pos = pos;
    }
}