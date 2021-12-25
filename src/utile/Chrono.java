/********************************************************************
 * 							WarStone								*
 *  -------------------------------------------------------------	*
 * |	 Université Jean-Monnet    L3-Infos 		    2021	 |	*
 *  -------------------------------------------------------------	*
 * 	  BEGGARI ISLEM - CHATAIGNIER ANTOINE - BENGUEZZOU Idriss		*
 * 																	*
 *	 													utile		*
 * ******************************************************************/
package utile;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

/**
 * La Class Chrono.
 */
public class Chrono {
    
    /** timer. */
    Timer timer;
    
    /** g */
    Graphics g;
   
    /**
     * Instantiates a new chrono.
     *
     * @param secondes int
     */
    public Chrono(int secondes) {
       	timer = new Timer();
       	timer.schedule(new RemindTask(), secondes * 1000); 
    }

    /**
     * La Class RemindTask.
     */
    class RemindTask extends TimerTask {
        
        /**
         * Run.
         */
        public void run() {
        	System.out.println("Notification du timer !");
        	
        	timer.cancel();
        }
    }
}
