/********************************************************************
 * 							WarStone								*
 *  -------------------------------------------------------------	*
 * |	 Université Jean-Monnet    L3-Infos 		    2021	 |	*
 *  -------------------------------------------------------------	*
 * 	  BEGGARI ISLEM - CHATAIGNIER ANTOINE - BENGUEZZOU Idriss		*
 * 																	*
 * 														sprite		*
 * ******************************************************************/
package sprite;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class SpriteSheetBuilder.
 */
public class SpriteSheetBuilder implements java.io.Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The sprite sheet. */
	private transient BufferedImage spriteSheet;
	
	/** The sprite hauteur. */
	private int spriteLargeur, spriteHauteur;
	
	/** The nombre sprite. */
	private int nombreSprite;
	
	/** The cols. */
	private int lignes, cols;

	/**
	 * With sheet.
	 *
	 * @param img the img
	 * @return the sprite sheet builder
	 */
	public SpriteSheetBuilder withSheet(BufferedImage img) {
		spriteSheet = img;
		return this;
	}

	/**
	 * With rows.
	 *
	 * @param lignes the lignes
	 * @return the sprite sheet builder
	 */
	public SpriteSheetBuilder withRows(int lignes) {
		this.lignes = lignes;
		return this;
	}

	/**
	 * With columns.
	 *
	 * @param cols the cols
	 * @return the sprite sheet builder
	 */
	public SpriteSheetBuilder withColumns(int cols) {
		this.cols = cols;
		return this;
	}

	/**
	 * With sprite size.
	 *
	 * @param Largeur the largeur
	 * @param Hauteur the hauteur
	 * @return the sprite sheet builder
	 */
	public SpriteSheetBuilder withSpriteSize(int Largeur, int Hauteur) {
		this.spriteLargeur = Largeur;
		this.spriteHauteur = Hauteur;
		return this;
	}

	/**
	 * With sprite count.
	 *
	 * @param nombre the nombre
	 * @return the sprite sheet builder
	 */
	public SpriteSheetBuilder withSpriteCount(int nombre) {
		this.nombreSprite = nombre;
		return this;
	}

	/**
	 * Gets the cols.
	 *
	 * @return the cols
	 */
	protected int getCols() {
		return cols;
	}

	/**
	 * Gets the lignes.
	 *
	 * @return the lignes
	 */
	protected int getLignes() {
		return lignes;
	}

	/**
	 * Gets the nombre sprite.
	 *
	 * @return the nombre sprite
	 */
	protected int getNombreSprite() {
		return nombreSprite;
	}

	/**
	 * Gets the sprite largeur.
	 *
	 * @return the sprite largeur
	 */
	protected int getSpriteLargeur() {
		return spriteLargeur;
	}

	/**
	 * Gets the sprite hauteur.
	 *
	 * @return the sprite hauteur
	 */
	protected int getSpriteHauteur() {
		return spriteHauteur;
	}

	/**
	 * Gets the sprite sheet.
	 *
	 * @return the sprite sheet
	 */
	protected BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	/**
	 * Builds the.
	 *
	 * @return the sprite sheet
	 */
	public SpriteSheet build() {
		int nombre = getNombreSprite();
		int lignes = getLignes();
		int cols = getCols();

		if (nombre == 0)
			nombre = lignes * cols;

		BufferedImage sprite = getSpriteSheet();

		int Largeur = getSpriteLargeur();
		int Hauteur = getSpriteHauteur() + lignes;

		if (Largeur == 0)
			Largeur = sprite.getWidth() / cols;

		if (Hauteur == 0)
			Hauteur = sprite.getHeight() / lignes;

		int x = 0;
		int y = lignes * Largeur;

		List<BufferedImage> sprites = new ArrayList<>(nombre);

		for (int index = 0; index < nombre; index++) {
			sprites.add(sprite.getSubimage(x, y, Largeur, Hauteur));
			x += Largeur;
		}

		return new SpriteSheet(sprites);
	}
}