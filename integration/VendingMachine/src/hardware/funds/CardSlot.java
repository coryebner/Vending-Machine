package hardware.funds;

import hardware.AbstractHardware;
import hardware.exceptions.DisabledException;
import hardware.exceptions.EmptyException;

/**
 * Represents a device that accepts and reads magnetic stripe cards (credit
 * cards, debit cards, etc.).
 */
public class CardSlot extends AbstractHardware<CardSlotListener> {
    private Card card = null;
    /**
     * 
     * Inserts a card into the card slot. If successful, announces a
     * "cardInserted" event to its listeners.
     * 
     * @param card
     * 			   the card inserted into the current card slot.
     * @throws CardSlotNotEmptyException
     *             if the card slot already contains a card.
     * @throws DisabledException
     *             if the card slot is currently disabled.
     */
    public void insertCard(Card card) throws CardSlotNotEmptyException,
	    DisabledException {
	if(isDisabled())
	    throw new DisabledException();

	if(this.card != null)
	    throw new CardSlotNotEmptyException();

	this.card = card;

	notifyCardInserted();
    }

    /**
     * Ejects the card from the card slot. If successful, announces a
     * "cardEjected" event to its listeners.
     * 
     * @throws EmptyException
     *             if the card slot does not contain a card.
     * @throws DisabledException
     *             if the card slot is currently disabled.
     */
    public void ejectCard() throws EmptyException, DisabledException {
	if(isDisabled())
	    throw new DisabledException();

	if(card == null)
	    throw new EmptyException();

	card = null;

	notifyCardEjected();
    }
   
    /**
     * Reads the data stored on the card that is currently inserted and returns the card. Causes no
     * events to be announced.
     * @return Card the card associated to the current card slot
     * @throws EmptyException
     *             if there is no card currently in the card slot.
     */
    public Card readCardData() throws EmptyException {
	if(card == null)
	    throw new EmptyException();

	return card;
    }

    /**
     * Allows a card to be loaded into the slot without causing events to be
     * announced.
     * 
     * @param card
     * 		  the card loaded into the card slot without events.
     */
    public void loadWithoutEvents(Card card) {
	this.card = card;
    }

    /**
     * An event that notifies listeners a card has been inserted into the card slot.
     */
    private void notifyCardInserted() {
	Class<?>[] parameterTypes = new Class<?>[] { CardSlot.class };
	Object[] args = new Object[] { this };
	notifyListeners(CardSlotListener.class, "cardInserted", parameterTypes, args);
    }

    /*
     * An event that notifies listeners a card has been ejected from the card slot.
     */
    private void notifyCardEjected() {
	Class<?>[] parameterTypes = new Class<?>[] { CardSlot.class };
	Object[] args = new Object[] { this };
	notifyListeners(CardSlotListener.class, "cardEjected", parameterTypes, args);
    }
}
