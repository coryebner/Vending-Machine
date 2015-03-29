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
	 * 
	 * @throws CapacityExceededException
	 * 
	 * @throws DisabledException
	 */
    void acceptBanknote(Banknote banknote) throws CapacityExceededException, DisabledException;

}