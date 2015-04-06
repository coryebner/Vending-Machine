package business.funds;

import com.paypal.base.rest.PayPalRESTException;

import hardware.funds.Card;
import hardware.funds.Card.CardType;

/** Description of PayPayController
 * @author Dianna Yim
 * @author Jenny Wu
 * 
 * Class to interact with hardware to conduct a PayPal Transaction
 */
public class PayPalController {
	
	/** Description of ConductTransaction for PayPal
	 * @param price 	The price in cents of the transaction attempted
	 * @return 			The return code based on success of the transaction
	 */
	public TransactionReturnCode ConductTransaction(int price)
	{
		try {
			PayPalTransaction p = new PayPalTransaction();
			return p.processPayPalPayment(price);
		} 
		
		catch (Exception e) {
			return TransactionReturnCode.UNSUCCESSFUL;			
		}
	}
	
	/** Description of ConductTransaction for a credit card
	 * @param price 	The price in cents of the transaction attempted
	 * @return 			The return code based on success of the transaction
	 */
	protected TransactionReturnCode ConductCreditCardTransaction(int price, Card creditCard){
		CardType type = creditCard.getType();
		String t = type.toString();
//			if(type != CardType.VISA && type != CardType.MASTERCARD){--------------------> To be implemented by hardware
//				return null;	// Replace with specific error return code
//			}
			
		String name = creditCard.getName();
		
		if(name == null)
			return TransactionReturnCode.CREDITCARDERROR; //Replace with specific error return code
			
		String substrings[] = name.split(" ");
			
		if(name.length() < 2 )	
			return TransactionReturnCode.CREDITCARDERROR; //Replace with specific error return code
		
		String firstName = substrings[0], lastName = substrings[substrings.length-1];
		
		String number = creditCard.getNumber();
			
		String expiryAsString = null; //creditCard.getExpiry(); --------> To be implemented by hardware
			
		String expirySubStrings[] = expiryAsString.split("/");
			
		if(expirySubStrings.length != 2)
			return TransactionReturnCode.CREDITCARDERROR; // Replace with specific error return code
		
		int month = Integer.getInteger(expirySubStrings[0],0).intValue();	
		
		int year = Integer.getInteger(expirySubStrings[1],0).intValue();
			
		if(month > 12 || month < 1 || year > 9999 || year < 1900)
			return TransactionReturnCode.CREDITCARDERROR; //Replace with specific error return code
		
		try {
			PayPalTransaction p = new PayPalTransaction();
			return p.processCreditCardPayment(firstName, lastName, t, number, month, year, price);
		}
		
		catch (Exception e)
		{
			return TransactionReturnCode.CREDITCARDERROR;
		}
	}
	
}
