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

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Le sprite stand by gauche. */
	public transient SpriteSheet spriteStandByGauche;

	/** Le sprite stand by droite. */
	public transient SpriteSheet spriteStandByDroite;

	/** Le sprite stand by haut. */
	public transient SpriteSheet spriteStandByHaut;

	/** Le sprite stand by bas. */
	public transient SpriteSheet spriteStandByBas;

	/** Le sprite attack gauche. */
	public transient SpriteSheet spriteAttackGauche;

	/** Le sprite attack droite. */
	public transient SpriteSheet spriteAttackDroite;

	/** Le sprite attack haut. */
	public transient SpriteSheet spriteAttackHaut;

	/** Le sprite attack bas. */
	public transient SpriteSheet spriteAttackBas;

	/** Le sprite deplace gauche. */
	public transient SpriteSheet spriteDeplaceGauche;

	/** soldat. */
	private final Soldat soldat;

	/**
	 * Instantiates a new sprite initializer.
	 *
	 * @param s the s
	 */
	/*
	 * On initialise le sprite pour chaque Heros lors de sa creation Si une partie
	 * est charge depuis une sauvegarde, on recharge les sprites en fonction du type
	 * (Aucun sprite est enregistre dans la sauvegarde !)
	 */
	public SpriteInitializer(Soldat s) {
		soldat = s;
		// Creation des sprites StandBy
		spriteStandByGauche = initSprite(LIGNE_STANDBY_GAUCHE, NB_IMAGE_STANDBY);
		spriteStandByDroite = initSprite(LIGNE_STANDBY_DROITE, NB_IMAGE_STANDBY);

		spriteStandByHaut = initSprite(LIGNE_STANDBY_HAUT, NB_IMAGE_STANDBY);
		spriteStandByBas = initSprite(LIGNE_STANDBY_BAS, NB_IMAGE_STANDBY);

		// Creation des sprites Attaque
		spriteAttackGauche = initSprite(LIGNE_ATTACK_GAUCHE, NB_IMAGE_ATTACK);
		spriteAttackDroite = initSprite(LIGNE_ATTACK_DROITE, NB_IMAGE_ATTACK);

		spriteAttackHaut = initSprite(LIGNE_ATTACK_HAUT, NB_IMAGE_ATTACK);
		spriteAttackBas = initSprite(LIGNE_ATTACK_BAS, NB_IMAGE_ATTACK);

		spriteDeplaceGauche = initSprite(LIGNE_MOVE_GAUCHE, NB_IMAGE_MOVE);

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
		try {
			final BufferedImage sprite = ImageIO.read(new File(soldat.getSprite()));
			spriteSheet = new SpriteSheetBuilder().withSheet(sprite).withColumns(0)
					.withSpriteSize(LARGEUR_IMAGE, HAUTEUR_IMAGE).withRows(ligne).withSpriteCount(nbImage).build();
			spriteEngine.start();
		} catch (final IOException ex) {
			System.out.println(" Erreur Sptite Attack Left-> " + ex);
			ex.printStackTrace();
		}
		return spriteSheet;
	}
}
