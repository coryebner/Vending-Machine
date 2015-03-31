package business.selection_delivery;

import com.vendingmachinesareus.CapacityExceededException;
import com.vendingmachinesareus.DisabledException;
import com.vendingmachinesareus.EmptyException;

import business.stub.DisplayController;
import business.stub.FundsController;
/*
 * The Selection Controller is the abstract shell for the CodeSelectionController
 * and ButtonSelectionController (Need to ask Liam about TouchScreenController)
 * 
 * REQUIRES: Instantiate this as either a code or button selection controller 
 * 				ex: selectionController = new CodeSelectionController();
 */
public abstract class SelectionController {

	protected InventoryManager inventory;
	protected DisplayController display;
	protected FundsController funds;
	
	public SelectionController(InventoryManager inv, DisplayController disp, FundsController f)
	{
		inventory = inv;
		display = disp;
		funds = f;
	}
	
	/*
	 * REQUIRES: Nothing
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
			display.getDisplayController().setDisplay("The product selected is empty", 5000);
			if (!machine.getBalanceController().returnFunds(machine.getProductRack(index).getCost())) {
				machine.getDisplayController().setDisplay("Unable to return funds - please contact support", 5000);
			}*/
		}
	}


}
