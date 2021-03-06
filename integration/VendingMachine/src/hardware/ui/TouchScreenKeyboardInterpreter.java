package hardware.ui;

import hardware.AbstractHardware;

/**
 * A class that allows the Touch Screen to interact with software indirectly.
 */
public class TouchScreenKeyboardInterpreter extends AbstractHardware<TouchScreenKeyboardInterpreterListener>{

	public void enterString(String inputString){
		if(inputString != null)
			notifyStringEntered(inputString);
	}
	
	private void notifyStringEntered(String str) {
		Class<?>[] parameterTypes = new Class<?>[] {TouchScreenKeyboardInterpreter.class, String.class};
		Object[] args = new Object[] {this, str};
		notifyListeners(TouchScreenKeyboardInterpreterListener.class, "commandEntered", parameterTypes, args);
    }

}
