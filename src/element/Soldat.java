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
import java.awt.image.BufferedImage;

import carte.Camera;
import carte.Carte;
import sprite.SpriteInitializer;
import sprite.SpriteSheet;
import utile.Position;

public abstract class Soldat extends Element implements ISoldat, Cloneable{
	private static final long serialVersionUID = 1L;
	
	private final int POINTS_DE_VIE_MAX, PUISSANCE, TIR, PORTEE_VISUELLE;
   
	protected transient SpriteInitializer soldatSprite;
	public transient SpriteSheet dernierSprite;
	
	private int pointsDeVie;
    public boolean aJoue;
    private Position pos;    
    public Carte carte;
    public Position nouvellePos;
    public boolean deplacement, combat, mort;
    private Position[] champVisuelle = new Position[5];
    private Projectile fleche = null;
    protected int deplacementX;
    protected int deplacementY;
    
    public boolean activeDeplacement;
    
    public Soldat(Carte carte, int pts, int portee, int puiss, int tir, Position pos) {
    	this.carte = carte;
    	
    	POINTS_DE_VIE_MAX = pointsDeVie = pts;
    	PORTEE_VISUELLE = portee; PUISSANCE = puiss; TIR = tir;
    	
    	this.deplacementX = this.deplacementY = 0;    
    	
    	this.pos = pos;
    	
    	this.aJoue = false;
    	this.deplacement = false;
    	this.activeDeplacement = false;     
    }
    
   public void seDeplace(Position nouvPos) {
    	// Supression du soldat a sa position
	   carte.setElementVide(this.getPosition());
    	
    	// definition des nouvelles position
    	this.getPosition().setX(nouvPos.getX());
    	this.getPosition().setY(nouvPos.getY());
    	
    	// Positionnement du soldat sur la carte
    	carte.setElement(this);
    }
    
   public boolean combat(Soldat soldat) {
    	// On verifie que le solodat attaquer se trouve bien a sa portee
    	if(!this.estDedans(soldat.getPosition()))
    		return false;

    	this.fleche = new Projectile(this.getPosition(), soldat.getPosition());

    	int puissance;
    
    	if (!this.getPosition().estVoisine(soldat.getPosition())) 
    		puissance = (int) (Math.random() * this.TIR);
		else
			puissance = (int) (Math.random() * this.PUISSANCE);
    	
//    	Projectile a = new Projectile(soldat.getPosition(), this.getPosition(), carte);
//    	a.dessineProjectile(frame.getGraphics());
    	
    	soldat.pointsDeVie -= puissance;

    	if(soldat.getPoints() <= 0){
    		soldat.pointsDeVie = 0;	
    		soldat.mort = true;
    		if(!carte.listeActionMort.contains(soldat))
    			carte.listeActionMort.add(soldat);
    	}
    	return true;
    }
    
    private void initChampVisuelle() {
    	this.champVisuelle[0] = new Position(this.getPosition().getX() - this.getPortee(), this.getPosition().getY() - this.getPortee());
    	this.champVisuelle[1] = new Position(this.getPosition().getX() + this.getPortee(), this.getPosition().getY() - this.getPortee());
     	this.champVisuelle[2] = new Position(this.getPosition().getX() + this.getPortee(), this.getPosition().getY() + this.getPortee());
     	this.champVisuelle[3] = new Position(this.getPosition().getX() - this.getPortee(), this.getPosition().getY() + this.getPortee());
    }
    
    public boolean estDedans(Position p) {
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
    
    protected void dessineSprite(Graphics g, Camera cam) {
    	int dx = cam.getDx() * NB_PIX_CASE;
    	int dy = cam.getDy() * NB_PIX_CASE;
    	
    	if(this.soldatSprite == null) {
    		this.soldatSprite = new SpriteInitializer(this);
    		this.dernierSprite = this.soldatSprite.spriteStandByDroite;
    	}
    	
    	BufferedImage sprite = this.dernierSprite.getSprite(spriteEngine.getCycleProgress()); 
		g.drawImage(sprite, (this.pos.getX() * NB_PIX_CASE) - dx + this.deplacementX, (this.pos.getY() * NB_PIX_CASE) - dy + this.deplacementY, NB_PIX_CASE, NB_PIX_CASE, null);
		
		if(this.fleche != null && this.combat) {
			this.fleche.dessineProjectile(g, cam);
				if(this.fleche.toucher) this.fleche = null;
		}
			
		this.effectuerDeplacement();
    }
    
    private void effectuerDeplacement() {
    	if(this.activeDeplacement) {
    		this.deplacementX += this.nouvellePos.getX() - this.getPosition().getX();
    		this.deplacementY += this.nouvellePos.getY() - this.getPosition().getY();
    		this.finDeplacement();
    	}
    }
    
    private void finDeplacement() {
    	if(Math.abs(this.deplacementX) >= NB_PIX_CASE || Math.abs(this.deplacementY) >= NB_PIX_CASE) {
			this.deplacementX = this.deplacementY = 0;
			this.seDeplace(this.nouvellePos);
			this.activeDeplacement = false;
    	}
    }
    
    public void changeSprite(Position clic, Camera cam) {   	
    	if(clic == null)
    		return;
    	
    	if(this.soldatSprite == null) {
    		this.soldatSprite = new SpriteInitializer(this);
    		this.dernierSprite = this.soldatSprite.spriteStandByDroite;
    	}
    	   	
    	if(!this.combat && !this.deplacement && !this.mort)
    		this.setStandBySprite(clic);
    	else if (this.combat)
    		this.setAttackSprite(clic);
    	else if(this.deplacement) {
    		this.setDeplacementSprite(clic);
    		this.activeDeplacement = true;
    		this.nouvellePos = clic;
    	}
    	else if(this.mort)
    		this.dernierSprite = this.soldatSprite.spriteMort;
    }
    
    private void setStandBySprite(Position clic) {
    	if(clic.getX() < this.getPosition().getX())
			this.dernierSprite = this.soldatSprite.spriteStandByGauche;
    	else if(clic.getX() > this.getPosition().getX())
    		this.dernierSprite = this.soldatSprite.spriteStandByDroite;
      	else if(clic.getY() > this.getPosition().getY())
      		this.dernierSprite = this.soldatSprite.spriteStandByBas;
    	else if(clic.getY() < this.getPosition().getY())
    		this.dernierSprite = this.soldatSprite.spriteStandByHaut;
    
    	this.deplacementX = this.deplacementY = 0;
    	this.activeDeplacement = false;
    }
    
    private void setAttackSprite(Position clic) {
    	if(clic.getX() < this.getPosition().getX()) 
    		this.dernierSprite = this.soldatSprite.spriteAttackGauche;
    	else if(clic.getX() > this.getPosition().getX())
    		this.dernierSprite = this.soldatSprite.spriteAttackDroite;
    	else if(clic.getY() > this.getPosition().getY())
    		this.dernierSprite = this.soldatSprite.spriteAttackHaut;
    	else if(clic.getY() < this.getPosition().getY())
    		this.dernierSprite = this.soldatSprite.spriteAttackBas;
    
    	this.deplacementX = this.deplacementY = 0;
		this.activeDeplacement = false;
    }
    
    private void setDeplacementSprite(Position clic) {
    	switch(clic.getPositionCardinal(this.getPosition())) {
		case NORD: this.dernierSprite = this.soldatSprite.spriteDeplaceHaut;
		break;
		case NORD_OUEST: this.dernierSprite = this.soldatSprite.spriteDeplaceGauche;
		break;
		case OUEST: this.dernierSprite = this.soldatSprite.spriteDeplaceGauche;
		break;
		case SUD_OUEST: this.dernierSprite = this.soldatSprite.spriteDeplaceGauche;
		break;
		case SUD: this.dernierSprite = this.soldatSprite.spriteDeplaceBas;
		break;
		case SUD_EST: this.dernierSprite = this.soldatSprite.spriteDeplaceDroite;
		break;
		case EST: this.dernierSprite = this.soldatSprite.spriteDeplaceDroite;
		break;
		case NORD_EST: this.dernierSprite = this.soldatSprite.spriteDeplaceDroite;
		break;
		default: this.dernierSprite = this.soldatSprite.spriteStandByBas;
		break;
    	}
    }
    
    public void dessinBarreVie(Graphics g, Camera cam) {
    	int dx = cam.getDx() * NB_PIX_CASE;
    	int dy = cam.getDy() * NB_PIX_CASE;
    			
    	g.setColor(COULEUR_VIE_R);
 		g.fillRect(((this.pos.getX() * NB_PIX_CASE) - ( Math.min(this.getPointsMax(), NB_PIX_CASE - PADDING_VIE_CASE_LARGEUR) / 2) + NB_PIX_CASE/2) - dx, (this.pos.getY() * NB_PIX_CASE + PADDING_VIE_CASE) - dy, Math.min(this.getPointsMax(), NB_PIX_CASE - PADDING_VIE_CASE_LARGEUR), NB_PIX_CASE/8); 
 		
 		g.setColor(COULEUR_VIE_V);
 		g.fillRect(((this.pos.getX() * NB_PIX_CASE) - ( Math.min(this.getPointsMax(), NB_PIX_CASE - PADDING_VIE_CASE_LARGEUR) / 2) + NB_PIX_CASE/2) - dx, (this.pos.getY() * NB_PIX_CASE + PADDING_VIE_CASE) - dy,  (int) (Math.min(this.getPointsMax(), NB_PIX_CASE - PADDING_VIE_CASE_LARGEUR) * ((float)this.getPoints() / (float)this.getPointsMax())), NB_PIX_CASE/8); 
 		
 		g.setColor(COULEUR_VIDE);
 		g.drawRect(((this.pos.getX() * NB_PIX_CASE) - ( Math.min(this.getPointsMax(), NB_PIX_CASE - PADDING_VIE_CASE_LARGEUR) / 2) + NB_PIX_CASE/2) - dx, (this.pos.getY() * NB_PIX_CASE + PADDING_VIE_CASE) - dy, Math.min(this.getPointsMax(), NB_PIX_CASE - PADDING_VIE_CASE_LARGEUR), NB_PIX_CASE/8); 
    }
   
    /**
     * Clone un objet et le retourne sous forme d'objet de type soldat
     * Utiliser pour copier un soldat lors d'une action de type deplacement
     * Dans la liste d'action 
     *
     * @return the object
     * @throws CloneNotSupportedException the clone not supported exception
     */
    public Soldat clone() throws CloneNotSupportedException {	
    	return (Soldat) super.clone();
    }
    
    public void setPosition(Position nouvPos) { pos = new Position(nouvPos.getX(), nouvPos.getY()); }
    public int getPointsMax() { return this.POINTS_DE_VIE_MAX; }
    public void setPoints(int pts) { this.pointsDeVie = pts; } // Utilise pour les bonus (lorsque le heros ce repose)
    public int getPortee() { return this.PORTEE_VISUELLE; }
    public int getPuissance() { return this.PUISSANCE; }
    public int getPoints() { return this.pointsDeVie; }
    public Position getPosition() { return pos; }
    public int getTir() { return this.TIR; }
    public abstract int getIndexSoldat();
    public abstract void mort(int index);
    public abstract String getSprite();
}