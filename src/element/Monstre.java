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
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import carte.Camera;
import carte.Carte;
import sprite.SpriteInitializer;
import utile.Position;

public class Monstre extends Soldat {
	private static final long serialVersionUID = 1L;

	TypesM m;
    String nom;
    
	public Monstre(Carte carte, TypesM m, String nom,Position pos){
        super(carte, m.getPoints(), m.getPortee(), m.getPuissance(), m.getTir(), pos);
        this.m = m;
        this.nom = nom;
        this.combat = false;
        this.soldatSprite = new SpriteInitializer(this);
        this.dernierSprite = this.soldatSprite.spriteStandByBas;
        carte.setElement(this);      
    }
	
	public void seDessiner(Graphics g, Camera cam) { 
    	int dx = cam.getDx() * NB_PIX_CASE;
    	int dy = cam.getDy() * NB_PIX_CASE;
    	g.drawImage(range, (this.getPosition().getX() * NB_PIX_CASE) - dx, (this.getPosition().getY() * NB_PIX_CASE) - dy, NB_PIX_CASE, NB_PIX_CASE, null);

    	g.setColor(COULEUR_GRILLE);
		g.drawRect(this.getPosition().getX() * NB_PIX_CASE - dx, this.getPosition().getY() * NB_PIX_CASE - dy, NB_PIX_CASE, NB_PIX_CASE); 
		    	
    	this.dessineSprite(g, cam);
    	
    	if(!this.activeDeplacement) {
    		this.dessinBarreVie(g, cam);
    		g.setColor(COULEUR_MONSTRE);
    		g.fillRect((this.getPosition().getX()  * NB_PIX_CASE) - dx, (this.getPosition().getY() * NB_PIX_CASE) - dy, NB_PIX_CASE, NB_PIX_CASE);
    	}
    }
    
  public void seDessinerMinia(Graphics g) { 
    	g.drawImage(range, this.getPosition().getX() * MINI_NB_PIX_CASE, this.getPosition().getY() * MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, null);   	
    	
    	g.drawImage(this.getImage(), this.getPosition().getX() * MINI_NB_PIX_CASE, this.getPosition().getY() * MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, null);   	

    	g.setColor(COULEUR_MONSTRE);
    	g.fillRect(this.getPosition().getX()  * MINI_NB_PIX_CASE , this.getPosition().getY() * MINI_NB_PIX_CASE , MINI_NB_PIX_CASE, MINI_NB_PIX_CASE);

    }
    
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
    
  public int getIndexSoldat() { return carte.listeMonstres.indexOf(this); }
  public void mort(int index) { carte.listeMonstres.remove(index); }
    
  public String toString() { 
	  return this.getPosition().toString() + " " + this.m.name() + " " + this.nom + " (" + this.m.getPoints() + "PV /" + this.getPoints() + ")";
  } 
    
  public String getSprite() { return this.m.getSprite(); }     
  public Image getImage() { return this.m.getImage(); }
  public String getType() { return this.m.name(); }
}