package hardware.funds;

import hardware.AbstractHardwareListener;

/**
 * Listens for events emanating from a banknote slot.
 */
public interface BanknoteSlotListener extends AbstractHardwareListener {
    /**
     * An event announcing that the indicated, valid coin has been inserted and
     * successfully delivered to the storage device connected to the indicated
     * coin slot.
     */
    void validBanknoteInserted(BanknoteSlot banknoteSlot, Banknote banknote);

    /**
     * An event announcing that the indicated coin has been returned.
     */
    void banknoteRejected(BanknoteSlot banknoteSlot, Banknote banknote);
}
