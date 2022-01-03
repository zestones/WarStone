package element;

import java.awt.Graphics;
import java.awt.Image;

import carte.Camera;
import carte.Carte;
import sprite.SpriteInitializer;
import utile.Position;

/**
 * Class Heros.
 */
public class Heros extends Soldat {
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
    /** bonus repos. */
    private int BONUS_REPOS;
    
    /** nom. */
    String nom;
	
	/** le type. */
	private TypesH h;
	
    /**
     * Instancie un nouveau heros.
     *
     * @param carte 
     * @param h 
     * @param nom 
     * @param pos 
     */
    public Heros(Carte carte, TypesH h, String nom, Position pos){
    	super(carte, h.getPoints(), h.getPortee(), h.getPuissance(), h.getTir(), pos);
        this.h = h;
    	this.nom = nom;
        carte.setElement(this);
       
        this.BONUS_REPOS = this.getPointsMax() / 10;
        this.soldatSprite = new SpriteInitializer(this);
        this.dernierSprite = this.soldatSprite.spriteStandByBas;
    }
   
    /**
     * Dessin du heros.
     *
     * @param g 
     * @param cam 
     */
    private void dessinHeros(Graphics g, Camera cam) { 
    	int dx = cam.getDx() * NB_PIX_CASE;
    	int dy = cam.getDy() * NB_PIX_CASE;
    	
    	/** Dessin de la case du heros */
    	g.drawImage(range, (this.getPosition().getX() * NB_PIX_CASE) - dx, (this.getPosition().getY() * NB_PIX_CASE) - dy, NB_PIX_CASE, NB_PIX_CASE, null);
    	/** On dessine la grille de cette case */
    	g.setColor(COULEUR_GRILLE);
		g.drawRect(this.getPosition().getX() * NB_PIX_CASE - dx, this.getPosition().getY() * NB_PIX_CASE - dy, NB_PIX_CASE, NB_PIX_CASE); 
		
		/** Dessin du sprite du heros */
    	this.dessineSprite(g, cam);
    	
    	/** Si le heros a deja effectuer une action on lui ajoute une couleur */
    	if(this.aJoue && !this.activeDeplacement) {
    		g.setColor(COULEUR_HEROS_DEJA_JOUE);
    		g.fillRect((this.getPosition().getX() * NB_PIX_CASE) - dx + this.deplacementX, (this.getPosition().getY() * NB_PIX_CASE) - dy + this.deplacementY, NB_PIX_CASE, NB_PIX_CASE); 
        }
        /** La barre de vie n'est pas dessiner si le heros se deplace */
        if(!this.activeDeplacement)
        	this.dessinBarreVie(g, cam);
    }
    
    /**
     * Desssine la zone du heros.
     *
     * @param g 
     * @param cam
     */
    public void desssinerZone(Graphics g, Camera cam) {
    	int portee = this.getPortee();
    	int dx = cam.getDx() * NB_PIX_CASE;
    	int dy = cam.getDy() * NB_PIX_CASE;
    	    	
    	for(int i = 0; i <= portee * 2; i++) {
    		for(int j = 0; j <= portee * 2 ; j++) {
    			Position porteeVisuelle = new Position(this.getPosition().getX() + i - portee, this.getPosition().getY() + j - portee);
    			if(porteeVisuelle.estValide() == false)
    				continue;
    			
    			if(carte.getElement(porteeVisuelle) == null) 
    				g.drawImage(range, porteeVisuelle.getX() * NB_PIX_CASE  - dx, porteeVisuelle.getY() * NB_PIX_CASE - dy, NB_PIX_CASE, NB_PIX_CASE, null);
    			else if(carte.getElement(porteeVisuelle) instanceof Obstacle)
    				carte.getElement(porteeVisuelle).seDessiner(g, cam);   			
    		}
    	}
    }
    
    /**
     * Se dessiner.
     *
     * @param g the g
     * @param cam the cam
     */
    public void seDessiner(Graphics g, Camera cam) {
    	this.dessinHeros(g, cam);
    }
    
    /**
     * Dessine selection.
     *
     * @param g 
     * @param herosSelectione 
     * @param clic 
     * @param cam 
     */
    public void dessineSelection(Graphics g, Heros herosSelectione, Position clic, Camera cam) {
    	/** dessin de la portee visuelle */
    	this.dessinePorteeVisuelle(g, cam);
    	/** dessin de la zone de deplacement */
    	this.dessineDeplacement(g, cam);
    	/** dessin des sprite dans la zonde de deplacement */
    	this.dessineSpriteDeplacement(g, herosSelectione, clic, cam);   	
    }
    
    /**
     * Dessine les sprite dans la zone de deplacement en fonction de la position de la souris.
     *
     * @param g 
     * @param herosSelectione
     * @param clic
     * @param cam 
     */
    private void dessineSpriteDeplacement(Graphics g, Heros herosSelectione, Position clic, Camera cam) {
    	int dx = cam.getDx() * NB_PIX_CASE;
    	int dy = cam.getDy() * NB_PIX_CASE;
    	
    	/** On dessine le sprite du heros selectione si la souris se trouve dans la zone de deplacement du heros et que la case est vide */
    	if(clic != null && clic.estVoisine(herosSelectione.getPosition()) && carte.estCaseVide(clic))
    		g.drawImage(herosSelectione.dernierSprite.getSprite(spriteEngine.getCycleProgress()), clic.getX() * NB_PIX_CASE - dx, clic.getY() * NB_PIX_CASE - dy, NB_PIX_CASE, NB_PIX_CASE, null);
    }

	/**
	 * Dessine la zone de deplacement.
	 *
	 * @param g 
	 * @param cam 
	 */
	private void dessineDeplacement(Graphics g, Camera cam){
    	int dx = cam.getDx() * NB_PIX_CASE;
    	int dy = cam.getDy() * NB_PIX_CASE;
    	
    	for(int i = -1; i < 2; i++)
    		for(int j = -1; j < 2; j++) {
    			if(i != 0 || j != 0) {
    				Position posVoisine = new Position(this.getPosition().getX() + i, this.getPosition().getY() + j);
    				if(!posVoisine.estValide()) 
    					continue;
    				
    				if(carte.estCaseVide(posVoisine)) {
    					g.setColor(COULEUR_DEPLACEMENT);
    					g.fillRect((this.getPosition().getX() + i) * NB_PIX_CASE - dx + this.deplacementX, (this.getPosition().getY() + j) * NB_PIX_CASE - dy + this.deplacementY, NB_PIX_CASE, NB_PIX_CASE);
    				}
    			}
    		}   	  
	}
    
	/**
	 * Dessine la portee visuelle.
	 *
	 * @param g 
	 * @param cam 
	 */
	private void dessinePorteeVisuelle(Graphics g, Camera cam) {
    	int portee = this.getPortee();
    	int dx = cam.getDx() * NB_PIX_CASE;
    	int dy = cam.getDy() * NB_PIX_CASE;
    
    	for(int i = 0; i <= portee * 2; i++) {
    		for(int j = 0; j <= portee  * 2 ; j++) {
    			Position porteeVisuelle = new Position(this.getPosition().getX() + i - portee, this.getPosition().getY() + j - portee);
    			if(!porteeVisuelle.estValide()) 
    				continue;
    			
    			if(carte.estCaseVide(porteeVisuelle)) {
    				g.setColor(COULEUR_PORTEE);
    				g.fillRect(porteeVisuelle.getX() * NB_PIX_CASE - dx + this.deplacementX, porteeVisuelle.getY() * NB_PIX_CASE - dy + this.deplacementY, NB_PIX_CASE, NB_PIX_CASE); 
    			}
    			else if(carte.getElement(porteeVisuelle) instanceof Monstre) {
    				g.setColor(COULEUR_ENEMIS);
    				g.fillRect(porteeVisuelle.getX() * NB_PIX_CASE - dx + this.deplacementX, porteeVisuelle.getY() * NB_PIX_CASE - dy + this.deplacementY, NB_PIX_CASE, NB_PIX_CASE); 
    			}
    			else if(carte.getElement(porteeVisuelle) instanceof Heros && carte.getElement(porteeVisuelle) != this) {
    				g.setColor(COULEUR_AMIS);
    				g.fillRect(porteeVisuelle.getX() * NB_PIX_CASE - dx + this.deplacementX, porteeVisuelle.getY() * NB_PIX_CASE - dy + this.deplacementY, NB_PIX_CASE, NB_PIX_CASE); 
    			}
    		}
    	}
	}
	
	/**
	 * dessin des elements dans la carte miniature
	 *
	 * @param g 
	 */
	public void seDessinerMinia(Graphics g) {
		int portee = h.getPortee();
		
    	for(int i = 0; i <= portee * 2; i++) {
    		for(int j = 0; j <= portee * 2 ; j++) {
    			Position porteeVisuelle = new Position(this.getPosition().getX() + i - portee, this.getPosition().getY() + j - portee);
    			if(!porteeVisuelle.estValide())
    				continue;
    			
    			if(this.carte.estCaseVide(porteeVisuelle)) 
    				g.drawImage(range, porteeVisuelle.getX() * MINI_NB_PIX_CASE, porteeVisuelle.getY() * MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, null);
    			else if (carte.getElement(porteeVisuelle) instanceof Monstre)
    				carte.getElement(porteeVisuelle).seDessinerMinia(g);
    			else if(carte.getElement(porteeVisuelle) instanceof Obstacle)
    				carte.getElement(porteeVisuelle).seDessinerMinia(g);
    		}
    	} 
    	this.dessinHerosMinia(g);
    }
    
    /**
     * Dessin du heros dans la miniCarte.
     *
     * @param g 
     */
    private void dessinHerosMinia(Graphics g) { 
    	g.drawImage(range, this.getPosition().getX() * MINI_NB_PIX_CASE, this.getPosition().getY() * MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, null);	
    	g.drawImage(this.getImage(), this.getPosition().getX() * MINI_NB_PIX_CASE, this.getPosition().getY() * MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, null);	
    }
    
	/**
	 * Repos.
	 */
	public void repos() {
    	if(!this.aJoue && this.getPoints() + BONUS_REPOS < this.getPointsMax())
    		this.setPoints(this.getPoints() + BONUS_REPOS);
    	else if (!this.aJoue && this.getPoints() + BONUS_REPOS > this.getPointsMax())
    		this.setPoints(this.getPointsMax());
    }

	/**
	 * Gets index soldat.
	 * 
	 * renvoie la position du heros dans la liste de heros
	 * 
	 * @return index soldat
	 */
	public int getIndexSoldat() { return carte.listeHeros.indexOf(this); }
	
	/**
	 * Gets points max.
	 *
	 * @return points max
	 */
	public int getPointsMax() { return this.h.getPoints(); }
	
	/**
	 * Gets histoire.
	 *
	 * @return histoire
	 */
	public String getHistoire() {return this.h.getHistoire();}
	
	/**
	 * Mort.
	 *
	 * @param index the index
	 */
	public void mort(int index) { carte.listeHeros.remove(index); }  
    
    /**
     * Gets sprite.
     *
     * @return sprite
     */
    public String getSprite() { return h.getSprite(); }
	
	/**
	 * Gets image.
	 *
	 * @return image
	 */
	public Image getImage() {return this.h.getImage(); }
	
	/**
	 * Gets type.
	 *
	 * @return type
	 */
	public String getType() { return ""+this.h.name(); }
	
	/**
	 * To string.
	 *
	 * @return string
	 */
	public String toString() {
		return this.getPosition().toString() + " " + this.h.name() + " " + this.nom + " (" + this.h.getPoints() + "PV /" + this.getPoints() + ")";
	}
}