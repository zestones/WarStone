
package sprite;

import java.awt.image.BufferedImage;
import java.util.List;

import fenetrejeu.panneaujeu.IConfig;

public class Sprite implements IConfig, java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private final transient List<BufferedImage> sprites;

	public Sprite(List<BufferedImage> sprites) {
		this.sprites = sprites;
	}

	public BufferedImage getImageSprite(double progress) {
		int frame = (int) (sprites.size() * progress);
		return sprites.get(frame);
	}
}
