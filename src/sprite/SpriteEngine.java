/********************************************************************
 * 							WarStone							    *
 *  -------------------------------------------------------------	*
 * |	 Université Jean-Monnet    L3-Infos 		    2021	 |	*
 *  -------------------------------------------------------------	*
 * 	  BEGGARI ISLEM - CHATAIGNIER ANTOINE - BENGUEZZOU Idriss		*
 * 																	*
 * 													sprite	        *
 * ******************************************************************/

package sprite;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

/**
 * La Class SpriteEngine.
 */
public class SpriteEngine implements java.io.Serializable {

	/**
	 * La Class TimerHandler.
	 */
	protected class TimerHandler implements ActionListener, java.io.Serializable {

		/** Constante serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/**
		 * Action performed.
		 *
		 * @param e event
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (cycleStartTime == null)
				cycleStartTime = System.currentTimeMillis();
			final long diff = (System.currentTimeMillis() - cycleStartTime) % 1000;
			cycleProgress = diff / 1000.0;
			final ActionEvent ae = new ActionEvent(SpriteEngine.this, ActionEvent.ACTION_PERFORMED,
					e.getActionCommand());
			for (final ActionListener listener : listeners)
				listener.actionPerformed(ae);
		}
	}

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** timer. */
	private Timer timer;

	/**  frames per second. */
	private final int framesPerSecond;

	/**  cycle start time. */
	private Long cycleStartTime;

	/**  timer handler. */
	private final TimerHandler timerHandler;

	/**  cycle progress. */
	private double cycleProgress;

	/**  listeners. */
	private transient List<ActionListener> listeners;

	/**
	 * Instantiates a new sprite engine.
	 *
	 * @param fps  int
	 */
	public SpriteEngine(int fps) {
		framesPerSecond = fps;
		timerHandler = new TimerHandler();
		listeners = new ArrayList<>(25);
	}

	/**
	 * action listener.
	 *
	 * @param actionListener  action listener
	 */
	public void addActionListener(ActionListener actionListener) {
		listeners.add(actionListener);
	}

	/**
	 * Gets cycle progress.
	 *
	 * @return  cycle progress
	 */
	public double getCycleProgress() {
		return cycleProgress;
	}

	/**
	 * Gets frames par seconde.
	 *
	 * @return  frames per second
	 */
	public int getFramesPerSecond() {
		return framesPerSecond;
	}

	/**
	 * Invaldiate.
	 */
	protected void invaldiate() {
		cycleProgress = 0;
		cycleStartTime = null;
	}

	/**
	 * Removes  action listener.
	 *
	 * @param actionListener  action listener
	 */
	public void removeActionListener(ActionListener actionListener) {
		listeners.remove(actionListener);
	}

	/**
	 * Start.
	 */
	public void start() {
		stop();
		timer = new Timer(1000 / framesPerSecond, timerHandler);
		timer.start();
	}

	/**
	 * Stop.
	 */
	public void stop() {
		if (timer != null)
			timer.stop();
		invaldiate();
	}
}