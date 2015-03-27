package business.selection_delivery;

/*
 * The Selection Controller is the abstract shell for the CodeSelectionController
 * and ButtonSelectionController (Need to ask Liam about TouchScreenController)
 * 
 * REQUIRES: Instantiate this as either a code or button selection controller 
 * 				ex: selectionController = new CodeSelectionController();
 */
public abstract class SelectionController {
	
	//protected AbstractVendingMachine machine;
	//protected FundsController funds;
	//protected InventoryController inventory;
	
	/*
	 * REQUIRES: Nothing
	 * 
	 * PROMISES: A pop will be dispensed if funds are sufficient
	 */
	public void dispenseProduct(){
		
	}

}
