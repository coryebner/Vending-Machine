package business.selection_delivery;

import com.vendingmachinesareus.ProductRack;

public class InventoryManager{
	public ProductRackManager racks[];	//Connected rack managers
	private int rackCount;
	
	public InventoryManager(ProductRack[] pr, int rc, String[] names, int[] costs)
	{
		rackCount = rc;
		
		racks = new ProductRackManager[rackCount];
		for (int i = 0; i < rackCount; i++)
		{//Create managers for each of the racks
			racks[i] = new ProductRackManager(pr[i], names[i], costs[i]);
		}
		
	}
	
	//Default constructor
	public InventoryManager()
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
	{//Return the product cost.
		racks[index].changePrice(cost);
	}
	
	public void changeName(int index, String newName)
	{//Change the name of the product for rack at index.
		racks[index].changeName(newName);
	}
}