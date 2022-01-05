package sprite;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import carte.Camera;
import carte.element.Element;
import carte.element.Soldat;
import fenetrejeu.finjeu.FinJeu;
import fenetrejeu.panneaujeu.IConfig;
import fenetrejeu.panneaujeu.PanneauJeu;
import utile.Position;
import utile.Position.POINT_CARDINAUX;

/**
 * Class GestionSprite.
 * 
 * Cette class s'occupe d'attribuer le sprite correspondant a l'action que le soldat realise
 * Et d'appeller les methode permettant la realisation des actions
 * 
 */
public class GestionSprite implements IConfig{

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** liste de position des soldat ayant realise un deplacement. */
	private List<Position> listePositionDeplacement;
	
	/** liste de soldat au repos. */
	private List<Soldat> listeRepos;
	private PanneauJeu pj;
	
	/**
	 * Instancie un nouveau "gestion sprite".
	 *
	 * @param pj
	 */
	public GestionSprite(PanneauJeu pj) {
		this.listeRepos = new ArrayList<>();
		this.listePositionDeplacement = new ArrayList<>();
		this.pj = pj;
	}
	
	/**
	 * Lance sprite action.
	 *
	 * Cree un chrono pour le temps d'animation
	 * Appelle les methodes permettant de changer les animations des soldats
	 *
	 * @param g
	 */
	public void lanceSpriteAction(Graphics g) {				
		/** On lance les animations donc la variable estFiniAction restera false tant que le chrono ne sera pas termine */
		pj.estFiniAction = false;
					 
		/** Lancement des Attaques */
		lanceSpriteAttaque(g, pj.c.listeActionAttaque, pj.cam);
		/** On lance les actions Mort juste apres les attaques */
		lanceSpriteMort(g, pj.c.listeActionMort, pj.cam);
		
		/** Si il n'y a plus de heros ou de monstres sur la carte on affiche un ecran fin de partie */
		if(pj.c.listeHeros.size() == 0 || pj.c.listeMonstres.size() == 0) {
			new FinJeu(pj.c.listeHeros.size(), pj.c.listeMonstres.size());
			return; 
		}
		/** Lancement des deplacements */
		lanceSpriteDeplacement(g, pj.c.listeActionDeplacement, pj.cam);
		
		int tmps;
		/** Calcule du temps pour realiser les actions */
		if(pj.c.listeActionAttaque.isEmpty() && pj.c.listeActionDeplacement.isEmpty()) //&& pj.c.listeActionMort.isEmpty()
			tmps = 1;
		else 
			tmps = (int) ((pj.c.listeActionAttaque.size() + pj.c.listeActionDeplacement.size()) * 0.2) + 4;
		 
		/** Lancement du chrono */
		new Chrono(tmps);
	}
	
	/**
	 * Lance sprite attaque.
	 *
	 * @param g 
	 * @param listeActionAttaque 
	 * @param cam 
	 */
	private void lanceSpriteAttaque(Graphics g, List<Soldat> listeActionAttaque, Camera cam) {
		int i = 0;
		 if(pj.c.listeActionAttaque.isEmpty()) return;
		 
		 /** Apres avoir attaque tout les soldats se repose */
		 listeRepos.addAll(listeActionAttaque);	
		 
		 /** On realise les attaques en parcourant la liste */
		 while(i < listeActionAttaque.size() - 1) {
			 if(i%2 != 0) {
				i++;
				continue;
			}
			if(listeActionAttaque.get(i).combat(listeActionAttaque.get(i + 1)))
				listeActionAttaque.get(i).changeSprite(listeActionAttaque.get(i + 1).getPosition(), cam);
			i++;
		 }	
	}
	
	/**
	 * Lance sprite deplacement.
	 *
	 * @param g 
	 * @param listeActionDeplacement 
	 * @param cam 
	 */
	private void lanceSpriteDeplacement(Graphics g, List<Soldat> listeActionDeplacement, Camera cam) {
		int i = 0;
		if(pj.c.listeActionDeplacement.isEmpty()) return;
			
		for(Soldat deplacer : listeActionDeplacement)
			listePositionDeplacement.add(new Position(deplacer.getPosition().getX(), deplacer.getPosition().getY()));
		
		while(i < listeActionDeplacement.size() - 1) {
			if(i % 2 != 0) {
				i++; 
				continue;
			}
			listeActionDeplacement.get(i).changeSprite(listeActionDeplacement.get(i + 1).getPosition(), cam);
			i++;
		 }
	}
	
	/**
	 * Lance sprite mort 
	 * 
	 * l'Animation n'a pas ete implemente
	 *
	 * @param g 
	 * @param listeActionMort 
	 * @param cam 
	 */
	private void lanceSpriteMort(Graphics g, List<Soldat> listeActionMort, Camera cam) {
		for(Soldat meurt : listeActionMort) {
			meurt.changeSprite(meurt.getPosition(), cam);
			pj.c.mort(meurt);
		}
			
	}
	
	/**
	 * Les soldats ayant effectue une attaque se repose
	 *
	 * @param g 
	 * @param listeRepos 
	 */
	private void reposSoldatAttaque(Graphics g, List<Soldat> listeRepos) {
		if(listeRepos.isEmpty()) return;
		int i = 0;

		while(i < listeRepos.size() - 1) {
			if(i%2 != 0) {
				i++;
				continue;
			}
			listeRepos.get(i).combat = false; 
			listeRepos.get(i).seDeplace = false;
			if(listeRepos.get(i).getPoints() <= 0) {
				i++;
				continue;
			}
			listeRepos.get(i).changeSprite(listeRepos.get(i + 1).getPosition(), pj.cam);
			i++;
		}
		listeRepos.clear();
	}
	
	/**
	 * Les soldat s'etant deplace se repose dans la direction de leur deplacement.
	 *
	 * @param g 
	 * @param listePosition
	 */
	private void reposSoldatDeplacement(Graphics g, List<Position> listePosition) {
		if(listePosition.isEmpty()) return;
		int i = 0;
		
		POINT_CARDINAUX direction;
		
		while(i < listePosition.size() - 1) {
			if(i%2 != 0) {
				i++;
				continue;
			}
			
			/** Direction du deplacement */
			direction = listePosition.get(i).getPositionCardinal(listePosition.get(i + 1));
			
			Element e = pj.c.getElement(listePosition.get(i + 1));
			
			if(e instanceof Soldat) {
				Soldat s = (Soldat) e;
				s.seDeplace = false; 
				/** Chaque soldat se repose dans la bonne direction */
				if(direction == POINT_CARDINAUX.NORD_OUEST || direction == POINT_CARDINAUX.OUEST || direction == POINT_CARDINAUX.SUD_OUEST)
					s.changeSprite(new Position(listePosition.get(i + 1).getX() + 1, listePosition.get(i + 1).getY()), pj.cam);
				else if(direction == POINT_CARDINAUX.NORD_EST || direction == POINT_CARDINAUX.EST || direction == POINT_CARDINAUX.SUD_EST)
					s.changeSprite(new Position(listePosition.get(i + 1).getX() - 1, listePosition.get(i + 1).getY()), pj.cam);
				else if(direction == POINT_CARDINAUX.NORD)
					s.changeSprite(new Position(listePosition.get(i + 1).getX(), listePosition.get(i + 1).getY() + 1), pj.cam);
				else if(direction == POINT_CARDINAUX.SUD)
					s.changeSprite(new Position(listePosition.get(i + 1).getX(), listePosition.get(i + 1).getY() - 1), pj.cam);
			}
			i++;
		}
		listePosition.clear();
	}
	
	/**
	 * Class Chrono.
	 */	
	class Chrono {
		Timer timer;
		
		/**
		 * Instancie un nouveau chrono.
		 *
		 * @param seconds 
		 */
		public Chrono(int seconds) {
			timer = new Timer();
			timer.schedule(new Notificateur(), seconds * 1000); 
		}

		/**
		 * Class Notificateur.
		 * 
		 * Realise les actions demande lorsque le temps du chrono est ecoule
		 * 
		 */
		class Notificateur extends TimerTask {
			public void run() {
				// On change les sprites d'action en sprite repos
				reposSoldatAttaque(pj.getGraphics(), listeRepos);
				reposSoldatDeplacement(pj.getGraphics(), listePositionDeplacement);
				
				// On supprime toute les actions realise au tour precedent
				pj.c.removeAllAction();
				
				pj.estFiniAction = true;
				pj.c.jouerSoldats(pj);
				timer.cancel();
			}
	    }
	}
}
