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
	* Creates a slot to accept banknotes of the indicated denominations
	*/
	public BanknoteSlot(int[] validValues) {
	this.validValues = validValues;
	noteReturned = null;
	}
	/**
	* Connects the slot to the proper storage device.
	* 
	* @param valid
	* 		The channel that connects the slot to the storage device
	*/
	public void connect(BanknoteChannel valid) {
	this.valid = valid;
	}
	
	private boolean isValid(Banknote banknote) {
		for(int vv : validValues) {
			if(vv == banknote.getValue())
				return true;
		}

		return false;
	}

	/**
	 * Tells the banknote slot that the indicated banknote is being inserted. If the
	 * banknote is valid and there is space in the machine to store it, a
	 * "validBanknoteInserted" event is announced to its listeners and the banknote is
	 * delivered to the storage device. If there is no space in the machine to
	 * store it or the banknote is invalid, a "coinRejected" event is announced to
	 * its listeners and the banknote is returned through the slot.
	 * @param banknote
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

	    public Banknote removeBanknote(){
	    	Banknote temp = noteReturned;
	    	noteReturned = null;
			return temp;
	    }
	    
	    
	    private void notifyValidBanknoteInserted(Banknote banknote) {
			Class<?>[] parameterTypes =
			        new Class<?>[] { BanknoteSlot.class, Banknote.class };
			Object[] args = new Object[] { this, banknote };
			notifyListeners(BanknoteSlotListener.class, "validBanknoteInserted", parameterTypes, args);
	    }

	    private void notifyBanknoteRejected(Banknote banknote) {
			Class<?>[] parameterTypes =
			        new Class<?>[] { BanknoteSlot.class, Banknote.class };
			Object[] args = new Object[] { this, banknote };
			notifyListeners(BanknoteSlotListener.class, "banknoteRejected", parameterTypes, args);
	    }
	}
