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

	public transient SpriteSheet spriteReposGauche;
	public transient SpriteSheet spriteReposDroit;
	public transient SpriteSheet spriteReposHaut;
	public transient SpriteSheet spriteReposBas;
	
	public transient SpriteSheet spriteAttaqueGauche;
	public transient SpriteSheet spriteAttaqueDroit;
	public transient SpriteSheet spriteAttaqueHaut;
	public transient SpriteSheet spriteAttaqueBas;
	
	public transient SpriteSheet spriteAttaqueAdjacentGauche;
	public transient SpriteSheet spriteAttaqueAdjacentDroit;
	public transient SpriteSheet spriteAttaqueAdjacentHaut;
	public transient SpriteSheet spriteAttaqueAdjacentBas;

	public transient SpriteSheet spriteDeplacementGauche;
	public transient SpriteSheet spriteDeplacementDroit;
	public transient SpriteSheet spriteDeplacementHaut;
	public transient SpriteSheet spriteDeplacementBas;
	
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
		spriteReposGauche = initSprite(LIGNE_REPOS_GAUCHE, NB_IMAGE_REPOS);
		spriteReposDroit = initSprite(LIGNE_REPOS_DROIT, NB_IMAGE_REPOS);

		spriteReposHaut = initSprite(LIGNE_REPOS_HAUT, NB_IMAGE_REPOS);
		spriteReposBas = initSprite(LIGNE_REPOS_BAS, NB_IMAGE_REPOS);

		// initialisation des sprites Attaque a distance
		spriteAttaqueGauche = initSprite(LIGNE_ATTAQUE_GAUCHE, NB_IMAGE_ATTAQUE);
		spriteAttaqueDroit = initSprite(LIGNE_ATTAQUE_DROIT, NB_IMAGE_ATTAQUE);
		spriteAttaqueHaut = initSprite(LIGNE_ATTAQUE_HAUT, NB_IMAGE_ATTAQUE);
		spriteAttaqueBas = initSprite(LIGNE_ATTAQUE_BAS, NB_IMAGE_ATTAQUE);
		
		// initialisation des sprites Attaque voisin
		spriteAttaqueAdjacentGauche = initSprite(LIGNE_ATTAQUE_ADJACENT_GAUCHE, NB_IMAGE_ATTAQUE_ADJACENT);
		spriteAttaqueAdjacentDroit = initSprite(LIGNE_ATTAQUE_ADJACENT_DROIT, NB_IMAGE_ATTAQUE_ADJACENT);
		spriteAttaqueAdjacentHaut = initSprite(LIGNE_ATTAQUE_ADJACENT_HAUT, NB_IMAGE_ATTAQUE_ADJACENT);
		spriteAttaqueAdjacentBas = initSprite(LIGNE_ATTAQUE_ADJACENT_BAS, NB_IMAGE_ATTAQUE_ADJACENT);
		
		// initialisation des sprites deplacement
		spriteDeplacementGauche = initSprite(LIGNE_DEPLACEMENT_GAUCHE, NB_IMAGE_DEPLACEMENT);
		spriteDeplacementDroit = initSprite(LIGNE_DEPLACEMENT_DROIT, NB_IMAGE_DEPLACEMENT);
		spriteDeplacementHaut = initSprite(LIGNE_DEPLACEMENT_HAUT, NB_IMAGE_DEPLACEMENT);
		spriteDeplacementBas = initSprite(LIGNE_DEPLACEMENT_BAS, NB_IMAGE_DEPLACEMENT);
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
				.withSpriteSize(IMAGE_LARGEUR, IMAGE_HAUTEUR)
				.withRows(ligne)
				.withSpriteCount(nbImage)
				.build();
		spriteEngine.start();
		return spriteSheet;
	}
}
