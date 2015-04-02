package business.selection_delivery;

import com.vendingmachinesareus.CapacityExceededException;
import com.vendingmachinesareus.DisabledException;
import com.vendingmachinesareus.EmptyException;

import business.stub.DisplayController;
import business.stub.FundsController;
/*
 * The Selection Controller is the abstract shell for the CodeSelectionController
 * and ButtonSelectionController (Need to ask Liam about TouchScreenController)
=======
import productController.MockDisplayController;
import productController.MockFundsController;
=======
>>>>>>> 9f472b29aae6c803798f38707652c2aa8e5426d0
import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.exceptions.EmptyException;


<<<<<<< HEAD
/*
 * The Selection Controller is the abstract shell for the CodeSelectionController
 * and ButtonSelectionController
>>>>>>> Ok actually adding changes now
=======
import business.stub.DisplayController;
import business.stub.FundsController;
/*
 * The Selection Controller is the abstract shell for the CodeSelectionController
 * and ButtonSelectionController
>>>>>>> 9f472b29aae6c803798f38707652c2aa8e5426d0
 * 
 * REQUIRES: Instantiate this as either a code or button selection controller 
 * 				ex: selectionController = new CodeSelectionController();
 */

public abstract class SelectionController {

	protected InventoryManager inventory;
	protected DisplayController display;
	protected FundsController funds;
	
	public SelectionController(InventoryManager inv, DisplayController disp, FundsController f)

public abstract class SelectionController extends
	AbstractController<SelectionControllerListener> {

	protected InventoryController inventory;
	protected MockDisplayController display;
	protected MockFundsController funds;
	
	public SelectionController(InventoryController inv, MockDisplayController disp, MockFundsController f)

	protected DisplayController display;
	protected FundsController funds;
	
	public SelectionController(InventoryController inv, DisplayController disp, FundsController f)
	{
		inventory = inv;
		display = disp;
		funds = f;
	}
	
	/*
	 * REQUIRES: Nothing
	 * REQUIRES: The rack index
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
		catch (EmptyException e){ 
		/*
			It shouldn't ever have to reach this catch block.
			It shouldn't ever have to reach this catch block.
			display.getDisplayController().setDisplay("The product selected is empty", 5000);
			if (!machine.getBalanceController().returnFunds(machine.getProductRack(index).getCost())) {
				machine.getDisplayController().setDisplay("Unable to return funds - please contact support", 5000);
			}*/
		}
	}

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
		Object[] args = new Object[] { this };
		notifyListeners(SelectionControllerListener.class, "emptySelection", parameterTypes, args);
	}
	
	protected void notifyInvalidSelection()
	{
		Class<?>[] parameterTypes =
		        new Class<?>[] { };
		Object[] args = new Object[] { this };
		notifyListeners(SelectionControllerListener.class, "invalidSelection", parameterTypes, args);
	}
	
	protected void notifyInsufficientFunds()
	{
		Class<?>[] parameterTypes =
		        new Class<?>[] { };
		Object[] args = new Object[] { this };
		notifyListeners(SelectionControllerListener.class, "insufficientFunds", parameterTypes, args);
	}
}
