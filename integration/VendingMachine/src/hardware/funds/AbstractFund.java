package hardware.funds;

import java.util.Locale;

import hardware.exceptions.SimulationException;

public abstract class AbstractFund implements IFund {
    private final int value;
    private final Locale locale;
    
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
	
	/* Default to Canadian Locale */
	this.locale = Locale.CANADA;
    }
    
    /**
     * Returns the value of the Fund in cents.
     */
    public int getValue() {
    	return this.value;
    }
    
    /** 
     * Returns the locale of the Fund
     */
	public Locale getLocale() {
		return this.locale;
	}

	/**
	 * Comparison of one fund to another first compares value in cents and then locale.
	 * 
	 * @param fund
	 * 		   the fund to be compared
	 * @return
	 * 		   true if both value in cents and locale of the fund parameter match the current fund object
	 */
	public int compareTo(IFund fund) {
		int ret = Integer.compare(this.getValue(), fund.getValue());
		if (ret == 0) {
			return this.locale.equals(fund.getLocale()) ? 0 : ret;
		}
		return ret;
	}
     
}
