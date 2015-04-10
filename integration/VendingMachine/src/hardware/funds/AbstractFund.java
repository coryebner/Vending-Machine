package hardware.funds;

import hardware.exceptions.SimulationException;

public abstract class AbstractFund implements IFund {
    private final int value;
    
    /**
     * Creates an fund with the specified value in cents.
     * 
     * @param value
     * 			   the initial value of the fund in cents
     * @throws SimulationException
     *             if the value is not positive.
     */
    public AbstractFund(int value) {
	if(value <= 0)
	    throw new SimulationException("The value of the " + this.getClass() + " must be positive: " + value);
	this.value = value;
    }
    
    /**
     * Returns the value of the Fund in cents.
     */
    public int getValue() {
    	return this.value;
    } 
     
}
