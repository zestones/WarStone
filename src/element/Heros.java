/********************************************************************
 * 							WarStone								*
 *  -------------------------------------------------------------	*
 * |	 Universit� Jean-Monnet    L3-Infos 		    2021	 |	*
 *  -------------------------------------------------------------	*
 * 	  BEGGARI ISLEM - CHATAIGNIER ANTOINE - BENGUEZZOU Idriss		*
 * 																	*
 * 															element	*
 * ******************************************************************/
package element;

import java.awt.Graphics;
import java.awt.Image;

import carte.Camera;
import carte.Carte;
import sprite.SpriteInitializer;
import utile.Position;

public class Heros extends Soldat {
	private static final long serialVersionUID = 1L;
	
    private int BONUS_REPOS;
    String nom;
	private TypesH h;
	
    public Heros(Carte carte, TypesH h, String nom, Position pos){
    	super(carte, h.getPoints(), h.getPortee(), h.getPuissance(), h.getTir(), pos);
        this.h = h;
    	this.nom = nom;
        carte.setElement(this);
       
        this.BONUS_REPOS = this.getPointsMax() / 10;
        this.soldatSprite = new SpriteInitializer(this);
        this.dernierSprite = this.soldatSprite.spriteStandByBas;
    }
   
    private void dessinHeros(Graphics g, Camera cam) { 
    	int dx = cam.getDx() * NB_PIX_CASE;
    	int dy = cam.getDy() * NB_PIX_CASE;
    	
    	g.drawImage(range, (this.getPosition().getX() * NB_PIX_CASE) - dx, (this.getPosition().getY() * NB_PIX_CASE) - dy, NB_PIX_CASE, NB_PIX_CASE, null);
    	
    	g.setColor(COULEUR_GRILLE);
		g.drawRect(this.getPosition().getX() * NB_PIX_CASE - dx, this.getPosition().getY() * NB_PIX_CASE - dy, NB_PIX_CASE, NB_PIX_CASE); 
		
    	this.dessineSprite(g, cam);

    	if(this.aJoue && !this.activeDeplacement) {
    		g.setColor(COULEUR_HEROS_DEJA_JOUE);
    		g.fillRect((this.getPosition().getX() * NB_PIX_CASE) - dx + this.deplacementX, (this.getPosition().getY() * NB_PIX_CASE) - dy + this.deplacementY, NB_PIX_CASE, NB_PIX_CASE); 
        }
        
        if(!this.activeDeplacement)
        	this.dessinBarreVie(g, cam);
    }
    
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
    
    public void seDessiner(Graphics g, Camera cam) {
    	this.dessinHeros(g, cam);
    }
    
    public void dessineSelection(Graphics g, Heros herosSelectione, Position clicDragged, Camera cam) {
    	this.dessinePorteeVisuelle(g, cam); 
    	this.dessineDeplacement(g, cam);
    	this.dessineSpriteDeplacement(g, herosSelectione, clicDragged, cam);   	
    }
    
    private void dessineSpriteDeplacement(Graphics g, Heros herosSelectione, Position clicDragged, Camera cam) {
    	int dx = cam.getDx() * NB_PIX_CASE;
    	int dy = cam.getDy() * NB_PIX_CASE;
    	
    	if(clicDragged != null && clicDragged.estVoisine(herosSelectione.getPosition()) && carte.getElement(clicDragged) == null)
    		g.drawImage(herosSelectione.dernierSprite.getSprite(spriteEngine.getCycleProgress()), clicDragged.getX() * NB_PIX_CASE - dx, clicDragged.getY() * NB_PIX_CASE - dy, NB_PIX_CASE, NB_PIX_CASE, null);
    }

	private void dessineDeplacement(Graphics g, Camera cam){
    	int dx = cam.getDx() * NB_PIX_CASE;
    	int dy = cam.getDy() * NB_PIX_CASE;
    	
    	for(int i = -1; i < 2; i++)
    		for(int j = -1; j < 2; j++) {
    			if(i != 0 || j != 0) {
    				Position posVoisine = new Position(this.getPosition().getX() + i, this.getPosition().getY() + j);
    				if(posVoisine.estValide() == false) 
    					continue;
    				
    				if(carte.getElement(posVoisine) == null) {
    					g.setColor(COULEUR_DEPLACEMENT);
    					g.fillRect((this.getPosition().getX() + i) * NB_PIX_CASE - dx + this.deplacementX, (this.getPosition().getY() + j) * NB_PIX_CASE - dy + this.deplacementY, NB_PIX_CASE, NB_PIX_CASE);
    				}
    			}
    		}   	  
	}
    
	private void dessinePorteeVisuelle(Graphics g, Camera cam) {
    	int portee = this.getPortee();
    	int dx = cam.getDx() * NB_PIX_CASE;
    	int dy = cam.getDy() * NB_PIX_CASE;
    
    	for(int i = 0; i <= portee * 2; i++) {
    		for(int j = 0; j <= portee  * 2 ; j++) {
    			Position porteeVisuelle = new Position(this.getPosition().getX() + i - portee, this.getPosition().getY() + j - portee);
    			if(porteeVisuelle.estValide() == false) 
    				continue;
    			
    			if(carte.getElement(porteeVisuelle) == null) {
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
    		}
    	} 
    	this.dessinHerosMinia(g);
    }
    
    private void dessinHerosMinia(Graphics g) { 
    	g.drawImage(range, this.getPosition().getX() * MINI_NB_PIX_CASE, this.getPosition().getY() * MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, null);	
    	g.drawImage(this.getImage(), this.getPosition().getX() * MINI_NB_PIX_CASE, this.getPosition().getY() * MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, null);	
    }
    
	public void repos() {
    	if(this.aJoue == false && this.getPoints() + BONUS_REPOS < this.getPointsMax())
    		this.setPoints(this.getPoints() + BONUS_REPOS);
    	else if (this.aJoue == false && this.getPoints() + BONUS_REPOS > this.getPointsMax())
    		this.setPoints(this.getPointsMax());
    }

	public int getIndexSoldat() { return carte.listeHeros.indexOf(this); }
	public int getPointsMax() { return this.h.getPoints(); }
	public String getHistoire() {return this.h.getHistoire();}
	public void mort(int index) { carte.listeHeros.remove(index); }  
    public String getSprite() { return h.getSprite(); }
	public Image getImage() {return this.h.getImage(); }
	public String getType() { return ""+this.h.name(); }
	public String toString() {
		return this.getPosition().toString() + " " + this.h.name() + " " + this.nom + " (" + this.h.getPoints() + "PV /" + this.getPoints() + ")";
	}
}