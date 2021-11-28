package wargame;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SpriteSheetBuilder implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private transient BufferedImage spriteSheet;
	private int spriteLargeur, spriteHauteur;
	private int nombreSprite;
	private int lignes, cols;
	
    public SpriteSheetBuilder withSheet(BufferedImage img) {
        spriteSheet = img;
        return this;
    }

    public SpriteSheetBuilder withRows(int lignes) {
        this.lignes = lignes;
        return this;
    }

    public SpriteSheetBuilder withColumns(int cols) {
        this.cols = cols;
        return this;
    }

    public SpriteSheetBuilder withSpriteSize(int Largeur, int Hauteur) {
        this.spriteLargeur = Largeur;
        this.spriteHauteur = Hauteur;
        return this;
    }

    public SpriteSheetBuilder withSpriteCount(int nombre) {
        this.nombreSprite = nombre;
        return this;
    }

    protected int getCols() { return cols; }
    protected int getLignes() { return lignes; }
    protected int getNombreSprite() { return nombreSprite; }
    protected int getSpriteLargeur() { return spriteLargeur; }
    protected int getSpriteHauteur() { return spriteHauteur; }
    protected BufferedImage getSpriteSheet() { return spriteSheet; }
    
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