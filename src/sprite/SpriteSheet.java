/********************************************************************
 * 							WarStone							*
 *  -------------------------------------------------------------	*
 * |	 Université Jean-Monnet    L3-Infos 		    2021	 |	*
 *  -------------------------------------------------------------	*
 * 	  BEGGARI ISLEM - CHATAIGNIER ANTOINE - BENGUEZZOU Idriss		*
 * 																	*
 * 													sprite	*
 * ******************************************************************/
package sprite;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import wargame.IConfig;

/**
 * La Class SpriteSheet.
 */
public class SpriteSheet implements IConfig, java.io.Serializable {
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** sprites. */
	private final transient List<BufferedImage> sprites;

	/**
	 * Instantiates a new sprite sheet.
	 *
	 * @param sprites List
	 */
	public SpriteSheet(List<BufferedImage> sprites) {
		this.sprites = new ArrayList<>(sprites);
	}

	/**
	 * Count.
	 *
	 * @return  int
	 */
	public int count() {
		return sprites.size();
	}

	/**
	 * Gets  sprite.
	 *
	 * @param progress  double
	 * @return  sprite
	 */
	public BufferedImage getSprite(double progress) {
		int frame = (int) (count() * progress);
		return sprites.get(frame);
	}
}
