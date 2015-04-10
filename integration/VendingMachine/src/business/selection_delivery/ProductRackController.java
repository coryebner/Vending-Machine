package business.selection_delivery;

import java.security.InvalidParameterException;

import SDK.logger.Logger;
import SDK.rifffish.Rifffish;
import SDK.rifffish.Stockout;
import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.products.Product;
import hardware.racks.ProductRack;
import hardware.racks.ProductRackListener;

/**
 * @class ProductRackController
 * 
 * Manages the product racks of the vending machine through containing information of
 * each product rack and listening for product rack events.
 */
public class ProductRackController implements ProductRackListener
{
	private ProductRack rack;	//Connected rack
	
	private int productCount;
	private int cost;
	private String name;
	private int productID;
	private Rifffish rifffish; // = new Rifffish("rsh_3wL4MyhWW4z3kfjoYfyN0gtt");
	private Logger logger;
	private SDK.rifffish.Product product;
	
	private static int nextValidID = 0;

	/**
	 * Sets up the product rack controller with the initial state of the machine.
	 * 
	 * @param pr		The product rack
	 * @param n		 	The name of the product
	 * @param c			The product cost
	 * @param quantity 	The initial quantity of products in the racks
	 * @param r 		A reference to the rifffish database
	 * @param l 		A reference to the logger
	 * @param machineID The current machine's id
	 */
	public ProductRackController(ProductRack pr, String n, int c, int quantity, Rifffish r, Logger l, int machineID)
	{//Remember and register to the pop can rack that this manager is responsible for and get the values.
		if(pr == null)
			throw new InvalidParameterException();
		
		rack = pr;
		pr.register(this);	//Register to appropriate pop can rack.
		
		refillQuantity(quantity);
		
		name = n;
		cost = c;
		productID = nextValidID++;
		
		rifffish = r;
		logger = l;
		
		if (rifffish != null) {
			product = rifffish.createProduct(new SDK.rifffish.Product(machineID, name, cost, quantity, rack.getMaxCapacity()));
			productID = product.getId();
		}
	}
	
	/**
	 * Creates the product rack controller with no initial setup.
	 */
	//Default constructor
	public ProductRackController()
	{//Starts off empty upon construction with 0 pops.
		productCount = 0;
	}

	/**
	 * Things that do stuff
	 */
	
	
	/**
	 * Refills the product rack to full
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
	
	
	/**
	 * Refills the product rack with the given quantity
	 * 
	 * @param quantity	The number of products to be added
	 */
	public void refillQuantity(int quantity)
	{
		int maxFill = this.getCapacity() - this.getCount();		//The max number of additional pops we can add
		
		if (quantity > maxFill)	//If we are trying to refill more than the rack is holding, cap it.
			quantity = maxFill;
		
		
		for (int j = 0 ; j < quantity; j++ )
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
	
	/**
	 * Returns the product rack
	 * 
	 * @return 		The product rack
	 */
	public ProductRack getRack()
	{//Returns the ProductRack.
		return rack;
	}
	
	/**
	 * Returns the stock of the rack
	 * 
	 * @return 			The number of products in the rack
	 */
	public int getCount()
	{//Return the amount of product.
		return productCount;
	}
	
	/**
	 * Returns the cost of the rack
	 * 
	 * @return 			The cost of the product in the rack
	 */
	public int getCost()
	{//Return the product cost.
		return cost;
	}
	
	/**
	 * Returns the capacity of the rack
	 * 
	 * @return 			The capacity of the rack
	 */
	public int getCapacity()
	{//Return the rack's capacity.
		return rack.getMaxCapacity();
	}

	/**
	 * Returns the name of the rack
	 * 
	 * @return 		The name of the product in the rack
	 */
	public String getName()
	{//Return the name of the product.
		return name;
	}
	
	/**
	 * Returns the productID of the rack
	 * 
	 * @return 			The productID of the rack
	 */
	public int getProductID()
	{//Return the ID of a product.
		return productID;
	}
	
	/**
	 * Returns true is the rack is full and false otherwise
	 * 
	 * @return 			true if the rack is full, false otherwise
	 */
	public boolean isFull()
	{
		if (productCount == getCapacity())
			return true;
		else
			return false;
	}	
	
	/**
	 * Returns true is the rack is empty and false otherwise
	 * 
	 * @return 			true if the rack is empty, false otherwise
	 */
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
	
	/**
	 * Changes the cost of the products in the given rack
	 * 
	 * @param newCost		The new cost
	 */
	public void changePrice(int newCost)
	{//Return the product cost.
		cost = newCost;
		
		if (rifffish != null) {
			product.setPrice(cost);
			rifffish.updateProduct(product);
		}
	}
	
	/**
	 * Changes the name of the products in the given rack
	 * 
	 * @param newName		The new name
	 */
	public void changeName(String newName)
	{//Change the name of the product.
		name = newName;
		
		if (rifffish != null) {
			product.setName(name);
			rifffish.updateProduct(product);
		}
	}
	
//	public void changeProductID(int newID)
//	{//Change the Id of a product.
//		productID = newID;
//		
//		//Update Database
//		SDK.rifffish.Product p = logger.getProduct(productID);
//		p.setId(newID);
//		logger.updateProduct(p);
//	}
	
//	public void changeProductID()
//	{
//		if (rifffish != null) {
//			
//		}
//		else {
//			
//		}
//	}
	
	
	/**
	 * Listeners
	 */

	/**
	 * Listens for a product being added to the rack and updates
	 * the stock values accordingly. Updates the database if database 
	 * is being used.
	 * 
	 * @param arg0		The product rack
	 * @param arg1		The product that was added
	 */
	@Override
	public void productAdded(ProductRack arg0, Product arg1)
	{//Add a pop to the count
		if (productCount < rack.getMaxCapacity())
			productCount++;
		
		if (rifffish != null) {
			product.setCurrentStockLevel(productCount);
			rifffish.updateProduct(product);
		}
	}

	/**
	 * Listens for a rack signaling that it is empty, sets the productCount
	 * to 0, and logs this in the database.
	 * 
	 * @param arg0		The product rack
	 */
	@Override
	public void productEmpty(ProductRack arg0)
	{//Rack is empty. Just set it to zero.
		productCount = 0;
		
		logger.log(new Stockout(productID, Rifffish.StockoutTypes.OUTOFSTOCK));
	}

	/**
	 * Listens for a rack signaling that it is full, sets the productCount
	 * to the racks capacity, and updates the database if the database is
	 * available.
	 * 
	 * @param arg0		The product rack
	 */
	@Override
	public void productFull(ProductRack arg0)
	{//Rack is full. Set it right to max.
		productCount = rack.getMaxCapacity();
		if (rifffish != null) {
			product.setCurrentStockLevel(productCount);
			rifffish.updateProduct(product);
		}
	}

	/**
	 * Listens for a product being removed from the rack and updates
	 * the stock values accordingly. Updates the database if database 
	 * is being used.
	 * 
	 * @param arg0		The product rack
	 * @param arg1		The product that was removed
	 */
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
