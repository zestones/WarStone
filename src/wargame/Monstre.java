package wargame;

import wargame.ISoldat.TypesH;

public class Monstre extends Soldat {
    TypesM m;
    Monstre(TypesM m, Position pos){
        super(m.getPoints(), m.getPortee(), m.getPuissance(), m.getTir(), pos);
        this.m = m;
    }
    
    public int getPoints() { return this.m.getPoints(); }
    public int getPortee() { return this.m.getPortee(); }
    public int getPuissance() { return this.m.getPuissance(); }
    public int getTir() { return this.m.getTir(); }
}