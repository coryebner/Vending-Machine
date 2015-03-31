package business.selection_delivery;

/**
 * Listens for events emanating from a selection controller.
 */
public interface SelectionControllerListener extends
        AbstractControllerListener {
    /**
     * An event that is announced to the listener when the chosen rack is empty
     */
    void nameChanged(int rackIndex, String newName);
    
    /**
     * An event that is announced to the listener when the chosen rack is invalid
     */
    void costChanged(int rackIndex, int newCost);
}
