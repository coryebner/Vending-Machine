package business.funds;
/** Description of PrepaidController
 * @author Jan Clarin
 * @author Andrei (Andy) Savu
 * @author Arthur Lee
 * @author Olabode (Sam) Adegbayike
 * 
 * Class to interact with hardware to conduct a Prepaid Transaction
 */
public class CoinRackController {

	private int quantity = 0;
	private int capacity;			// possibly stored rather than calling hardware all the time
	private int valueOfCoinRack;	// 5/10/25/100/200 cents
	
	/** Description of getQuantity for a specific rack of coins
	 * @return 			The number of coins in the specific rack
	 */
	protected int getQuanity(){
		return 0;
	}
	
	/** Description of isEmpty for a specific rack of coins
	 * @return 			If the number of coins in the specific rack equals zero
	 */
	protected boolean isEmpty(){
		return false;
	}
	
	/** Description of isFull for a specific rack of coins
	 * @return 			If the number of coins in the specific rack is at capacity
	 */
	protected boolean isFull(){
		return false;
	}
	
	/** Description of releaseCoin for a specific rack of coins
	 * 			If the number of coins in the specific rack has at least one coin
	 * 			it will be released
	 */
	protected void releaseCoin(){
		
	}
	
	/** Description of provideChange with coins
	 * 
	 * 
	 * @param amount 	The amount in cents of the amount of change to dispense
	 * @return 			The amount remaining to be returned
	 */
	protected int provideChange(int amount)
	{
		return 0;
	}
}
