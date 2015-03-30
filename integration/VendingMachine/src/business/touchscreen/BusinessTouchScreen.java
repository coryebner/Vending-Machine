package business.touchscreen;

import java.awt.Robot;
import java.util.ArrayList;

public class BusinessTouchScreen {

	private ArrayList<BusinessTouchScreenListener> listeners = new ArrayList<BusinessTouchScreenListener>();
	Robot robot;
	
	
	public BusinessTouchScreen() {
		try {
			robot = new Robot();
		} catch (Exception e) {
			System.out.println("Could not create Robot");
		}
	}
	
	
	//This should be the listener on the hardware GUI, name might change depending on their interface
	public void keyPress(String character){
		
		simulateKeyPress(character);
		notifyUserKeyboard(character);
	}
	
	
	private void simulateKeyPress(String input){
		
	}
	
	
	
	public void register(BusinessTouchScreenListener listener) {
		listeners.add(listener);
	}
	
	private void notifyUserKeyboard(String userInput){
		for (BusinessTouchScreenListener listener : listeners){
			listener.UserKeyboardInput(userInput);
		}
	}
	
	
}
