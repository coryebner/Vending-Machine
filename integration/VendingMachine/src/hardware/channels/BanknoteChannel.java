package hardware.channels;

import hardware.acceptors.AbstractBanknoteAcceptor;
import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.funds.Banknote;

/**
 * Represents a simple device (like, say, a conveyor belt) that allows banknote to move
 * between other devices.
 */
public class BanknoteChannel extends AbstractChannel<AbstractBanknoteAcceptor> {
    /**
     * Constructs a new banknote channel whose output is connected to the indicated
     * sink.
     */
    public BanknoteChannel(AbstractBanknoteAcceptor sink) {
    	super(sink);
    }

    /**
     * Moves the indicated banknote to the sink. This method should be called by the
     * source device, and not by an external application.
     * 
     * @throws CapacityExceededException
     *             if the sink has no space for the banknotes.
     * @throws DisabledException
     *             if the sink is currently disabled.
     */
    public void deliver(Banknote banknote) throws CapacityExceededException, DisabledException {
    	getSink().acceptBanknote(banknote);
    }

}
