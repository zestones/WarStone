package wargame;

import java.awt.Image;
import java.awt.Toolkit;

public interface ISoldat {
	Image humain = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/humain.png");
	Image nain = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/nain.png");
	Image elf = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/elf.png");
	Image hobbit = Toolkit.getDefaultToolkit().getImage("./res/img/soldat/heros/hobbit.png");
	
   static enum TypesH {
      HUMAIN (40,3,10,2, humain), NAIN (80,1,20,0, nain), ELF (70,5,10,6, elf), HOBBIT (20,3,5,2,hobbit);
      private final int POINTS_DE_VIE, PORTEE_VISUELLE, PUISSANCE, TIR;
      private final Image IMAGE;
      TypesH(int points, int portee, int puissance, int tir, Image img) {
    	  POINTS_DE_VIE = points; PORTEE_VISUELLE = portee;
    	  PUISSANCE = puissance; TIR = tir;
    	  IMAGE = img;
      }
      public int getPoints() { return POINTS_DE_VIE; }
      public int getPortee() { return PORTEE_VISUELLE; }
      public int getPuissance() { return PUISSANCE; }
      public int getTir() { return TIR; }
      public Image getImage() { return IMAGE;  }
      public static TypesH getTypeHAlea() {
         return values()[(int)(Math.random()*values().length)];
      }
   }
   public static enum TypesM {
      TROLL (100,1,30,0), ORC (40,2,10,3), GOBELIN (20,2,5,2);
      private final int POINTS_DE_VIE, PORTEE_VISUELLE, PUISSANCE, TIR;
      TypesM(int points, int portee, int puissance, int tir) {
    	  POINTS_DE_VIE = points; PORTEE_VISUELLE = portee;
    	  PUISSANCE = puissance; TIR = tir;
      }
      public int getPoints() { return POINTS_DE_VIE; }
      public int getPortee() { return PORTEE_VISUELLE; }
      public int getPuissance() { return PUISSANCE; }
      public int getTir() { return TIR; } 
      public static TypesM getTypeMAlea() {
         return values()[(int)(Math.random()*values().length)];
      }
   }
   int getPoints(); 
   int getPortee();
   void seDeplace(Position newPos);
   void combat(Soldat soldat);	
}