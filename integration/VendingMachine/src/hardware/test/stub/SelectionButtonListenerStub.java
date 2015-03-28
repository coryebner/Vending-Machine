package hardware.test.stub;

import hardware.ui.PushButton;
import hardware.ui.PushButtonListener;

public class SelectionButtonListenerStub extends AbstractStub implements
        PushButtonListener {
    @Override
    public void pressed(PushButton button) {
	recordCallTo("pressed");
    }
}
