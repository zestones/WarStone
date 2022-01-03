package element;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import carte.Camera;
import carte.Carte;
import sprite.SpriteInitializer;
import utile.Position;

/**
 * Class Monstre.
 */
public class Monstre extends Soldat {
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** le type. */
	TypesM m;
    
	/**
	 * Instancie un nouveau monstre.
	 *
	 * @param carte 
	 * @param m 
	 * @param nom 
	 * @param pos
	 */
	public Monstre(Carte carte, TypesM m, Position pos){
        super(carte, m.getPoints(), m.getPortee(), m.getPuissance(), m.getTir(), pos);
        this.m = m;
        this.combat = false;
        this.soldatSprite = new SpriteInitializer(this);
        this.dernierSprite = this.soldatSprite.spriteStandByBas;
        carte.setElement(this);      
    }
	
	/**
	 * Se dessiner.
	 *
	 * @param g 
	 * @param cam 
	 */
	public void seDessiner(Graphics g, Camera cam) { 
    	int dx = cam.getDx() * NB_PIX_CASE;
    	int dy = cam.getDy() * NB_PIX_CASE;

    	/** Dessin de la case du monstre */
    	g.drawImage(range, (this.getPosition().getX() * NB_PIX_CASE) - dx, (this.getPosition().getY() * NB_PIX_CASE) - dy, NB_PIX_CASE, NB_PIX_CASE, null);
    	/** On dessine la grille de cette case */
    	g.setColor(COULEUR_GRILLE);
		g.drawRect(this.getPosition().getX() * NB_PIX_CASE - dx, this.getPosition().getY() * NB_PIX_CASE - dy, NB_PIX_CASE, NB_PIX_CASE); 
		    
		/** Dessin du sprite du monstre */
    	this.dessineSprite(g, cam);
    	
    	/** Dessin de la barre de vie ainsi que le fond du monstre uniquement si le monstre ne se deplace pas */
    	if(!this.activeDeplacement) {
    		this.dessinBarreVie(g, cam);
    		g.setColor(COULEUR_MONSTRE);
    		g.fillRect((this.getPosition().getX()  * NB_PIX_CASE) - dx, (this.getPosition().getY() * NB_PIX_CASE) - dy, NB_PIX_CASE, NB_PIX_CASE);
    	}
    }
    
  /**
   * dessine le monstre dans la miniCarte.
   *
   * @param g 
   */
  public void seDessinerMinia(Graphics g) { 
    	g.drawImage(range, this.getPosition().getX() * MINI_NB_PIX_CASE, this.getPosition().getY() * MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, null);   	
    	
    	g.drawImage(this.getImage(), this.getPosition().getX() * MINI_NB_PIX_CASE, this.getPosition().getY() * MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, null);   	

    	g.setColor(COULEUR_MONSTRE);
    	g.fillRect(this.getPosition().getX()  * MINI_NB_PIX_CASE , this.getPosition().getY() * MINI_NB_PIX_CASE , MINI_NB_PIX_CASE, MINI_NB_PIX_CASE);

    }
    
  /**
   * Gets list heros in range.
   *
   * Retourne une liste de heros present dans la portee du monstre
   *
   * @return list heros in range
   */
  public List<Heros> getListHerosInRange(){
		List<Heros> listeHeros = new ArrayList<>();
		int portee = this.getPortee();

		for(int i = 0; i <= portee * 2; i++) {
			for(int j = 0; j <= portee * 2 ; j++) {
				Position porteeVisuelle = new Position(this.getPosition().getX() + i - portee, this.getPosition().getY() + j - portee);
				if(porteeVisuelle.estValide() == false)
    				continue; 
				if(carte.getElement(porteeVisuelle) instanceof Heros) 
					listeHeros.add((Heros)carte.getElement(porteeVisuelle));
			}
		}
		return listeHeros;
    }
    
  /**
   * Gets index soldat.
   *
   * renvoie la position du minstre dans la liste de monstres
   *
   * @return index soldat
   */
  public int getIndexSoldat() { return carte.listeMonstres.indexOf(this); }
  
  /**
   * Mort.
   *
   * @param index
   */
  public void mort(int index) { carte.listeMonstres.remove(index); }
    
  /**
   * To string.
   *
   * @return string
   */
  public String toString() { 
	  return this.getPosition().toString() + " " + this.m.name() + " (" + this.m.getPoints() + "PV /" + this.getPoints() + ")";
  } 
  
  /**
   * Gets histoire.
   *
   * @return histoire
   */
  public String getHistoire() {return this.m.getHistoire();}
  
  /**
   * Gets sprite.
   *
   * @return sprite
   */
  public String getSprite() { return this.m.getSprite(); }     
  
  /**
   * Gets image.
   *
   * @return image
   */
  public Image getImage() { return this.m.getImage(); }
  
  /**
   * Gets type.
   *
   * @return type
   */
  public String getType() { return this.m.name(); }
}