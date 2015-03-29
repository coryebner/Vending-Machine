package business.funds;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.funds.Card;
import hardware.funds.CardSlot;
import hardware.funds.CardSlotListener;

/** Description of PrepaidController
 * @author Gurvir Singh
 * 
 * Class to interact with hardware to conduct a Prepaid Transaction
 */
public class PrepaidController implements CardSlotListener {

	private boolean prePaidCardInserted;
	private Card prepaidCard;
	private VMCurrencies currencies;
	
	public PrepaidController(VMCurrencies vmcurrencies){
		currencies = vmcurrencies;
	}
	
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

	@Override
	public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cardInserted(CardSlot slot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cardEjected(CardSlot slot) {
		// TODO Auto-generated method stub
		
	}
	
}
