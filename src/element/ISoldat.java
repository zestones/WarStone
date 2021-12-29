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
 *  Interface ISoldat.
 */
public interface ISoldat extends ISprite{
	
	/**  Elf miniature. */
	//	Miniature des Heros
	Image ElfMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/miniature/Elf.png");
	
	/**  Hobbit miniature. */
	Image HobbitMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/miniature/Hobbit.png");
	
	/**  Humain miniature. */
	Image HumainMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/miniature/Humain.png");
	
	/**  Nain miniature. */
	Image NainMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/miniature/Nain.png");

	/**  Gobelin miniature. */
	// Miniature des Monstres
	Image GobelinMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/monstres/miniature/Gobelin.png");
	
	/**  Orc miniature. */
	Image OrcMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/monstres/miniature/Orc.png");
	
	/**  Troll miniature. */
	Image TrollMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/monstres/miniature/Troll.png");
	
	/**
	 *  Enum TypesH.
	 */
	static enum TypesH { 
		
		/**  humain. */
		HUMAIN (40, 2, 10, 2, humain, HumainMiniature), 
		/**  nain. */
		NAIN (80, 1, 20, 0, nain, NainMiniature), 
		/**  elf. */
		ELF (70, 5, 10, 6, elf, ElfMiniature), 
		/**  hobbit. */
		HOBBIT (20, 3, 5, 2, hobbit, HobbitMiniature);
      
		/**  tir. */
		private final int POINTS_DE_VIE, PORTEE_VISUELLE, PUISSANCE, TIR;
      
		/**  sprite. */
		private final String SPRITE;
      
		/**  image. */
		private final Image IMAGE;
      
		/**
		 * Instantiates a new types H.
		 *
		 * @param points  points
		 * @param portee  portee
		 * @param puissance  puissance
		 * @param tir  tir
		 * @param spritePath  sprite path
		 * @param imgMiniature  img miniature
		 */
		TypesH(int points, int portee, int puissance, int tir, String spritePath, Image imgMiniature) {
			POINTS_DE_VIE = points; PORTEE_VISUELLE = portee;
			PUISSANCE = puissance; TIR = tir;
			SPRITE = spritePath;
			IMAGE = imgMiniature;
		}
      
      /**
       * Gets  points.
       *
       * @return  points
       */
      public int getPoints() { return POINTS_DE_VIE; }
      
      /**
       * Gets  portee.
       *
       * @return  portee
       */
      public int getPortee() { return PORTEE_VISUELLE; }
      
      /**
       * Gets  puissance.
       *
       * @return  puissance
       */
      public int getPuissance() { return PUISSANCE; }
      
      /**
       * Gets  tir.
       *
       * @return  tir
       */
      public int getTir() { return TIR; }
      
      /**
       * Gets  sprite.
       *
       * @return  sprite
       */
      public String getSprite() { return SPRITE; }
      
      /**
       * Gets  image.
       *
       * @return  image
       */
      public Image getImage() { return IMAGE; } 
      
      /**
       * Gets  type H alea.
       *
       * @return  type H alea
       */
      public static TypesH getTypeHAlea() {
         return values()[(int)(Math.random()*values().length)];
      }
   }
   
   /**
    *  Enum TypesM.
    */
   public static enum TypesM {
      
	   /**  troll. */
	   TROLL (100,1,30,0, troll, TrollMiniature), 
	   /**  orc. */
	   ORC (40,2,10,3, orc, OrcMiniature), 
	   /**  gobelin. */
	   GOBELIN (20,2,5,2, gobelin, GobelinMiniature);
      
	   /**  tir. */
	   private final int POINTS_DE_VIE, PORTEE_VISUELLE, PUISSANCE, TIR;
	   
	   /**  sprite. */
	   private final String SPRITE;
      
	   /**  image. */
	   private final Image IMAGE;
      
      /**
       * Instantiates a new types M.
       *
       * @param points  points
       * @param portee  portee
       * @param puissance  puissance
       * @param tir  tir
       * @param spritePath  sprite path
       * @param imgMiniature  img miniature
       */
	   TypesM(int points, int portee, int puissance, int tir, String spritePath, Image imgMiniature) {
		   POINTS_DE_VIE = points; PORTEE_VISUELLE = portee;
		   PUISSANCE = puissance; TIR = tir;
		   SPRITE = spritePath;
		   IMAGE = imgMiniature;
      }
      
      /**
       * Gets  points.
       *
       * @return  points
       */
      public int getPoints() { return POINTS_DE_VIE; }
      
      /**
       * Gets  portee.
       *
       * @return  portee
       */
      public int getPortee() { return PORTEE_VISUELLE; }
      
      /**
       * Gets  puissance.
       *
       * @return  puissance
       */
      public int getPuissance() { return PUISSANCE; }
      
      /**
       * Gets  tir.
       *
       * @return  tir
       */
      public int getTir() { return TIR; } 
      
      /**
       * Gets  sprite.
       *
       * @return  sprite
       */
      public String getSprite() { return SPRITE; }
      
      /**
       * Gets  image.
       *
       * @return  image
       */
      public Image getImage() { return IMAGE; } 
      
      /**
       * Gets  type M alea.
       *
       * @return  type M alea
       */
      public static TypesM getTypeMAlea() {
    	  return values()[(int)(Math.random()*values().length)];
      }
   }
   
   /**
    * Gets  points.
    *
    * @return  points
    */
   int getPoints(); 
   
   /**
    * Gets  portee.
    *
    * @return  portee
    */
   int getPortee();
   
   /**
    * Se deplace.
    *
    * @param newPos  new pos
    */
   void seDeplace(Position newPos);
   
   /**
    * Combat.
    *
    * @param soldat  soldat
    * @return true, if successful
    */
   boolean combat(Soldat soldat);	

}