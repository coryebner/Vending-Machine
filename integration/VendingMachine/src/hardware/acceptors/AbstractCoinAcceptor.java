package hardware.acceptors;

import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.funds.Coin;

/**
 * A simple interface to allow a device to communicate with another device that
 * accepts coins.
 */
public interface AbstractCoinAcceptor extends IAcceptor {
    /**
     * Instructs the device to take the coin as input.
     * 
     * @param coin
     *            The coin to be taken as input.
     * @throws CapacityExceededException
     *             If the device does not have enough space for the coin.
     * @throws DisabledException 
     */
    void acceptCoin(Coin coin) throws CapacityExceededException, DisabledException;

}
