package hardware.funds;

/**
 * A simple representation of a coin.
 */
public class Coin extends AbstractFund {
	
	/**
	 * Invoke superclass constructor using single integer argument
	 * specifying the initial value of the coin in cents.
	 * 
	 * @param value
	 * 		   the initial value of the coin in cents
	 */
	public Coin(int value) {
		super(value);
	}
	
}
