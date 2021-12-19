package sprite;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import element.Soldat;
import wargame.IConfig;

public class SpriteInitializer implements IConfig, ISprite {
	private static final long serialVersionUID = 1L;
	
	public transient SpriteSheet spriteStandByGauche;
	public transient SpriteSheet spriteStandByDroite;
	public transient SpriteSheet spriteStandByHaut;
	public transient SpriteSheet spriteStandByBas;
	
	public transient SpriteSheet spriteAttackGauche;
	public transient SpriteSheet spriteAttackDroite;
	public transient SpriteSheet spriteAttackHaut;
	public transient SpriteSheet spriteAttackBas;
	
	private Soldat soldat;

	/* 
	 * On initialise le sprite pour chaque Heros lors de sa creation
	 * Si une partie est charge depuis une sauvegarde, on recharge les sprites en fonction du type 
	 * (Aucun sprite est enregistre dans la sauvegarde !)
	 */	
	public SpriteInitializer(Soldat s){
		this.soldat = s;
		this.spriteStandByGauche = initSprite(LIGNE_STANDBY_GAUCHE, NB_IMAGE_STANDBY);	
		this.spriteStandByDroite = initSprite(LIGNE_STANDBY_DROITE, NB_IMAGE_STANDBY);	
		
		this.spriteStandByHaut = initSprite(LIGNE_STANDBY_HAUT, NB_IMAGE_STANDBY);	
		this.spriteStandByBas = initSprite(LIGNE_STANDBY_BAS, NB_IMAGE_STANDBY);
		
		this.spriteAttackGauche = initSprite(LIGNE_ATTACK_GAUCHE, NB_IMAGE_ATTACK);
		this.spriteAttackDroite = initSprite(LIGNE_ATTACK_DROITE, NB_IMAGE_ATTACK);

		this.spriteAttackHaut = initSprite(LIGNE_ATTACK_HAUT, NB_IMAGE_ATTACK);
		this.spriteAttackBas = initSprite(LIGNE_ATTACK_BAS, NB_IMAGE_ATTACK);

		
	}
	
	/* Genere un Sprite a l'aide du numero de ligne et le nombre d'image */
	private SpriteSheet initSprite(int ligne, int nbImage) {
		SpriteSheet spriteSheet = null;
		try 
		{
			BufferedImage sprite = ImageIO.read(new File(this.soldat.getSprite()));
			spriteSheet = new SpriteSheetBuilder().
	    			withSheet(sprite).
	    			withColumns(0).
	    			withSpriteSize(LARGEUR_IMAGE, HAUTEUR_IMAGE).
	    			withRows(ligne).
	    			withSpriteCount(nbImage).
	    			build();
	    	spriteEngine.start();
	    } 
		catch (IOException ex) {
	    	System.out.println(" Erreur Sptite Attack Left-> " + ex);
	    	ex.printStackTrace();
	    }
		return spriteSheet;
	}	
}
