/********************************************************************
 * 							WarStone								*
 *  -------------------------------------------------------------	*
 * |	 Université Jean-Monnet    L3-Infos 		    2021	 |	*
 *  -------------------------------------------------------------	*
 * 	  BEGGARI ISLEM - CHATAIGNIER ANTOINE - BENGUEZZOU Idriss		*
 * 																	*
 * 														element		*
 * ******************************************************************/
package element;

import java.awt.Graphics;

import carte.Camera;
import carte.Carte;
import utile.Position;
import wargame.IConfig;

/**
 * The Class Soldat.
 */
public abstract class Soldat extends Element implements ISoldat, IConfig{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The portee visuelle. */
	private final int POINTS_DE_VIE_MAX, PUISSANCE, TIR, PORTEE_VISUELLE;
    
    /** The points de vie. */
    private int pointsDeVie;
    
    /** The a joue. */
    public boolean aJoue;
    
    /** The pos. */
    public Position pos;    
    
    /** The carte. */
    public Carte carte;
    
    /** The g. */
    public Graphics g;
    
    /** The nouvelle pos. */
    public Position nouvellePos;
    
    /** The deplacement. */
    public boolean deplacement;
//    private Projectile p; 
    
  /**
 * Instantiates a new soldat.
 *
 * @param carte the carte
 * @param pts the pts
 * @param portee the portee
 * @param puiss the puiss
 * @param tir the tir
 * @param pos the pos
 * @param aJoue the a joue
 */
public Soldat(Carte carte,int pts, int portee, int puiss, int tir, Position pos, boolean aJoue) {
    	this.carte = carte;
        POINTS_DE_VIE_MAX = pointsDeVie = pts;
        PORTEE_VISUELLE = portee; PUISSANCE = puiss; TIR = tir;
        this.pos = pos;
        this.aJoue = aJoue;
        this.deplacement = false;
  }
    
    /**
     * Se deplace.
     *
     * @param nouvPos the nouv pos
     */
    /* Met a jours les positions du Soldat */
    public void seDeplace(Position nouvPos) {
    	// Supression du soldat a sa position
    	carte.setElementVide(this.getPosition());
    	
    	// definition des nouvelles position
    	this.getPosition().setX(nouvPos.getX());
    	this.getPosition().setY(nouvPos.getY());
    	
    	// Positionnement du soldat sur la carte
    	carte.setElement(this);
    	
    	this.aJoue = true; 
    }
    
    /**
     * Combat.
     *
     * @param soldat the soldat
     */
    /* On fait combatre deux soldats */
    public void combat(Soldat soldat) {
    	int pH, pM;
    	
    	if (!this.getPosition().estVoisine(soldat.getPosition())) {
    		pH = (int) (Math.random() * this.TIR);
			pM = (int) (Math.random() * soldat.TIR);
		}
		else { 
			pH = (int) (Math.random() * this.PUISSANCE);
			pM = (int) (Math.random() * soldat.PUISSANCE);
//			this.p = new Projectile(this.getPosition(), soldat.getPosition(), carte);
		}
    	
    	soldat.pointsDeVie -= pH;
    	if(soldat.pointsDeVie > 0) {
    		if (soldat.dedans(this.getPosition()))
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
    
    /**
     * Dessin barre vie.
     *
     * @param g the g
     * @param cam the cam
     */
    public void dessinBarreVie(Graphics g, Camera cam) {
    	int dx = cam.getDx() * NB_PIX_CASE;
    	int dy = cam.getDy() * NB_PIX_CASE;
    			
    	g.setColor(COULEUR_VIE_R);
 		g.fillRect(((this.pos.getX() * NB_PIX_CASE) - ( Math.min(this.getPointsMax(), NB_PIX_CASE - PADDING_VIE_CASE_LARGEUR) / 2) + NB_PIX_CASE/2) - dx, (this.pos.getY() * NB_PIX_CASE + PADDING_VIE_CASE) - dy, Math.min(this.getPointsMax(), NB_PIX_CASE - PADDING_VIE_CASE_LARGEUR), NB_PIX_CASE/8); 
 		
 		g.setColor(COULEUR_VIE_V);
 		g.fillRect(((this.pos.getX() * NB_PIX_CASE) - ( Math.min(this.getPointsMax(), NB_PIX_CASE - PADDING_VIE_CASE_LARGEUR) / 2) + NB_PIX_CASE/2) - dx, (this.pos.getY() * NB_PIX_CASE + PADDING_VIE_CASE) - dy,  (int) (Math.min(this.getPointsMax(), NB_PIX_CASE - PADDING_VIE_CASE_LARGEUR) * ((float)this.getPoints() / (float)this.getPointsMax())), NB_PIX_CASE/8); 
 		
 		g.setColor(COULEUR_VIDE);
 		g.drawRect(((this.pos.getX() * NB_PIX_CASE) - ( Math.min(this.getPointsMax(), NB_PIX_CASE - PADDING_VIE_CASE_LARGEUR) / 2) + NB_PIX_CASE/2) - dx, (this.pos.getY() * NB_PIX_CASE + PADDING_VIE_CASE) - dy, Math.min(this.getPointsMax(), NB_PIX_CASE - PADDING_VIE_CASE_LARGEUR), NB_PIX_CASE/8); 
    }
       
    /**
     * Dedans.
     *
     * @param position the position
     * @return true, if successful
     */
    public abstract boolean dedans(Position position);
    
    /**
     * Gets the index soldat.
     *
     * @return the index soldat
     */
    public abstract int getIndexSoldat();
    
    /**
     * Mort.
     *
     * @param index the index
     */
    public abstract void mort(int index);
 
    /**
     * Gets the points max.
     *
     * @return the points max
     */
    public int getPointsMax() { return this.POINTS_DE_VIE_MAX; }
    
    /**
     * Sets the points.
     *
     * @param pts the new points
     */
    public void setPoints(int pts) { this.pointsDeVie = pts; } // Utilise pour les bonus (lorsque le heros ce repose)
    
    /**
     * Gets the portee.
     *
     * @return the portee
     */
    public int getPortee() { return this.PORTEE_VISUELLE; }
    
    /**
     * Gets the puissance.
     *
     * @return the puissance
     */
    public int getPuissance() { return this.PUISSANCE; }
    
    /**
     * Gets the points.
     *
     * @return the points
     */
    public int getPoints() { return this.pointsDeVie; }
    
    /**
     * Gets the tir.
     *
     * @return the tir
     */
    public int getTir() { return this.TIR; }
    
    /**
     * Gets the position.
     *
     * @return the position
     */
    public Position getPosition() { return pos; }
	
	/**
	 * Gets the sprite.
	 *
	 * @return the sprite
	 */
	public abstract String getSprite();
	
	/**
	 * Dessine sprite.
	 *
	 * @param g2 the g 2
	 * @param clicDragged the clic dragged
	 * @param cam the cam
	 */
	public abstract void dessineSprite(Graphics g2, Position clicDragged, Camera cam);
}