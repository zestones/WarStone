package wargame;

import java.awt.Graphics;

public abstract class Element implements IConfig {	
	private static final long serialVersionUID = 1L;
	public abstract void seDessiner(Graphics g);
	public abstract Position getPosition();
}
