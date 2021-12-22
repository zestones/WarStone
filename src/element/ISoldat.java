package element;

import java.awt.Image;
import java.awt.Toolkit;

import sprite.ISprite;
import utile.Position;
import wargame.IConfig;
	
public interface ISoldat extends IConfig, ISprite{
	//	Miniature des Heros
	Image ElfMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/miniature/Elf.png");
	Image HobbitMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/miniature/Hobbit.png");
	Image HumainMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/miniature/Humain.png");
	Image NainMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/miniature/Nain.png");

	// Miniature des Monstres
	Image GobelinMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/monstres/miniature/Gobelin.png");
	Image OrcMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/monstres/miniature/Orc.png");
	Image TrollMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/monstres/miniature/Troll.png");
	
	static enum TypesH { 
      HUMAIN (40,2,10,2, humain, HumainMiniature), NAIN (80,1,20,0, nain, NainMiniature), ELF (70,5,10,6, elf, ElfMiniature), HOBBIT (20,3,5,2,hobbit, HobbitMiniature);
      private final int POINTS_DE_VIE, PORTEE_VISUELLE, PUISSANCE, TIR;
      private final String SPRITE;
      private final Image IMAGE;
      TypesH(int points, int portee, int puissance, int tir, String spritePath, Image imgMiniature) {
    	  POINTS_DE_VIE = points; PORTEE_VISUELLE = portee;
    	  PUISSANCE = puissance; TIR = tir;
    	  SPRITE = spritePath;
    	  IMAGE = imgMiniature;
      }
      public int getPoints() { return POINTS_DE_VIE; }
      public int getPortee() { return PORTEE_VISUELLE; }
      public int getPuissance() { return PUISSANCE; }
      public int getTir() { return TIR; }
      public String getSprite() { return SPRITE; }
      public Image getImage() { return IMAGE; } 
      public static TypesH getTypeHAlea() {
         return values()[(int)(Math.random()*values().length)];
      }
   }
   public static enum TypesM {
      TROLL (100,1,30,0, troll, TrollMiniature), ORC (40,2,10,3, orc, OrcMiniature), GOBELIN (20,2,5,2, gobelin, GobelinMiniature);
      private final int POINTS_DE_VIE, PORTEE_VISUELLE, PUISSANCE, TIR;
      private final String SPRITE;
      private final Image IMAGE;
      TypesM(int points, int portee, int puissance, int tir, String spritePath, Image imgMiniature) {
    	  POINTS_DE_VIE = points; PORTEE_VISUELLE = portee;
    	  PUISSANCE = puissance; TIR = tir;
    	  SPRITE = spritePath;
    	  IMAGE = imgMiniature;
      }
      
      public int getPoints() { return POINTS_DE_VIE; }
      public int getPortee() { return PORTEE_VISUELLE; }
      public int getPuissance() { return PUISSANCE; }
      public int getTir() { return TIR; } 
      public String getSprite() { return SPRITE; }
      public Image getImage() { return IMAGE; } 
      public static TypesM getTypeMAlea() {
         return values()[(int)(Math.random()*values().length)];
      }
   }
   int getPoints(); 
   int getPortee();
   void seDeplace(Position newPos);
   void combat(Soldat soldat);	
}