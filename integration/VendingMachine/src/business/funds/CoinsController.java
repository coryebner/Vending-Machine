package business.funds;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.funds.Coin;
import hardware.funds.CoinReceptacle;
import hardware.funds.CoinReceptacleListener;
import hardware.racks.CoinRack;

/**
 * Description of PrepaidController
 * 
 * @author Jan Clarin
 * @author Andrei (Andy) Savu
 * @author Arthur Lee
 * @author Olabode (Sam) Adegbayike
 * 
 *         Class to interact with hardware to conduct a coins Transaction
 */
public class CoinsController implements CoinReceptacleListener {

	private CoinReceptacle coinReceptacle;
	private CoinRackController[] coinRackControllers;
	private int[] productPrices;
	private int availableBalance; // value of coins in cents in the receptacle.
	private boolean exactChangeStatus;
	private boolean fullOfChangeStatus;

	/**
	 * Public constructor.
	 * 
	 * @param coinReceptacle
	 *            the coin receptacle.
	 * @param coinRacks
	 *            list of coin rack references.
	 * @param coinRackDenominations
	 *            value of each coin in a coin rack.
	 * @param coinRackQuantities
	 *            the number of coins in each rack.
	 * @param productPrices
	 *            prices of each product.
	 */
	public CoinsController(CoinReceptacle coinReceptacle, CoinRack[] coinRacks,
			int[] coinRackDenominations, int[] coinRackQuantities,
			int[] productPrices) {
		this.productPrices = productPrices;

		// Initialize all coin rack controllers.
		coinRackControllers = new CoinRackController[coinRacks.length];
		for (int i = 0; i < coinRacks.length; i++) {
			coinRackControllers[i] = new CoinRackController(coinRacks[i],
					coinRackDenominations[i], coinRackQuantities[i]);
		}
	}

	/**
	 * Description of ConductTransaction with coins
	 * 
	 * @param price
	 *            The price in cents of the transaction attempted
	 * @return The return code based on success of the transaction
	 */
	public TransactionReturnCode ConductTransaction(int price) {
		if (availableBalance >= price) {
			return null;
		} else if (availableBalance > price) {
			// TODO: Check if change is possible.
			// If possible, return availableBalance - price in change.
			return null; // return success.
		}

		// Not enough money.
		return null; // return insufficient funds.
	}

	/**
	 * Description of provideChange with coins
	 * 
	 * Makes calls to each coinRackHandler to dispense their change in order
	 * (greatest to least) TODO: Check if at least $4.95 in change other fail.
	 * 
	 * @param amount
	 *            The amount in cents of the amount of change to dispense
	 * @return The code of the success of change being provided
	 */
	public TransactionReturnCode provideChange(int amount) {
		return null;
	}

	/**
	 * Description of getAvailableBalance for all of the coins
	 * 
	 * @return The value of the coins in the temporary storage bin available for
	 *         a transaction
	 */
	public int getAvailableBalance() {
		return availableBalance;
	}

	/**
	 * Description of getExactChangeStatus for coins
	 * 
	 * @return The current status of the exact change state
	 */
	public boolean getExactChangeStatus() {
		return exactChangeStatus;
	}

	/**
	 * Description of getCoinRackControllers for coins
	 * 
	 * @return The array of coin rack controllers (for use to register)
	 */
	public CoinRackController[] getCoinRackControllers() {
		return coinRackControllers;
	}

	/**
	 * Description of isExactChangeActive if no change will be provided
	 * 
	 * @return The state of not providing change due to exact change status
	 */
	public boolean isExactChangeActive() {
		return exactChangeStatus;
	}

	/**
	 * Description of isFullOfChangeActive if at least one coin rack is full of
	 * coins
	 * 
	 * @return The state of at least one rack being full of coins
	 */
	public boolean isFullOfChangeActive() {
		return fullOfChangeStatus;
	}

	@Override
	public void coinAdded(CoinReceptacle receptacle, Coin coin) {
		// Add value of coins to the available balance.
		availableBalance += coin.getValue();
	}

	@Override
	public void coinsRemoved(CoinReceptacle receptacle) {
		// Set available balance to 0 because all coins were dumped.
		availableBalance = 0;
	}

	@Override
	public void coinsFull(CoinReceptacle receptacle) {
		// TODO: Should coins be dumped when full?
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
