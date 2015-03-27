package business.selection_delivery;

/*
 * Handles events involving code selection for purchasing a product
 */
public class CodeSelectionController extends SelectionController /*implements PushButtonCodeInterpreterListener*/{
	
	/*
	 * REQUIRES: The hardware
	 * 			 The funds controller
	 * 		 	 The inventory controller
	 * 			 
	 * PROMISES:  To setup the CodeSelectionController
	 */
	public CodeSelectionController(/*AbstractVendingMachine m, FundsController funds, InventoryController inventory*/){
		
	}

	/*
	 * Event triggered when PushButtonCodeInterpreter is selected
	 */
	public void codeEntered(String code/*, PushButtonCodeInterpreter interpreter*/){
		
	}

}
