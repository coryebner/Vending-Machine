package hardware.test;

import hardware.test.stub.DisplayListenerStub;
import hardware.ui.Display;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DisplayTest {
    private Display display;
    private DisplayListenerStub listener;

    @Before
    public void setup() {
	display = new Display();
	listener = new DisplayListenerStub();
	display.register(listener);

	listener.init();
    }

    @After
    public void teardown() {
	display.deregisterAll();
	display = null;
	listener = null;
    }

    @Test
    public void testSetMessage() {
	listener.expect("messageChange");

	display.display("message");
	listener.assertProtocol();
    }

    @Test
    public void testChangeMessage() {
	listener.expect("messageChange");
	display.loadWithoutEvents("message");

	display.display("new message");
	listener.assertProtocol();
    }
}
