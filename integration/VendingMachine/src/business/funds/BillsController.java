package business.funds;
/** Description of PrepaidController
 * @author Jan Clarin
 * @author Andrei (Andy) Savu
 * @author Arthur Lee
 * @author Olabode (Sam) Adegbayike
 * 
 * Class to interact with hardware to conduct a Bills Transaction
 */
public class BillsController {
	
	private int availableBalance = 0;
	
	/** Description of ConductTransaction with bills
	 * @param price 	The price in cents of the transaction attempted
	 * @return 			The return code based on success of the transaction
	 */
	protected TransactionReturnCode ConductTransaction(int price)
	{
		return null;
	}
	
	/** Description of getAvailableBalance of the bills in the temporary storage bin
	 * @return 			The value of the bills currently stored
	 */
	protected int getAvailableBalance(){
		return availableBalance;
	}
	

}
