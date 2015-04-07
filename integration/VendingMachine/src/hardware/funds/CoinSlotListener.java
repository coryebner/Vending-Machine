package hardware.funds;

import hardware.AbstractHardwareListener;

/**
 * Listens for events emanating from a coin slot.
 */
public interface CoinSlotListener extends AbstractHardwareListener {
	
	/**
     * An event announcing that the indicated, valid coin has been inserted and
     * successfully delivered to the storage device connected to the indicated
     * coin slot.
	 * @param coinSlot
	 * 		  the coin slot the has had a valid coin successfully delivered to it.
	 * @param coin
	 * 		  the coin that has been successfully delivered to the storage device 
	 * 		  connected to the coin slot.
	 */
    void validCoinInserted(CoinSlot coinSlot, Coin coin);
    
    /**
     * An event announcing that the indicated coin has been returned.
	 * @param coinSlot
	 * 		  the coin slot the has had a valid coin successfully delivered to it.
	 * @param coin
	 * 		  the coin that has been successfully delivered to the storage device 
	 * 		  connected to the coin slot.
     */
    void coinRejected(CoinSlot coinSlot, Coin coin);
}
