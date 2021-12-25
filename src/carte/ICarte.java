/********************************************************************
 * 							WarStone								*
 *  -------------------------------------------------------------	*
 * |	 Université Jean-Monnet    L3-Infos 		    2021	 |	*
 *  -------------------------------------------------------------	*
 * 	  BEGGARI ISLEM - CHATAIGNIER ANTOINE - BENGUEZZOU Idriss		*
 * 																	*
 * 														carte		*
 * ******************************************************************/
package carte;

import java.awt.Graphics;
import java.util.List;

import element.Element;
import element.Heros;
import element.Soldat;
import utile.Position;
import wargame.PanneauJeu;

public interface ICarte {
	/**
	 * Trouve un element a une Position donner
	 * @param pos
	 * @return Element
	 */
	Element getElement(Position pos);

	/**
	 * Trouve aléatoirement une position vide sur la carte
	 * @return Position
	 */
	Position trouvePositionVide();
	
	/**
	 *  Trouve une position vide choisie
	 *	aléatoirement parmi les 8 positions adjacentes de pos
	 * @param pos
	 * @return Position
	 */
	Position trouvePositionVide(Position pos);
	/**
	 * Trouve aléatoirement un héros sur la carte
	 * @return Heros
	 */
	Heros trouveHeros(); 
	
	/**
	 *  Trouve un héros choisi aléatoirement
	 *  parmi les 8 positions adjacentes de pos
	 * @param herosTrouve
	 * @return Heros
	 */
	Heros trouveHeros(List<Heros>herosTrouve); 
	
	/**
	 * Deplace un soldat si possible et renvoie sont status (réussie ou non)
	 * @param pos
	 * @param soldat
	 * @return boolean
	 */
	boolean deplaceSoldat(Position pos, Soldat soldat);
	/**
	 * Supression d'un soldat lors de la perte de tout ses Points de vie
	 * @param perso
	 */
	void mort(Soldat perso);
	/**
	 * Gere les actions des heros (i.e Combat deplacement)
	 * et renvoie le status (réussie ou non)
	 * @param pj
	 * @param pos
	 * @param pos2
	 * @return boolean
	 */
	boolean actionHeros(PanneauJeu pj, Position pos, Position pos2);
	/**
	 * Fonction principale de la class carte les tours et les appels au fonction sont 
	 * géré par cette methode
	 * @param pj
	 */
	void jouerSoldats(PanneauJeu pj);
	
	/**
	 * Methode de dessin de la carte
	 * @param g
	 * @param cam
	 */
	void toutDessiner(Graphics g, Camera cam);
}