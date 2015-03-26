package hardware.test.stub;

import hardware.PushButton;
import hardware.PushButtonListener;

public class SelectionButtonListenerStub extends AbstractStub implements
        PushButtonListener {
    @Override
    public void pressed(PushButton button) {
	recordCallTo("pressed");
    }
}
