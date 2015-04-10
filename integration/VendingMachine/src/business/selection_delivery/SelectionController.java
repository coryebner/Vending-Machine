package business.selection_delivery;

import java.security.InvalidParameterException;

import business.funds.FundsController;
import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.exceptions.EmptyException;


/**
 * @class SelectionController
 * 
 * Abstract class that handles selection and product dispensing events
 * for the vending machine.
 */
public abstract class SelectionController extends
	AbstractController<SelectionControllerListener> {

	protected InventoryController inventory;
	protected FundsController funds;
	
	/**
	 * Sets up the selection controller.
	 * 
	 * @param inv		A reference to the inventory controller
	 * @param f 		a reference to the funds controller
	 */
	public SelectionController(InventoryController inv, FundsController f)
	{
		if(inv == null || f == null)
			throw new InvalidParameterException();
		
		inventory = inv;
		funds = f;
	}
	
	/**
	 * Dispenses a product at the given rack index. Handles any exception 
	 * that may occur.
	 * 
	 * @param index		The index of the rack
	 */
	protected void dispense(int index)
	{
		try {
			inventory.getRack(index).dispenseProduct();
		}
		catch (CapacityExceededException e) {
			//Amy: We should never be writing directly to display
			//display.setDisplay("The coin receptacles are full", 5000);
		}
		catch (DisabledException e) {
			//display.setDisplay("Product dispensing functions are disabled", 5000);	
		}
		catch (EmptyException e) {/*
			It shouldn't ever have to reach this catch block.
		
			display.getDisplayController().setDisplay("The product selected is empty", 5000);
			if (!machine.getBalanceController().returnFunds(machine.getProductRack(index).getCost())) {
				machine.getDisplayController().setDisplay("Unable to return funds - please contact support", 5000);
			}*/
			notifyEmptySelection();
		}
	}

	/**
	 * Notifications
	 */
	protected void notifyEmptySelection()
	{
		Class<?>[] parameterTypes =
		        new Class<?>[] { };
		Object[] args = new Object[] {};
		notifyListeners(SelectionControllerListener.class, "emptySelection", parameterTypes, args);
	}
	
	protected void notifyInvalidSelection()
	{
		Class<?>[] parameterTypes =
		        new Class<?>[] { };
		Object[] args = new Object[] {};
		notifyListeners(SelectionControllerListener.class, "invalidSelection", parameterTypes, args);
	}
	
	protected void notifyInsufficientFunds(int fundsRequired)
	{
		Class<?>[] parameterTypes =
		        new Class<?>[] {int.class};
		Object[] args = new Object[] {fundsRequired};
		notifyListeners(SelectionControllerListener.class, "insufficientFunds", parameterTypes, args);
	}
}
