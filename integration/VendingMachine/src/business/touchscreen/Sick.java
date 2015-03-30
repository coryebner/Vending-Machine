package business.touchscreen;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Sick {
	Robot robot;
	public Sick() {
		try {
			robot = new Robot();
		} catch (Exception e) {

		}
	}
	
	void testInput(int ascii) throws InterruptedException {
		while (true) {
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_K);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Sick sick = new Sick();
		sick.testInput(0xFF);
	}
} 
