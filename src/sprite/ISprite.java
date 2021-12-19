package sprite;

public interface ISprite {
	
	// Chemin des images pour les Heros
	String humain = "./res/img/soldat/heros/humain.png";
	String nain = "./res/img/soldat/heros/nain.png";
	String elf = "./res/img/soldat/heros/elf.png";
	String hobbit = "./res/img/soldat/heros/hobbit.png";
	
	// Chemin des images pour les monstres
	String troll = "./res/img/soldat/monstres/troll.png";
	String orc = "./res/img/soldat/monstres/orc.png";
	String gobelin = "./res/img/soldat/monstres/gobelin.png";
	
	// Frequence des images
	public SpriteEngine spriteEngine = new SpriteEngine(33);
	
	int LARGEUR_IMAGE = 64;
	int HAUTEUR_IMAGE = 58;

	int LIGNE_STANDBY_HAUT = 0;
	int LIGNE_STANDBY_GAUCHE = 1;
	int LIGNE_STANDBY_BAS = 2;
	int LIGNE_STANDBY_DROITE = 3;
	
	int NB_IMAGE_STANDBY = 7;
	
	int LIGNE_ATTACK_GAUCHE = 17;
	int LIGNE_ATTACK_DROITE = 19;
	int LIGNE_ATTACK_HAUT = 18;
	int LIGNE_ATTACK_BAS = 16;
	
	int NB_IMAGE_ATTACK = 13;

}
