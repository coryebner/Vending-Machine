package business.selection_delivery;

import business.stub.DisplayController;
import business.stub.FundsController;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
// TODO: Changed these like the ones in ButtonSelectionController - the same comments
//  apply here I suppose - Liam Mar 31
//import com.vendingmachinesareus.DisabledException;
//import com.vendingmachinesareus.EmptyException;
import hardware.ui.PushButtonCodeInterpreter;
import hardware.ui.PushButtonCodeInterpreterListener;

import hardware.exceptions.DisabledException;
import hardware.exceptions.EmptyException;


/**
 * @class CodeSelectionController
 * 
 * Implementation of SelectionController for generic product vending machines. Listens
 *  for codeEntered ()events from the PushButtonInterpreterListener, and
 *  selects the correct product based on the code entered.
 */
public class CodeSelectionController
    extends SelectionController
    implements PushButtonCodeInterpreterListener
{
	private int offset;
	
	/**
	 * Constructor
	 * 
	 * Registers us with the CandyVendingMachine's PushButtonInterpreter to
	 *  listen for codeEntered() events.
	 */
	// TODO: Changed these from MockDisplayManager, MockFundsManager - should they have been?
	//  It wouldn't compile otherwise... - Liam Mar 31
	public CodeSelectionController(InventoryController inv, DisplayController disp, FundsController f, PushButtonCodeInterpreter interp, int off)
	{
		super(inv, disp, f);
		//interp.register(this); //Amy: Doesn't configuration handle registering listeners? Jon: Yes they do apparently.
		
		offset = off;
	}

	/**
	 * codeEntered - listens for a valid code entered into the
	 *  PushButtonCodeInterpreter, then translates the code to a product index
	 *  and dispenses the appropriate product.
	 *  
	 *  @param code			entered push button code (eg. "A0", "F7")
	 *  @param interpreter	interpreter which received the code
	 */
	@Override
	public void codeEntered(String code, PushButtonCodeInterpreter interpreter) {
		int index = productIndex(code, offset);
		int cost = inventory.getCost(index);
		
		if (index == -1)
		{//Index of -1 is thrown by getIndex as an error.
			notifyInvalidSelection();
			//display.setDisplay("Error: Invalid code", 5000);
			return;
		}
		
		if (inventory.isEmpty(index))
		{//We are out of stock. Output message and leave function.
			notifyEmptySelection();
			//display.setDisplay("The product selected is empty", 5000);
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
			notifyInsufficientFunds();
			/*display.setDisplay("Insufficient funds for product: $"
														+ Double.toString( cost / 100)
														+ " required"
														, 4000);*/
		}
	}
	
	/**
	 * productIndex - translates a product code (eg. "A0", "F7") into an index
	 *  into the machine's product racks.
	 *  
	 * @param code			code to interpret
	 * @return				product index for dispense()
	 */
	private int productIndex(String code, int offset)
	{
		/**
		 * Parse the parts of the string code into chars.
		 * */
		char letter, number;
		letter = code.charAt(0);
		number = code.charAt(1);
		
		/**
		 * Assigning index value
		 * */
		int index = -1;
		
		index = offset;					//Start at offset, i.e. PushButtons are taking up a number of indices.
		index += (letter - 65) * 10;	//The letter decides the first digit of the index
		index += (number - 48);			//The number decides the second digit of the index
		
		/**
		 * Error Checking
		 * */
		if (code.length() != 2)
			return -1;	//If the code is not two chars long, something is terribly wrong.
		
		if (letter < 65 || letter > 70)
			return -1;	//First part of code must be an uppercase letter.
		
		if (number < 48 || number > 57)
			return -1;	//Second part of code must be a number.
		
		if (index < 0 || index >= inventory.getRackCount())
			return -1;	//If index is out of range, something is terribly wrong.
		
		return index;
	}
	
	/**
	 * Messages we don't care about - just ignore them!
	 */
	@Override
	public void disabled(AbstractHardware<AbstractHardwareListener> arg0) {}

	@Override
	public void enabled(AbstractHardware<AbstractHardwareListener> arg0) {}

}
