package carte.element;

import java.awt.Image;
import java.awt.Toolkit;

import sprite.ISprite;
import utile.Position;
	
/**
 *  Interface ISoldat.
 */
public interface ISoldat extends ISprite{
	
	/** miniature des Heros. */
	Image ElfMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/miniature/Elf.png");
	Image HobbitMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/miniature/Hobbit.png");
	Image HumainMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/miniature/Humain.png");
	Image NainMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/miniature/Nain.png");
	Image SorcierMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/miniature/Sorcier.png");
		
	/** Miniature des Monstres */
	Image GobelinMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/monstres/miniature/Gobelin.png");
	Image OrcMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/monstres/miniature/Orc.png");
	Image TrollMiniature = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/monstres/miniature/Troll.png");
	
	/** Icon des heros sur la Mini Carte */
	Image ElfMiniCarte = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/mini-Carte/Elf.png");
	Image HobbitMiniCarte = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/mini-Carte/Hobbit.png");
	Image HumainMiniCarte = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/mini-Carte/Humain.png");
	Image NainMiniCarte = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/mini-Carte/Nain.png");
	Image SorcierMiniCarte = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/mini-Carte/Sorcier.png");
	
	/** Icon des Monstres sur la miniCarte*/
	Image GobelinMiniCarte = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/monstres/mini-Carte/Gobelin.png");
	Image OrcMiniCarte = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/monstres/mini-Carte/Orc.png");
	Image TrollMiniCarte = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/monstres/mini-Carte/Troll.png");
	

	/** Description des heros */
	String histoireNain = "Puisqu'ils vivent essentiellement sous terre, les Nains sont peu férus d'agriculture "
			+ "et d'élevage, préférant commercer avec les autres races pour obtenir ces biens. <br> <br>"
			+ "Ils sont dits être des amis loyaux, mais des adversaires rancuniers "
			+ "et tenaces, qui n'oublient jamais ni une insulte, ni une bonne action.";
	
	String histoireHumain = "Des creatures intelligentes et mallines "
			+"leur point fort est ce qu'on appelle le cerveau "
			+"et c'est avec ca qu'ils ont pu conquerir le monde. <br><br>"
			+"Ils sont capables d'utiliser differentes armes et techniques de magie";
	
	String histoireElf = "Créature légendaire anthropomorphe "
			+ "vivant le plus souvent dans des forêts. <br><br>"
			+ "Considérés comme immortels et dotés de pouvoirs magiques";
	
	String histoireHobbit = "Créatures apparentées aux Hommes caractérisées par leur petite taille,"
			+"leur pieds à l'abondante pilosité, leurs oreilles légèrement pointues et leur visage rubicond <br><br>"
			+"vivant Au cœur de l'Eriador.";
	
	/** Description des Monstres */
	String histoireGoblin = "Créature légendaire, anthropomorphe et de petite taille,<br>"
			+"issue du folklore médiéval européen.Ils vivent généralement en groupe dans les cavernes.<br><br>"
			+"On les trouve en grand nombre dans la Moria.";
	
	String histoireTroll = "Un être de la mythologie nordique, "
			+ "incarnant les forces naturelles ou la magie, <br>"
			+ "caractérisé principalement par son opposition aux hommes <br><br>"
			+ "Ils vivent dans le dangereux Pays des Trolls, "
			+ "un lieu peuplé de nombreuses créatures tout aussi dangereuses.";
	
	String histoireOrc = "Les Orcs vivent en symbiose avec des champignons et algues qui leur donnent leur couleur verte, <br>"
			+ "et leur incroyable résistance et potentiel de régénération.<br>"
			+ "Leur culture est semblable : les plus forts dirigent, <br> et deviennent plus grands et plus forts <br>"
			+ "grâce à l’énergie psychique des Orcs sous leur commandement.";
	
	
	/**
	 * Enum TypesH.
	 */
	static enum TypesH { 
		HUMAIN (40, 2, 10, 2, humain, HumainMiniature, HumainMiniCarte, histoireHumain), 
		NAIN (80, 1, 20, 0, nain, NainMiniature, NainMiniCarte, histoireNain), 
		ELF (70, 5, 10, 6, elf, ElfMiniature, ElfMiniCarte ,histoireElf), 
		HOBBIT (20, 3, 5, 2, hobbit, HobbitMiniature, HobbitMiniCarte, histoireHobbit),
		SORCIER (30, 3, 1, 10, sorcier, SorcierMiniature, SorcierMiniCarte, "");
      
		private final int POINTS_DE_VIE, PORTEE_VISUELLE, PUISSANCE, TIR;	
		private final String SPRITE;
		private final Image IMAGE_MINIATURE;
		private final Image IMAGE_MINICARTE;
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
		TypesH(int points, int portee, int puissance, int tir, String cheminSprite, Image imgMiniature, Image imgMiniCarte, String histoire) {
			POINTS_DE_VIE = points; PORTEE_VISUELLE = portee;
			PUISSANCE = puissance; TIR = tir;
			SPRITE = cheminSprite;
			IMAGE_MINIATURE = imgMiniature;
			IMAGE_MINICARTE = imgMiniCarte;
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
      public Image getMiniature() { return IMAGE_MINIATURE; } 
      
      /**
       * Gets  image.
       *
       * @return  image
       */
      public Image getImageMiniCarte() { return IMAGE_MINICARTE; } 

      
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
       TROLL (100,1,30,0, troll, TrollMiniature, TrollMiniCarte,histoireTroll), 
	   ORC (40,2,10,3, orc, OrcMiniature, OrcMiniCarte,histoireOrc), 
	   GOBELIN (20,2,5,2, gobelin, GobelinMiniature, GobelinMiniCarte,histoireGoblin);
      
	   private final int POINTS_DE_VIE, PORTEE_VISUELLE, PUISSANCE, TIR;
	   private final String SPRITE;
       private final Image IMAGE_MINIATURE;
       private final Image IMAGE_MINICARTE;
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
	   TypesM(int points, int portee, int puissance, int tir, String cheminSprite, Image imgMiniature, Image imgMiniCarte, String histoire) {
		   POINTS_DE_VIE = points; PORTEE_VISUELLE = portee;
		   PUISSANCE = puissance; TIR = tir;
		   SPRITE = cheminSprite;
		   IMAGE_MINIATURE = imgMiniature;
		   IMAGE_MINICARTE = imgMiniCarte;
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
      public Image getMiniature() { return IMAGE_MINIATURE; } 
      
      /**
       * Gets  image.
       *
       * @return  image
       */
      public Image getImageMiniCarte() { return IMAGE_MINICARTE; } 

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