/********************************************************************
 * 							WarStone								*
 *  -------------------------------------------------------------	*
 * |	 Universit� Jean-Monnet   		 L3-Infos 		   2021	 |	*
 *  -------------------------------------------------------------	*
 * 	  BEGGARI ISLEM - CHATAIGNIER ANTOINE - BENGUEZZOU Idriss		*
 * 																	*
 * 												wargame.evenement	*
 * ******************************************************************/
package wargame.evenement;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import carte.Carte;
import fenetrejeu.FenetreJeu;
import fenetrejeu.IFenetre;
import infosgame.InfosElement;
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
		
		this.bouttonHover();
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
    			if(!Carte.modeConf)
    				new Sauvegarde(pj.c);
    			else {
    				pj.c.genereSoldats();
    				new Sauvegarde(pj.c);
    				menu.doClick();
    			}
    		}
    	});
		
		play.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){    	
    				pj.c.genereSoldats();
    				// On suprime tout le contenu
    				panelPrincipal.removeAll();
    				panelPrincipal.revalidate();
    				
    				// Suprime les listes des obstacle
    				InfosElement.removeObstacleList();
    				// On vide le panel
    				infosElementBody.removeAll();
    				infosElementBody.revalidate();
    				
    				// On supprime le header et les fleches
    				header.removeAll();
    				fleche.removeAll();
    				// On supprime le panneau que l'on va remplacer
    				frame.remove(panelPrincipal);
    				
    				// On supprime le boutton fin de tour pour qu'il n'aparaisse pas si une config est lancer
    				menuBar.remove(finTour);	
    				menuBar.remove(play);	
    				// On arrete la music de conf et lance music jeux
    				gameMusic.clip.start();
    				
    				new FenetreJeu(pj.c);
    		
    				frame.repaint();
    		}
    	});
		
		musicOn.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){    			
    			if(!Carte.modeConf) {
    				if(!musicOn.isSelected()) 
    					gameMusic.clip.stop();
    				else 
    					gameMusic.clip.start();
    			}
    			else {
    				if(!musicOn.isSelected()) 
    					configMusic.clip.stop();
    				else 
    					configMusic.clip.start();
    			}
    		}
    	});
		
		menu.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  	
				// On suprime tout le contenu
				panelPrincipal.removeAll();
				panelPrincipal.revalidate();
				
				// Suprime les listes des obstacle
				InfosElement.removeObstacleList();
				// On vide le panel
				infosElementBody.removeAll();
				infosElementBody.revalidate();
				
				// On supprime le header et les fleches
				header.removeAll();
				fleche.removeAll();
				// On supprime le panneau que l'on va remplacer
				frame.remove(panelPrincipal);
				
				// On supprime le boutton fin de tour pour qu'il n'aparaisse pas si une config est lancer
				menuBar.remove(finTour);	
				menuBar.remove(play);
				// On lance la music du menu	
				menuMusic.clip.start();
				// On enleve le boutton de music
				musicBoutton.removeAll();
				musicBoutton.revalidate();
				// et on le remplace par un nouveau
				musicBoutton.setBouttonImage("unmute");
				MenuEvent.estMusicActif = true;
				
				// Creation du menu
				MenuJeu.initMenuJeu();
				frame.repaint();
			} 	 
		});  
	}	
	
	private void bouttonHover() {
		play.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			play.setForeground(Color.WHITE);
    			play.setBackground(Color.BLACK);
    		}	
		});
		sauvegarde.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			sauvegarde.setForeground(Color.WHITE);
    			sauvegarde.setBackground(Color.BLACK);
    		}	
		});
		restart.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			restart.setForeground(Color.WHITE);
    			restart.setBackground(Color.BLACK);
    		}	
		});
		finTour.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			finTour.setForeground(Color.WHITE);
    			finTour.setBackground(Color.BLACK);
    		}	
		});
		menu.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			menu.setForeground(Color.WHITE);
    			menu.setBackground(Color.BLACK);
    		}	
		});
		
		header.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			menu.setForeground(Color.BLACK);
    			menu.setBackground(Color.WHITE);
    			
    			finTour.setForeground(Color.BLACK);
    			finTour.setBackground(Color.WHITE);
    			
    			restart.setForeground(Color.BLACK);
    			restart.setBackground(Color.WHITE);
    			
    			sauvegarde.setForeground(Color.BLACK);
    			sauvegarde.setBackground(Color.WHITE);
    			
    			play.setForeground(Color.BLACK);
    			play.setBackground(Color.WHITE);
    		}	
		});
	}
	
	
}
