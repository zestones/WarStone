package utile;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import menu.IMenu;


public class Boutton extends JButton implements IMenu{
	private static final long serialVersionUID = 1L;

	public static int NOMBRE_BOUTTON = 0;
	private Color couleur = COULEUR_BOUTTON;
	
	
	public Boutton(int x, int y, int largeur, int hauteur){
    	
		this.setSize(largeur, hauteur);
    	y = BOUTTON_POSITION_Y + (HAUTEUR_BOUTTON + HAUTEUR_BOUTTON/4 ) * NOMBRE_BOUTTON;

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
    
    public void hoverBoutton(){
    	couleur = COULEUR_BOUTTON_HOVER;
    	this.repaint();
    }
    
    public void unsetHoverBoutton() {
    	couleur = COULEUR_BOUTTON;
    	this.repaint();
    }
    
    public void setBouttonText(String txt) {
    	// On creer le label qui va contenir l'image
    	JLabel label = new JLabel();
    	
    	label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Pushster", Font.BOLD, 20));
		label.setText(txt);
		
		// enfin ajout du label au boutton
    	this.add(label);	
    }
    
    public void setBouttonImage(String image){
    	double coef =  0.69;
    	// chargement de l'image
    	ImageIcon icon = new ImageIcon("./res/img/background/menu/" + image + ".png");
    	Image monImage = icon.getImage();
    	// Resize pour en fonction de la taille d'un boutton
    	Image img = monImage.getScaledInstance((int) (monImage.getWidth(this) * coef), (int) (monImage.getHeight(this) * coef), Image.SCALE_SMOOTH);
    		
    	// On creer le label qui va contenir l'image
		JLabel label = new JLabel();
		// Ajout de l'image au label
		label.setIcon(new ImageIcon(img));
		// enfin ajout du label au boutton
		this.add(label);	
	}
}