package business.selection_delivery.tests;

import hardware.test.stub.AbstractStub;
import hardware.ui.PushButton;
import hardware.ui.PushButtonListener;

public class PushButtonListenerStub extends AbstractStub implements
PushButtonListener{

	@Override
	public void pressed(PushButton button) {
		recordCallTo("pressed");
		
	}

	
	
}
