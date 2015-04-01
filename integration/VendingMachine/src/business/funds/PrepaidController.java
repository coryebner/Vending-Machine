package business.funds;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.exceptions.EmptyException;
import hardware.funds.Card;
import hardware.funds.CardSlot;
import hardware.funds.CardSlotListener;
import hardware.funds.Card.CardType;
import business.funds.VMCurrencies;

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
	
	/**
	 * Description of PrepaidController Contructor with no paramerers for a Prepaid Card
	 * Meant for disabled version of card
	 */
	public PrepaidController() {
		currencies = null;
		prepaidCard = null;
		prepaidCardInserted = false;
		isDisabled = true;
	}
	
	/** Description of PrepaidController Constructor for a Prepaid Card
	 * @param vmcurrencies 	The currency of the current vending machine
	 */
	public PrepaidController(VMCurrencies vmcurrencies) {
		currencies = vmcurrencies;
		prepaidCard = null;
		prepaidCardInserted = false;
		isDisabled = false;
	}
	
	/** Description of ConductTransaction for a Prepaid Card
	 * @param price 	The price in cents of the transaction attempted
	 * @return			Returns creditcarderror if no card inserted
	 * @return 			Returns successful based on transaction success
	 * @return			Returns unsuccessful upon an unsuccessful transaction 
	 * @return			Returns insufficientfunds if there are not enough funds
	 * @return 			Returns disabled if prepaid card controller is disabled
	 */
	public TransactionReturnCode ConductTransaction(int price) {
		if(!isDisabled) {
			int exchangePrice;
			if(!prepaidCardInserted) {
				return TransactionReturnCode.CREDITCARDERROR;
			}
			else {
				if(prepaidCard.getCurrency().getNumericCode() == currencies.getVMCurrency().getNumericCode())
					exchangePrice = price;
				else {
					//Get exchange rates for prepaid card and vending machine
					float ppEx = currencies.getExchangeRate(prepaidCard.getCurrency());
					float vmEx = currencies.getExchangeRate(currencies.getVMCurrency());
					
					exchangePrice = (int) Math.ceil(currencies.ExchangeFromToCurrency(new SupportedCurrency(prepaidCard.getCurrency(), ppEx), new SupportedCurrency(currencies.getVMCurrency(), vmEx), price));
				}
				if(price <= getAvailableBalance()) {
					if(prepaidCard.requestFunds(exchangePrice, ""))
						return TransactionReturnCode.SUCCESSFUL; //Payment successful
					else
						return TransactionReturnCode.UNSUCCESSFUL; //Could not request funds, card failure.
				}
				else
					return TransactionReturnCode.INSUFFICIENTFUNDS; //Insufficient funds
			}
		}
		else
			return TransactionReturnCode.DISABLED; //Prepaid Card Controller disabled
	}
	
	/** Description of getAvailableBalance for a Prepaid Card
	 * @return 			The value of the prepaid card if inserted
	 * @return			Returns 0 if no card inserted or machine disabled.
	 */
	public int getAvailableBalance(){
		if(isCardInserted()) {
			//Get exchange rates for prepaid card and vending machine
			float ppEx = currencies.getExchangeRate(prepaidCard.getCurrency());
			float vmEx = currencies.getExchangeRate(currencies.getVMCurrency());
			
			//Exchanges prepaid currency to vending machine currency
			return (int) Math.ceil(currencies.ExchangeFromToCurrency(new SupportedCurrency(prepaidCard.getCurrency(), ppEx), new SupportedCurrency(currencies.getVMCurrency(), vmEx), prepaidCard.getCardBalance()));
		}
		return 0;
		
	}
	
	/** Description of isCardInserted for a Prepaid Card
	 * Check if the card is inserted into the card
	 * @return 			Indicates the presence of a card in the slot
	 */
	public boolean isCardInserted(){
		return prepaidCardInserted;
	}
	
	/** Description of disablePrepaidController for a Prepaid Card
	 * Disables the functionality of the prepaid card controller
	 */
	public void disablePrepaidController() {
		isDisabled = true;
		prepaidCardInserted = false;
	}
	
	/** Description of isDisabled for a Prepaid Card
	 * @return			Returns whether the prepaid controller is currently disabled
	 */
	public boolean isDisabled() {
		return isDisabled;
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
			if(slot.readCardData().getType() == CardType.PREPAID) { //Checks if card is of type PREPAID
				prepaidCard = slot.readCardData();
				prepaidCardInserted = true;
			}
		} catch (EmptyException e) {
			prepaidCardInserted = false; //Should never happen
		}
	}

	@Override
	public void cardEjected(CardSlot slot) {
		prepaidCard = null;
		prepaidCardInserted = false;
	}
	
}
