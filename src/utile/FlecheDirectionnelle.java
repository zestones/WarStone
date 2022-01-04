package utile;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import carte.Camera;
import element.Soldat;
import wargame.IConfig;

/**
 * Class Fleche.
 * 
 * Creation de la fleche
 * 
 */
public class FlecheDirectionnelle implements IConfig {
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The cam. */
	private Camera cam;
	
	/**
	 * Instantiates a new fleche.
	 *
	 * @param cam the cam
	 */
	public FlecheDirectionnelle(Camera cam) {
		this.cam = cam;		
	}
	
	/**
	 * Dessine fleche.
	 *
	 * @param g the g
	 * @param x1 the x 1
	 * @param y1 the y 1
	 * @param x2 the x 2
	 * @param y2 the y 2
	 * @param d the d
	 * @param h the h
	 * @param clique the clic
	 */
	
	// https://stackoverflow.com/questions/2027613/how-to-draw-a-directed-arrow-line-in-java
	public void dessineFlecheDirectionnelle(Graphics g, int x1, int y1, int x2, int y2, int d, int h, Position clique) {
	    int dx = x2 - x1, dy = y2 - y1;
	    double D = Math.sqrt(dx*dx + dy*dy);
	    double xm = D - d, xn = xm, ym = h, yn = -h, x;
	    double sin = dy / D, cos = dx / D;
	    	    
	    x = xm*cos - ym*sin + x1;
	    ym = xm*sin + ym*cos + y1;
	    xm = x;

	    x = xn*cos - yn*sin + x1;
	    yn = xn*sin + yn*cos + y1;
	    xn = x;

	    int[] xpoints = {x2, (int) xm, (int) xn};
	    int[] ypoints = {y2, (int) ym, (int) yn};


	    // On recupre les positions des point X2, Y2
	    int Cx = x2 / TAILLE_CARREAU + cam.getDx(); 
	    int Cy = y2 / TAILLE_CARREAU + cam.getDy();
	    	
	    // On recupere la distance entre les deux point   
	    Position pos2 = new Position((x1/TAILLE_CARREAU + cam.getDx()), (y1/TAILLE_CARREAU + cam.getDy()));
	    Position pos1 = new Position(Cx, Cy);
	    
	    int distance = pos1.getDistance(pos2);
	    
	    // SetStroke utilisable uniquement avec Graphicd2D
	    Graphics2D g2 = (Graphics2D) g;
	    g2.setStroke(new BasicStroke(2));
	    
	    g.setColor(COULEUR_EAU);
	   
	    // On recalcule le deuxieme point de la ligne de la fleche pour qu'il soit centre
	    if(clique.getX() < Cx && clique.getY() == Cy) x2 -= d;
	    else if(clique.getX() > Cx && clique.getY() == Cy) x2 += d;
	    if(clique.getX() == Cx && clique.getY() > Cy) y2 += d;
	    else if(clique.getX() == Cx && clique.getY() < Cy) y2 -= d;
	    if(clique.getX() > Cx && clique.getY() < Cy) {
	    	x2 += d/(distance+1); y2 -= d/(distance+1);
	    }
	    else if(clique.getX() > Cx && clique.getY() > Cy) {
	    	x2 += d/(distance+1); y2 += d/(distance+1);
	    }
	    else if(clique.getX() < Cx && clique.getY() < Cy) {
	    	x2 -= d/(distance+1); y2 -= d/(distance+1);
	    }
	    else if(clique.getX() < Cx && clique.getY() > Cy) {
	    	x2 -= d/(distance+1); y2 += d/(distance+1);
	    }
	    
	    g.drawLine(x1, y1, x2, y2);
	   
	    g.fillPolygon(xpoints, ypoints, 3);
	}
	
	/**
	 * Dessine deplacement.
	 *
	 * @param herosSelectione le heros selectione
	 * @param dessineFleche 
	 * @param draggedCam la position
	 * @return true, if successful
	 */
	public boolean estFlecheDessinable(Soldat herosSelectione, boolean dessineFleche, Position draggedCam) {
		// Si aucun heros est selectione est que dessine Fleche est true alors on dessine
		if(herosSelectione == null && dessineFleche)
			return true;
		// Si on a selectione un heros on verifie que la position de draggedCam 
		// ne se situe pas dans la zone de deplacement du heros
		if(herosSelectione != null && dessineFleche)
			if(!herosSelectione.getPosition().estVoisine(draggedCam))
				return true;
		return false;			
	}	
	
	
	
}
