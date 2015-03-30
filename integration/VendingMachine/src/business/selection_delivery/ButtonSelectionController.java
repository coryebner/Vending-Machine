package business.selection_delivery;

import com.vendingmachinesareus.AbstractHardware;
import com.vendingmachinesareus.AbstractHardwareListener;
import com.vendingmachinesareus.DisabledException;
import com.vendingmachinesareus.EmptyException;
import com.vendingmachinesareus.PushButton;
import com.vendingmachinesareus.PushButtonListener;


/**
 * @class PopSelectionController
 * 
 * Implementation of SelectionController for pop vending machines. Listens
 *  for pressed() events from the PushButtonListener, then selects the correct
 *  product based on the button pressed.
 */
public class ButtonSelectionController
	extends SelectionController
	implements PushButtonListener
{
	private PushButton[] buttons;
	private int numButtons;
	
	/**
	 * Constructor
	 * 
	 * Registers us with the PopVendingMachine's PushButtons to listen for
	 *  pressed() events.
	 */
	public ButtonSelectionController(InventoryManager inv, DisplayController disp, FundsController f, PushButton[] butts, int numButts)
	{
		super(inv, disp, f);
		
		buttons = butts;
		numButtons= numButts;
		
		for (int i = 0; i < numButts; ++i)
		{//Register the buttons.
			buttons[i].register(this);
		}
	}
	
	/**
	 * pressed() - listens for a selection button press, then selects the
	 *  correct product based on the button's index and dispenses it.
	 *  
	 *  @param button			button that generated the event
	 */
	public void pressed(PushButton button) //Amy: This is sent a string in hardware now ; Jon: In the GUI anyway. As long as hardware still sends us the button it's fine.
	{
		int index = getIndex(button);
		int cost = inventory.getCost(index);

		if (index == -1)
		{//Index of -1 is thrown by getIndex as an error.
			display.setDisplay("Error: Invalid code", 5000);
			return;
		}
		
		if (inventory.isEmpty(index))
		{//We are out of stock. Output message and leave function.
			display.setDisplay("The product selected is empty", 5000);
			return;
		}
			
		if (funds.conductTransaction(cost))
		{//If we can afford paying
			dispense(index);

			try
			{//Attempt to eject the card after a transaction.
				funds.getCardSlot().ejectCard();
			}
			catch (EmptyException | DisabledException e)
			{
				//Catch block. There might not have been a card.
				//We don't care. We always try anyway. Does not matter if it does not work.
			}
		}
		else
		{//We cannot afford to pay
			display.setDisplay("Insufficient funds for product: $"
														+ Double.toString( cost / 100)
														+ " required"
														, 4000);
		}
	}
	
	/**
	 * getIndex() - translates a button into an integer product index
	 * 
	 * @param button			button pressed
	 * @return					index of product to dispense
	 */
	private int getIndex(PushButton button)
	{
		for (int i = 0; i < numButtons; i++)
		{
			if (buttons[i] == button)
			{//If the button that was pressed matches the reference in our array of buttons, return that index.
				return i;
			}
		}

		return -1; //returns -1 if button to pop # search was unsuccessful
	}

	/**
	 * Messages we don't care about - ignore them!
	 */
	@Override
	public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {}
	@Override
    public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {}
}
