package utile.style;

import javax.swing.JCheckBox;
import javax.swing.border.EmptyBorder;

import menu.IMenu;

/**
 * Class CheckBox.
 */
public class CheckBox implements IMenu {
	
	/**
	 * Sets check box style.
	 *
	 * @param txt 
	 * @return CheckBox
	 */
	public static JCheckBox setCheckBoxStyle(String txt) {
		JCheckBox checkBox = new JCheckBox(txt); 
		
		checkBox.setForeground(COULEUR_BUTTON_BACKGROUND);
    	checkBox.setBackground(COULEUR_BUTTON_FOREGROUND);

    	checkBox.setFont(BUTTON_FONT);
    	
    	checkBox.setBorder(new EmptyBorder(5, 15, 5, 15));
    	
    	return checkBox;
	}
	
	/**
	 * Sets checked style.
	 *
	 * @param checkBox 
	 */
	public static void setCheckedStyle(JCheckBox checkBox) {
		checkBox.setForeground(COULEUR_BUTTON_FOREGROUND);
    	checkBox.setBackground(COULEUR_BUTTON_BACKGROUND);
	}
	
	/**
	 * Unchecked style.
	 *
	 * @param checkBox
	 */
	public static void unCheckedStyle(JCheckBox checkBox) {
		checkBox.setForeground(COULEUR_BUTTON_BACKGROUND);
    	checkBox.setBackground(COULEUR_BUTTON_FOREGROUND);
	}
	
}
