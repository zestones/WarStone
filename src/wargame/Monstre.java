package wargame;

import java.io.File;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Monstre extends Soldat {
	private static final long serialVersionUID = 1L;
	TypesM m;
    String nom;
    private Position[] champVisuelle = new Position[5];
    public transient SpriteSheet spriteSheet;

    Monstre(Carte carte, TypesM m, String nom,Position pos){
        super(carte, m.getPoints(), m.getPortee(), m.getPuissance(), m.getTir(), pos, false);
        this.m = m;
        this.nom = nom;
        carte.plateau[this.pos.getX()][this.pos.getY()] = this;
        this.initialiseSprite();
    }
    
    /* 
     * On initialise le sprite pour chaque Monstre lors de sa creation
     * Si une partie est charge depuis une sauvegarde on recharge les sprites en fonction su type 
     * (Aucun sprite est enregistre dans la sauvegarde !)
     *  */
    private void initialiseSprite() {
		try {
	    	BufferedImage sprite = ImageIO.read(new File(this.m.getSprite()));
	    	spriteSheet = new SpriteSheetBuilder().
	    			withSheet(sprite).
	    			withColumns(0).
	    			withSpriteSize(64,62).
	    			withRows(1).
	    			withSpriteCount(7).
	    			build();
	    	spriteEngine.start();
	    } catch (IOException ex) {
	    	System.out.println(" Error -> " + ex);
	    	ex.printStackTrace();
	    }
    }
    
    /* Initialise le "champs visuelle" i.e les positions de la portee du Heros */
    private void initChampVisuelle() {
    	this.champVisuelle[0] = new Position(this.getPosition().getX() - this.getPortee(), this.getPosition().getY() - this.getPortee());
    	this.champVisuelle[1] = new Position(this.getPosition().getX() + this.getPortee(), this.getPosition().getY() - this.getPortee());
     	this.champVisuelle[2] = new Position(this.getPosition().getX() + this.getPortee(), this.getPosition().getY() + this.getPortee());
     	this.champVisuelle[3] = new Position(this.getPosition().getX() - this.getPortee(), this.getPosition().getY() + this.getPortee());
    }
    
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
	    
    /* Dessin du Monstre sur la carte */
    public void seDessiner(Graphics g) { 
    	g.drawImage(range, this.pos.getX() * NB_PIX_CASE, this.pos.getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE, null);

    	if(this.spriteSheet == null)
    		this.initialiseSprite();
    	
    	BufferedImage sprite = spriteSheet.getSprite(spriteEngine.getCycleProgress());
    	Graphics2D  g2d = (Graphics2D) g.create();
    	g2d.drawImage(sprite, this.pos.getX() * NB_PIX_CASE ,this.pos.getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE, null);
    	g2d.dispose();
    
    	g.setColor(COULEUR_MONSTRE);
		g.fillRect(this.pos.getX()  * NB_PIX_CASE , this.pos.getY() * NB_PIX_CASE , NB_PIX_CASE, NB_PIX_CASE);    
    }
    
    /* Affichage des infos du monstre */
    public String toString() {
    	return this.getPosition().toString() + " " + this.m.name() + " " + this.nom + " (" + this.m.getPoints() + "PV /" + this.getPoints() + ")";
    }
}