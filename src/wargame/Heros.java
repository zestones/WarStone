package wargame;

import wargame.ISoldat.TypesH;

public class Heros extends Soldat{
    TypesH h;
    Heros(TypesH h, Position pos){
        super(h.getPoints(), h.getPortee(), h.getPuissance(), h.getTir(), pos);
    this.h = h;
    }
    
    public int getPoints() { return this.h.getPoints(); }
    public int getPortee() { return this.h.getPortee(); }
    public int getPuissance() { return this.h.getPuissance(); }
    public int getTir() { return this.h.getTir(); }
}