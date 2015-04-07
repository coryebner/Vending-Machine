package hardware.test.ui;

import hardware.test.stub.PushButtonListenerStub;
import hardware.ui.PushButton;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SelectionButtonTest {
    private PushButton button;
    private PushButtonListenerStub listener;

    @Before
    public void setup() {
	button = new PushButton();
	listener = new PushButtonListenerStub();
	button.register(listener);

	listener.init();
    }

    @After
    public void teardown() {
	button.deregisterAll();
	button = null;
	listener = null;
    }

    @Test
    public void testPress() {
	listener.expect("pressed");

	button.press();
	listener.assertProtocol();
    }
}
