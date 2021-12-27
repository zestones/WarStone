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

	/** troll. */
	// Chemin des sprites pour les monstres
	String troll = "./res/img/soldat/monstres/sprite/troll.png";

	/** orc. */
	String orc = "./res/img/soldat/monstres/sprite/orc.png";

	/** gobelin. */
	String gobelin = "./res/img/soldat/monstres/sprite/gobelin.png";

	/** frequence des image pour la lecture des sprites. */
	public SpriteEngine spriteEngine = new SpriteEngine(33);

	/** largeur image. */
	int LARGEUR_IMAGE = 64;

	/** hauteur image. */
	int HAUTEUR_IMAGE = 58;

	/** La ligne standby haut. */
	// Infos sur le sprite StandBy
	int LIGNE_STANDBY_HAUT = 0;

	/** La ligne standby gauche. */
	int LIGNE_STANDBY_GAUCHE = 1;

	/** La ligne standby bas. */
	int LIGNE_STANDBY_BAS = 2;

	/** La ligne standby droite. */
	int LIGNE_STANDBY_DROITE = 3;

	/** Le nb image standby. */
	int NB_IMAGE_STANDBY = 7;

	/** La ligne attack gauche. */
	int LIGNE_ATTACK_GAUCHE = 17;

	/** La ligne attack droite. */
	int LIGNE_ATTACK_DROITE = 19;

	/** La ligne attack haut. */
	int LIGNE_ATTACK_HAUT = 18;

	/** La ligne attack bas. */
	int LIGNE_ATTACK_BAS = 16;

	/** Le nb image attack. */
	int NB_IMAGE_ATTACK = 13;

	/** La ligne move gauche. */
	int LIGNE_DEPLACEMENT_GAUCHE = 9;

	/** La ligne move droite. */
	int LIGNE_DEPLACEMENT_DROITE = 11;

	/** La ligne move haut. */
	int LIGNE_DEPLACEMENT_HAUT = 8;

	/** La ligne move bas. */
	int LIGNE_DEPLACEMENT_BAS = 10;

	/** Le nb image move. */
	int NB_IMAGE_DEPLACEMENT = 9;
}
