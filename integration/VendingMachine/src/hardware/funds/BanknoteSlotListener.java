package hardware.funds;

import hardware.AbstractHardwareListener;

/**
 * Listens for events emanating from a banknote slot.
 */
public interface BanknoteSlotListener extends AbstractHardwareListener {

	/**
     * An event announcing that the indicated, valid bank note has been inserted and
     * successfully delivered to the storage device connected to the indicated
     * bank note slot.
	 * @param banknoteSlot
	 * 		  the bank note slot that was successfully delivered too.
	 * @param banknote
	 * 		  the bank note successfully delivered to the bank note slot.
	 */
    void validBanknoteInserted(BanknoteSlot banknoteSlot, Banknote banknote);

    /**
     * An event announcing that the indicated bank note has been returned.
     * @param banknoteSlot
     * 		  the bank note slot that has has a rejected bank note returned too.
     * @param banknote
     * 		  the bank note that has been rejected and returned
     */
    void banknoteRejected(BanknoteSlot banknoteSlot, Banknote banknote);
}
