package hardware.funds;

	/**
	 * A representation of a banknote
	 */
public class Banknote extends AbstractFund {

		public Banknote(int value) {
			super(value);
		}

		@Override
		public int compareTo(IRackable o) {
			// TODO : If banknote comparison is a possibility
			return 0;
		}

}