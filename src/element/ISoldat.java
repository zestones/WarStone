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

	Image SorcierMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/miniature/Sorcier.png");

	
	/**  Gobelin miniature. */
	Image GobelinMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/monstres/miniature/Gobelin.png");
	
	/**  Orc miniature. */
	Image OrcMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/monstres/miniature/Orc.png");
	
	/**  Troll miniature. */
	Image TrollMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/monstres/miniature/Troll.png");
		
	/** histoire Nain*/
	String histoireNain = "Puisqu'ils vivent essentiellement sous terre, les Nains sont peu férus d'agriculture \r\n"
			+ "et d'élevage, préférant commercer avec les autres races pour obtenir ces biens. \r\n"
			+ "Ils sont dits être des amis loyaux, mais des adversaires rancuniers \r\n"
			+ "et tenaces, qui n'oublient jamais ni une insulte, ni une bonne action.";
	
	/** The histoire humain. */
	String histoireHumain = "Des creatures intelligentes et mallines \r\n"
			+"leur point fort est ce qu'on appelle le cerveau \r\n"
			+"et c'est avec ca qu'ils ont pu conquerir le monde\r\n"
			+"ils sont capables d'utiliser des differentes armes et techniques de magie";
	
	/** The histoire elf. */
	String histoireElf = "Créature légendaire anthropomorphe \r\n"
			+ "vivant le plus souvent dans des forêts \r\n"
			+ "considérés comme immortels et dotés de pouvoirs magiques";
	
	/** The histoire hobbit. */
	String histoireHobbit = "Créatures apparentées aux Hommes caractérisées par leur petite taille \r\n"
			+"leur pieds à l'abondante pilosité, leurs oreilles légèrement pointues et leur visage rubicond\r\n"
			+"vivant Au cœur de l'Eriador";
	
	/** The histoire goblin. */
	String histoireGoblin = "Créature légendaire, anthropomorphe et de petite taille,\r\n"
			+"issue du folklore médiéval européen.Ils vivent généralement en groupe dans les cavernes.\r\n"
			+"On les trouve en grand nombre dans la Moria.";
	
	/** The histoire troll. */
	String histoireTroll = "Un être de la mythologie nordique, \r\n"
			+ "incarnant les forces naturelles ou la magie, \r\n"
			+ "caractérisé principalement par son opposition aux hommes\r\n"
			+ "Ils vivent dans le dangereux Pays des Trolls, \r\n"
			+ "un lieu peuplé de nombreuses créatures tout aussi dangereuses,";
	
	/** The histoire orc. */
	String histoireOrc = "Les Orks vivent en symbiose avec des champignons et algues qui leur donnent leur couleur verte, \r\n"
			+ "et leur incroyable résistance et potentiel de régénération. \r\n"
			+ "Leur culture est semblable : les plus forts dirigent, et deviennent plus grands et plus forts \r\n"
			+ "grâce à l’énergie psychique des Orks sous leur commandement.";
	
	
	/**
	 * The Enum TypesH.
	 */
	static enum TypesH { 
		HUMAIN (40, 2, 10, 2, humain, HumainMiniature, histoireHumain), 
		NAIN (80, 1, 20, 0, nain, NainMiniature, histoireNain), 
		ELF (70, 5, 10, 6, elf, ElfMiniature, histoireElf), 
		HOBBIT (20, 3, 5, 2, hobbit, HobbitMiniature, histoireHobbit),
		SORCIER (30, 3, 1, 10, sorcier, SorcierMiniature, "");
      
		private final int POINTS_DE_VIE, PORTEE_VISUELLE, PUISSANCE, TIR;	
		private final String SPRITE;
		private final Image IMAGE;
		private final String HISTOIRE;
    
		/**
		 * instancie un nouveau TypesH.
		 *
		 * @param points
		 * @param portee
		 * @param puissance 
		 * @param tir 
		 * @param cheminSprite 
		 * @param imgMiniature  
		 * @param histoire
		 */
		TypesH(int points, int portee, int puissance, int tir, String cheminSprite, Image imgMiniature, String histoire) {
			POINTS_DE_VIE = points; PORTEE_VISUELLE = portee;
			PUISSANCE = puissance; TIR = tir;
			SPRITE = cheminSprite;
			IMAGE = imgMiniature;
			HISTOIRE = histoire;
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
      public String getCheminSprite() { return SPRITE; }
      
      /**
       * Gets  image.
       *
       * @return  image
       */
      public Image getMiniature() { return IMAGE; } 
      
      
      /**
       * Gets the histoire.
       *
       * @return the histoire
       */
      public String getHistoire() { return HISTOIRE; } 
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
       TROLL (100,1,30,0, troll, TrollMiniature, histoireTroll), 
	   ORC (40,2,10,3, orc, OrcMiniature, histoireOrc), 
	   GOBELIN (20,2,5,2, gobelin, GobelinMiniature, histoireGoblin);
      
	   private final int POINTS_DE_VIE, PORTEE_VISUELLE, PUISSANCE, TIR;
	   private final String SPRITE;
       private final Image IMAGE;
       private final String HISTOIRE;
      
      /**
       * Instancie un nouveau TypesM.
       *
       * @param points
       * @param portee 
       * @param puissance 
       * @param tir 
       * @param cheminSprite 
       * @param imgMiniature 
       * @param histoire
       */
	   TypesM(int points, int portee, int puissance, int tir, String cheminSprite, Image imgMiniature,String histoire) {
		   POINTS_DE_VIE = points; PORTEE_VISUELLE = portee;
		   PUISSANCE = puissance; TIR = tir;
		   SPRITE = cheminSprite;
		   IMAGE = imgMiniature;
		   HISTOIRE = histoire;
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
      public String getCheminSprite() { return SPRITE; }
      
      /**
       * Gets  image.
       *
       * @return  image
       */
      public Image getMiniature() { return IMAGE; } 
      
      /**
       * Gets  histoire.
       *
       * @return  String
       */
      public String getHistoire() { return HISTOIRE; } 
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