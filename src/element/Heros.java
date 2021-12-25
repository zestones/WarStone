/********************************************************************
 * 							WarStone								*
 *  -------------------------------------------------------------	*
 * |	 Université Jean-Monnet    L3-Infos 		    2021	 |	*
 *  -------------------------------------------------------------	*
 * 	  BEGGARI ISLEM - CHATAIGNIER ANTOINE - BENGUEZZOU Idriss		*
 * 																	*
 * 															element	*
 * ******************************************************************/
package element;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import carte.Camera;
import carte.Carte;
import sprite.SpriteInitializer;
import sprite.SpriteSheet;
import utile.Position;

/**
 * The Class Heros.
 */
public class Heros extends Soldat {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The champ visuelle. */
	private Position[] champVisuelle = new Position[5];
	
	/** The heros sprite. */
	private transient SpriteInitializer herosSprite;
	
	/** The dernier sprite. */
	public transient SpriteSheet dernierSprite;
    
    /** The bonus repos. */
    private int BONUS_REPOS;
    
    /** The combat. */
    public boolean combat;
    
    /** The nom. */
    String nom;
	
	/** The h. */
	private TypesH h;
    
    /**
     * Instantiates a new heros.
     *
     * @param carte the carte
     * @param h the h
     * @param nom the nom
     * @param pos the pos
     */
    public Heros(Carte carte, TypesH h, String nom, Position pos){
        super(carte, h.getPoints(), h.getPortee(), h.getPuissance(), h.getTir(), pos, false);
        this.h = h;
        this.nom = nom;
        this.combat = false;
                
        carte.setElement(this);;   
        this.BONUS_REPOS = this.getPointsMax() / 10;
        this.herosSprite = new SpriteInitializer(this);
        this.dernierSprite = this.herosSprite.spriteStandByBas;
    }
   
    /**
     * Inits the champ visuelle.
     */
    /* Initialise le "champs visuelle" i.e les positions de la portee du Heros */
    private void initChampVisuelle() {
    	this.champVisuelle[0] = new Position(this.getPosition().getX() - this.getPortee(), this.getPosition().getY() - this.getPortee());
    	this.champVisuelle[1] = new Position(this.getPosition().getX() + this.getPortee(), this.getPosition().getY() - this.getPortee());
     	this.champVisuelle[2] = new Position(this.getPosition().getX() + this.getPortee(), this.getPosition().getY() + this.getPortee());
     	this.champVisuelle[3] = new Position(this.getPosition().getX() - this.getPortee(), this.getPosition().getY() + this.getPortee());
    }
    
    /**
     * Dedans.
     *
     * @param p the p
     * @return true, if successful
     */
    /* Renvoie true si la position pos donnee en parametre est a la portee du Hero 
     * i.e la position est dans sont champVisuelle
     */
	public boolean dedans(Position p) {
		int nbrCotes = this.champVisuelle.length - 1;
		int[] listeAngle = new int[nbrCotes];
			
		this.initChampVisuelle();
		for(int i = 0; i < nbrCotes - 1; i++)
			listeAngle[i] = p.signeAngle(this.champVisuelle[i], this.champVisuelle[i + 1]);
		
		listeAngle[nbrCotes - 1] = p.signeAngle(this.champVisuelle[nbrCotes - 1], this.champVisuelle[0]);
		
		for(int k = 0; k < listeAngle.length; k++) {
			for(int j = 1; j < nbrCotes - 1; j++)
				if(listeAngle[k] != listeAngle[j])
					return false;
		}
		return true;
   }
	
	/**
	 * Dessin heros.
	 *
	 * @param g the g
	 * @param cam the cam
	 */
	/* Methode de dessin du Heros */
    private void dessinHeros(Graphics g, Camera cam) { 
    	int dx = cam.getDx() * NB_PIX_CASE;
    	int dy = cam.getDy() * NB_PIX_CASE;
    	
     	g.drawImage(range, (this.pos.getX() * NB_PIX_CASE) - dx, (this.pos.getY() * NB_PIX_CASE) - dy, NB_PIX_CASE, NB_PIX_CASE, null);
    	
    	this.dessineSprite(g, cam);
    	
        if(this.aJoue == true) {
    		g.setColor(COULEUR_HEROS_DEJA_JOUE);
    		g.fillRect((this.pos.getX() * NB_PIX_CASE) - dx, (this.pos.getY() * NB_PIX_CASE) - dy, NB_PIX_CASE, NB_PIX_CASE); 
        }
     
        this.dessinBarreVie(g, cam);
    }
       
    /**
     * Dessine sprite.
     *
     * @param g the g
     * @param cam the cam
     */
    /* On dessine le Heros */
    private void dessineSprite(Graphics g, Camera cam) {
    	int dx = cam.getDx() * NB_PIX_CASE;
    	int dy = cam.getDy() * NB_PIX_CASE;
    	
    	if(this.herosSprite == null) {
    		this.herosSprite = new SpriteInitializer(this);
    		this.dernierSprite = this.herosSprite.spriteStandByDroite;
    	}
    	
    	BufferedImage sprite = this.dernierSprite.getSprite(spriteEngine.getCycleProgress()); 
		g.drawImage(sprite, (this.pos.getX() * NB_PIX_CASE) - dx, (this.pos.getY() * NB_PIX_CASE) - dy, NB_PIX_CASE, NB_PIX_CASE, null);
    }
    
    /**
     * Dessine sprite.
     *
     * @param g the g
     * @param clic the clic
     * @param cam the cam
     */
    public void dessineSprite(Graphics g, Position clic, Camera cam) {
    	int dx = cam.getDx() * NB_PIX_CASE;
    	int dy = cam.getDy() * NB_PIX_CASE;
    	
//    	System.out.println("Combat : " + this.combat);
    	if(clic == null)
    		return;
    	
    	if(this.herosSprite == null) {
    		this.herosSprite = new SpriteInitializer(this);
    		this.dernierSprite = this.herosSprite.spriteStandByDroite;
    	}
    	
    	BufferedImage sprite = null;    	
    	
    	if(this.combat == false && this.deplacement == false) {
	    	if(clic.getX() < this.getPosition().getX())
	    		this.dernierSprite = this.herosSprite.spriteStandByGauche;
	    	else if(clic.getX() > this.getPosition().getX())
	    		this.dernierSprite = this.herosSprite.spriteStandByDroite;
	      	else if(clic.getY() > this.getPosition().getY())
	      		this.dernierSprite = this.herosSprite.spriteStandByBas;
	    	else if(clic.getY() < this.getPosition().getY())
	    		this.dernierSprite = this.herosSprite.spriteStandByHaut;
    	}
    	else if (this.combat == true){
    		if(clic.getX() < this.getPosition().getX()) 
    			this.dernierSprite = this.herosSprite.spriteAttackGauche;
    		else if(clic.getX() > this.getPosition().getX())
	    		this.dernierSprite = this.herosSprite.spriteAttackDroite;
	      	else if(clic.getY() > this.getPosition().getY())
	      		this.dernierSprite = this.herosSprite.spriteAttackHaut;
	    	else if(clic.getY() < this.getPosition().getY())
	    		this.dernierSprite = this.herosSprite.spriteAttackBas;
    	}
    	else if(this.deplacement == true) {
    		if(clic.getX() < this.getPosition().getX()) {
    			this.dernierSprite = this.herosSprite.spriteDeplaceGauche;
//    			this.deplacement(clic);
    		}
    	}
    	
    	sprite = this.dernierSprite.getSprite(spriteEngine.getCycleProgress());
    	g.drawImage(sprite, this.pos.getX() * NB_PIX_CASE - dx, this.pos.getY() * NB_PIX_CASE - dy, NB_PIX_CASE, NB_PIX_CASE, null);

    	this.dessinBarreVie(g, cam);

    }	
    
//    private void deplacement(Position clic) {
//    	while(this.getPosition().getX() * NB_PIX_CASE != clic.getX() * NB_PIX_CASE && this.getPosition().getY() * NB_PIX_CASE != clic.getY() * NB_PIX_CASE) {
//    		this.getPosition().translater(-5, 0);
//    	}
//    }
    
    
    /**
 * Se dessiner.
 *
 * @param g the g
 * @param cam the cam
 */
/* Methode principale du dessin de Heros:
     * Affichage de la portee visuelle i.e les cases visible par le général 
     */
    public void seDessiner(Graphics g, Camera cam) {
    	int portee = this.getPortee();
    	int dx = cam.getDx() * NB_PIX_CASE;
    	int dy = cam.getDy() * NB_PIX_CASE;
    	
    	for(int i = 0; i <= portee * 2; i++) {
    		for(int j = 0; j <= portee * 2 ; j++) {
    			Position porteeVisuelle = new Position(this.pos.getX() + i - portee, this.pos.getY() + j - portee);
    			if(porteeVisuelle.estValide() == false)
    				continue;
    			
    			if(carte.getElement(porteeVisuelle) == null) 
    				g.drawImage(range, porteeVisuelle.getX() * NB_PIX_CASE  - dx, porteeVisuelle.getY() * NB_PIX_CASE  - dy, NB_PIX_CASE, NB_PIX_CASE, null);
    			else if (carte.getElement(porteeVisuelle) instanceof Monstre)
    				carte.getElement(porteeVisuelle).seDessiner(g, cam);
    			else if(carte.getElement(porteeVisuelle) instanceof Obstacle)
    				carte.getElement(porteeVisuelle).seDessiner(g, cam);
    			
    			g.setColor(COULEUR_GRILLE);
    			g.drawRect((porteeVisuelle.getX() * NB_PIX_CASE) - dx, (porteeVisuelle.getY() * NB_PIX_CASE) - dy, NB_PIX_CASE, NB_PIX_CASE); 
    		}
    	} 
    	this.dessinHeros(g, cam);
    }
    
    /**
     * Dessine selection.
     *
     * @param g the g
     * @param herosSelectione the heros selectione
     * @param clicDragged the clic dragged
     * @param cam the cam
     */
    /* Dessine le Heros selectionne avec sa portee & deplacement */
    public void dessineSelection(Graphics g, Heros herosSelectione, Position clicDragged, Camera cam) {
    	this.dessinePorteeVisuelle(g, cam); 
    	this.dessineDeplacement(g, cam);
    	this.dessineSpriteDeplacement(g, herosSelectione, clicDragged, cam);   	
    }
    
    /**
     * Dessine sprite deplacement.
     *
     * @param g the g
     * @param herosSelectione the heros selectione
     * @param clicDragged the clic dragged
     * @param cam the cam
     */
    private void dessineSpriteDeplacement(Graphics g, Heros herosSelectione, Position clicDragged, Camera cam) {
    	int dx = cam.getDx() * NB_PIX_CASE;
    	int dy = cam.getDy() * NB_PIX_CASE;
    	
    	if(clicDragged != null && clicDragged.estVoisine(herosSelectione.getPosition()) && carte.getElement(clicDragged) == null)
    		g.drawImage(herosSelectione.dernierSprite.getSprite(spriteEngine.getCycleProgress()), clicDragged.getX() * NB_PIX_CASE - dx, clicDragged.getY() * NB_PIX_CASE - dy, NB_PIX_CASE, NB_PIX_CASE, null);
		
	}

	/**
	 * Dessine deplacement.
	 *
	 * @param g the g
	 * @param cam the cam
	 */
	/*Methode de dessin des deplacement possible pour le heros selectionne*/
    private void dessineDeplacement(Graphics g, Camera cam){
    	int dx = cam.getDx() * NB_PIX_CASE;
    	int dy = cam.getDy() * NB_PIX_CASE;
    
    	for(int i = -1; i < 2; i++)
    		for(int j = -1; j < 2; j++) {
    			if(i != 0 || j != 0) {
    				Position posVoisine = new Position(this.pos.getX() + i, this.pos.getY() + j);
    				if(posVoisine.estValide() == false) 
    					continue;
    				
    				if(carte.getElement(posVoisine) == null) {
    					g.setColor(COULEUR_DEPLACEMENT);
    					g.fillRect((this.pos.getX() + i) * NB_PIX_CASE - dx, (this.pos.getY() + j) * NB_PIX_CASE - dy, NB_PIX_CASE, NB_PIX_CASE);
    				}
    			}
    			g.setColor(COULEUR_GRILLE);
    			g.drawRect((this.pos.getX() + i) * NB_PIX_CASE - dx, (this.pos.getY() + j) * NB_PIX_CASE - dy, NB_PIX_CASE, NB_PIX_CASE);
    		}   	  
    }
    
    /**
     * Dessine portee visuelle.
     *
     * @param g the g
     * @param cam the cam
     */
    /* Dessine la portee visuelle du heros selectionne*/
    private void dessinePorteeVisuelle(Graphics g, Camera cam) {
    	int portee = this.getPortee();
    	int dx = cam.getDx() * NB_PIX_CASE;
    	int dy = cam.getDy() * NB_PIX_CASE;
    
    	for(int i = 0; i <= portee * 2; i++) {
    		for(int j = 0; j <= portee  * 2 ; j++) {
    			Position porteeVisuelle = new Position(this.pos.getX() + i - portee, this.pos.getY() + j - portee);
    			if(porteeVisuelle.estValide() == false) 
    				continue;
    			
    			if(carte.getElement(porteeVisuelle) == null) {
    				g.setColor(COULEUR_PORTEE);
    				g.fillRect(porteeVisuelle.getX() * NB_PIX_CASE - dx, porteeVisuelle.getY() * NB_PIX_CASE - dy, NB_PIX_CASE, NB_PIX_CASE); 
    			}
    			else if(carte.getElement(porteeVisuelle) instanceof Monstre) {
    				g.setColor(COULEUR_ENEMIS);
    				g.fillRect(porteeVisuelle.getX() * NB_PIX_CASE - dx, porteeVisuelle.getY() * NB_PIX_CASE - dy, NB_PIX_CASE, NB_PIX_CASE); 
    			}
    			else if(carte.getElement(porteeVisuelle) instanceof Heros && carte.getElement(porteeVisuelle) != this) {
    				g.setColor(COULEUR_AMIS);
    				g.fillRect(porteeVisuelle.getX() * NB_PIX_CASE - dx, porteeVisuelle.getY() * NB_PIX_CASE - dy, NB_PIX_CASE, NB_PIX_CASE); 
    			}
    			
    			g.setColor(COULEUR_GRILLE);
    			g.drawRect(porteeVisuelle.getX() * NB_PIX_CASE - dx, porteeVisuelle.getY() * NB_PIX_CASE - dy, NB_PIX_CASE, NB_PIX_CASE); 
    		}
    	}	
    }
    
    /**
     * Repos.
     */
    /*Si le Heros n'a effectue aucune action durant le tour alors il obtient un bonus de vie*/
    public void repos() {
    	if(this.aJoue == false && this.getPoints() + BONUS_REPOS < this.getPointsMax())
    		this.setPoints(this.getPoints() + BONUS_REPOS);
    	else if (this.aJoue == false && this.getPoints() + BONUS_REPOS > this.getPointsMax())
    		this.setPoints(this.getPointsMax());
    }

    /**
     * Gets the index soldat.
     *
     * @return the index soldat
     */
    public int getIndexSoldat() { return carte.listeHeros.indexOf(this); }
    
    /**
     * Gets the points max.
     *
     * @return the points max
     */
    public int getPointsMax() { return this.h.getPoints(); }
	
	/**
	 * Mort.
	 *
	 * @param index the index
	 */
	public void mort(int index) { carte.listeHeros.remove(index); }

    /**
     * To string.
     *
     * @return the string
     */
    /* Affichage des infos du Heros */
    public String toString() {
    	return this.getPosition().toString() + " " + this.h.name() + " " + this.nom + " (" + this.h.getPoints() + "PV /" + this.getPoints() + ")";
    }
    
	/**
	 * Se dessiner minia.
	 *
	 * @param g the g
	 */
	public void seDessinerMinia(Graphics g) {
		int portee = h.getPortee();
		
    	for(int i = 0; i <= portee * 2; i++) {
    		for(int j = 0; j <= portee * 2 ; j++) {
    			Position porteeVisuelle = new Position(this.getPosition().getX() + i - portee, this.getPosition().getY() + j - portee);
    			if(porteeVisuelle.estValide() == false)
    				continue;
    			
    			if(this.carte.getElement(porteeVisuelle) == null) 
    				g.drawImage(range, porteeVisuelle.getX() * MINI_NB_PIX_CASE, porteeVisuelle.getY() * MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, null);
    			else if (carte.getElement(porteeVisuelle) instanceof Monstre)
    				carte.getElement(porteeVisuelle).seDessinerMinia(g);
    			else if(carte.getElement(porteeVisuelle) instanceof Obstacle)
    				carte.getElement(porteeVisuelle).seDessinerMinia(g);
    			
    			g.setColor(COULEUR_GRILLE);
    			g.drawRect(porteeVisuelle.getX() * MINI_NB_PIX_CASE, porteeVisuelle.getY() * MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, MINI_NB_PIX_CASE); 
    		}
    	} 
    	this.dessinHerosMinia(g);
    }

	/**
	 * Dessin heros minia.
	 *
	 * @param g the g
	 */
	/* Methode de dessin du Heros */
    private void dessinHerosMinia(Graphics g) { 
    	g.drawImage(range, this.getPosition().getX() * MINI_NB_PIX_CASE, this.getPosition().getY() * MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, null);	
    	g.drawImage(this.getImage(), this.getPosition().getX() * MINI_NB_PIX_CASE, this.getPosition().getY() * MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, null);	
    }


	/**
	 * Gets the sprite path.
	 *
	 * @return the sprite
	 */
	public String getSprite() { return this.h.getSprite(); }
	
	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public Image getImage() {return this.h.getImage(); }
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() { return ""+this.h.name(); }
}