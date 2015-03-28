package business.funds;
/** Description of PrepaidController
 * @author Gurvir Singh
 * 
 * Class to interact with hardware to conduct a Prepaid Transaction
 */
public class PrepaidController {

	private boolean cardInserted;
	
	/** Description of ConductTransaction for a Prepaid Card
	 * @param price 	The price in cents of the transaction attempted
	 * @return 			The return code based on success of the transaction
	 */
	protected TransactionReturnCode ConductTransaction(int price)
	{
		return null;
	}
	
	/** Description of getAvailableBalance for a Prepaid Card
	 * @return 			The value of the prepaid card if inserted, otherwise zero
	 */
	protected int getAvailableBalance(){
		return 0;
		
	}
	
	/** Description of isCardInserted for a Prepaid Card
	 * @return 			Indicates the presence of a card in the slot
	 */
	protected boolean isCardInserted(){
		return false;
	}
	
}
