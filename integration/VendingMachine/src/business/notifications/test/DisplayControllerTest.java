package business.notifications.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.Timer;


/*
 * quick manual test to check if the timer works correctly
 */

public class DisplayControllerTest {
    
	Timer eventTimer = null;
	
	public DisplayControllerTest() {
		eventTimer = new Timer(5000, listener);
	}
	
	ActionListener listener = new ActionListener(){
		  public void actionPerformed(ActionEvent event){
			 System.out.println("end");
			 eventTimer.stop();
		  }
	};
	
	public void SimulateTimer() {
		int time = 0;
	    try(Scanner sc = new Scanner(System.in)) {
		while(time >= 0) {
		    time = sc.nextInt();
			eventTimer.setInitialDelay(time);
			eventTimer.restart();
			System.out.println("start");
			eventTimer.start();
			}
	    }
	}

	public static void main(String[] args) {
		DisplayControllerTest dc = new DisplayControllerTest();
		dc.SimulateTimer();
	}


	
}
