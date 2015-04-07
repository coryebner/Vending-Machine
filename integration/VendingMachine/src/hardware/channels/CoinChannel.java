package hardware.channels;

import hardware.acceptors.AbstractCoinAcceptor;
import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.funds.Coin;

/**
 * Represents a simple device (like, say, a tube) that allows coins to move
 * between other devices.
 */
public class CoinChannel extends AbstractChannel<AbstractCoinAcceptor> {

    /**
     * Constructs a new coin channel whose output is connected to the indicated
     * sink.
	 * @param sink
	 * 			the output to be associated to the new coin channel
     */
    public CoinChannel(AbstractCoinAcceptor sink) {
    	super(sink);
    }

    /**
     * Moves the indicated coin to the sink. This method should be called by the
     * source device, and not by an external application.
     * 
     * @param coin
     * 			   the coin to be moved to the associated channel sink.
     * @throws CapacityExceededException
     *             if the sink has no space for the coin.
     * @throws DisabledException
     *             if the sink is currently disabled.
     */
    public void deliver(Coin coin) throws CapacityExceededException,
	    DisabledException {
	getSink().acceptCoin(coin);
    }

}
