package fenetrejeu.finjeu;

import java.awt.FlowLayout;

import javax.swing.JPanel;

/**
 * Interface IFinJeu.
 */
public interface IFinJeu {
	
	/** Chemin youLooseImg. */
	String youLooseImg = "./res/img/background/menu/youLose.jpg";
	
	/** Chemin youWinImg. */
	String youWinImg = "./res/img/background/menu/youWin.jpg";
	
	/** Panel de la page fin jeu. */
	JPanel panelFinJeu = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));	
}
