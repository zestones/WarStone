package sprite;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

public class AnimateurSprite implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private transient List<ActionListener> listeners;
	private final TimerHandler timerHandler;
	private final int imgParSec;
	private double progression;
	private Timer timer;
	private Long debut;
		
	public AnimateurSprite(int nb) {
		imgParSec = nb;
		timerHandler = new TimerHandler();
		listeners = new ArrayList<>(25);
	}

	public void addActionListener(ActionListener actionListener) {
		listeners.add(actionListener);
	}

	public double getProgression() {
		return progression;
	}

	public void start() {
		timer = new Timer(1000 / imgParSec, timerHandler);
		timer.start();
	}

	protected class TimerHandler implements ActionListener, java.io.Serializable {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			if (debut == null)
				debut = System.currentTimeMillis();
		
			final long diff = (System.currentTimeMillis() - debut) % 1000;
			progression = diff / 1000.0;
			
			final ActionEvent ae = new ActionEvent(AnimateurSprite.this, ActionEvent.ACTION_PERFORMED, e.getActionCommand());
			
			for(final ActionListener listener : listeners)
				listener.actionPerformed(ae);
		}
	}
	
}