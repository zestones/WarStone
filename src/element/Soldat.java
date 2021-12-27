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
import utile.Position.POINT_CARDINAUX;
import wargame.IConfig;

public abstract class Soldat extends Element implements ISoldat, IConfig, Cloneable{
	private static final long serialVersionUID = 1L;
	private final int POINTS_DE_VIE_MAX, PUISSANCE, TIR, PORTEE_VISUELLE;
   
	protected transient SpriteInitializer soldatSprite;
	public transient SpriteSheet dernierSprite;
	
	private int pointsDeVie;
    public boolean aJoue;
    public Position pos;    
    public Carte carte;
    public Position nouvellePos;
    public boolean deplacement, combat, mort;
    private Position[] champVisuelle = new Position[5];
    
    protected int deplacementX;
	protected int deplacementY;
	protected POINT_CARDINAUX direction;
	
    public boolean activeDeplacement;
   
    public Soldat(Carte carte, int pts, int portee, int puiss, int tir, Position pos, boolean aJoue) {
    	this.carte = carte;
    	
    	POINTS_DE_VIE_MAX = pointsDeVie = pts;
        PORTEE_VISUELLE = portee; PUISSANCE = puiss; TIR = tir;
        
        this.pos = pos;
        this.aJoue = aJoue;
        this.deplacement = false;
        this.activeDeplacement = false;    
        this.deplacementX = 0;
        this.deplacementY = 0;
        this.direction = POINT_CARDINAUX.MILIEU;
        
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
    
    /**
     * Clone un objet et le retourne sous forme d'objet de type soldat
     * Utiliser pour copier un soldat lors d'une action de type deplacement
     *
     * @return the object
     * @throws CloneNotSupportedException the clone not supported exception
     */
    public Soldat clone() throws CloneNotSupportedException {	
    	return (Soldat) super.clone();
    }
    
    public boolean combat(Soldat soldat) {
    	// On verifie que le solodat attaquer se trouve bien a sa portee
    	if(!this.dedans(soldat.getPosition()))
    		return false;
    	
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
    
    protected void dessineSprite(Graphics g, Camera cam) {
    	int dx = cam.getDx() * NB_PIX_CASE;
    	int dy = cam.getDy() * NB_PIX_CASE;
    	
    	if(this.soldatSprite == null) {
    		this.soldatSprite = new SpriteInitializer(this);
    		this.dernierSprite = this.soldatSprite.spriteStandByDroite;
    	}
    	
    	BufferedImage sprite = this.dernierSprite.getSprite(spriteEngine.getCycleProgress()); 
		g.drawImage(sprite, (this.pos.getX() * NB_PIX_CASE) - dx + this.deplacementX, (this.pos.getY() * NB_PIX_CASE) - dy + this.deplacementY, NB_PIX_CASE, NB_PIX_CASE, null);
		this.effectuerDeplacement();
		this.finDeplacement();
    }
    
    private void effectuerDeplacement() {
    	if(this.activeDeplacement) {
			switch(this.direction) {
			case NORD: 
				this.deplacementY -= 1;
				break;
			case NORD_OUEST:
    			this.deplacementX -= 1;
				this.deplacementY -= 1;
				break;
			case OUEST:
				this.deplacementX -= 1;
				break;
			case SUD_OUEST:
				this.deplacementX -= 1;
				this.deplacementY += 1;
				break;
			case SUD:
				this.deplacementY += 1;
				break;
			case SUD_EST:
				this.deplacementX += 1;
				this.deplacementY += 1;
				break;
			case EST:
				this.deplacementX += 1;
				break;
			case NORD_EST:					
				this.deplacementX += 1;
				this.deplacementY -= 1;
				break;
			default: 
				this.deplacementX = 0;
				this.deplacementY = 0;
				break;
			}
		}
    }
    
    private void finDeplacement() {
    	if(Math.abs(this.deplacementX) >= NB_PIX_CASE || Math.abs(this.deplacementY) >= NB_PIX_CASE) {
			this.deplacementX = this.deplacementY = 0;
			this.seDeplace(this.nouvellePos);
			this.activeDeplacement = false;
		}
    }
    
    
    public void changeSprite(Graphics g, Position clic, Camera cam) {   	
    	if(clic == null)
    		return;
    	
    	if(this.soldatSprite == null) {
    		this.soldatSprite = new SpriteInitializer(this);
    		this.dernierSprite = this.soldatSprite.spriteStandByDroite;
    	}
    	   	
    	if(this.combat == false && this.deplacement == false) {
    		this.setStandBySprite(clic);
    	}
    	else if (this.combat == true){
    		this.setAttackSprite(clic);
    	}
    	else if(this.deplacement == true) {
    		this.setDeplacementSprite(clic);
    		this.activeDeplacement = true;
    		this.nouvellePos = clic;
    	}
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
		case NORD: 
			this.dernierSprite = this.soldatSprite.spriteDeplaceHaut;
			this.direction = POINT_CARDINAUX.NORD;
			break;
		case NORD_OUEST:
			this.dernierSprite = this.soldatSprite.spriteDeplaceGauche;
			this.direction = POINT_CARDINAUX.NORD_OUEST;
			break;
		case OUEST:
			this.dernierSprite = this.soldatSprite.spriteDeplaceGauche;
			this.direction = POINT_CARDINAUX.OUEST;
			break;
		case SUD_OUEST:
			this.dernierSprite = this.soldatSprite.spriteDeplaceGauche;
			this.direction = POINT_CARDINAUX.SUD_OUEST;
			break;
		case SUD:
			this.dernierSprite = this.soldatSprite.spriteDeplaceBas;
			this.direction = POINT_CARDINAUX.SUD;
			break;
		case SUD_EST:
			this.dernierSprite = this.soldatSprite.spriteDeplaceDroite;
		this.direction = POINT_CARDINAUX.SUD_EST;
			break;
		case EST:
			this.dernierSprite = this.soldatSprite.spriteDeplaceDroite;
			this.direction = POINT_CARDINAUX.EST;
			break;
		case NORD_EST:					
			this.dernierSprite = this.soldatSprite.spriteDeplaceDroite;
			this.direction = POINT_CARDINAUX.NORD_EST;
			break;
		default: 
			this.dernierSprite = this.soldatSprite.spriteStandByBas;
			this.direction = POINT_CARDINAUX.MILIEU;
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