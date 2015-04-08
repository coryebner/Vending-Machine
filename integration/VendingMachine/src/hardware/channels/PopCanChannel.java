package hardware.channels;

import hardware.acceptors.AbstractPopCanAcceptor;
import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.products.PopCan;

/**
 * Represents the hardware through which a pop can is carried from one device to
 * another. Once the hardware is configured, pop can channels will not be used
 * directly by other applications.
 */
public class PopCanChannel extends AbstractChannel<AbstractPopCanAcceptor> {

    /**
     * Creates a new pop can channel whose output will go to the indicated sink.
	 * @param sink
	 * 			the output to be associated to the new pop can channel
     */
    public PopCanChannel(AbstractPopCanAcceptor sink) {
	super(sink);
    }

    /**
     * This method should only be called from simulated hardware devices.
     * 
      * @param pop
     * 			   the pop can to be moved to the associated channel sink.
     * @throws CapacityExceededException
     *             if the output sink cannot accept the pop.
     * @throws DisabledException
     *             if the output sink is currently disabled.
     */

    public void acceptPop(PopCan pop) throws CapacityExceededException,
	    DisabledException {
    	getSink().acceptPop(pop);
    }
}
