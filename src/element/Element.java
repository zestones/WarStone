package element;
import java.awt.Graphics;
import java.awt.Image;

import element.ISoldat.TypesH;
import utile.Position;
import wargame.IConfig;

public abstract class Element implements IConfig {	
	private static final long serialVersionUID = 1L;
	
	public abstract void seDessinerMinia(Graphics g);
	public abstract void seDessiner(Graphics g);
	public abstract Position getPosition();
	public abstract String toString();
	public abstract Image getImage();
	public abstract String getType();
}
