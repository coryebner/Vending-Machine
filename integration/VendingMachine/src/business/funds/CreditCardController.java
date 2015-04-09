package business.funds;

import java.security.InvalidParameterException;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.exceptions.DisabledException;
import hardware.exceptions.EmptyException;
import hardware.funds.Card;
import hardware.funds.Card.CardType;
import hardware.funds.CardSlot;
import hardware.funds.CardSlotListener;

/** Description of CreditCardController
 * @author James Pihooja
 * 
 * Class to interact with hardware to conduct a CreditCard Transaction
 */
public class CreditCardController implements CardSlotListener{

	private boolean creditCardInserted;
	private Card creditCard;
	private PayPalController payPalController;
	private CardSlot cardSlot;
	
	public CreditCardController(PayPalController payPalController){
		if(payPalController == null){
			throw new InvalidParameterException();
		}
		this.payPalController = payPalController;
		creditCardInserted = false;
		creditCard = null;
	}
	
	/** Description of ConductTransaction for a Credit Card
	 * @param price 	The price in cents of the transaction attempted
	 * @return 			The return code based on success of the transaction
	 */
	public TransactionReturnCode ConductTransaction(int price)
	{
		if(creditCardInserted){
			TransactionReturnCode returnCode = payPalController.ConductCreditCardTransaction(price, creditCard);
			if(returnCode == TransactionReturnCode.SUCCESSFUL) {
				try {
					cardSlot.ejectCard();
				} catch (EmptyException e) {
				} catch (DisabledException e) {
				}
				cardSlot = null;
			}
			return returnCode;
		}
		return TransactionReturnCode.CREDITCARDERROR;
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
		try {
			Card temp = slot.readCardData();
			if(temp.getType() == CardType.UNKNOWN){///////////////needs to be fixed
				cardSlot = slot;
				creditCard = temp;
				creditCardInserted = true;
			}
		} catch (EmptyException e) {
			creditCardInserted = false;
		}
	}

	@Override
	public void cardEjected(CardSlot slot) {
		creditCardInserted = false;
		cardSlot = null;
	}

}
