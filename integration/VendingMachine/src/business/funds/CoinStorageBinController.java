package business.funds;

import java.util.HashMap;
import java.util.Map;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.funds.Coin;
import hardware.funds.CoinReceptacle;
import hardware.funds.CoinReceptacleListener;
import hardware.ui.IndicatorLight;

/**
 * Manages the state of the coin storage (overflow) bin.
 * 
 * @author Jan Clarin
 * @author James Pihooja
 * 
 *         Keeps track of the quantity of each coin denomination.
 */
public class CoinStorageBinController implements CoinReceptacleListener {

	private IndicatorLight outOfOrderLight;
	private boolean full = false;
	private Map<Integer, Integer> denominationQuantities = new HashMap<>();

	/**
	 * Public constructor.
	 * 
	 * @param coinRackDenominations
	 *            the denomination of each rack.
	 * @param outOfOrderLight
	 *            reference to the out-of-order light. null if it doesn't exist.
	 */
	public CoinStorageBinController(int[] coinRackDenominations,
			IndicatorLight outOfOrderLight) {
		this.outOfOrderLight = outOfOrderLight;
		// Initialize the quantity of each denomination to 0.
		for (int coinRackDenomination : coinRackDenominations) {
			denominationQuantities.put(coinRackDenomination, 0);
		}
	}

	/**
	 * Public constructor used for returning the vending machine back to a
	 * state.
	 * 
	 * @param denominationQuantities
	 *            the quantity of each coin denomination.
	 * @param outOfOrderLight
	 *            reference to the out-of-order light. null if it doesn't exist.
	 */
	public CoinStorageBinController(
			Map<Integer, Integer> denominationQuantities,
			IndicatorLight outOfOrderLight) {
		this.denominationQuantities = denominationQuantities;
		this.outOfOrderLight = outOfOrderLight;
	}

	/**
	 * Returns the number of coins for all quantities. Use Map.keySet() to get
	 * all denominations.
	 * 
	 * @return a map from denominations to their quantities.
	 */
	public Map<Integer, Integer> getQuantities() {
		return denominationQuantities;
	}

	/**
	 * Returns the quantity for a certain coin denomination.
	 * 
	 * @param denomination
	 *            the denomination to return the quantity of.
	 * @return the number of coins for the denomination.
	 */
	public int getQuantityByDenomination(int denomination) {
		return denominationQuantities.get(denomination);
	}

	/**
	 * Returns the total value of the coins in the storage (overflow) bin.
	 * 
	 * @return integer indicating the total value of the coins in cents.
	 */
	public int getTotalValueStoredInBin() {
		int value = 0;
		for (Integer denomination : denominationQuantities.keySet()) {
			value += denomination * denominationQuantities.get(denomination);
		}
		return value;
	}

	/**
	 * Returns the number of coin denominations.
	 * 
	 * @return the number of coin denominations.
	 */
	public int getNumberOfDenominations() {
		return denominationQuantities.size();
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
	public void coinAdded(CoinReceptacle receptacle, Coin coin) {
		int coinValue = coin.getValue();
		// Increment the quantity of the coin denomination.
		denominationQuantities.put(coinValue,
				denominationQuantities.get(coinValue) + 1);
	}

	@Override
	public void coinsFull(CoinReceptacle receptacle) {
		full = true;
		// Turns on the out-of-order light if it exists.
		if (outOfOrderLight != null)
			outOfOrderLight.activate();
	}

	@Override
	public void coinsRemoved(CoinReceptacle receptacle) {
		full = false;
		// Resets the quantity of each coin denomination.
		for (Integer denomination : denominationQuantities.keySet()) {
			denominationQuantities.put(denomination, 0);
		}
	}

	@Override
	public void enabled(CoinReceptacle receptacle) {
	}

	@Override
	public void disabled(CoinReceptacle receptacle) {
	}

	@Override
	public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {
	}

	@Override
	public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {
	}
}
