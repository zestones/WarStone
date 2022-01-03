/********************************************************************
 * 							WarStone							    *
 *  -------------------------------------------------------------	*
 * |	 Université Jean-Monnet    L3-Infos 		    2021	 |	*
 *  -------------------------------------------------------------	*
 * 	  BEGGARI ISLEM - CHATAIGNIER ANTOINE - BENGUEZZOU Idriss		*
 * 																	*
 *	 													sprite  	*
 * ******************************************************************/


package sprite;

/**
 * Interface ISprite.
 */
public interface ISprite {

	/** Chemin des sprites. */
	/** humain. */
	String humain = "./res/img/soldat/heros/sprite/humain.png";

	/** nain. */
	String nain = "./res/img/soldat/heros/sprite/nain.png";

	/** elf. */
	String elf = "./res/img/soldat/heros/sprite/elf.png";

	/** hobbit. */
	String hobbit = "./res/img/soldat/heros/sprite/hobbit.png";

	String sorcier = "./res/img/soldat/heros/sprite/sorcier.png";
	
	/** troll. */
	// Chemin des sprites pour les monstres
	String troll = "./res/img/soldat/monstres/sprite/troll.png";

	/** orc. */
	String orc = "./res/img/soldat/monstres/sprite/orc.png";

	/** gobelin. */
	String gobelin = "./res/img/soldat/monstres/sprite/gobelin.png";

	/** frequence des image pour la lecture des sprites. */
	public SpriteEngine spriteEngine = new SpriteEngine(33);

	int IMAGE_LARGEUR = 64;
	int IMAGE_HAUTEUR = 58;

	int LIGNE_REPOS_HAUT = 0;
	int LIGNE_REPOS_GAUCHE = 1;
	int LIGNE_REPOS_BAS = 2;
	int LIGNE_REPOS_DROIT = 3;

	int NB_IMAGE_REPOS = 7;

	int LIGNE_ATTAQUE_GAUCHE = 17;
	int LIGNE_ATTAQUE_DROIT = 19;
	int LIGNE_ATTAQUE_HAUT = 18;
	int LIGNE_ATTAQUE_BAS = 16;

	int NB_IMAGE_ATTAQUE = 13;

	int LIGNE_ATTAQUE_ADJACENT_HAUT = 14;
	int LIGNE_ATTAQUE_ADJACENT_GAUCHE = 13;
	int LIGNE_ATTAQUE_ADJACENT_BAS = 12;
	int LIGNE_ATTAQUE_ADJACENT_DROIT = 15;
			
	int NB_IMAGE_ATTAQUE_ADJACENT = 6;
	
	int LIGNE_DEPLACEMENT_GAUCHE = 9;
	int LIGNE_DEPLACEMENT_DROIT = 11;
	int LIGNE_DEPLACEMENT_HAUT = 8;
	int LIGNE_DEPLACEMENT_BAS = 10;

	int NB_IMAGE_DEPLACEMENT = 9;
	
	int LIGNE_MORT= 20;
	int NB_IMAGE_MORT = 6;
	
}
