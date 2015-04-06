package business.funds;

import java.util.Locale;

import hardware.funds.Card;
import hardware.funds.Card.CardType;

import javax.swing.JOptionPane;

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
			int reply = JOptionPane.showConfirmDialog(null, "Insufficient Funds. Would you like to pay with PayPal?", "Insufficient Funds", JOptionPane.YES_NO_OPTION);
			
			if (reply != JOptionPane.YES_OPTION)
			{
				return TransactionReturnCode.INSUFFICIENTFUNDS;
			}
			
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
	public TransactionReturnCode ConductCreditCardTransaction(int price, Card creditCard){
		CardType type = creditCard.getType();
		String t = type.toString();
		
		if (!type.equals(CardType.VISA) && !type.equals(CardType.MASTERCARD))
		{
			return TransactionReturnCode.CREDITCARDERROR;
		}
			
		String name = creditCard.getName();
		
		if(name == null)
		{
			return TransactionReturnCode.CREDITCARDERROR; //Replace with specific error return code
		}
			
		String substrings[] = name.split(" ");
			
		if(name.length() < 2 )	
		{
			return TransactionReturnCode.CREDITCARDERROR; //Replace with specific error return code
		}
		
		String firstName = substrings[0], lastName = substrings[substrings.length-1];
		
		String number = creditCard.getNumber();
			
		String expiryAsString = creditCard.getExpiryDate();
			
		String expirySubStrings[] = expiryAsString.split("/");
			
		if(expirySubStrings.length != 2)
		{
			return TransactionReturnCode.CREDITCARDERROR; // Replace with specific error return code
		}
		
		int month = Integer.parseInt(expirySubStrings[0]);	
		
		int year = Integer.parseInt(expirySubStrings[1]);
		
		try {
			PayPalTransaction p = new PayPalTransaction();
			return p.processCreditCardPayment(firstName, lastName, t, number, month, year, price);
		}
		
		catch (Exception e)
		{
			return TransactionReturnCode.CREDITCARDERROR;
		}
	}
	
//	public static void main (String [] args)
//	{
//		Card cc = new Card(CardType.VISA, "4214021408540409", "Tim Johnson","1234", "03/2020", Locale.US, 1000);
//		ConductCreditCardTransaction(300, cc);
//	}

}