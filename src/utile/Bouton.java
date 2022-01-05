package utile;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import menu.IMenu;


/**
 * Class Boutton.
 * 
 * Cree des bouttons
 * 
 */
public class Bouton extends JButton implements IMenu {
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** le nombre boutton cree. */
	public static int NOMBRE_BOUTON = 0;
	
	/** la couleur. */
	private Color couleur = COULEUR_BOUTON_MENU;
	
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
	public Bouton(int x, int y, int largeur, int hauteur, boolean setPositionY) {
    	this.largeur = largeur;
    	this.hauteur = hauteur;
		
    	this.setSize(largeur, hauteur);
    	if(setPositionY) {
    		y = y + (hauteur + hauteur/4 ) * NOMBRE_BOUTON;
    		NOMBRE_BOUTON++;
    	}
    	this.setLocation(x, y);  	
    	this.setOpaque(false);
    	this.setBorderPainted(false);   	
	}
	
	/**
	 * Paintcomponent.
	 *
	 * @param g
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
    public void hoverBouton(Color c){
    	couleur = c;
    	this.repaint();
    }
    
    /**
     * Unset hover boutton.
     *
     * @param c la couleur
     */
    public void unsetHoverBouton(Color c) {
    	couleur = c;
    	this.repaint();
    }
    
    /**
     * Sets boutton text.
     *
     * @param txt
     */
    public void setBoutonText(String txt) {
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
     * @param image
     */
    public void setBoutonImage(String image){
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
     * @param text
     * @return button
     */
    public static JButton setButtonStyle(String text) {
    	JButton button = new JButton(text);

    	button.setForeground(Color.BLACK);
    	button.setBackground(Color.WHITE);
    	button.setFont(BUTTON_FONT);
    	
    	Border bordure = BorderFactory.createLineBorder(Color.GRAY, 2);;
    	Border margin = new EmptyBorder(5, 15, 5, 15);

    	Border compound = new CompoundBorder(bordure, margin);
    	
    	button.setBorder(compound);
    	    	
    	return button;
    }  
    
    public static void setHoverButton(JButton btn) {
    	btn.setForeground(Color.WHITE);
		btn.setBackground(Color.BLACK);
    }
    
    public static void unsetHoverButton(JButton btn) {
    	btn.setForeground(Color.BLACK);
		btn.setBackground(Color.WHITE);
    }
    
}