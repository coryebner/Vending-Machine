package business.selection_delivery;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.products.Product;
import hardware.racks.AbstractRack;
import hardware.racks.PopCanRack;
import hardware.racks.ProductRack;
import hardware.racks.ProductRackListener;


public class ProductRackController implements ProductRackListener
{
	private ProductRack rack;	//Connected rack
	
	private int productCount;
	private int cost;
	private String name;
	
	public ProductRackController(ProductRack pr, String n, int c, int quantity)
	{//Remember and register to the pop can rack that this manager is responsible for and get the values.
		rack = pr;
		pr.register(this);	//Register to appropriate pop can rack.
		
		refillQuantity(quantity);
		
		name = n;
		cost = c;
	}
	
	//Default constructor
	public ProductRackController()
	{//Starts off empty upon construction with 0 pops.
		productCount = 0;
	}

	/**
	 * Things that do stuff
	 */
	public void refill()
	{
		int popNeeded = getCapacity() - productCount;
		
		for (int i = 0; i < popNeeded; i++)
		{
			try {
				rack.addProduct(new Product());
			} catch (CapacityExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DisabledException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void refillQuantity(int quantity)
	{
		productCount = quantity;
		
		for (int j = 0 ; j <= quantity; j++ )
		{
			try{
			rack.addProduct(new Product());
			} catch (CapacityExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DisabledException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Getters
	 */
	public ProductRack getRack()
	{//Returns the ProductRack.
		return rack;
	}
	
	public int getCount()
	{//Return the amount of product.
		return productCount;
	}
	
	public int getCost()
	{//Return the product cost.
		return cost;
	}
	
	public int getCapacity()
	{//Return the rack's capacity.
		return rack.getMaxCapacity();
	}

	public String getName()
	{//Return the name of the product.
		return name;
	}
	
	public boolean isFull()
	{
		if (productCount == getCapacity())
			return true;
		else
			return false;
	}	
	
	public boolean isEmpty()
	{
		if (productCount == 0)
			return true;
		else
			return false;
	}	
	
	
	/**
	 * Setters
	 */
	public void changePrice(int newCost)
	{//Return the product cost.
		cost = newCost;
	}
	
	public void changeName(String newName)
	{//Change the name of the product.
		name = newName;
	}
	
	
	/**
	 * Listeners
	 */

	@Override
	public void productAdded(ProductRack arg0, Product arg1)
	{//Add a pop to the count
		if (productCount < rack.getMaxCapacity())
			productCount++;
	}

	@Override
	public void productEmpty(ProductRack arg0)
	{//Rack is empty. Just set it to zero.
		productCount = 0;
	}

	@Override
	public void productFull(ProductRack arg0)
	{//Rack is full. Set it right to max.
		productCount = rack.getMaxCapacity();
	}

	@Override
	public void productRemoved(ProductRack arg0, Product arg1)
	{//A pop has been removed. If not already empty, decrement the count.
		if (productCount > 0)
			productCount--;
	}

	/**
	 * Things that I do not care about
	 */
	@Override
	public void disabled(AbstractHardware<AbstractHardwareListener> arg0) {}

	@Override
	public void enabled(AbstractHardware<AbstractHardwareListener> arg0) {}
}
