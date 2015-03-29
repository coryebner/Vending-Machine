package hardware.funds;

/**
 * A simple representation of a coin.
 */
public class Coin extends AbstractFund {
	
	public Coin(int value) {
		super(value);
	}

	@Override
	public int compareTo(IRackable o) {
		// TODO: If coin comparison is a possibility
		return 0;
	}
	
}
