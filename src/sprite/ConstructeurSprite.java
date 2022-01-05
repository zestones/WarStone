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
	
	protected Sprite genereSprite() {
		int nbImage = this.nbImage;
		int ligne = this.ligne;
		
		int largeur = this.largeur;
		int hauteur = this.haurteur;

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