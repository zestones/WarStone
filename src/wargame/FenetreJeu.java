package wargame;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;  

public class FenetreJeu extends JFrame implements IConfig{
	private static final long serialVersionUID = 1L;
    private final JSplitPane splitPane;  
    private final JPanel bottomPanel;    
    private final JPanel inputPanel;      
    private final JLabel textField;   
    private final JButton button;       

    public FenetreJeu(){

    	splitPane = new JSplitPane();
    	bottomPanel = new JPanel();      
        
        inputPanel = new JPanel();
        textField = new JLabel();    
        button = new JButton("Fin du Tour");    
        
        button.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				System.out.println("Hello tu a finit ton tour !");
			}  
		});  
        
        inputPanel.setPreferredSize(new Dimension(MENUBAR_LARGEUR, MENUBAR_HAUTEUR));
        setPreferredSize(new Dimension(FEN_LARGEUR, FEN_HAUTEUR));     
        getContentPane().setLayout(new GridLayout());  
        getContentPane().add(splitPane);               
        
        splitPane.setBackground(COULEUR_INCONNU);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);  
        splitPane.setDividerLocation(FEN_HAUTEUR - MENUBAR_HAUTEUR - MENUBAR_HAUTEUR/2);                    
        splitPane.setTopComponent(new PanneauJeu());                  
        splitPane.setBottomComponent(bottomPanel);            

        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        bottomPanel.add(inputPanel);            

        inputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));     
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));   

        inputPanel.add(textField);       
        inputPanel.add(button);      

        pack();   
        setVisible(true);
       
        
    }

    public static void main(String args[]){
    	new FenetreJeu();
    }
}