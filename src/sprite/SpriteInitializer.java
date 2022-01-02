/********************************************************************
 * 							WarStone							    *
 *  -------------------------------------------------------------	*
 * |	 Université Jean-Monnet    L3-Infos 		    2021	 |	*
 *  -------------------------------------------------------------	*
 * 	  BEGGARI ISLEM - CHATAIGNIER ANTOINE - BENGUEZZOU Idriss		*
 * 																	*
 * 													   sprite	    *
 * ******************************************************************/
package sprite;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import element.Soldat;
import wargame.IConfig;

/**
 * La Class SpriteInitializer.
 */
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
	
	public transient SpriteSheet spriteAttackRangeGauche;
	public transient SpriteSheet spriteAttackRangeDroite;
	public transient SpriteSheet spriteAttackRangeHaut;
	public transient SpriteSheet spriteAttackRangeBas;

	public transient SpriteSheet spriteDeplaceGauche;
	public transient SpriteSheet spriteDeplaceDroite;
	public transient SpriteSheet spriteDeplaceHaut;
	public transient SpriteSheet spriteDeplaceBas;
	
	public transient SpriteSheet spriteMort;
	
	private transient final Soldat soldat;
	private transient BufferedImage sprite;
	private String chemin;
	
	/**
	 * On initialise le sprite pour chaque soldat lors de sa creation Si une partie
	 * est charge depuis une sauvegarde, on recharge les sprites en fonction du type
	 * (Aucun sprite est enregistre dans la sauvegarde !)
	 */
	public SpriteInitializer(Soldat s) {
		this.soldat = s;
		this.chemin = this.soldat.getSprite();
		
		try {
			this.sprite = ImageIO.read(new File(this.chemin));
		} catch (IOException e) {
			e.printStackTrace();
		}
 
		// initialisation des sprites StandBy
		spriteStandByGauche = initSprite(LIGNE_STANDBY_GAUCHE, NB_IMAGE_STANDBY);
		spriteStandByDroite = initSprite(LIGNE_STANDBY_DROITE, NB_IMAGE_STANDBY);

		spriteStandByHaut = initSprite(LIGNE_STANDBY_HAUT, NB_IMAGE_STANDBY);
		spriteStandByBas = initSprite(LIGNE_STANDBY_BAS, NB_IMAGE_STANDBY);

		// initialisation des sprites Attaque a distance
		spriteAttackGauche = initSprite(LIGNE_ATTACK_GAUCHE, NB_IMAGE_ATTACK);
		spriteAttackDroite = initSprite(LIGNE_ATTACK_DROITE, NB_IMAGE_ATTACK);
		spriteAttackHaut = initSprite(LIGNE_ATTACK_HAUT, NB_IMAGE_ATTACK);
		spriteAttackBas = initSprite(LIGNE_ATTACK_BAS, NB_IMAGE_ATTACK);
		
		// initialisation des sprites Attaque voisin
		spriteAttackRangeGauche = initSprite(LIGNE_ATTACK_RANGE_GAUCHE, NB_IMAGE_ATTACK_RANGE);
		spriteAttackRangeDroite = initSprite(LIGNE_ATTACK_RANGE_DROITE, NB_IMAGE_ATTACK_RANGE);
		spriteAttackRangeHaut = initSprite(LIGNE_ATTACK_RANGE_HAUT, NB_IMAGE_ATTACK_RANGE);
		spriteAttackRangeBas = initSprite(LIGNE_ATTACK_RANGE_BAS, NB_IMAGE_ATTACK_RANGE);
		
		// initialisation des sprites deplacement
		spriteDeplaceGauche = initSprite(LIGNE_DEPLACEMENT_GAUCHE, NB_IMAGE_DEPLACEMENT);
		spriteDeplaceDroite = initSprite(LIGNE_DEPLACEMENT_DROITE, NB_IMAGE_DEPLACEMENT);
		spriteDeplaceHaut = initSprite(LIGNE_DEPLACEMENT_HAUT, NB_IMAGE_DEPLACEMENT);
		spriteDeplaceBas = initSprite(LIGNE_DEPLACEMENT_BAS, NB_IMAGE_DEPLACEMENT);
	}

	/**
	 * Inits Le sprite.
	 *
	 * @param ligne   la ligne
	 * @param nbImage le nb image
	 * @return spriteSheet le sprite Sheet
	 */
	/* Genere un Sprite a l'aide du numero de ligne et le nombre d'image */
	private SpriteSheet initSprite(int ligne, int nbImage) {
		SpriteSheet spriteSheet = null;
		spriteSheet = new SpriteSheetBuilder()
				.withSheet(this.sprite)
				.withColumns(0)
				.withSpriteSize(LARGEUR_IMAGE, HAUTEUR_IMAGE)
				.withRows(ligne)
				.withSpriteCount(nbImage)
				.build();
		spriteEngine.start();
		return spriteSheet;
	}
}
