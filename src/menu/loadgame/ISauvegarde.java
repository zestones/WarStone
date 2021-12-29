package menu.loadgame;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import utile.Boutton;

public interface ISauvegarde {
	/** Liste de boutton des parties sauvegarder */
	List<Boutton> listeBoutton = new ArrayList<>();
	/**Liste des chemins des parties sauvegarder */
	List<String> listeSauvegarde = new ArrayList<>();
	/**le chemin des sauvegarde*/
	String chemin = "./res/sauvegarde/";
	/**NB MAX de partie */
	int MAX_SAUVEGARDE = 5;
	
	/** Panel principal pour la page loadgame */
	JPanel panelLoadGame = new JPanel();

}
