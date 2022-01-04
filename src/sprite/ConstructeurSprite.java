package sprite;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Class ContructeurSprite.
 */
public class ConstructeurSprite implements java.io.Serializable, ISprite {
	private static final long serialVersionUID = 1L;
	private transient BufferedImage spriteImage;
	private int largeur, haurteur;
	private int nbImage;
	private int ligne;
	
	public ConstructeurSprite(BufferedImage img, int ligne, int nbImage) {
		this.spriteImage = img;
		this.ligne = ligne;
		this.nbImage = nbImage;
		this.tailleImage();
	}
	
	private void tailleImage() {
		this.largeur = IMAGE_LARGEUR;
		this.haurteur = IMAGE_HAUTEUR;
	}

	private int getLigne() {
		return this.ligne;
	}
	
	private int getNombreImage() {
		return this.nbImage;
	}
	
	private int getLargeur() {
		return this.largeur;
	}

	private int getHauteur() {
		return this.haurteur;
	}

	private BufferedImage getSpriteImage() {
		return this.spriteImage;
	}

	
	protected Sprite genereSprite() {
		int nbImage = getNombreImage();
		int ligne = getLigne();
		
		int largeur = getLargeur();
		int hauteur = getHauteur();

		BufferedImage sprite = getSpriteImage();
		
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