package business.selection_delivery;

<<<<<<< HEAD
import com.vendingmachinesareus.AbstractHardware;
import com.vendingmachinesareus.AbstractHardwareListener;
// TODO: Should the rest of these use hardware.* instead of com.vendingmachinesareus.*?
// The exceptions thrown by the hardware are of the hardware.* type, which is why I had
//  to remove these and import the hardware versions... this seems like something that
//  should probably be consistent - Liam Mar 31
//import com.vendingmachinesareus.DisabledException;
//import com.vendingmachinesareus.EmptyException;
import com.vendingmachinesareus.PushButton;
import com.vendingmachinesareus.PushButtonListener;

import business.stub.*;

import hardware.exceptions.EmptyException;
import hardware.exceptions.DisabledException;
=======
//Imports for logging
import productController.MockDisplayController;
import productController.MockFundsController;
//import rifffish.Error;
//import rifffish.Rifffish;
//import rifffish.Rifffish.PaymentMethod;
//import rifffish.Transaction;
import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.exceptions.DisabledException;
import hardware.exceptions.EmptyException;
import hardware.ui.PushButton;
import hardware.ui.PushButtonListener;


>>>>>>> Ok actually adding changes now

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
<<<<<<< HEAD
	public ButtonSelectionController(InventoryManager inv, DisplayController disp, FundsController f, PushButton[] butts, int numButts)
=======
	public ButtonSelectionController(InventoryController inv, MockDisplayController disp, MockFundsController f, PushButton[] butts, int numButts)
>>>>>>> Ok actually adding changes now
	{
		super(inv, disp, f);
		
		buttons = butts;
		numButtons= numButts;
		
<<<<<<< HEAD
		for (int i = 0; i < numButts; ++i)
		{//Register the buttons.
			buttons[i].register(this);
		}
=======
		/* Configuration is handling this.
		for (int i = 0; i < numButts; ++i)
		{//Register the buttons.
			buttons[i].register(this);
		}*/
>>>>>>> Ok actually adding changes now
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
<<<<<<< HEAD
			display.setDisplay("Error: Invalid code", 5000);
=======
			notifyInvalidSelection();
			//display.setDisplay("Error: Invalid selection", 5000);
>>>>>>> Ok actually adding changes now
			return;
		}
		
		if (inventory.isEmpty(index))
		{//We are out of stock. Output message and leave function.
<<<<<<< HEAD
			display.setDisplay("The product selected is empty", 5000);
			return;
		}
			
		if (funds.conductTransaction(cost))
=======
			notifyEmptySelection();
			//display.setDisplay("The product selected is empty", 5000);
			return;
		}
		
		boolean pass = funds.conductTransaction(cost);
//		Rifffish logger = new Rifffish("rsh_3wL4MyhWW4z3kfjoYfyN0gtt");
//		Error e = logger.log(new Transaction(1, PaymentMethod.COIN, true));
//			
		if (pass)
>>>>>>> Ok actually adding changes now
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
<<<<<<< HEAD
			display.setDisplay("Insufficient funds for product: $"
														+ Double.toString( cost / 100)
														+ " required"
														, 4000);
=======
			notifyInsufficientFunds();
			/*display.setDisplay("Insufficient funds for product: $"
														+ Double.toString( cost / 100)
														+ " required"
														, 4000);*/
>>>>>>> Ok actually adding changes now
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
<<<<<<< HEAD
=======

>>>>>>> Ok actually adding changes now
}
