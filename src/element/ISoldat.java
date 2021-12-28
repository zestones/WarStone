/********************************************************************
 * 							WarStone								*
 *  -------------------------------------------------------------	*
 * |	 Université Jean-Monnet    L3-Infos 		    2021	 |	*
 *  -------------------------------------------------------------	*
 * 	  BEGGARI ISLEM - CHATAIGNIER ANTOINE - BENGUEZZOU Idriss		*
 * 																	*
 * 														element		*
 * ******************************************************************/
package element;

import java.awt.Image;
import java.awt.Toolkit;

import sprite.ISprite;
import utile.Position;
	
/**
 * The Interface ISoldat.
 */
public interface ISoldat extends ISprite{
	
	/** The Elf miniature. */
	//	Miniature des Heros
	Image ElfMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/miniature/Elf.png");
	
	/** The Hobbit miniature. */
	Image HobbitMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/miniature/Hobbit.png");
	
	/** The Humain miniature. */
	Image HumainMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/miniature/Humain.png");
	
	/** The Nain miniature. */
	Image NainMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/miniature/Nain.png");

	/** The Gobelin miniature. */
	// Miniature des Monstres
	Image GobelinMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/monstres/miniature/Gobelin.png");
	
	/** The Orc miniature. */
	Image OrcMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/monstres/miniature/Orc.png");
	
	/** The Troll miniature. */
	Image TrollMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/monstres/miniature/Troll.png");
	
	/**
	 * The Enum TypesH.
	 */
	static enum TypesH { 
		
		/** The humain. */
		HUMAIN (40, 2, 10, 2, humain, HumainMiniature), 
		/** The nain. */
		NAIN (80, 1, 20, 0, nain, NainMiniature), 
		/** The elf. */
		ELF (70, 5, 10, 6, elf, ElfMiniature), 
		/** The hobbit. */
		HOBBIT (20, 3, 5, 2, hobbit, HobbitMiniature);
      
		/** The tir. */
		private final int POINTS_DE_VIE, PORTEE_VISUELLE, PUISSANCE, TIR;
      
		/** The sprite. */
		private final String SPRITE;
      
		/** The image. */
		private final Image IMAGE;
      
		/**
		 * Instantiates a new types H.
		 *
		 * @param points the points
		 * @param portee the portee
		 * @param puissance the puissance
		 * @param tir the tir
		 * @param spritePath the sprite path
		 * @param imgMiniature the img miniature
		 */
		TypesH(int points, int portee, int puissance, int tir, String spritePath, Image imgMiniature) {
			POINTS_DE_VIE = points; PORTEE_VISUELLE = portee;
			PUISSANCE = puissance; TIR = tir;
			SPRITE = spritePath;
			IMAGE = imgMiniature;
		}
      
      /**
       * Gets the points.
       *
       * @return the points
       */
      public int getPoints() { return POINTS_DE_VIE; }
      
      /**
       * Gets the portee.
       *
       * @return the portee
       */
      public int getPortee() { return PORTEE_VISUELLE; }
      
      /**
       * Gets the puissance.
       *
       * @return the puissance
       */
      public int getPuissance() { return PUISSANCE; }
      
      /**
       * Gets the tir.
       *
       * @return the tir
       */
      public int getTir() { return TIR; }
      
      /**
       * Gets the sprite.
       *
       * @return the sprite
       */
      public String getSprite() { return SPRITE; }
      
      /**
       * Gets the image.
       *
       * @return the image
       */
      public Image getImage() { return IMAGE; } 
      
      /**
       * Gets the type H alea.
       *
       * @return the type H alea
       */
      public static TypesH getTypeHAlea() {
         return values()[(int)(Math.random()*values().length)];
      }
   }
   
   /**
    * The Enum TypesM.
    */
   public static enum TypesM {
      
	   /** The troll. */
	   TROLL (100,1,30,0, troll, TrollMiniature), 
	   /** The orc. */
	   ORC (40,2,10,3, orc, OrcMiniature), 
	   /** The gobelin. */
	   GOBELIN (20,2,5,2, gobelin, GobelinMiniature);
      
	   /** The tir. */
	   private final int POINTS_DE_VIE, PORTEE_VISUELLE, PUISSANCE, TIR;
	   
	   /** The sprite. */
	   private final String SPRITE;
      
	   /** The image. */
	   private final Image IMAGE;
      
      /**
       * Instantiates a new types M.
       *
       * @param points the points
       * @param portee the portee
       * @param puissance the puissance
       * @param tir the tir
       * @param spritePath the sprite path
       * @param imgMiniature the img miniature
       */
	   TypesM(int points, int portee, int puissance, int tir, String spritePath, Image imgMiniature) {
		   POINTS_DE_VIE = points; PORTEE_VISUELLE = portee;
		   PUISSANCE = puissance; TIR = tir;
		   SPRITE = spritePath;
		   IMAGE = imgMiniature;
      }
      
      /**
       * Gets the points.
       *
       * @return the points
       */
      public int getPoints() { return POINTS_DE_VIE; }
      
      /**
       * Gets the portee.
       *
       * @return the portee
       */
      public int getPortee() { return PORTEE_VISUELLE; }
      
      /**
       * Gets the puissance.
       *
       * @return the puissance
       */
      public int getPuissance() { return PUISSANCE; }
      
      /**
       * Gets the tir.
       *
       * @return the tir
       */
      public int getTir() { return TIR; } 
      
      /**
       * Gets the sprite.
       *
       * @return the sprite
       */
      public String getSprite() { return SPRITE; }
      
      /**
       * Gets the image.
       *
       * @return the image
       */
      public Image getImage() { return IMAGE; } 
      
      /**
       * Gets the type M alea.
       *
       * @return the type M alea
       */
      public static TypesM getTypeMAlea() {
    	  return values()[(int)(Math.random()*values().length)];
      }
   }
   
   /**
    * Gets the points.
    *
    * @return the points
    */
   int getPoints(); 
   
   /**
    * Gets the portee.
    *
    * @return the portee
    */
   int getPortee();
   
   /**
    * Se deplace.
    *
    * @param newPos the new pos
    */
   void seDeplace(Position newPos);
   
   /**
    * Combat.
    *
    * @param soldat the soldat
    * @return true, if successful
    */
   boolean combat(Soldat soldat);	

}