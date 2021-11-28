package wargame;

public interface ISoldat extends IConfig{
	static enum TypesH {
      HUMAIN (40,3,10,2, humain), NAIN (80,1,20,0, nain), ELF (70,5,10,6, elf), HOBBIT (20,3,5,2,hobbit);
      private final int POINTS_DE_VIE, PORTEE_VISUELLE, PUISSANCE, TIR;
      private final String SPRITE;
      TypesH(int points, int portee, int puissance, int tir, String spritePath) {
    	  POINTS_DE_VIE = points; PORTEE_VISUELLE = portee;
    	  PUISSANCE = puissance; TIR = tir;
    	  SPRITE = spritePath;
      }
      public int getPoints() { return POINTS_DE_VIE; }
      public int getPortee() { return PORTEE_VISUELLE; }
      public int getPuissance() { return PUISSANCE; }
      public int getTir() { return TIR; }
      public String getSprite() { return SPRITE; }
      public static TypesH getTypeHAlea() {
         return values()[(int)(Math.random()*values().length)];
      }
   }
   public static enum TypesM {
      TROLL (100,1,30,0, troll), ORC (40,2,10,3, orc), GOBELIN (20,2,5,2, gobelin);
      private final int POINTS_DE_VIE, PORTEE_VISUELLE, PUISSANCE, TIR;
      private final String SPRITE;
      TypesM(int points, int portee, int puissance, int tir, String spritePath) {
    	  POINTS_DE_VIE = points; PORTEE_VISUELLE = portee;
    	  PUISSANCE = puissance; TIR = tir;
    	  SPRITE = spritePath;
      }
      public int getPoints() { return POINTS_DE_VIE; }
      public int getPortee() { return PORTEE_VISUELLE; }
      public int getPuissance() { return PUISSANCE; }
      public int getTir() { return TIR; } 
      public String getSprite() { return SPRITE; }
      public static TypesM getTypeMAlea() {
         return values()[(int)(Math.random()*values().length)];
      }
   }
   int getPoints(); 
   int getPortee();
   void seDeplace(Position newPos);
   void combat(Soldat soldat);	
}