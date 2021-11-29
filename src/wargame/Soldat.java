package wargame;

public abstract class Soldat extends Element implements ISoldat,IConfig{
	private static final long serialVersionUID = 1L;
	
	private final int POINTS_DE_VIE_MAX, PUISSANCE, TIR, PORTEE_VISUELLE;
    private int pointsDeVie;
    public boolean aJoue;
    public Position pos;    
    public Carte carte;

  Soldat(Carte carte,int pts, int portee, int puiss, int tir, Position pos, boolean aJoue) {
    	this.carte = carte;
        POINTS_DE_VIE_MAX = pointsDeVie = pts;
        PORTEE_VISUELLE = portee; PUISSANCE = puiss; TIR = tir;
        this.pos = pos;
        this.aJoue = aJoue;
    }
    
  
    /* Met a jours les positions du Soldat */
    public void seDeplace(Position newPos) {
    	Heros h; Monstre m;
    	this.carte.plateau[pos.getX()][pos.getY()] = null;
    	pos.setX(newPos.getX());
    	pos.setY(newPos.getY());
    	carte.plateau[pos.getX()][pos.getY()] = this;
    	if(this instanceof Heros) {
    		h = (Heros)this;
    		h.aJoue = true;
    	}
    	else {
    		m = (Monstre) this;
    		m.aJoue = true;	
    	}
    		
    }
    
    /* On fait combatre deux soldats */
    public void combat(Soldat soldat) {
    	int pH, pM;
    	
    	if (this.getPosition().estVoisine(soldat.getPosition()) == false) {
			pH = (int) (Math.random() * this.TIR);
			pM = (int) (Math.random() * soldat.TIR);
		}
		else {
			pH = (int) (Math.random() * this.PUISSANCE);
			pM = (int) (Math.random() * soldat.PUISSANCE);
		}
    	
    	soldat.pointsDeVie -= pH;
    	
    	if(soldat.pointsDeVie > 0) {
    		if (soldat.dedans(this.getPosition()) == true)
    			this.pointsDeVie -= pM;
    	}
    	else {
    		soldat.pointsDeVie = 0;	
    		carte.mort(soldat);
    	}
    	if(this.pointsDeVie <= 0) {
    		this.pointsDeVie = 0;
    		carte.mort(this);    
    	}
    	
    	this.aJoue = true;
    }
    
    
    protected abstract boolean dedans(Position position);
    
    public int getPointsMax() { return this.POINTS_DE_VIE_MAX; }
    public void setPoints(int pts) { this.pointsDeVie = pts; } // Utilise pour les bonus (lorsque le heros ce repose)
    public int getPortee() { return this.PORTEE_VISUELLE; }
    public int getPuissance() { return this.PUISSANCE; }
    public int getPoints() { return this.pointsDeVie; }
    public int getTir() { return this.TIR; }
    public Position getPosition() { return pos; }
    
}