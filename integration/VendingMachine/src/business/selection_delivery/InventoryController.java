package business.selection_delivery;

import java.security.InvalidParameterException;

import hardware.racks.ProductRack;
import SDK.logger.Logger;
import SDK.rifffish.Rifffish;
import business.config.ConfigurationListener;


/**
 * @class InventoryController
 * 
 * Manages the inventory of the vending machine through containing information of
 * each product rack and listening for configuration events.
 */
public class InventoryController implements ConfigurationListener
{
	public ProductRackController racks[];	//Connected rack managers
	private int rackCount;
	
	/**
	 * Sets up the inventory controller with the initial state of the machine.
	 * Array items must come in order of the racks.
	 * 
	 * @param pr		An array of product racks
	 * @param numRacks 	The number of product racks
	 * @param names		An array of the product names
	 * @param costs		An array of product costs
	 * @param quantity 	An array of initial quantities of products in the racks
	 * @param r 		A reference to the rifffish database
	 * @param l 		A reference to the logger
	 * @param machineID The current machine's id
	 */
	public InventoryController(ProductRack[] pr, int numRacks, String[] names, int[] costs, int [] quantity, Rifffish r, Logger l, int machineID)
	{
		if(pr == null || names == null || costs == null || quantity == null)
			throw new InvalidParameterException();
		
		rackCount = numRacks;
	
		racks = new ProductRackController[rackCount];
		for (int i = 0; i < rackCount; i++)
		{//Create managers for each of the racks
			racks[i] = new ProductRackController(pr[i], names[i], costs[i], quantity[i], r, l, machineID);
		}
		
	}
	
	/**
	 * Creates the inventory controller with no initial setup.
	 */
	public InventoryController()
	{
		racks = null;
		rackCount = 0;
	}

	/**
	 * Things that do stuff
	 */
	
	/**
	 * Refills all product racks to full
	 */
	public void refillAll()
	{//Refill all the racks.
		for (int i = 0; i < rackCount; i++)
		{
			racks[i].refill();
		}
	}
	
	/**
	 * Refills the specified product rack with the given quantity
	 * 
	 * @param index		The index of the rack to be filled
	 * @param quantity	The number of products to be added
	 */
	public void refillQuantity(int index, int quantity)
	{
		racks[index].refillQuantity(quantity);
	}
	
	/**
	 * Refills the specified product rack to full
	 * 
	 * @param index		The index of the rack to be filled
	 */
	public void refillRack(int index)
	{//Refill the rack at the index number.
		racks[index].refill();
	}
	
	/**
	 * Getters
	 */
	
	/**
	 * Returns the product rack at the given index
	 * 
	 * @param index		The index of the rack
	 * 
	 * @return 			The product rack at the given index
	 */
	public ProductRack getRack(int index)
	{//Returns the ProductRack at index.
		return racks[index].getRack();
	}
	
	/**
	 * Gets the current number of product racks in the machine
	 * 
	 * @return 			The number of product racks in the machine
	 */
	public int getRackCount()
	{
		return rackCount;
	}
	
	/**
	 * Returns the stock of the rack at the given index
	 * 
	 * @param index		The index of the rack
	 * 
	 * @return 			The number of products in the rack
	 */
	public int getCount(int index)
	{//Return the amount of product for the rack at the index number.
		return racks[index].getCount();
	}
	
	/**
	 * Returns the cost of the rack at the given index
	 * 
	 * @param index		The index of the rack
	 * 
	 * @return 			The cost of products in the rack
	 */
	public int getCost(int index)
	{//Return the product cost for the rack at the index number.
		return racks[index].getCost();
	}
	
	/**
	 * Returns the capacity of the rack at the given index
	 * 
	 * @param index		The index of the rack
	 * 
	 * @return 			The capacity of the rack
	 */
	public int getCapacity(int index)
	{//Return the rack's capacity for the rack at the index number.
		return racks[index].getCapacity();
	}
	
	/**
	 * Returns the name of the rack at the given index
	 * 
	 * @param index		The index of the rack
	 * 
	 * @return 			The name of products in the rack
	 */
	public String getName(int index)
	{//Return the name of the product for the rack at the index number.
		return racks[index].getName();
	}
	
	/**
	 * Returns the productID of the rack at the given index
	 * 
	 * @param index		The index of the rack
	 * 
	 * @return 			The productID of products in the rack
	 */
	public int getProductID(int index)
	{//Return the productID of the product for the rack at the index number.
		return racks[index].getProductID();
	}
	
	/**
	 * Returns true is the rack is full and false otherwise
	 * 
	 * @param index		The index of the rack
	 * 
	 * @return 			true if the rack is full, false otherwise
	 */
	public boolean isFull(int index)
	{//Return whether the rack at the index number is full.
		return racks[index].isFull();
	}	
	
	/**
	 * Returns true is the rack is empty and false otherwise
	 * 
	 * @param index		The index of the rack
	 * 
	 * @return 			true if the rack is empty, false otherwise
	 */
	public boolean isEmpty(int index)
	{//Return whether the rack at the index number is empty.
		return racks[index].isEmpty();
	}	
	

	/**
	 * Setters
	 */
	
	/**
	 * Changes the cost of the products in the given rack
	 * 
	 * @param index		The index of the rack
	 * @param cost		The new cost
	 */
	public void changePrice(int index, int cost)
	{//Change the price of a product.
		racks[index].changePrice(cost);
	}
	
	/**
	 * Changes the name of the products in the given rack
	 * 
	 * @param index		The index of the rack
	 * @param name		The new name
	 */
	public void changeName(int index, String newName)
	{//Change the name of the product for rack at index.
		racks[index].changeName(newName);
	}
//	
//	public void changeProductID(int index, int productID)
//	{//Change the ID of a product.
//		racks[index].changeProductID(productID);
//	}
	
	/**
	 * Listeners
	 */
	
	/**
	 * Listens for a name change of a product rack and then makes the
	 * change in the inventory.
	 * 
	 * @param index		The index of the rack
	 * @param newName   The new name
	 */
	@Override
	public void nameChanged(int index, String newName)
	{//Config has told us a name has changed. Change it.
		changeName(index, newName);
	}
	
	/**
	 * Listens for a price change of a product rack and then makes the
	 * change in the inventory.
	 * 
	 * @param index		The index of the rack
	 * @param newPrice  The new price
	 */
	@Override
	public void priceChanged(int index, int newPrice)
	{//Config has told us a price has changed. Change it.
		changePrice(index, newPrice);
	}
}