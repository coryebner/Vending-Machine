package business.selection_delivery;

import java.security.InvalidParameterException;

import business.funds.FundsController;
import business.stub.DisplayController;
import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.exceptions.EmptyException;


/*
 * The Selection Controller is the abstract shell for the CodeSelectionController
 * and ButtonSelectionController
 * 
 * REQUIRES: Instantiate this as either a code or button selection controller 
 * 				ex: selectionController = new CodeSelectionController();
 */
public abstract class SelectionController extends
	AbstractController<SelectionControllerListener> {

	protected InventoryController inventory;
	protected DisplayController display;
	protected FundsController funds;
	
	public SelectionController(InventoryController inv, DisplayController disp, FundsController f)
	{
		if(inv == null || disp == null || f == null)
			throw new InvalidParameterException();
		
		inventory = inv;
		display = disp;
		funds = f;
	}
	
	/*
	 * REQUIRES: The rack index
	 * 
	 * PROMISES: A pop will be dispensed if funds are sufficient
	 */
	protected void dispense(int index)
	{
		try {
			inventory.getRack(index).dispenseProduct();
		}
		catch (CapacityExceededException e) {
			display.setDisplay("The coin receptacles are full", 5000);
		}
		catch (DisabledException e) {
			display.setDisplay("Product dispensing functions are disabled", 5000);	
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
