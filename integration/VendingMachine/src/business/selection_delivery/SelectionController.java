package business.selection_delivery;

<<<<<<< HEAD
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
import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.exceptions.EmptyException;


/*
 * The Selection Controller is the abstract shell for the CodeSelectionController
 * and ButtonSelectionController
>>>>>>> Ok actually adding changes now
 * 
 * REQUIRES: Instantiate this as either a code or button selection controller 
 * 				ex: selectionController = new CodeSelectionController();
 */
<<<<<<< HEAD
public abstract class SelectionController {

	protected InventoryManager inventory;
	protected DisplayController display;
	protected FundsController funds;
	
	public SelectionController(InventoryManager inv, DisplayController disp, FundsController f)
=======
public abstract class SelectionController extends
	AbstractController<SelectionControllerListener> {

	protected InventoryController inventory;
	protected MockDisplayController display;
	protected MockFundsController funds;
	
	public SelectionController(InventoryController inv, MockDisplayController disp, MockFundsController f)
>>>>>>> Ok actually adding changes now
	{
		inventory = inv;
		display = disp;
		funds = f;
	}
	
	/*
<<<<<<< HEAD
	 * REQUIRES: Nothing
=======
	 * REQUIRES: The rack index
>>>>>>> Ok actually adding changes now
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
<<<<<<< HEAD
=======
			It shouldn't ever have to reach this catch block.
		
>>>>>>> Ok actually adding changes now
			display.getDisplayController().setDisplay("The product selected is empty", 5000);
			if (!machine.getBalanceController().returnFunds(machine.getProductRack(index).getCost())) {
				machine.getDisplayController().setDisplay("Unable to return funds - please contact support", 5000);
			}*/
<<<<<<< HEAD
		}
	}


=======
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
>>>>>>> Ok actually adding changes now
}
