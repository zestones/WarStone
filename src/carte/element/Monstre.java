package carte.element;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import carte.Camera;
import carte.Carte;
import sprite.InitialiseurSprite;
import utile.Position;

/**
 * Class Monstre.
 */
public class Monstre extends Soldat {
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	public int SEUIL_VIE_CRITIQUE;
	
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
        this.SEUIL_VIE_CRITIQUE = this.getPointsMax() / 10;
        
        this.spriteSoldat = new InitialiseurSprite(this);
        this.dernierSprite = this.spriteSoldat.spriteReposBas;
                        
        carte.setElement(this);      
    }
	
	/**
	 * Se dessiner.
	 *
	 * @param g 
	 * @param cam 
	 */
	public void seDessiner(Graphics g, Camera cam) { 
		int dx = cam.getDx() * TAILLE_CARREAU;
		int dy = cam.getDy() * TAILLE_CARREAU;

    	/** Dessin de la case du monstre */
    	g.drawImage(terre, (this.getPosition().getX() * TAILLE_CARREAU) - dx, (this.getPosition().getY() * TAILLE_CARREAU) - dy, TAILLE_CARREAU, TAILLE_CARREAU, null);
    	/** On dessine la grille de cette case */
    	g.setColor(COULEUR_GRILLE);
		g.drawRect(this.getPosition().getX() * TAILLE_CARREAU - dx, this.getPosition().getY() * TAILLE_CARREAU - dy, TAILLE_CARREAU, TAILLE_CARREAU); 
		    
		/** Dessin du sprite du monstre */
		this.dessineSprite(g, cam);
    	
    	/** Dessin de la barre de vie ainsi que le fond du monstre uniquement si le monstre ne se deplace pas */
    	if(!this.estActifDeplacement) {
    		this.dessineBarreVie(g, cam);
    		g.setColor(COULEUR_MONSTRE);
    		g.fillRect((this.getPosition().getX()  * TAILLE_CARREAU) - dx, (this.getPosition().getY() * TAILLE_CARREAU) - dy, TAILLE_CARREAU, TAILLE_CARREAU);
    	}
    }
    
  /**
   * dessine le monstre dans la miniCarte.
   *
   * @param g 
   */
	public void seDessinerMiniCarte(Graphics g) { 
		g.drawImage(terre, this.getPosition().getX() * TAILLE_CARREAU_MINI_CARTE, this.getPosition().getY() * TAILLE_CARREAU_MINI_CARTE, TAILLE_CARREAU_MINI_CARTE, TAILLE_CARREAU_MINI_CARTE, null);   	
	  
		g.drawImage(this.getImageMiniCarte(), this.getPosition().getX() * TAILLE_CARREAU_MINI_CARTE, this.getPosition().getY() * TAILLE_CARREAU_MINI_CARTE, TAILLE_CARREAU_MINI_CARTE, TAILLE_CARREAU_MINI_CARTE, null);   		

		g.setColor(COULEUR_MONSTRE);
		g.fillRect(this.getPosition().getX()  * TAILLE_CARREAU_MINI_CARTE , this.getPosition().getY() * TAILLE_CARREAU_MINI_CARTE , TAILLE_CARREAU_MINI_CARTE, TAILLE_CARREAU_MINI_CARTE);
	}
    
  /**
   * Gets list heros in range.
   *
   * Retourne une liste de heros present a la portee du monstre
   *
   * @return list heros in range
   */
	public List<Heros> getListHerosDansPortee(){
		List<Heros> listeHeros = new ArrayList<>();
		int portee = this.getPortee();

		for(int i = 0; i <= portee * 2; i++) {
			for(int j = 0; j <= portee * 2 ; j++) {
				Position porteeVisuelle = new Position(this.getPosition().getX() + i - portee, this.getPosition().getY() + j - portee);
				if(!porteeVisuelle.estValide())
					continue; 
				if(carte.getElement(porteeVisuelle) instanceof Heros) 
					listeHeros.add((Heros)carte.getElement(porteeVisuelle));
			}
		}
		return listeHeros;
	}
    
  
  /**
	 * Raproche monstre.
	 * Si le monstre est confiant il se rapproche du heros pour le combatre
	 *
	 * @param h
	 * @param m 
	 * @return true, if successful
	 */
	public void seRapproche(Heros h) {
		List<Position> positionVoisine = this.getPosition().getPositionAdjacente();
		Position pos = null;
		int distance = Math.max(NB_COLONNES, NB_LIGNES);
		
		for(Position posVoisine : positionVoisine) {
			int distanceHeroPosVoisine = posVoisine.getDistance(h.getPosition());
			if(distance > distanceHeroPosVoisine) {
				pos = posVoisine;
				distance = distanceHeroPosVoisine;
			}
		}
		
		if(pos != null) carte.deplaceSoldat(pos, this);
	}
  
	/**
	 * Fuite monstre.
	 * Si le monstre n'est pas de taille face au heros alors il fuit
	 * 
	 * @param h
	 * @param m
	 * @return true, if successful
	 */
	public void prendFuite(Heros h) {
		List<Position> positionVoisine = this.getPosition().getPositionAdjacente();
		Position posFuite = null;
		int distance = 0;
		// On cherche une position vide le plus eloigne du heros 
		for(Position posVoisine : positionVoisine) {
			if(h.getPosition().getDistance(posVoisine) > distance && posVoisine.estValide() && carte.estCaseVide(posVoisine)) {
				posFuite = posVoisine;
				distance = (int) h.getPosition().getDistance(posVoisine);
			}
		}
		
		// Si on a trouver aucune position on a pas le choix
		// Le monstre doit attaquer le heros
		if(posFuite != null) carte.deplaceSoldat(posFuite, this);
	}
  
	/**
	 *  Methode qui gere le combat des Monstres.
	 *
	 * @param pj
	 * @param pos 
	 * @param pos2 
	 * @return boolean
	 */
	public void attaque(Position pos2) {
		// On verifie que le Monstre n'a pas deja jouer et que la position d'attaque (pos2) n'est pas un autre Monstre
		if (carte.getElement(pos2) instanceof Monstre || this.aJoue)
			return;
		
		// Si un Heros est present en pos2 alors on fait combatre notre monstre
		Heros h = (Heros) carte.getElement(pos2);
		this.combat = h.combat = true;
		carte.listeActionAttaque.addAll(Arrays.asList(this, h, h, this));
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
  public String getCheminSprite() { return this.m.getCheminSprite(); }     
  
  /**
   * Gets image.
   *
   * @return image
   */
  public Image getMiniature() { return this.m.getMiniature(); }

  /**
   * Gets image.
   *
   * @return image
   */
  public Image getImageMiniCarte() { return this.m.getImageMiniCarte(); }
  
  /**
   * Gets type.
   *
   * @return type
   */
  public String getType() { return this.m.name(); }
}