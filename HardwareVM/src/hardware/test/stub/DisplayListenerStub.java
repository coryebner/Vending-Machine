package hardware.test.stub;

import hardware.Display;
import hardware.DisplayListener;

public class DisplayListenerStub extends AbstractStub implements
        DisplayListener {
    @Override
    public void messageChange(Display display, String oldMsg,
	    String newMsg) {
	recordCallTo("messageChange");
    }
}
