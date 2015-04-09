package hardware.funds;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

import hardware.exceptions.SimulationException;

/**
 * A simple data class representing the information read from a magnetic stripe
 * card.
 * <p>
 * The vending machine is only capable of recognizing 3 kinds of cards: Visa and
 * MasterCard credit cards, and prepaid cards. All others will register as
 * "unknown".
 */
public class Card {
	
  	/* Represents the anticipated/expected format of the expiration date */
	private String acceptedExpirationFormat = "mm/yyyy";
	
    /**
     * The types of cards recognized by the vending machine, plus "unknown".
     */
    public enum CardType {
	PREPAID, VISA, MASTERCARD, UNKNOWN
    }

    private String number, name, pin, expiry;
    private CardType type;
    private int maxAmount;
    private Locale cardLocale;

    /**
     * Card constructor - All arguments must be provided to Card for an object to be created:
     * 
     * @param type
     *		   the CardType (PREPAID, VISA or MASTERCARD)
     * @param number
     * 		   the card number
     * @param name
     * 		   the card name
     * @param pin
     * 		  the card pin number
     * @param expiry
     * 		  the card expiration date in the form of MM/YYYY
     * @param cardLocale
     * 		  the card locale
     * @param maxAmount
     *		   the maximum amount the card can carry.
     */
    public Card(CardType type, String number, String name, String pin, String expiry, Locale cardLocale, int maxAmount) {
	if(type == null || number == null || name == null || pin == null || expiry == null)
	    throw new SimulationException("The arguments may not be null");

    if(!isValidExpiration(this.acceptedExpirationFormat, expiry))
    	throw new SimulationException("Invalid expiration format (mm/yyyy)");
    
	this.type = type;
	this.number = number;
	this.name = name;
	this.pin = pin;
	this.expiry = expiry;
	this.maxAmount = maxAmount;
	
	if(cardLocale == null)
		this.cardLocale = Locale.CANADA;
    else
    	this.cardLocale = cardLocale;
    }

    /**
     * Returns the card type of the card.
     */
    public CardType getType() {
	return type;
    }
    
    /**
     * getCardLocale
     * @return Locale instance based on the instance defined for current Card
     */
    public Locale getCardLocale() {
    	return cardLocale;
    }

    /**
     * Return the number recorded on the card.
     * @return the number string recorded on the card.
     */
    public String getNumber() {
	return number;
    }


    /**
     * Returns the name recorded on the card.
     * @return the name recorded on the card.
     */
    public String getName() {
	return name;
    }
    
    /**
     * Returns the expiration date recorded on the card.
     * @return the expiration date on the card.
     */
    public String getExpiryDate(){
    return expiry;
    }
    
    /**
     * Returns the current card balance.
     * @return the maximum amount of the card.
     */
    public int getCardBalance(){
    	return maxAmount;
    }

    /**
     * Tests whether a given PIN conforms to what is stored on the card.
     * @param pin
     * 		   the pin to be checked
     * @return true if the incoming pin to be checked equals the card's pin.
     */
    public boolean checkPin(String pin) {
	if(this.pin.equals(pin))
	    return true;

	return false;
    }

    
    /**
     * Request the indicated amount from the card. If the card is a credit or
     * debit card, this simulates contacting the banking authority and
     * requesting the amount; if it is a prepaid card, the money is deducted
     * from the total on the card. Credit and debit cards use a PIN check as
     * well; this is ignored for prepaid cards.
     * 
     * @param amountInCents
     *            The amount requested, in cents.
     * @param pin
     * 			  the pin associated to the card; used to verify access.
     * @return true if and only if the amount requested is available.
     */
    public boolean requestFunds(int amountInCents, String pin) {
	switch(type) {
	case PREPAID:
	    if(amountInCents <= maxAmount) {
		maxAmount -= amountInCents;
		return true;
	    }
	    else
		return false;
	default:
	    return false;
	}
    }
    
    /**
     * isValidExpiration
     * 
     * @param   format 	String representing the SimpleDataFormat (ie: yy/MMMM)
     * @param   value	String to test against the expected format 
     * @return	true 	if the format of the expiration is as expected
     * 			false	otherwise
     */
    private static boolean isValidExpiration(String format, String value) {
        Date date = null;
        try {
            date = new SimpleDateFormat(format).parse(value);
        } catch (ParseException ex) {
            return false;
        }
        return date != null;
    }
    
    // Liam Apr 02: Compatibility hack - funds wasn't up to date with the fact
    //  that this method was gone, this should be removed by someone who knows
    //  what they're doing
    public Currency getCurrency()
    {
    	return Currency.getInstance(getCardLocale().toString());
    }
}
