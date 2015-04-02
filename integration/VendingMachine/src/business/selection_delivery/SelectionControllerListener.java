package business.selection_delivery;

/**
 * Listens for events emanating from a selection controller.
 */
public interface SelectionControllerListener extends
        AbstractControllerListener {
    /**
     * An event that is announced to the listener when the chosen rack is empty
     */
    void emptySelection();
    
    /**
     * An event that is announced to the listener when the chosen rack is invalid
     */
    void invalidSelection();
    
    /**
     * An event that is announced to the listener when there are insufficient funds for the chosen selection.
     */
    void insufficientFunds(int fundsRequired);
}
