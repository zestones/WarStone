package sprite;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import carte.element.Soldat;
import fenetrejeu.panneaujeu.IConfig;

/**
 * La Class SpriteInitializer.
 */
public class InitialiseurSprite implements IConfig, ISprite {
	private static final long serialVersionUID = 1L;

	public transient Sprite spriteReposGauche;
	public transient Sprite spriteReposDroit;
	public transient Sprite spriteReposHaut;
	public transient Sprite spriteReposBas;
	
	public transient Sprite spriteAttaqueGauche;
	public transient Sprite spriteAttaqueDroit;
	public transient Sprite spriteAttaqueHaut;
	public transient Sprite spriteAttaqueBas;
	
	public transient Sprite spriteAttaqueAdjacentGauche;
	public transient Sprite spriteAttaqueAdjacentDroit;
	public transient Sprite spriteAttaqueAdjacentHaut;
	public transient Sprite spriteAttaqueAdjacentBas;

	public transient Sprite spriteDeplacementGauche;
	public transient Sprite spriteDeplacementDroit;
	public transient Sprite spriteDeplacementHaut;
	public transient Sprite spriteDeplacementBas;
	
	public transient Sprite spriteMort;
	
	private transient final Soldat soldat;
	private transient BufferedImage sprite;
	private String chemin;
	
	/**
	 * On initialise le sprite pour chaque soldat lors de sa creation Si une partie
	 * est charge depuis une sauvegarde, on recharge les sprites en fonction du type
	 * (Aucun sprite est enregistre dans la sauvegarde !)
	 */
	public InitialiseurSprite(Soldat s) {
		this.soldat = s;
		this.chemin = this.soldat.getCheminSprite();
		
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
	 * @param ligne
	 * @param nbImage
	 * @return spriteSheet
	 */
	/* Genere un Sprite a l'aide du numero de ligne et le nombre d'image */
	private Sprite initSprite(int ligne, int nbImage) {
		ConstructeurSprite consSprite = new ConstructeurSprite(this.sprite, ligne, nbImage);
		Sprite spriteSheet = consSprite.genereSprite();
		
		spriteEngine.start();
		return spriteSheet;
	}
}
