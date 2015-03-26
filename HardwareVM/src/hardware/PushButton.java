package hardware;

/**
 * Represents a simple push button on the vending machine. It ignores the
 * enabled/disabled state.
 */
public class PushButton extends
        AbstractHardware<PushButtonListener> {
    /**
     * Simulates the pressing of the button. Notifies its listeners of a
     * "pressed" event.
     */
    public void press() {
	notifyPressed();
    }

    private void notifyPressed() {
	Class<?>[] parameterTypes =
	        new Class<?>[] { PushButton.class };
	Object[] args = new Object[] { this };
	notifyListeners(PushButtonListener.class, "pressed", parameterTypes, args);
    }
}
