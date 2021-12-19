package element;

import java.awt.Graphics;

import utile.Position;
import wargame.IConfig;

public abstract class Element implements IConfig {	
	private static final long serialVersionUID = 1L;

	public abstract void seDessiner(Graphics g);
	public abstract Position getPosition();
	
}
