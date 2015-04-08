package hardware.test.stub;

import hardware.ui.Display;
import hardware.ui.DisplayListener;

public class DisplayListenerStub extends AbstractStub implements
        DisplayListener {
    @Override
    public void messageChange(Display display, String oldMsg,
	    String newMsg) {
	recordCallTo("messageChange");
    }
}
