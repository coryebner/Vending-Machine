package hardware;

/**
 * Listens for events emanating from a card slot.
 */
public interface CardSlotListener extends AbstractHardwareListener {
    /**
     * Announces that the indicated card slot has had a card inserted into it.
     */
    void cardInserted(CardSlot slot);

    /**
     * Announces that the indicated card slot has had a card ejected from it.
     */
    void cardEjected(CardSlot slot);
}
