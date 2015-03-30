package business.funds;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.exceptions.EmptyException;
import hardware.funds.Card;
import hardware.funds.CardSlot;
import hardware.funds.CardSlotListener;

/** Description of PrepaidController
 * @author Gurvir Singh
 * @author Kai Chen
 * 
 * Class to interact with hardware to conduct a Prepaid Transaction
 */
public class PrepaidController implements CardSlotListener {

	private boolean prepaidCardInserted;
	private boolean isDisabled;
	private Card prepaidCard;
	private VMCurrencies currencies;
	
	public PrepaidController(VMCurrencies vmcurrencies) {
		currencies = vmcurrencies;
		prepaidCard = null;
		prepaidCardInserted = false;
		isDisabled = false;
	}
	
	/** Description of ConductTransaction for a Prepaid Card
	 * @param price 	The price in cents of the transaction attempted
	 * @return 			The return code based on success of the transaction
	 * @throws EmptyException 
	 */
	protected TransactionReturnCode ConductTransaction(int price) {
		if(!isDisabled) {
			int exchangePrice;
			if(!prepaidCardInserted) {
				//TODO throw new EmptyException();
				return TransactionReturnCode.CREDITCARDERROR;
			}
			else {
				//TODO
				//if(prepaidCard.getCurrencyLocale() == currencies)
				//	exchangePrice = price;
				//else
				//	exchangePrice = ExchangeFromToCurrency(currencies, prepaidCard.getCurrencyLocale(), price);
				if(price <= getAvailableBalance()) {
					if(prepaidCard.requestFunds(price, "")) //TODO Remove PIN
						return TransactionReturnCode.SUCCESSFUL; 
					else
						return TransactionReturnCode.UNSUCCESSFUL; //Could not request funds, card failure.
				}
				else
					return TransactionReturnCode.UNSUCCESSFUL; //TODO Insufficient funds
			}
		}
		else
			return TransactionReturnCode.CREDITCARDERROR;
	}
	
	/** Description of getAvailableBalance for a Prepaid Card
	 * @return 			The value of the prepaid card if inserted, otherwise zero
	 */
	protected int getAvailableBalance(){
		//TODO return prepaidCard.checkCardBalance()
		return 0;
		
	}
	
	/** Description of isCardInserted for a Prepaid Card
	 * @return 			Indicates the presence of a card in the slot
	 */
	protected boolean isCardInserted(){
		return prepaidCardInserted;
	}
	
	protected void disablePrepaidController() {
		isDisabled = true;
	}

	@Override
	public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {
		isDisabled = false;
	}

	@Override
	public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {
		isDisabled = true;
	}

	@Override
	public void cardInserted(CardSlot slot) {
		try {
			prepaidCard = slot.readCardData();
			prepaidCardInserted = true;
		} catch (EmptyException e) {
			prepaidCardInserted = false;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Override
	public void cardEjected(CardSlot slot) {
		prepaidCardInserted = false;
		prepaidCard = null;
	}
	
}
