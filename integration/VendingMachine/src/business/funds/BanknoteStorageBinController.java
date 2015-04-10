package business.funds;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.funds.Banknote;
import hardware.funds.BanknoteReceptacle;
import hardware.funds.BanknoteReceptacleListener;
import hardware.ui.IndicatorLight;

/**
 * Maintains the state of the banknote storage bin, where banknotes are stored
 * after a transaction.
 * 
 * @author Jan Clarin
 * @author James Pihooja
 * 
 *         Keeps track of the number of banknotes in the bin.
 */
public class BanknoteStorageBinController implements BanknoteReceptacleListener {

	private IndicatorLight outOfOrderLight;
	private boolean full = false;
	private int quantity;

	/**
	 * Public constructor.
	 * 
	 * @param quantity
	 *            the number of banknotes in the storage bin.
	 */
	public BanknoteStorageBinController(int quantity,
			IndicatorLight outOfOrderLight) {
		this.quantity = quantity;
		this.outOfOrderLight = outOfOrderLight;
	}

	/**
	 * Returns the number of banknotes in the storage bin.
	 * 
	 * @return the quantity of the banknotes.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Returns a boolean indicating if the storage bin is full.
	 * 
	 * @return boolean indicating if the storage bin is full.
	 */
	public boolean isFull() {
		return full;
	}

	@Override
	public void banknoteAdded(BanknoteReceptacle receptacle, Banknote banknote) {
		quantity++;
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
//////////////////

	@Override
	public void banknoteRemoved(BanknoteReceptacle receptacle) {
		full = false;
		quantity = 0;	
	}

	@Override
	public void banknoteFull(BanknoteReceptacle receptacle) {
		full = true;
		// Turn on the out-of-order light if it exists.
		if (outOfOrderLight != null)
			outOfOrderLight.activate();
	}


}
