package sprite;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

/**
 * Class AnimateurSprite.
 */
public class AnimateurSprite implements java.io.Serializable {
	
	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private transient List<ActionListener> listeners;
	private final TimerHandler timerHandler;
	private final int imgParSec;
	private double progression;
	private Timer timer;
	private Long debut;
		
	/**
	 * Instancie un nouvelle animateur sprite.
	 *
	 * @param nb image par seconde
	 */
	public AnimateurSprite(int nb) {
		imgParSec = nb;
		timerHandler = new TimerHandler();
		listeners = new ArrayList<>(25);
	}

	/**
	 * add Action listeners a la liste de listeners.
	 *
	 * @param actionListener
	 */
	public void addActionListener(ActionListener actionListener) {
		listeners.add(actionListener);
	}

	/**
	 * Gets progression.
	 *
	 * @return progression
	 */
	public double getProgression() {
		return progression;
	}

	/**
	 * Start.
	 */
	public void start() {
		timer = new Timer(1000 / imgParSec, timerHandler);
		timer.start();
	}

	/**
	 * Class TimerHandler.
	 */
	protected class TimerHandler implements ActionListener, java.io.Serializable {
		/** Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/**
		 * Action performed.
		 *
		 * @param e
		 */
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