/********************************************************************
 * 							WarStone								*
 *  -------------------------------------------------------------	*
 * |	 Université Jean-Monnet   		 L3-Infos 		   2021	 |	*
 *  -------------------------------------------------------------	*
 * 	  BEGGARI ISLEM - CHATAIGNIER ANTOINE - BENGUEZZOU Idriss		*
 * 																	*
 * 												wargame.evenement	*
 * ******************************************************************/
package wargame.evenement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import carte.Carte;
import fenetrejeu.IFenetre;
import menu.MenuEvent;
import menu.MenuJeu;
import menu.loadgame.ISauvegarde;
import menu.loadgame.Sauvegarde;
import sprite.SpriteController;
import utile.Fleche;
import wargame.PanneauJeu;

public class ButtonEvent implements IFenetre, ISauvegarde {
	private static final long serialVersionUID = 1L;
	private PanneauJeu pj;
	public int tour;
	private SpriteController spriteController;

	public ButtonEvent(PanneauJeu pj) {
		this.tour = 0;
		this.pj = pj;
		
		this.spriteController = new SpriteController(pj);
		
		this.cameraEvent();
		this.partieEvent();
	}
	
	private void cameraEvent() {
		// Boutton restart recharge une carte cree aleatoirement
		cameraBas.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				pj.cam.deplacement(0, 1);;
			}  
		});  
		cameraHaut.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				pj.cam.deplacement(0, -1);
			}  
		});  
		cameraGauche.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				pj.cam.deplacement(-1, 0);
			}  
		});  
		cameraDroite.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				pj.cam.deplacement(1, 0);
			}  
		}); 
	}
	
	private void partieEvent() {
		// Boutton restart recharge une carte cree aleatoirement
		restart.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  	
				if(Carte.modeConf)
					pj.c = new Carte(5);
				else 
					pj.c = new Carte();
				pj.majMiniCarte();
				
				pj.flecheDirectionnelle = new Fleche(pj.cam);
								
				tour = 0;
				pj.herosSelectione = null;
				pj.elem = null;
			}  
		});  
		
		// Boutton Fin de Tour 
		finTour.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){						
				// On oublie le dernier heros selectionne
				pj.herosSelectione = null;
				
				if(pj.estFiniAction) {
					pj.c.joueTour(tour);
					tour = tour == 0 ? 1 : 0; 
					spriteController.lanceSpriteAction(pj.getGraphics());
				}			
			}  
		});  
		
		// Boutton de sauvegarde de la partie
		sauvegarde.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){    			
    			new Sauvegarde(pj.c);
    		}
    	});
		
		musicOn.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){    			
    			if(!musicOn.isSelected())
    				gameMusic.clip.stop();
    			else
    				gameMusic.clip.start();
    		}
    	});
		
		menu.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  	
				// On suprime tout le contenu
				panelPrincipal.removeAll();
				panelPrincipal.revalidate();
				
				header.removeAll();
				fleche.removeAll();
				// On supprime le panneau que l'on va remplacer
				frame.remove(panelPrincipal);
				
				gameMusic.clip.stop();
				menuMusic.clip.start();
				
				musicBoutton.removeAll();
				musicBoutton.revalidate();
				musicBoutton.setBouttonImage("unmute");
				MenuEvent.estMusicActif = true;
				// Creation du menu
				MenuJeu.initMenuJeu();
				frame.repaint();
			} 	 
		});  
	}
}
