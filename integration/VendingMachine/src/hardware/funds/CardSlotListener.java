package hardware.funds;

import hardware.AbstractHardwareListener;

/**
 * Listens for events emanating from a card slot.
 */
public interface CardSlotListener extends AbstractHardwareListener {
	/**
     * Announces that the indicated card slot has had a card inserted into it.
	 * @param slot
	 * 		  the indicated card slot that has had a card inserted into it.
	 */
    void cardInserted(CardSlot slot);

    /**
     * Announces that the indicated card slot had a card ejected from it is.
     * @param slot
     * 		  the indicated card slot that has had a card ejected from it.
     */
    void cardEjected(CardSlot slot);
}
