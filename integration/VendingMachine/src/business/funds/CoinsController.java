package business.funds;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.exceptions.EmptyException;
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

	private CoinRackController[] coinRackControllers;
	private int[] productPrices;
	private int availableBalance; // value of coins in cents in the receptacle.
	private boolean exactChangeStatus;
	private boolean fullOfChangeStatus;
	private boolean bestEffortChange;

	/**
	 * Public constructor.
	 * 
	 * @param bestEffortChange
	 *            boolean indicating whether best-effort change mode is on
	 *            best-effort: give as much change as possible.
	 * @param coinRacks
	 *            list of coin rack references.
	 * @param coinRackDenominations
	 *            value of each coin in a coin rack.
	 * @param productPrices
	 *            prices of each product.
	 */
	public CoinsController(boolean bestEffortChange, CoinRack[] coinRacks,
			int[] coinRackDenominations, int[] coinRackQuantities,
			int[] productPrices) {
		this.bestEffortChange = bestEffortChange;
		this.productPrices = productPrices;

		// Initialize all coin rack controllers.
		coinRackControllers = new CoinRackController[coinRacks.length];
		for (int i = 0; i < coinRacks.length; i++) {
			coinRackControllers[i] = new CoinRackController(coinRacks[i],
					coinRackDenominations[i], coinRackDenominations[i]);
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
		// Return success if enough coins.
		if (availableBalance >= price) {
			return TransactionReturnCode.SUCCESSFUL;
		} else { // Not enough money.
			return TransactionReturnCode.INSUFFICIENTFUNDS;
		}
	}

	/**
	 * Description of provideChange with coins
	 * 
	 * Makes calls to each coinRackHandler to dispense their change in order
	 * (greatest to least)
	 * 
	 * @param amount
	 *            The amount in cents of the amount of change to dispense
	 * @return The code of the success of change being provided
	 */
	public TransactionReturnCode provideChange(int amount) {
		boolean exactChangePossible = isChangePossible(amount);

		// Return change if best-effort change mode is on or if exact change
		// is possible. Otherwise, don't return change.
		if (bestEffortChange || exactChangePossible) {
			int sum = 0;
			// Start dispensing coins from highest denomination with coins
			// in its respective racks.
			for (int i = coinRackControllers.length - 1; i >= 0; i--) {
				int rackDenomination = coinRackControllers[i]
						.getCoinRackDenomination();

				// Dispense coin at this denomination if there are coins
				// in the rack and dispensing another coin of this
				// denomination is less or equal to the amount of total change.
				while (coinRackControllers[i].getQuantity() > 0
						&& sum + rackDenomination <= amount) {
					// Dispense coin.
					try {
						coinRackControllers[i].releaseCoin();
					} catch (EmptyException e) {
						break;
					}
					sum += rackDenomination;
				}
				if (sum == amount)
					break;
			}

			return TransactionReturnCode.SUCCESSFUL;
		}
		// Cannot return exact change.
		return TransactionReturnCode.UNSUCCESSFUL;
	}

	/**
	 * Returns a boolean indicating whether or not a specific amount can be
	 * returned as change.
	 * 
	 * @param amount
	 *            the amount of money to be returned change.
	 * @return boolean indicating whether or not the amount can be returned.
	 */
	private boolean isChangePossible(int amount) {
		boolean changePossible;
		int sum = 0;

		for (int i = coinRackControllers.length - 1; i >= 0; i--) {
			int numCoinsUsed = 0;
			int rackDenomination = coinRackControllers[i]
					.getCoinRackDenomination();
			int rackQuantity = coinRackControllers[i].getQuantity();
			while (rackQuantity - numCoinsUsed > 0
					&& sum + rackDenomination <= amount) {
				numCoinsUsed++;
				sum += rackDenomination;
			}
		}

		changePossible = sum == amount;

		if (changePossible) {
			// TODO: Turn on exact change light.
		} else {
			// TODO: Turn off exact change light if it's on.
		}
		return changePossible;
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
	 * Returns a boolean indicating if at least one coin rack is full of coins.
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
		// Taken care of by hardware.
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
