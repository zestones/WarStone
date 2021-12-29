package menu.menupage;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import utile.Boutton;

public interface ISauvegarde {
	List<Boutton> listeBoutton = new ArrayList<>();
	
	String chemin = "./res/sauvegarde/";
	int MAX_SAUVEGARDE = 5;
	List<String> listeSauvegarde = new ArrayList<>();
	
	JPanel panelLoadGame = new JPanel();

}
