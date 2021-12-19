package sprite;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import wargame.IConfig;


public class SpriteSheet implements IConfig, java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private final transient List<BufferedImage> sprites;

    public SpriteSheet(List<BufferedImage> sprites) { this.sprites = new ArrayList<>(sprites); }
    public int count() { return sprites.size(); }

    public BufferedImage getSprite(double progress) {
        int frame = (int) (count() * progress);
        return sprites.get(frame);
    }
}
