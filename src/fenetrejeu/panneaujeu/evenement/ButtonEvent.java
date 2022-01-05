package fenetrejeu.panneaujeu.evenement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import carte.Carte;
import fenetrejeu.FenetreJeu;
import fenetrejeu.IFenetre;
import fenetrejeu.infosjeu.InfosElement;
import fenetrejeu.infosjeu.MiniCarte;
import fenetrejeu.panneaujeu.PanneauJeu;
import menu.MenuEvent;
import menu.MenuJeu;
import menu.loadgame.ISauvegarde;
import menu.loadgame.Sauvegarde;
import sprite.GestionSprite;
import utile.FlecheDirectionnelle;
import utile.style.Bouton;

public class ButtonEvent implements IFenetre, ISauvegarde {
	private static final long serialVersionUID = 1L;

	private GestionSprite spriteController;
	private PanneauJeu pj;
	public int tour;

	public ButtonEvent(PanneauJeu pj) {
		this.tour = 0;
		this.pj = pj;
		
		this.spriteController = new GestionSprite(pj);
		
		this.cameraEvent();
		this.jeuEvent();
		
		this.boutonHover();
	}
	
	private void cameraEvent() {
		// Boutton restart recharge une carte cree aleatoirement
		cameraBas.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				pj.cam.deplaceCamera(0, 1);;
			}  
		});  
		cameraHaut.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				pj.cam.deplaceCamera(0, -1);
			}  
		});  
		cameraGauche.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				pj.cam.deplaceCamera(-1, 0);
			}  
		});  
		cameraDroite.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				pj.cam.deplaceCamera(1, 0);
			}  
		}); 
	}
	
	private void jeuEvent() {
		// Boutton restart recharge une carte cree aleatoirement
		recommencer.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  	
				pj.c = new Carte();
				MiniCarte.majMiniCarte(pj);
				
				pj.flecheDirectionnelle = new FlecheDirectionnelle(pj.cam);
								
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
		
		jouer.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){    	
    			// le joueur 1 commence toujours en premier
    			tour = 0;
    			pj.c.genereSoldats();
    			// On suprime tout le contenu
    			panelPrincipal.removeAll();
    			panelPrincipal.revalidate();
    			// On arrete la music de config
    			configMusic.clip.stop();
    			// Suprime les listes des obstacle
    			InfosElement.removeElementList();
    			pj.elem = null;
    			
    			// On vide le panel
    			descriptifElementPanel.removeAll();
    			descriptifElementPanel.revalidate();
    			// On supprime le header et les fleches
    			headerPanel.removeAll();
    			flecheMiniCartePanel.removeAll();
    			// On supprime le panneau que l'on va remplacer
    			frame.remove(panelPrincipal);
    			
    			// On supprime le boutton fin de tour pour qu'il n'aparaisse pas si une config est lancer
    			menuBar.remove(finTour);	
    			menuBar.remove(jouer);	
    			menuBar.remove(heros);
    			menuBar.remove(obstacle);
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
		
		obstacle.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){    			
    			InfosElement.dessineObstacleDeposable();
    		}
    	});
		
		heros.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){    			
    			InfosElement.dessineHerosDeposable();
    		}
    	});
		
		
		menu.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  	
    			// le joueur 1 commence toujours en premier
    			tour = 0;
    			
    			// On change le style du bouton
    			Bouton.unsetHoverButton(menu);
    			
    			// On suprime tout le contenu
				panelPrincipal.removeAll();
				panelPrincipal.revalidate();
				
				// Suprime les listes des obstacle
				InfosElement.removeElementList();
				// On vide le panel
				descriptifElementPanel.removeAll();
				descriptifElementPanel.revalidate();
				
				pj.elem = null;
				
				// On supprime le header et les fleches
				headerPanel.removeAll();
				flecheMiniCartePanel.removeAll();
				// On supprime le panneau que l'on va remplacer
				frame.remove(panelPrincipal);
				
				// On supprime le boutton fin de tour pour qu'il n'aparaisse pas si une config est lancer
				menuBar.remove(finTour);	
				menuBar.remove(jouer);	
				menuBar.remove(heros);
				menuBar.remove(obstacle);
				// On lance la music du menu	
				menuMusic.clip.start();
				// On enleve le boutton de music
				musiqueBouton.removeAll();
				musiqueBouton.revalidate();
				// et on le remplace par un nouveau
				musiqueBouton.setBoutonImage("unmute");
				MenuEvent.estMusicActif = true;
				
				// Creation du menu
				MenuJeu.initMenuJeu();
				
				frame.repaint();
			} 	 
		});  
	}	
	
	private void boutonHover() {
		jouer.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			Bouton.setHoverButton(jouer);
    		}	
		});
		
		obstacle.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			Bouton.setHoverButton(obstacle);
    		}	
		});
		
		heros.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			Bouton.setHoverButton(heros);
    		}	
		});
				
		sauvegarde.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			Bouton.setHoverButton(sauvegarde);
    		}	
		});
		recommencer.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			Bouton.setHoverButton(recommencer);
    		}	
		});
		finTour.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			Bouton.setHoverButton(finTour);
    		}	
		});
		menu.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			Bouton.setHoverButton(menu);
    		}	
		});
		
		headerPanel.addMouseMotionListener(new MouseAdapter() {
    		public void mouseMoved(MouseEvent e) {
    			unsetAllHoverButton();
    		}	
		});
	}
	
	public void unsetAllHoverButton() {
		Bouton.unsetHoverButton(menu);
		Bouton.unsetHoverButton(finTour);
		Bouton.unsetHoverButton(recommencer);
		Bouton.unsetHoverButton(sauvegarde);
		Bouton.unsetHoverButton(jouer);
		Bouton.unsetHoverButton(heros);
		Bouton.unsetHoverButton(obstacle);	
	}
	
	
}
