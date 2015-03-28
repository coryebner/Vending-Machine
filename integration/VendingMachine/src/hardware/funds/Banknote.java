package hardware.funds;

import hardware.exceptions.SimulationException;

	/**
	 * A representation of a banknote
	 */
public class Banknote {
    private int value;

    /**
     * Creates a Banknote with the specified value in cents.
     * 
     * @throws SimulationException
     *             if the value is not positive.
     */
    public Banknote(int value) {
    	if(value <= 0)
	    throw new SimulationException("The value of the banknote must be positive: " + value);
    	this.value = value;
    }

    /**
     * Returns the value of the Banknote in cents.
     */
    public int getValue() {
    	return value;
    }
}