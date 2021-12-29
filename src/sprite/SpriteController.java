/********************************************************************
 * 							WarStone								*
 *  -------------------------------------------------------------	*
 * |	 Université Jean-Monnet    L3-Infos 		    2021	 |	*
 *  -------------------------------------------------------------	*
 * 	  BEGGARI ISLEM - CHATAIGNIER ANTOINE - BENGUEZZOU Idriss		*
 * 																	*
 * 														sprite		*
 * ******************************************************************/
package sprite;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import carte.Camera;
import element.Element;
import element.Soldat;
import utile.Position;
import wargame.IConfig;
import wargame.PanneauJeu;

public class SpriteController implements IConfig{

	private static final long serialVersionUID = 1L;
	private List<Position> listePositionDeplacement;
	private List<Soldat> listeRepos;
	private PanneauJeu pj;
	
	public SpriteController(PanneauJeu pj) {
		this.listeRepos = new ArrayList<>();
		this.listePositionDeplacement = new ArrayList<>();
		this.pj = pj;
	}
	
	public void lanceSpriteAction(Graphics g) {		
		 pj.estFiniAction = false;
					 
		 lanceSpriteAttaque(g, pj.c.listeActionAttaque, pj.cam);
		 lanceSpriteMort(g, pj.c.listeActionMort, pj.cam);
		 lanceSpriteDeplacement(g, pj.c.listeActionDeplacement, pj.cam);
		 
		 int tmps;
		 if(pj.c.listeActionAttaque.isEmpty() && pj.c.listeActionDeplacement.isEmpty()) //&& pj.c.listeActionMort.isEmpty()
			 tmps = 1;
		 else 
			 tmps = 5;
		 
		 new Chrono(tmps);
	}
	
	private void lanceSpriteAttaque(Graphics g, List<Soldat> listeActionAttaque, Camera cam) {
		int i = 0;
		 if(pj.c.listeActionAttaque.isEmpty()) return;
		
		 listeRepos.addAll(listeActionAttaque);	
		 
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
	
	private void lanceSpriteMort(Graphics g, List<Soldat> listeActionMort, Camera cam) {
		for(Soldat meurt : listeActionMort) {
			meurt.changeSprite(meurt.getPosition(), cam);
			pj.c.mort(meurt);
		}
			
	}
	
	private void reposSoldatAttaque(Graphics g, List<Soldat> listeRepos) {
		if(listeRepos.isEmpty()) return;
		int i = 0;

		while(i < listeRepos.size() - 1) {
			if(i%2 != 0) {
				i++;
				continue;
			}
			listeRepos.get(i).combat = false; 
			listeRepos.get(i).deplacement = false;
			if(listeRepos.get(i).getPoints() <= 0) {
				i++;
				continue;
			}
			listeRepos.get(i).changeSprite(listeRepos.get(i + 1).getPosition(), pj.cam);
			i++;
		}
		listeRepos.clear();
	}
	
	private void reposSoldatDeplacement(Graphics g, List<Position> listePosition) {
		if(listePosition.isEmpty()) return;
		int i = 0;
		while(i < listePosition.size() - 1) {
			if(i%2 != 0) {
				i++;
				continue;
			}
			Element e = pj.c.getElement(listePosition.get(i + 1));
			if(e instanceof Soldat) {
				Soldat s = (Soldat) e;
				s.deplacement = false; 
				s.changeSprite(listePosition.get(i), pj.cam);
			}
			i++;
		}
		listePosition.clear();
	}
	
	/* Class Timer */
	class Chrono {
		Timer timer;
		public Chrono(int seconds) {
			timer = new Timer();
			timer.schedule(new RemindTask(), seconds * 1000); 
		}

		class RemindTask extends TimerTask {
			public void run() {
				// On recharge les sprites standBy
				reposSoldatAttaque(pj.getGraphics(), listeRepos);
				reposSoldatDeplacement(pj.getGraphics(), listePositionDeplacement);
				
				// On supprime toute les actions realise au tour precedent
				pj.c.removeAllAction();
				
				pj.estFiniAction = true;
				pj.c.jouerSoldats(pj, pj.buttonEvent.tour);
				timer.cancel();
			}
	    }
	}
}
