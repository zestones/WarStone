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
	
	public transient SpriteSheet spriteDeplaceGauche;
	public transient SpriteSheet spriteDeplaceDroite;
	public transient SpriteSheet spriteDeplaceHaut;
	public transient SpriteSheet spriteDeplaceBas;
	
	private transient final Soldat soldat;

	/**
	 * On initialise le sprite pour chaque soldat lors de sa creation Si une partie
	 * est charge depuis une sauvegarde, on recharge les sprites en fonction du type
	 * (Aucun sprite est enregistre dans la sauvegarde !)
	 */
	public SpriteInitializer(Soldat s) {
		this.soldat = s;
		System.out.println("chemint -- > "  + soldat.getSprite());
		String chemin = this.soldat.getSprite();
		
		// Creation des sprites StandBy
		spriteStandByGauche = initSprite(LIGNE_STANDBY_GAUCHE, NB_IMAGE_STANDBY, chemin);
		spriteStandByDroite = initSprite(LIGNE_STANDBY_DROITE, NB_IMAGE_STANDBY, chemin);

		spriteStandByHaut = initSprite(LIGNE_STANDBY_HAUT, NB_IMAGE_STANDBY, chemin);
		spriteStandByBas = initSprite(LIGNE_STANDBY_BAS, NB_IMAGE_STANDBY, chemin);

		// Creation des sprites Attaque
		spriteAttackGauche = initSprite(LIGNE_ATTACK_GAUCHE, NB_IMAGE_ATTACK, chemin);
		spriteAttackDroite = initSprite(LIGNE_ATTACK_DROITE, NB_IMAGE_ATTACK, chemin);

		spriteAttackHaut = initSprite(LIGNE_ATTACK_HAUT, NB_IMAGE_ATTACK, chemin);
		spriteAttackBas = initSprite(LIGNE_ATTACK_BAS, NB_IMAGE_ATTACK, chemin);

		spriteDeplaceGauche = initSprite(LIGNE_DEPLACEMENT_GAUCHE, NB_IMAGE_DEPLACEMENT, chemin);
		spriteDeplaceDroite = initSprite(LIGNE_DEPLACEMENT_DROITE, NB_IMAGE_DEPLACEMENT, chemin);
		
		spriteDeplaceHaut = initSprite(LIGNE_DEPLACEMENT_HAUT, NB_IMAGE_DEPLACEMENT, chemin);
		spriteDeplaceBas = initSprite(LIGNE_DEPLACEMENT_BAS, NB_IMAGE_DEPLACEMENT, chemin);
	}

	/**
	 * Inits Le sprite.
	 *
	 * @param ligne   la ligne
	 * @param nbImage le nb image
	 * @return spriteSheet le sprite Sheet
	 */
	/* Genere un Sprite a l'aide du numero de ligne et le nombre d'image */
	private SpriteSheet initSprite(int ligne, int nbImage, String chemin) {
		SpriteSheet spriteSheet = null;
		try {
			BufferedImage sprite = ImageIO.read(new File(chemin));
			spriteSheet = new SpriteSheetBuilder().withSheet(sprite).withColumns(0)
					.withSpriteSize(LARGEUR_IMAGE, HAUTEUR_IMAGE).withRows(ligne).withSpriteCount(nbImage).build();
			spriteEngine.start();
		} catch (final IOException ex) {
			System.out.println(" Erreur Sprite " + ex);
			ex.printStackTrace();
		}
		return spriteSheet;
	}
}
