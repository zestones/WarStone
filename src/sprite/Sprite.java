package sprite;

import java.awt.image.BufferedImage;
import java.util.List;

import fenetrejeu.panneaujeu.IConfig;

/**
 * Class Sprite.
 */
public class Sprite implements IConfig, java.io.Serializable {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private final transient List<BufferedImage> sprites;

	/**
	 * Instancie un nouveau sprite.
	 *
	 * @param sprites
	 */
	public Sprite(List<BufferedImage> sprites) {
		this.sprites = sprites;
	}

	/**
	 * Gets image sprite.
	 *
	 * @param progression 
	 * @return image sprite
	 */
	public BufferedImage getImageSprite(double progression) {
		int frame = (int) (sprites.size() * progression);
		return sprites.get(frame);
	}
}
