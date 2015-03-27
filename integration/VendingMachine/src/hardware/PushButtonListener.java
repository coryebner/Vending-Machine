package hardware;

/**
 * Listens for events emanating from a selection button.
 */
public interface PushButtonListener extends
        AbstractHardwareListener {
    /**
     * An event that is announced to the listener when the indicated button has
     * been pressed.
     */
    void pressed(PushButton button);
}
