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

import carte.Camera;
import carte.Carte;
import fenetrejeu.IFenetre;
import menu.menupage.ISauvegarde;
import menu.menupage.Sauvegarde;
import sprite.SpriteController;
import utile.Fleche;
import wargame.PanneauJeu;

public class ButtonEvent implements IFenetre, ISauvegarde {
	private static final long serialVersionUID = 1L;
	private Camera cam;
	private PanneauJeu pj;
	public int tour;
	private SpriteController spriteController;

	public ButtonEvent(PanneauJeu pj) {
		this.tour = 0;
		this.cam = pj.cam;
		this.pj = pj;
		
		this.spriteController = new SpriteController(pj);
		
		this.cameraEvent();
		this.partieEvent();
	}
	
	private void cameraEvent() {
		// Boutton restart recharge une carte cree aleatoirement
		cameraBas.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				cam.deplacement(0, 1);;
			}  
		});  
		cameraHaut.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				cam.deplacement(0, -1);
			}  
		});  
		cameraGauche.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				cam.deplacement(-1, 0);
			}  
		});  
		cameraDroite.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				cam.deplacement(1, 0);
			}  
		}); 
	}
	
	private void partieEvent() {
		// Boutton restart recharge une carte cree aleatoirement
		restart.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  	
				pj.c = new Carte();
				pj.majMiniCarte();
				
				pj.flecheDirectionnelle = new Fleche(cam);
								
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
		
		// Bouton de recuperation de sauvegarde
		reprendre.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				pj.c = Sauvegarde.recupSauvegarde(listeBoutton.size() - 1);
				
				pj.c.nombreSoldatVivant(pj);
				
				// Mise a jour de la miniCarte
				pj.majMiniCarte();
				pj.flecheDirectionnelle = new Fleche(cam);
				
				pj.repaint();
			}    		
		});
	}
}
