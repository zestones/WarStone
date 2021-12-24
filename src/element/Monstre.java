package element;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import carte.Camera;
import carte.Carte;
import sprite.SpriteInitializer;
import sprite.SpriteSheet;
import utile.Position;

public class Monstre extends Soldat {
	private static final long serialVersionUID = 1L;
	TypesM m;
    String nom;
    private Position[] champVisuelle = new Position[5];
    private transient SpriteInitializer monstreSprite;
	public transient SpriteSheet dernierSprite;
    public boolean combat;

    public Monstre(Carte carte, TypesM m, String nom,Position pos){
        super(carte, m.getPoints(), m.getPortee(), m.getPuissance(), m.getTir(), pos, false);
        this.m = m;
        this.nom = nom;
        this.combat = false;
        
        carte.setElement(this);
        this.monstreSprite = new SpriteInitializer(this);
        this.initChampVisuelle();
        
        this.dernierSprite = this.monstreSprite.spriteStandByBas;
    }
    
    private void dessineSprite(Graphics g, Camera cam) {
    	if(this.monstreSprite == null) {
    		this.monstreSprite = new SpriteInitializer(this);
			this.dernierSprite = this.monstreSprite.spriteStandByDroite;
    	}
    	BufferedImage sprite = this.dernierSprite.getSprite(spriteEngine.getCycleProgress());
		g.drawImage(sprite, (this.pos.getX() * NB_PIX_CASE) - cam.getDx() * NB_PIX_CASE ,(this.pos.getY() * NB_PIX_CASE) - cam.getDy() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE, null);
    }
    
    public void dessineSprite(Graphics g, Position clic, Camera cam) {
//    	System.out.println("Combat : " + this.combat);
    	if(clic == null)
    		return;
    	
    	if(this.monstreSprite == null) {
    		this.monstreSprite = new SpriteInitializer(this);
    		this.dernierSprite = this.monstreSprite.spriteStandByDroite;
    	}
    	
    	BufferedImage sprite = null;    	
    	
    	if(this.combat == false && this.deplacement == false) {
	    	if(clic.getX() < this.getPosition().getX())
	    		this.dernierSprite = this.monstreSprite.spriteStandByGauche;
	    	else if(clic.getX() > this.getPosition().getX())
	    		this.dernierSprite = this.monstreSprite.spriteStandByDroite;
	      	else if(clic.getY() > this.getPosition().getY())
	      		this.dernierSprite = this.monstreSprite.spriteStandByBas;
	    	else if(clic.getY() < this.getPosition().getY())
	    		this.dernierSprite = this.monstreSprite.spriteStandByHaut;
    	}
    	else if (this.combat == true){
    		if(clic.getX() < this.getPosition().getX()) 
    			this.dernierSprite = this.monstreSprite.spriteAttackGauche;
    		else if(clic.getX() > this.getPosition().getX())
	    		this.dernierSprite = this.monstreSprite.spriteAttackDroite;
	      	else if(clic.getY() > this.getPosition().getY())
	      		this.dernierSprite = this.monstreSprite.spriteAttackHaut;
	    	else if(clic.getY() < this.getPosition().getY())
	    		this.dernierSprite = this.monstreSprite.spriteAttackBas;
    	}
    	else if(this.deplacement == true) {
    		if(clic.getX() < this.getPosition().getX()) {
    			this.dernierSprite = this.monstreSprite.spriteDeplaceGauche;
//    			this.deplacement(clic);
    		}
    	}
    	sprite = this.dernierSprite.getSprite(spriteEngine.getCycleProgress());
    	g.drawImage(sprite, (this.pos.getX() * NB_PIX_CASE) - cam.getDx() * NB_PIX_CASE, (this.pos.getY() * NB_PIX_CASE) - cam.getDy() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE, null);

    }
    
    /* Initialise le "champs visuelle" i.e les positions de la portee du Heros */
    private void initChampVisuelle() {
    	this.champVisuelle[0] = new Position(this.getPosition().getX() - this.getPortee(), this.getPosition().getY() - this.getPortee());
    	this.champVisuelle[1] = new Position(this.getPosition().getX() + this.getPortee(), this.getPosition().getY() - this.getPortee());
     	this.champVisuelle[2] = new Position(this.getPosition().getX() + this.getPortee(), this.getPosition().getY() + this.getPortee());
     	this.champVisuelle[3] = new Position(this.getPosition().getX() - this.getPortee(), this.getPosition().getY() + this.getPortee());
    }
    
    /* Renvoie true si la position pos donnee en parametre est a la portee du Monstre 
     * i.e la position est dans sont champVisuelle
     */
	public boolean dedans(Position p) {
		int nbrCotes = this.champVisuelle.length - 1;
		int[] listeAngle = new int[nbrCotes];
			
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
	    
    /* Dessin du Monstre sur la carte */
    public void seDessiner(Graphics g, Camera cam) { 

    	g.drawImage(range, (this.pos.getX() * NB_PIX_CASE) - cam.getDx() * NB_PIX_CASE, (this.pos.getY() * NB_PIX_CASE) - cam.getDy() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE, null);

    	this.dessineSprite(g, cam);
    	this.dessinBarreVie(g, cam);
    	
    	g.setColor(COULEUR_MONSTRE);
		g.fillRect((this.pos.getX()  * NB_PIX_CASE) - cam.getDx() * NB_PIX_CASE, (this.pos.getY() * NB_PIX_CASE) - cam.getDy() * NB_PIX_CASE , NB_PIX_CASE, NB_PIX_CASE);
    }
    
    /* Dessin du Monstre sur la carte */
    public void seDessinerMinia(Graphics g) { 
    	g.drawImage(range, this.pos.getX() * MINI_NB_PIX_CASE, this.pos.getY() * MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, null);   	
    	
    	g.drawImage(this.getImage(), this.pos.getX() * MINI_NB_PIX_CASE, this.pos.getY() * MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, MINI_NB_PIX_CASE, null);   	

    	g.setColor(COULEUR_MONSTRE);
    	g.fillRect(this.pos.getX()  * MINI_NB_PIX_CASE , this.pos.getY() * MINI_NB_PIX_CASE , MINI_NB_PIX_CASE, MINI_NB_PIX_CASE);

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
    
    /* Affichage des infos du monstre */
    public String toString() { 
    	return this.getPosition().toString() + " " + this.m.name() + " " + this.nom + " (" + this.m.getPoints() + "PV /" + this.getPoints() + ")";
    } 
    
    public String getSprite() { return this.m.getSprite(); } 
    public Image getImage() { return this.m.getImage(); }

	@Override
	public String getType() { return this.m.name(); }
}