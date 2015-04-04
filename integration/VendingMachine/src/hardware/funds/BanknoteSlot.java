package hardware.funds;

import hardware.AbstractHardware;
import hardware.channels.BanknoteChannel;
import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.exceptions.SimulationException;

/**
 * Represents a simple slot where banknotes can be inserted.
 */
public class BanknoteSlot extends AbstractHardware<BanknoteSlotListener> {
	private int[] validValues;
	private BanknoteChannel valid;
	private Banknote noteReturned;

	/**
	 * Creates a new bank note slot - that is used to accept bank notes of the indicated denominations 
	 * @param validValues
	 * 		   the accepted denominations of the new bank note slot 
	 */
	public BanknoteSlot(int[] validValues) {
		this.validValues = validValues;
		noteReturned = null;
	}
	/**
	* Connects the slot to the proper storage device.
	* 
	* @param valid
	* 		The bank note channel that connects the slot to the storage device
	*/
	public void connect(BanknoteChannel valid) {
		this.valid = valid;
	}
	
	/**
	 * Determine the validity of a bank note 
	 * 
	 * @param banknote
	 * 		   the bank note to check
	 * @return
	 * 		   true if the bank note is one of the accepted denomination values 
	 * 		   false otherwise
	 */
	private boolean isValid(Banknote banknote) {
		for(int vv : validValues) {
			if(vv == banknote.getValue())
				return true;
		}

		return false;
	}

	/**
	 * Tells the bank note slot that the indicated bank note is being inserted. If the
	 * bank note is valid and there is space in the machine to store it, a
	 * "validBanknoteInserted" event is announced to its listeners and the bank note is
	 * delivered to the storage device. If there is no space in the machine to
	 * store it or the bank note is invalid, a "coinRejected" event is announced to
	 * its listeners and the bank note is returned through the slot.
	 * 
	 * 
	 * @param banknote
	 * 			The bank note to be added to the associated bank note slot. 
	 * @throws DisabledException
	 * 		The current slot is disabled
	 */
	 public void addBanknote(Banknote banknote) throws DisabledException {
		if(isDisabled())
		    throw new DisabledException();
		if(isValid(banknote) && valid.hasSpace() && (noteReturned==null)) {
		    try {
		    	valid.deliver(banknote);
			}
			catch(CapacityExceededException e) {
			// Should never happen
				throw new SimulationException(e);
			}
			finally {
			   	notifyValidBanknoteInserted(banknote);
			}
		}
		else if(noteReturned == null) {
			try {
				noteReturned = banknote;
			}
			finally {
				notifyBanknoteRejected(banknote);
			}
		}
		else
			throw new SimulationException("Unable to route banknote: All channels full");
	}

	 /**
	  * Remove a bank note from the bank note slot
	  * @return the bank note removed from the bank note slot
	  */
	    public Banknote removeBanknote(){
	    	Banknote temp = noteReturned;
	    	noteReturned = null;
			return temp;
	    }
	    
	    
	    /**
	     * An event that notifies listeners a valid bank note has been inserted
	     * 
	     * @param banknote
	     * 		   the bank note that has been validated and inserted into the bank note slot.
	     */
	    private void notifyValidBanknoteInserted(Banknote banknote) {
			Class<?>[] parameterTypes =
			        new Class<?>[] { BanknoteSlot.class, Banknote.class };
			Object[] args = new Object[] { this, banknote };
			notifyListeners(BanknoteSlotListener.class, "validBanknoteInserted", parameterTypes, args);
	    }

	    /**
	     * An event that notifies listeners a bank note has been rejected.
	     * 
	     * @param banknote
	     * 		   the bank note that has been rejected from the bank note slot.
	     */
	    private void notifyBanknoteRejected(Banknote banknote) {
			Class<?>[] parameterTypes =
			        new Class<?>[] { BanknoteSlot.class, Banknote.class };
			Object[] args = new Object[] { this, banknote };
			notifyListeners(BanknoteSlotListener.class, "banknoteRejected", parameterTypes, args);
	    }
	}
