package wargame;

import java.awt.Color;
import java.awt.Graphics;

import wargame.ISoldat.TypesH;

public class Monstre extends Soldat {
	private static final long serialVersionUID = 1L;
	TypesM m;
    String nom;
    private Position[] champVisuelle = new Position[5];
    
    Monstre(Carte carte, TypesM m, String nom,Position pos){
        super(carte, m.getPoints(), m.getPortee(), m.getPuissance(), m.getTir(), pos, false);
        this.m = m;
        this.nom = nom;
        carte.plateau[this.pos.getX()][this.pos.getY()] = this;
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
    	int posX = this.pos.getX() * NB_PIX_CASE + NB_PIX_CASE/2 - g.getFontMetrics().stringWidth(this.nom)/2;
    	int posY = this.pos.getY() * NB_PIX_CASE + NB_PIX_CASE/2 + g.getFontMetrics().stringWidth(this.nom)/2;
    	
    	g.setColor(COULEUR_MONSTRES);
    	g.fillRect(this.pos.getX() * NB_PIX_CASE, this.pos.getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE); 
    	g.setColor(Color.white);
    	g.drawString(this.nom, posX, posY);
	}
    
    public String toString() {
    	return this.getPosition().toString() + " " + this.m.name() + " " + this.nom + " (" + this.m.getPoints() + "PV /" + this.getPoints() + ")";
    }
}