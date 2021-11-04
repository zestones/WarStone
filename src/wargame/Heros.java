package wargame;

import java.awt.Graphics;

import wargame.ISoldat.TypesH;

public class Heros extends Soldat{
    TypesH h;
    String nom;
    private Carte carte;
    private Position[] champVisuelle = new Position[5];
 
    Heros(Carte carte, TypesH h, String nom, Position pos){
        super(carte, h.getPoints(), h.getPortee(), h.getPuissance(), h.getTir(), pos);
        this.h = h;
        this.nom = nom;
        this.carte = carte;
        carte.plateau[this.pos.getX()][this.pos.getY()] = this;
        this.initChampVisuelle();
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
     	int posX = this.pos.getX() * NB_PIX_CASE + NB_PIX_CASE/2 - g.getFontMetrics().stringWidth(this.nom)/2;
    	int posY = this.pos.getY() * NB_PIX_CASE + NB_PIX_CASE/2 + g.getFontMetrics().stringWidth(this.nom)/2;
   
    	g.setColor(COULEUR_HEROS);
    	g.fillRect(this.pos.getX() * NB_PIX_CASE, this.pos.getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE); 
    	g.setColor(COULEUR_TEXTE);
    	g.drawString(this.nom, posX, posY);
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
    			
    			if(this.carte.plateau[porteeVisuelle.getX()][porteeVisuelle.getY()] == null) {
    				g.setColor(COULEUR_VIDE);
    				g.fillRect(porteeVisuelle.getX() * NB_PIX_CASE, porteeVisuelle.getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE); 
    			}
    			
    			g.setColor(COULEUR_GRILLE);
				g.drawRect(porteeVisuelle.getX() * NB_PIX_CASE, porteeVisuelle.getY() * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE); 
    		}
    	}
    	this.dessinHeros(g);
    }
    
    public int getPoints() { return this.h.getPoints(); }
    public int getPortee() { return this.h.getPortee(); }
    public int getPuissance() { return this.h.getPuissance(); }
    public int getTir() { return this.h.getTir(); }

}