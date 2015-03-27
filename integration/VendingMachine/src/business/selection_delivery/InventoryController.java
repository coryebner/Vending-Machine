package business.selection_delivery;

/*
 * The InventoryController keeps track of all information pertaining 
 * to the product racks. 
 */
public class InventoryController /*implements PopCanRackListener*/ {
	
	private String names[];
	private int prices[];
	private int rackAmounts[];
	private int racks[]; //needs to be changed to type Rack
	
	/*
	 * REQUIRES: Arrays containing the prices and names of all products
	 * 			 An Array containing all the hardware racks
	 * 			 Arrays must be same length
	 * 
	 * PROMISES: To setup the inventory for the machine
	 */
	public InventoryController(int prodPrices[],String prodNames[] /*, prodRacks[]*/){
		names = prodNames;
		prices = prodPrices;
	}

	/*
	 * REQUIRES: The rack number
	 * 
	 * PROMISES: To get the quantity of product in the rack
	 */
	public int getQty(int rackNum){	
		return 0;
	}
	
	/*
	 * REQUIRES: The rack number
	 * 
	 * PROMISES: To check if the rack is full
	 */
	public boolean isFull(int rackNum){
		return false;
	}

	/*
	 * REQUIRES: The rack number
	 * 
	 * PROMISES: To check if the rack is empty
	 */
	public boolean isEmpty(int rackNum){
		return false;
	}

	/*
	 * REQUIRES: The rack number
	 * 
	 * PROMISES: To change the name of the product in a rack
	 */
	public void changeName(int rackNum){
		
	}

	/*
	 * REQUIRES: The rack number
	 * 
	 * PROMISES: To get the name of the product in a rack
	 */
	public String getName(int rackNum){
		return null;
	}

	/*
	 * REQUIRES: The rack number
	 * 
	 * PROMISES: To change the price of the product in a rack
	 */
	public void changePrice(int rackNum){
		
	}

	/*
	 * REQUIRES: The rack number
	 * 
	 * PROMISES: To get the price of the product in a rack
	 */
	public int getPrice(int rackNum){
		return 0;
	}
	
	/*
	 * REQUIRES: nothing
	 * 
	 * PROMISES: To refill all the racks in the machine
	 */
	public void refillAll(){
		
	}

	/*
	 * REQUIRES: The rack number
	 * 
	 * PROMISES: To refill the specified rack
	 */
	public void refillRack(int rackNum){
		
	}
}
