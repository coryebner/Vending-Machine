package hardware.test;

import hardware.PushButton;
import hardware.test.stub.SelectionButtonListenerStub;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SelectionButtonTest {
    private PushButton button;
    private SelectionButtonListenerStub listener;

    @Before
    public void setup() {
	button = new PushButton();
	listener = new SelectionButtonListenerStub();
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
