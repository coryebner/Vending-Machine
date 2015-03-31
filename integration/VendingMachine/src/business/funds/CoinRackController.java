package business.funds;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.exceptions.EmptyException;
import hardware.funds.Coin;
import hardware.racks.CoinRack;
import hardware.racks.CoinRackListener;

/**
 * Maintains the state of a given coin rack.
 * 
 * @author Jan Clarin
 * @author Andrei (Andy) Savu
 * @author Arthur Lee
 * @author Olabode (Sam) Adegbayike
 * 
 *         Class to interact with hardware to conduct a Coins transaction.
 */
public class CoinRackController implements CoinRackListener {

	private CoinRack coinRack;
	private int valueOfCoinRack; // in cents.
	private int quantity;

	protected CoinRackController(CoinRack coinRack, int valueOfCoinRack,
			int quantity) {
		this.coinRack = coinRack;
		this.valueOfCoinRack = valueOfCoinRack;
		this.quantity = quantity;
	}

	/**
	 * Returns the coin denomination for the coins in the rack.
	 * 
	 * @return coin denomination in cents
	 */
	protected int getValueOfCoinRack() {
		return valueOfCoinRack;
	}

	/**
	 * Description of getQuantity for a specific rack of coins
	 * 
	 * @return The number of coins in the specific rack
	 */
	protected int getQuantity() {
		return quantity;
	}

	/**
	 * Description of isEmpty for a specific rack of coins
	 * 
	 * @return If the number of coins in the specific rack equals zero
	 */
	protected boolean isEmpty() {
		return quantity == 0;
	}

	/**
	 * Description of releaseCoin for a specific rack of coins If the number of
	 * coins in the specific rack has at least one coin it will be released
	 * 
	 * @throws EmptyException
	 *             thrown when rack is empty.
	 */
	protected void releaseCoin() throws EmptyException {
		try {
			coinRack.releaseCoin();
		} catch (CapacityExceededException e) {
			e.printStackTrace();
		} catch (DisabledException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Description of provideChange with coins TODO: Check if at least 3 coins
	 * in each rack.
	 * 
	 * @param amount
	 *            The amount in cents of the amount of change to dispense
	 * @return The amount remaining to be returned
	 */
	protected int provideChange(int amount) {
		return 0;
	}

	@Override
	public void coinsFull(CoinRack rack) {
		// Should never be encountered since we have an overflow bin for coin racks.
	}

	@Override
	public void coinsEmpty(CoinRack rack) {
		quantity = 0;
	}

	@Override
	public void coinAdded(CoinRack rack, Coin coin) {
		quantity++;
	}

	/**
	 * Assuming that only ONE coin is removed.
	 */
	@Override
	public void coinRemoved(CoinRack rack, Coin coin) {
		quantity--;
	}

	@Override
	public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {
	}

	@Override
	public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {
	}

}
