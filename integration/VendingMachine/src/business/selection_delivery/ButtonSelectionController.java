package business.selection_delivery;

import java.security.InvalidParameterException;

import business.funds.FundsController;
import business.funds.TransactionReturnCode;
import business.stub.DisplayController;
import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.exceptions.DisabledException;
import hardware.exceptions.EmptyException;
import hardware.ui.PushButton;
import hardware.ui.PushButtonListener;



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
	public ButtonSelectionController(InventoryController inv, FundsController f, PushButton[] butts, int numButts)
	{
		super(inv, f);
		
		if(butts == null)
			throw new InvalidParameterException();
		
		buttons = butts;
		numButtons= numButts;
		
		/* Configuration is handling this.
		for (int i = 0; i < numButts; ++i)
		{//Register the buttons.
			buttons[i].register(this);
		}*/
	}
	
	/**
	 * pressed() - listens for a selection button press, then selects the
	 *  correct product based on the button's index and dispenses it.
	 *  
	 *  @param button			button that generated the event
	 */
	public void pressed(PushButton button)
	{
		int index = getIndex(button);
		int cost = inventory.getCost(index);
		int id = inventory.getProductID(index);

		if (index == -1)
		{//Index of -1 is thrown by getIndex as an error.
			notifyInvalidSelection();
			//display.setDisplay("Error: Invalid selection", 5000);
			return;
		}
		
		if (inventory.isEmpty(index))
		{//We are out of stock. Output message and leave function.
			notifyEmptySelection();
			//display.setDisplay("The product selected is empty", 5000);
			return;
		}	
			
		TransactionReturnCode transInfo = funds.ConductTransaction(id,cost);
		
		switch(transInfo){
			case SUCCESSFUL: 
				dispense(index);
				break;
			case UNSUCCESSFUL:
				break;
			case INSUFFICIENTFUNDS:
				notifyInsufficientFunds(cost);
				break;
			case DISABLED:				
				break;
			case TIMEOUT:		
				break;
			case CREDITCARDERROR:
				break;
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
