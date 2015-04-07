package hardware.funds;

import hardware.AbstractHardware;
import hardware.acceptors.AbstractBanknoteAcceptor;
import hardware.channels.BanknoteChannel;
import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.exceptions.SimulationException;

import java.util.Vector;

/**
 * A place for banknotes to be stored, there is a transaction storage, for when a banknote is 
 * entered during a transaction and before it is used, and a permanent storage.
 * A banknote receptacle can be disabled to prevent more banknotes from being placed within it.
 * A banknote receptacle has a maximum capacity of banknotes that can be stored within it.
 */
public class BanknoteReceptacle extends AbstractHardware<BanknoteReceptacleListener> implements
        AbstractBanknoteAcceptor {
    private Vector<Banknote> banknotesEntered = new Vector<Banknote>();
    private int maxCapacity;
    private BanknoteChannel banknoteStore, banknoteReturn;

    /**
     * Creates a bank note receptacle with the indicated capacity.
     * 
     * @param capacity
     * 			   the initial capacity of the bank note receptacle 
     * @throws SimulationException
     *             if the capacity is not a positive integer.
     */
    public BanknoteReceptacle(int capacity) {
		if(capacity <= 0)
		    throw new SimulationException("Capacity must be positive: " + capacity);
	
		maxCapacity = capacity;
    }


    /**
     * Loads the indicated bank notes into the receptacle without causing events to
     * be announced.
     * 
     * @param banknotes
     * 			the bank notes (variable length parameter) to be loaded into the receptacle
     * @throws SimulationException
     * 		   if the loading exceeds the capacity of the receptacle.
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
     * @param banknote
     * 			   the bank note to be accepted by the bank note receptacle
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

		if(banknotesEntered.size() >= maxCapacity) {
		    notifyBanknotesFull();
		}
    }
    
    public void connect(BanknoteChannel banknoteStore, BanknoteChannel banknoteReturn) {
    	this.banknoteStore = banknoteStore;
    	this.banknoteReturn = banknoteReturn;
    }
    
    /**
     * Causes the receptacle to attempt to move its bank notes to the bank notes storage. Any
     * bank notes that either do not fit in the bank notes storage are returned. A successful storage
     * will cause a "banknoteRemoved" event to be announced to its listeners.
     * 
     * @throws CapacityExceededException
     *             if one of the output channels fails to accept the bank note.
     * @throws DisabledException
     *             if the receptacle is disabled.
     */
    public void storeBanknotes() throws CapacityExceededException, DisabledException {
		if(isDisabled())
		    throw new DisabledException();
	
		for(Banknote banknote: banknotesEntered) {
		    BanknoteChannel bcs = banknoteStore;
	
		    if(bcs != null && bcs.hasSpace())
			bcs.deliver(banknote);
		    else if(banknoteReturn != null) {
				if(banknoteReturn.hasSpace())
				    banknoteReturn.deliver(banknote);
				else
				    throw new CapacityExceededException();
		    }
		    else
			throw new SimulationException("The 'return' output channel has not been defined, but it is needed for discarding bills.");
		}
	
		if(!banknotesEntered.isEmpty()) {
		    banknotesEntered.clear();
		    notifyBanknotesRemoved();
		}
    }

    /**
     * Instructs this bank note receptacle to return all of its bank notes to the user. If
     * any bank notes are returned, a "banknoteReturned" event will be announced to its
     * listeners.
     * 
     * @throws CapacityExceededException
     *             if the bank note return is overfull.
     * @throws DisabledException
     *             if the receptacle is disabled.
     */
    public void returnBanknotes() throws CapacityExceededException, DisabledException {
		if(isDisabled())
		    throw new DisabledException();
	
		for(Banknote banknote: banknotesEntered)
		    banknoteReturn.deliver(banknote);
	
		if(!banknotesEntered.isEmpty()) {
		    banknotesEntered.clear();
		    notifyBanknotesRemoved();
		}
    }

    
    /**
     * Returns whether this bank note receptacle has enough space to accept at least
     * one more bank note. Causes no events.
     */
    @Override
    public boolean hasSpace() {
    	return banknotesEntered.size() < maxCapacity;
    }
    
    /**
     * Notify listeners that a bank note has been added to the receptacle.
     * 
     * @param banknote
     * 		   the bank note being added to the receptacle (acceptor)
     */
    private void notifyBanknoteAdded(Banknote banknote) {
		Class<?>[] parameterTypes = new Class<?>[] { BanknoteReceptacle.class, Banknote.class };
		Object[] args = new Object[] { this, banknote };
		notifyListeners(BanknoteReceptacleListener.class, "banknoteAdded", parameterTypes, args);
	}
    
    /**
     * Notify listeners the bank note acceptor is full
     */
    private void notifyBanknotesFull() {
		Class<?>[] parameterTypes = new Class<?>[] { BanknoteReceptacle.class };
		Object[] args = new Object[] { this };
		notifyListeners(BanknoteReceptacleListener.class, "banknoteFull", parameterTypes, args);
    }
   
    /**
     * Notify listeners bank notes have been removed from the temporary bank note receptacle.
     */
    private void notifyBanknotesRemoved() {
	Class<?>[] parameterTypes = new Class<?>[] { BanknoteReceptacle.class };
	Object[] args = new Object[] { this };
	notifyListeners(BanknoteReceptacleListener.class, "banknoteRemoved", parameterTypes, args);
    }

}
