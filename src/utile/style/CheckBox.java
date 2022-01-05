package utile.style;

import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.border.EmptyBorder;

import menu.IMenu;

public class CheckBox implements IMenu {
	
	public static JCheckBox setCheckBoxStyle(String txt) {
		JCheckBox checkBox = new JCheckBox(txt); 
		
		checkBox.setForeground(Color.BLACK);
    	checkBox.setBackground(Color.WHITE);

    	checkBox.setFont(BUTTON_FONT);

    	checkBox.setBorder(new EmptyBorder(5, 15, 5, 15));
    	
    	return checkBox;
	}
}
