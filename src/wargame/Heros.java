package wargame;

import java.io.File;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Heros extends Soldat{
	private static final long serialVersionUID = 1L;
	TypesH h;
    String nom;
    private Position[] champVisuelle = new Position[5];
    private static final int BONUS_REPOS = 10;
    // On ne serialize pas les sprites 
    private transient SpriteSheet spriteSheet;
    
    Heros(Carte carte, TypesH h, String nom, Position pos){
        super(carte, h.getPoints(), h.getPortee(), h.getPuissance(), h.getTir(), pos, false);
        this.h = h;
        this.nom = nom;
        carte.plateau[this.pos.getX()][this.pos.getY()] = this;   
        this.initialiseSprite();
    }
    
    
    /* 
     * On initialise le sprite pour chaque Heros lors de sa creation
     * Si une partie est charge depuis une sauvegarde, on recharge les sprites en fonction du type 
     * (Aucun sprite est enregistre dans la sauvegarde !)
     *  */
    private void initialiseSprite() {
		try {
	    	BufferedImage sprite = ImageIO.read(new File(this.h.getSprite()));
	    	spriteSheet = new SpriteSheetBuilder().
	    			withSheet(sprite).
	    			withColumns(0).
	    			withSpriteSize(64,62).
	    			withRows(3).
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
	
	/* Methode de dessin du Heros */
    private void dessinHeros(Graphics g) { 
    	g.drawImage(range, this.pos.getX() * NB_PIX_CASE, this.pos.getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE, null);
    	
    	this.dessineSprite(g);
    	
        if(this.aJoue == true) {
    		g.setColor(COULEUR_HEROS_DEJA_JOUE);
    		g.fillRect(this.pos.getX() * NB_PIX_CASE, this.pos.getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE); 
    	}
    }
   
    /* On dessine le Heros */
    private void dessineSprite(Graphics g) {
    	if(this.spriteSheet == null)
    		this.initialiseSprite();
    	
    	BufferedImage sprite = spriteSheet.getSprite(spriteEngine.getCycleProgress());
		Graphics2D  g2d = (Graphics2D) g.create();
		g2d.drawImage(sprite, this.pos.getX() * NB_PIX_CASE ,this.pos.getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE, null);
		g2d.dispose();
	}
    
    /* Methode principale du dessin de Heros:
     * Affichage de la portee visuelle i.e les cases visible par le général 
     */
    public void seDessiner(Graphics g) {
    	int portee = this.getPortee();
    	
    	for(int i = 0; i <= portee * 2; i++) {
    		for(int j = 0; j <= portee  * 2 ; j++) {
    			Position porteeVisuelle = new Position(this.pos.getX() + i - portee, this.pos.getY() + j - portee);
    			if(porteeVisuelle.estValide() == false)
    				porteeVisuelle.verifPosition();
    			
    			if(carte.plateau[porteeVisuelle.getX()][porteeVisuelle.getY()] == null) 
    				g.drawImage(range, porteeVisuelle.getX() * NB_PIX_CASE, porteeVisuelle.getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE, null);
    			else if (carte.plateau[porteeVisuelle.getX()][porteeVisuelle.getY()] instanceof Monstre)
    				carte.plateau[porteeVisuelle.getX()][porteeVisuelle.getY()].seDessiner(g);
    			else if(carte.plateau[porteeVisuelle.getX()][porteeVisuelle.getY()] instanceof Obstacle)
    				carte.plateau[porteeVisuelle.getX()][porteeVisuelle.getY()].seDessiner(g);
    			
    			g.setColor(COULEUR_GRILLE);
    			g.drawRect(porteeVisuelle.getX() * NB_PIX_CASE, porteeVisuelle.getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE); 
    		}
    	}
    	this.dessinHeros(g);
    }
    
    /* Dessine le Heros selectionne avec sa portee & deplacement */
    public void dessineSelection(Graphics g) {
    	this.dessinePorteeVisuelle(g);
    	this.dessineDeplacement(g);
    }
    
    /*Methode de dessin des deplacement possible pour le heros selectionne*/
    private void dessineDeplacement(Graphics g){
       	for(int i = -1; i < 2; i++)
    		for(int j = -1; j < 2; j++) {
    			if(i != 0 || j != 0) {
    				Position posVoisine = new Position(this.pos.getX() + i, this.pos.getY() + j);
    				if(posVoisine.estValide() == false)
        				posVoisine.verifPosition();
    				if(carte.plateau[posVoisine.getX()][posVoisine.getY()] == null) {
    					g.setColor(COULEUR_DEPLACEMENT);
    					g.fillRect((this.pos.getX() + i) * NB_PIX_CASE , (this.pos.getY() + j) * NB_PIX_CASE , NB_PIX_CASE, NB_PIX_CASE);
    				}
    			}
    			g.setColor(COULEUR_GRILLE);
    			g.drawRect((this.pos.getX() + i) * NB_PIX_CASE , (this.pos.getY() + j) * NB_PIX_CASE , NB_PIX_CASE, NB_PIX_CASE);
    		}   	  
    }
    
    /* Dessine la portee visuelle du heros selectionne*/
    private void dessinePorteeVisuelle(Graphics g) {
    	int portee = this.getPortee();
    	boolean dejaDessine = false;
    	for(int i = 0; i <= portee * 2; i++) {
    		for(int j = 0; j <= portee  * 2 ; j++) {
    			Position porteeVisuelle = new Position(this.pos.getX() + i - portee, this.pos.getY() + j - portee);
    			if(porteeVisuelle.estValide() == false) {
    				porteeVisuelle.verifPosition();
    				dejaDessine = true;
    			}
    			
    			if(carte.plateau[porteeVisuelle.getX()][porteeVisuelle.getY()] == null && dejaDessine == false) {
    				g.setColor(COULEUR_PORTEE);
    				g.fillRect(porteeVisuelle.getX() * NB_PIX_CASE, porteeVisuelle.getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE); 
    			}
    			
    			g.setColor(COULEUR_GRILLE);
    			g.drawRect(porteeVisuelle.getX() * NB_PIX_CASE, porteeVisuelle.getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE); 
    			dejaDessine = false;
    		}
    	}	
    }
    
    /*Si le Heros n'a effectue aucune action durant le tour alors il obtient un bonus de vie*/
    public void repos() {
    	if(this.aJoue == false && this.getPoints() + BONUS_REPOS < this.getPointsMax())
    		this.setPoints(this.getPoints()+BONUS_REPOS);
    	else if (this.aJoue == false && this.getPoints() + BONUS_REPOS > this.getPointsMax())
    		this.setPoints(this.getPointsMax());
    }
    
    public int getPointsMax() { return this.h.getPoints(); }
    
    /* Affichage des infos du Heros */
    public String toString() {
    	return this.getPosition().toString() + " " + this.h.name() + " " + this.nom + " (" + this.h.getPoints() + "PV /" + this.getPoints() + ")";
    }
}