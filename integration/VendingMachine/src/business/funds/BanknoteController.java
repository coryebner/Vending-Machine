package business.funds;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.funds.Banknote;
import hardware.funds.BanknoteReceptacle;
import hardware.funds.BanknoteReceptacleListener;

/**
 * Description of PrepaidController
 * 
 * @author Jan Clarin
 * @author Andrei (Andy) Savu
 * @author Arthur Lee
 * @author Olabode (Sam) Adegbayike
 * 
 *         Class to interact with hardware to conduct a Bills Transaction
 */
public class BanknoteController implements BanknoteReceptacleListener {

	private int availableBalance = 0;

	/**
	 * Description of ConductTransaction with bills
	 * 
	 * @param price
	 *            The price in cents of the transaction attempted
	 * @return The return code based on success of the transaction
	 */
	public TransactionReturnCode ConductTransaction(int price) {
		// Return success if enough coins.
		if (availableBalance >= price) {
			return TransactionReturnCode.SUCCESSFUL;
		} else { // Not enough money.
			return TransactionReturnCode.INSUFFICIENTFUNDS;
		}
	}

	/**
	 * Description of getAvailableBalance of the bills in the temporary storage
	 * bin
	 * 
	 * @return The value of the bills currently stored
	 */
	public int getAvailableBalance() {
		return availableBalance;
	}

	@Override
	public void banknoteAdded(BanknoteReceptacle receptacle, Banknote banknote) {
		availableBalance += banknote.getValue();
	}

	@Override
	public void BanknoteRemoved(BanknoteReceptacle receptacle) {
		availableBalance = 0;
	}

	@Override
	public void banknoteFull(BanknoteReceptacle receptacle) {
		// TODO Auto-generated method stub
	}

	@Override
	public void enabled(BanknoteReceptacle receptacle) {
	}

	@Override
	public void disabled(BanknoteReceptacle receptacle) {
	}

	@Override
	public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {
	}

	@Override
	public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {
	}
}
