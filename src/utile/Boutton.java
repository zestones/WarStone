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


public class Boutton extends JButton implements IMenu{
	private static final long serialVersionUID = 1L;

	public static int NOMBRE_BOUTTON = 0;
	private Color couleur = COULEUR_BOUTTON_MENU;
	private int largeur, hauteur;
		
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
	
    @Override
    protected void paintComponent(Graphics g){
        g.setColor(couleur);
        g.fillRoundRect(0, 0, getWidth(), getHeight(),  30, 30); 
    }
    
    public void hoverBoutton(Color c){
    	couleur = c;
    	this.repaint();
    }
    
    public void unsetHoverBoutton(Color c) {
    	couleur = c;
    	this.repaint();
    }
    
    public void setBouttonText(String txt) {
    	// On creer le label qui va contenir l'image
    	JLabel label = new JLabel();
    	
    	label.setHorizontalAlignment(JLabel.CENTER);
    	label.setVerticalAlignment(JLabel.CENTER);
    	label.setForeground(Color.white);
		label.setFont(new Font("Oxygen", Font.BOLD, 30));
		label.setText(txt);
		
		// enfin ajout du label au boutton
    	this.add(label);	
    }
    
    public void setBouttonImage(String image){
    	double coef =  0.89;
    	// chargement de l'image
    	ImageIcon icon = new ImageIcon("./res/img/background/menu/" + image + ".png");
    	Image monImage = icon.getImage();
    	
    	// Resize pour en fonction de la taille d'un boutton
    	Image img = monImage.getScaledInstance((int) (this.largeur * coef), (int) (this.hauteur * coef), Image.SCALE_SMOOTH);
    		
    	// On creer le label qui va contenir l'image
		JLabel label = new JLabel();
		// Ajout de l'image au label
		label.setIcon(new ImageIcon(img));
		// enfin ajout du label au boutton
		this.add(label);	
	}
    
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