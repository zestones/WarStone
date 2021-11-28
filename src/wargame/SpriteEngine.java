package wargame;

import javax.swing.Timer;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpriteEngine implements java.io.Serializable  {
	private static final long serialVersionUID = 1L;
	
	private Timer timer;
    private int framesPerSecond;
    private Long cycleStartTime;
    private TimerHandler timerHandler;

    private double cycleProgress;

    private transient List<ActionListener> listeners;

    public SpriteEngine(int fps) {
        framesPerSecond = fps;
        timerHandler = new TimerHandler();
        listeners = new ArrayList<>(25);
    }

    public int getFramesPerSecond() {
        return framesPerSecond;
    }

    public double getCycleProgress() {
        return cycleProgress;
    }

    protected void invaldiate() {
        cycleProgress = 0;
        cycleStartTime = null;
    }

    public void stop() {
        if (timer != null) {
            timer.stop();
        }
        invaldiate();
    }

    public void start() {
        stop();
        timer = new Timer(1000 / framesPerSecond, timerHandler);
        timer.start();
    }

    public void addActionListener(ActionListener actionListener) {
        listeners.add(actionListener);
    }

    public void removeActionListener(ActionListener actionListener) {
        listeners.remove(actionListener);
    }

    protected class TimerHandler implements ActionListener, java.io.Serializable {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			if (cycleStartTime == null) {
				cycleStartTime = System.currentTimeMillis();
			}
			long diff = (System.currentTimeMillis() - cycleStartTime) % 1000;
            cycleProgress = diff / 1000.0;
            ActionEvent ae = new ActionEvent(SpriteEngine.this, ActionEvent.ACTION_PERFORMED, e.getActionCommand());
            for (ActionListener listener : listeners) {
                listener.actionPerformed(ae);
            }
		}

    }
}