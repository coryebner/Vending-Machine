package hardware.acceptors;

import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.funds.Banknote;

/**
 *
 */
public interface AbstractBanknoteAcceptor extends IAcceptor {
	/**
	 * 
	 * @param banknote
	 * 			the bank note to be accepted into the bank note acceptor
	 * 
	 * @throws CapacityExceededException
	 * 			if the device not have enough space for the bank note
	 * 
	 * @throws DisabledException
	 * 			if the current device is disabled
	 */
    void acceptBanknote(Banknote banknote) throws CapacityExceededException, DisabledException;
}