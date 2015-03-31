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
	private int rackDenomination; // in cents.
	private int quantity;

	/**
	 * Public constructor.
	 * 
	 * @param coinRack reference to the coin rack.
	 * @param rackDenomination denomination of the coin rack
	 * @param quantity number of coins in the coin rack.
	 */
	public CoinRackController(CoinRack coinRack, int rackDenomination,
			int quantity) {
		this.coinRack = coinRack;
		this.rackDenomination = rackDenomination;
		this.quantity = quantity;
	}

	/**
	 * Releases a coin from the coin rack.
	 */
	public void releaseCoin() {
		try {
			coinRack.releaseCoin();
		} catch (EmptyException e) {
			e.printStackTrace(); // Should never occur.
		} catch (CapacityExceededException e) {
			e.printStackTrace(); // Should never occur.
		} catch (DisabledException e) {
			e.printStackTrace(); // Should never occur.
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
	public int provideChange(int amount) {
		return 0;
	}

	/**
	 * Returns the coin denomination for the coins in the rack.
	 * 
	 * @return coin denomination in cents
	 */
	public int getCoinRackDenomination() {
		return rackDenomination;
	}

	/**
	 * Returns the number of coins in the specific rack.
	 * 
	 * @return The number of coins in the specific rack
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Returns a boolean indicating if the rack is empty.
	 * 
	 * @return If the number of coins in the specific rack equals zero
	 */
	public boolean isEmpty() {
		return quantity == 0;
	}

	@Override
	public void coinsFull(CoinRack rack) {
		// Should never be encountered since we have an overflow bin for coin
		// racks.
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
