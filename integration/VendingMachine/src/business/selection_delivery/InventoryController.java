package business.selection_delivery;

import java.security.InvalidParameterException;

import hardware.racks.ProductRack;
import SDK.logger.Logger;
import SDK.rifffish.Rifffish;
import business.config.ConfigurationListener;

public class InventoryController implements ConfigurationListener
{
	public ProductRackController racks[];	//Connected rack managers
	private int rackCount;
	
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
	
	//Default constructor
	public InventoryController()
	{
		racks = null;
		rackCount = 0;
	}

	/**
	 * Things that do stuff
	 */
	public void refillAll()
	{//Refill all the racks.
		for (int i = 0; i < rackCount; i++)
		{
			racks[i].refill();
		}
	}
	
	public void refillQuantity(int index, int quantity)
	{
		racks[index].refillQuantity(quantity);
	}
	
	public void refillRack(int index)
	{//Refill the rack at the index number.
		racks[index].refill();
	}
	
	/**
	 * Getters
	 */
	public ProductRack getRack(int index)
	{//Returns the ProductRack at index.
		return racks[index].getRack();
	}
	
	public int getRackCount()
	{
		return rackCount;
	}
	
	public int getCount(int index)
	{//Return the amount of product for the rack at the index number.
		return racks[index].getCount();
	}
	
	public int getCost(int index)
	{//Return the product cost for the rack at the index number.
		return racks[index].getCost();
	}
	
	public int getCapacity(int index)
	{//Return the rack's capacity for the rack at the index number.
		return racks[index].getCapacity();
	}
	
	public String getName(int index)
	{//Return the name of the product for the rack at the index number.
		return racks[index].getName();
	}
	
	public int getProductID(int index)
	{//Return the productID of the product for the rack at the index number.
		return racks[index].getProductID();
	}
	
	public boolean isFull(int index)
	{//Return whether the rack at the index number is full.
		return racks[index].isFull();
	}	
	
	public boolean isEmpty(int index)
	{//Return whether the rack at the index number is empty.
		return racks[index].isEmpty();
	}	
	

	/**
	 * Setters
	 */
	public void changePrice(int index, int cost)
	{//Change the price of a product.
		racks[index].changePrice(cost);
	}
	
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
	
	@Override
	public void nameChanged(int index, String newName)
	{//Config has told us a name has changed. Change it.
		changeName(index, newName);
	}
	
	@Override
	public void priceChanged(int index, int newPrice)
	{//Config has told us a price has changed. Change it.
		changePrice(index, newPrice);
	}
}