package sprite;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Class ContructeurSprite.
 */
public class ConstructeurSprite implements java.io.Serializable, ISprite {
	
	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private transient BufferedImage spriteImage;
	private int largeur, hauteur;
	private int nbImage;
	private int ligne;
	
	/**
	 * Instancie un nouveau constructeur de sprite.
	 *
	 * @param img img
	 * @param ligne ligne
	 * @param nbImage nb image
	 */
	public ConstructeurSprite(BufferedImage img, int ligne, int nbImage) {
		this.spriteImage = img;
		this.ligne = ligne;
		this.nbImage = nbImage;
		this.tailleImage();
	}
	
	/**
	 * Taille image.
	 */
	private void tailleImage() {
		this.largeur = IMAGE_LARGEUR;
		this.hauteur = IMAGE_HAUTEUR;
	}	
	
	/**
	 * Genere sprite.
	 *
	 * @return sprite
	 */
	protected Sprite genereSprite() {
		int nbImage = this.nbImage;
		int ligne = this.ligne;
		
		int largeur = this.largeur;
		int hauteur = this.hauteur;

		BufferedImage sprite = this.spriteImage;
		
		int x = 0;
		int y = ligne * largeur;

		List<BufferedImage> sprites = new ArrayList<>(nbImage);

		for(int index = 0; index < nbImage; index++) {
			sprites.add(sprite.getSubimage(x, y, largeur, hauteur));
			x += largeur;
		}

		return new Sprite(sprites);
	}
}