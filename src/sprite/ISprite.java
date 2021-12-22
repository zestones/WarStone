package sprite;

public interface ISprite {
	
	// Chemin des images pour les Heros
	String humain = "./res/img/soldat/heros/sprite/humain.png";
	String nain = "./res/img/soldat/heros/sprite/nain.png";
	String elf = "./res/img/soldat/heros/sprite/elf.png";
	String hobbit = "./res/img/soldat/heros/sprite/hobbit.png";
	
	// Chemin des images pour les monstres
	String troll = "./res/img/soldat/monstres/sprite/troll.png";
	String orc = "./res/img/soldat/monstres/sprite/orc.png";
	String gobelin = "./res/img/soldat/monstres/sprite/gobelin.png";
	
	// Frequence des images
	public SpriteEngine spriteEngine = new SpriteEngine(33);
	
	// Taille d'une image d'un sprite
	int LARGEUR_IMAGE = 64;
	int HAUTEUR_IMAGE = 58;
	
	// Infos sur le sprite StandBy
	int LIGNE_STANDBY_HAUT = 0;
	int LIGNE_STANDBY_GAUCHE = 1;
	int LIGNE_STANDBY_BAS = 2;
	int LIGNE_STANDBY_DROITE = 3;
	
	int NB_IMAGE_STANDBY = 7;
	
	// Infos sur le sprite Attaque 
	int LIGNE_ATTACK_GAUCHE = 17;
	int LIGNE_ATTACK_DROITE = 19;
	int LIGNE_ATTACK_HAUT = 18;
	int LIGNE_ATTACK_BAS = 16;
	
	int NB_IMAGE_ATTACK = 13;

	
	// Infos sur le sprite deplacement
	int LIGNE_MOVE_GAUCHE = 9;
	int LIGNE_MOVE_DROITE = 19;
	int LIGNE_MOVE_HAUT = 18;
	int LIGNE_MOVE_BAS = 16;
	
	int NB_IMAGE_MOVE = 9;
}
