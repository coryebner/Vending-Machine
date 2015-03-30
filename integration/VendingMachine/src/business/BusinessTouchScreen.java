package business;

import java.util.ArrayList;

public class BusinessTouchScreen {

	private ArrayList<BusinessTouchScreenListener> listeners = new ArrayList<BusinessTouchScreenListener>();
	
	
	
	
	public void register(BusinessTouchScreenListener listener) {
		listeners.add(listener);
	}
	
	private void notifyUserKeyboard(String userInput){
		for (BusinessTouchScreenListener listener : listeners){
			listener.UserSelectionInput(userInput);
		}
	}
	
	private void notifyUserSelection(String userInput){
		for (BusinessTouchScreenListener listener : listeners){
			listener.UserSelectionInput(userInput);
		}
	}
	
}
