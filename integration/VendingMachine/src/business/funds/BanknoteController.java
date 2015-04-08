package business.funds;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.funds.Banknote;
import hardware.funds.BanknoteReceptacle;
import hardware.funds.BanknoteReceptacleListener;
import hardware.ui.PushButton;
import hardware.ui.PushButtonListener;

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
public class BanknoteController implements BanknoteReceptacleListener,
		PushButtonListener {

	private int availableBalance = 0;
	private BanknoteStorageBinController banknoteStorageController;
	private BanknoteReceptacle banknoteReceptacle;

	public BanknoteController(BanknoteReceptacle bnReceptacle,
			BanknoteStorageBinController controller) {
		this.banknoteStorageController = controller;
		this.banknoteReceptacle = bnReceptacle;
	}

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
			// banknoteReceptacle.storeBanknotes();
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

	@Override
	public void pressed(PushButton button) {
		// If the overflow is FULL then some funds will have been stored in the
		if (banknoteStorageController.isFull()) {
			return;
		}
		 try {
			 banknoteReceptacle.returnBanknotes();
		 } catch (CapacityExceededException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 } catch (DisabledException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }

	}

	@Override
	public void banknoteRemoved(BanknoteReceptacle receptacle) {
		availableBalance = 0;
	}

	@Override
	public void banknoteFull(BanknoteReceptacle receptacle) {
		try {
			receptacle.returnBanknotes();
		} catch (CapacityExceededException | DisabledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
