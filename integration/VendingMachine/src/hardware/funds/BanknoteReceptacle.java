package hardware.funds;

import hardware.AbstractHardware;
import hardware.acceptors.AbstractBanknoteAcceptor;
import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.exceptions.SimulationException;

import java.util.Vector;

/**
 * A place for all banknotes to be stored, there is no intermediate step. The banknotes are
 * either stored or returned. A banknote receptacle can be disabled to
 * prevent more banknotes from being placed within it. A banknote receptacle has a
 * maximum capacity of banknotes that can be stored within it.
 */
public class BanknoteReceptacle extends AbstractHardware<BanknoteReceptacleListener> implements
        AbstractBanknoteAcceptor {
    private Vector<Banknote> banknotesEntered = new Vector<Banknote>();
    private int maxCapacity;

    /**
     * Creates a banknote receptacle with the indicated capacity.
     * 
     * @throws SimulationException
     *             if the capacity is not a positive integer.
     */
    public BanknoteReceptacle(int capacity) {
		if(capacity <= 0)
		    throw new SimulationException("Capacity must be positive: " + capacity);
	
		maxCapacity = capacity;
    }



    /**
     * Loads the indicated coins into the receptacle without causing events to
     * be announced.
     * 
     * @throws SimulationException
     *             if the loading exceeds the capacity of the receptacle.
     */
    public void loadWithoutEvents(Banknote... banknotes) throws SimulationException {
		if(maxCapacity < banknotesEntered.size() + banknotes.length)
		    throw new SimulationException("Capacity exceeded by attempt to load");
	
		for(Banknote banknote: banknotes)
		    banknotesEntered.add(banknote);
    }

    /**
     * Causes the indicated banknote to be added to the receptacle if it has space.
     * A successful addition causes a "banknoteAdded" event to be announced to its
     * listeners. If a successful addition causes the receptacle to become full,
     * it will also announce a "banknotesFull" event to its listeners.
     * 
     * @throws CapacityExceededException
     *             if the receptacle has no space.
     * @throws DisabledException
     *             if the receptacle is disabled.
     */
    public void acceptBanknote(Banknote banknote) throws CapacityExceededException, DisabledException {
		if(isDisabled())
		    throw new DisabledException();
	
		if(banknotesEntered.size() >= maxCapacity)
		    throw new CapacityExceededException();
	
		banknotesEntered.add(banknote);
	
		notifyBanknoteAdded(banknote);
	
		if(banknotesEntered.size() >= maxCapacity)
		    notifyBanknotesFull();
    }
    
    /**
     * Returns whether this banknote receptacle has enough space to accept at least
     * one more banknote. Causes no events.
     */
    @Override
    public boolean hasSpace() {
    	return banknotesEntered.size() < maxCapacity;
    }

    private void notifyBanknoteAdded(Banknote banknote) {
		Class<?>[] parameterTypes = new Class<?>[] { BanknoteReceptacle.class, Banknote.class };
		Object[] args = new Object[] { this, banknote };
		notifyListeners(BanknoteReceptacleListener.class, "banknoteAdded", parameterTypes, args);
	}
    
    private void notifyBanknotesFull() {
		Class<?>[] parameterTypes = new Class<?>[] { BanknoteReceptacle.class };
		Object[] args = new Object[] { this };
		notifyListeners(BanknoteReceptacleListener.class, "BanknotesFull", parameterTypes, args);
    }


}
