package utile;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import menu.IMenu;


/**
 * Class Boutton.
 * 
 * Cree des bouttons
 * 
 */
public class Boutton extends JButton implements IMenu{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** le nombre boutton cree. */
	public static int NOMBRE_BOUTTON = 0;
	
	/** la couleur. */
	private Color couleur = COULEUR_BOUTTON_MENU;
	
	/** les dimensions. */
	private int largeur, hauteur;
		
	/**
	 * instancie un nouveau boutton.
	 *
	 * @param x la pos x
	 * @param y la pos y
	 * @param largeur la largeur
	 * @param hauteur la hauteur
	 */
	public Boutton(int x, int y, int largeur, int hauteur) {
    	this.largeur = largeur;
    	this.hauteur = hauteur;
		
    	this.setSize(largeur, hauteur);
    	y = y + (hauteur + hauteur/4 ) * NOMBRE_BOUTTON;

    	this.setLocation(x, y);  	
    	this.setOpaque(false);
    	this.setBorderPainted(false);
    	
    	NOMBRE_BOUTTON++;
	}
	
	/**
	 * Instancie un nouveau boutton.
	 * Sans le placement en y
	 *
	 * @param x la pos x
	 * @param y la pos y
	 * @param largeur la largeur
	 * @param hauteur la hauteur
	 * @param boolean
	 */
	public Boutton(int x, int y, int largeur, int hauteur, boolean posY) {
    	this.largeur = largeur;
    	this.hauteur = hauteur;
		
    	this.setSize(largeur, hauteur);
    	
    	this.setLocation(x, y);  	
    	this.setOpaque(false);
    	this.setBorderPainted(false);
	}

	/**
	 * Paintcomponent.
	 *
	 * @param g the g
	 */
	protected void paintComponent(Graphics g){
        g.setColor(couleur);
        g.fillRoundRect(0, 0, getWidth(), getHeight(),  30, 30); 
    }
    
    /**
     * Hover boutton.
     *
     * @param c la couleur
     */
    public void hoverBoutton(Color c){
    	couleur = c;
    	this.repaint();
    }
    
    /**
     * Unset hover boutton.
     *
     * @param c la couleur
     */
    public void unsetHoverBoutton(Color c) {
    	couleur = c;
    	this.repaint();
    }
    
    /**
     * Sets boutton text.
     *
     * @param txt le text du boutton
     */
    public void setBouttonText(String txt) {
    	/** On creer le label qui va contenir l'image */
    	JLabel label = new JLabel();
    	
    	label.setHorizontalAlignment(JLabel.CENTER);
    	label.setVerticalAlignment(JLabel.CENTER);
    	label.setForeground(Color.white);
		label.setFont(new Font("Oxygen", Font.BOLD, 27));
		label.setText(txt);
		
		/** enfin ajout du label au boutton */
    	this.add(label);	
    }
    
    /**
     * Sets boutton image.
     *
     * @param image le noù de l'image
     */
    public void setBouttonImage(String image){
    	double coef =  0.89;
    	/** chargement de l'image */
    	ImageIcon icon = new ImageIcon("./res/img/background/menu/" + image + ".png");
    	Image monImage = icon.getImage();
    	
    	/** Resize pour en fonction de la taille d'un boutton */
    	Image img = monImage.getScaledInstance((int) (this.largeur * coef), (int) (this.hauteur * coef), Image.SCALE_SMOOTH);
    		
    	/** On creer le label qui va contenir l'image */
		JLabel label = new JLabel();
		/** Ajout de l'image au label */
		label.setIcon(new ImageIcon(img));
		/** enfin ajout du label au boutton */
		this.add(label);	
	}
    
    /**
     * Sets boutton style.
     *
     * @param text le text
     * @return button
     */
    public static JButton setBouttonStyle(String text) {
    	JButton boutton = new JButton(text);

    	boutton.setForeground(Color.BLACK);
    	boutton.setBackground(Color.WHITE);

    	Border line = new LineBorder(Color.BLACK);
    	Border margin = new EmptyBorder(5, 15, 5, 15);
    	Border compound = new CompoundBorder(line, margin);
    	
    	boutton.setBorder(compound);
    	
    	return boutton;
    }       
}