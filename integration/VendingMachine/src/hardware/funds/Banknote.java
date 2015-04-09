package hardware.funds;

	/**
	 * A representation of a banknote
	 */
public class Banknote extends AbstractFund {

	/**
	 * Invoke superclass constructor using single integer argument
	 * specifying the initial value of the bank note in cents.
	 * 
	 * @param value
	 * 		   the initial value of the bank note in cents
	 */
		public Banknote(int value) {
			super(value);
		}

}