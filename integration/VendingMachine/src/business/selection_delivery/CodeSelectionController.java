package business.selection_delivery;

import business.funds.FundsController;
import business.funds.TransactionReturnCode;
import business.stub.DisplayController;
import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.exceptions.DisabledException;
import hardware.exceptions.EmptyException;
import hardware.ui.PushButtonCodeInterpreter;
import hardware.ui.PushButtonCodeInterpreterListener;


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
	public CodeSelectionController(InventoryController inv, FundsController f, int off)
	{
		super(inv, f);
		
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
	public void codeEntered(String code, PushButtonCodeInterpreter arg1) {
		int index = productIndex(code, offset);
		int cost = inventory.getCost(index);
		int id = inventory.getProductID(index);

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
	 * productIndex - translates a product code (eg. "A0", "F7") into an index
	 *  into the machine's product racks.
	 *  
	 * @param code			code to interpret
	 * @return				product index for dispense()
	 */
	public int productIndex(String code, int offset) //made this public for testing purposes, was private
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
