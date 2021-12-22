package utile;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

public class Chrono {
    Timer timer;
    Graphics g;
   
    public Chrono(int secondes) {
       	timer = new Timer();
       	timer.schedule(new RemindTask(), secondes * 1000); 
    }

    class RemindTask extends TimerTask {
        public void run() {
        	System.out.println("Notification du timer !");
        	
        	timer.cancel();
        }
    }
}
