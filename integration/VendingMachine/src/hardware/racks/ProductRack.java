package hardware.racks;

import hardware.channels.ProductChannel;
import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.exceptions.EmptyException;
import hardware.exceptions.SimulationException;
import hardware.products.Product;

/**
 * ProductRack
 * 
 * @synopsis
 * 		Represents a Product rack :)
 * 
 * Note: To increase functionality of ProductRack - this is the best place to handle that.
 * 
 *@see AbstractRack 
 *
 */
public class ProductRack extends AbstractRack<ProductRackListener, Product, ProductChannel> {
	
	private String productName;
	private int productPrice;
	private String productImage;

	/**
	 * Explicit superclass constructor call to initialize the maximum capacity of the product rack
	 * 
	 * @param 	capacity
	 * @see		AbstractRack
	 */
	public ProductRack(int capacity) {
		super(capacity);
	}
	
	/**
	 * Constructor that initializes the product rack with a maximum capacity, a name, a price and an image.
	 * 
	 * @deprecated Product racks no longer store the name, price or image of a product.
	 * 
	 * @param capacity
	 * @param name
	 * @param price
	 * @param image
	 * 
	 * @see AbstractRack
	 */
	public ProductRack(int capacity, String name, int price, String image){
		super(capacity);
		productName = name;
		productPrice = price;
		productImage = image;
	}
	
	/**
	 * Standard setter that sets the name of the product assigned to this rack.
	 * 
	 * @deprecated Product racks no longer store the name, price or image of a product.
	 * 
	 * @param name
	 */
	public void setName(String name){
		productName = name;
	}
	
	/**
	 * Standard getter that returns the name of the product assigned to this rack.
	 * 
	 * @deprecated Product racks no longer store the name, price or image of a product.
	 */
	public String getName(){
		return productName;
	}
	
	/**
	 * Standard setter that sets the price of the product assigned to this rack.
	 * 
	 * @deprecated Product racks no longer store the name, price or image of a product.
	 * 
	 * @param price
	 */
	public void setPrice(int price){
		productPrice = price;
	}
	
	/**
	 * Standard getter that returns the price of the product assigned to this rack.
	 * 
	 * @deprecated Product racks no longer store the name, price or image of a product.
	 */
	public int getPrice(){
		return productPrice;
	}
	
	/**
	 * Standard setter that returns the image of the product assigned to this rack.
	 * 
	 * @deprecated Product racks no longer store the name, price or image of a product.
	 * 
	 * @param image A string that represents an image. NOTE: Is this a filepath or an actual image?
	 */
	public void setImage(String image){
		productImage = image;
	}
	
	/**
	 * Standard getter that returns the image of the product assigned to this rack.
	 * 
	 * @deprecated Product racks no longer store the name, price or image of a product.
	 */
	public String getImage(){
		return productImage;
	}

	/**
	 * Adds the indicated product to this product rack if there is sufficient
	 * space available. If the product is successfully added to this product
	 * rack, a "productAdded" event is announced to its listeners. If, as a result
	 * of adding this product, this product rack has become full, a "productFull"
	 * event is announced to its listeners.
	 * 
	 * @param product
	 *            The product to be added.
	 * @throws CapacityExceededException
	 *             if the product rack is already full.
	 * @throws DisabledException
	 *             if the product rack is currently disabled.
	 */
	public void addProduct(Product product) throws CapacityExceededException,
	DisabledException {

		addToRack(product);

		notifyProductAdded(product);

		if(getQueue().size() >= getMaxCapacity())
			notifyProductFull();
	}

	/**
	 * Causes one product to be removed from this product rack, to be placed in
	 * the output channel to which this product rack is connected. If a product
	 * is removed from this product rack, a "productRemoved" event is announced to
	 * its listeners. If the removal of the product causes this product rack to
	 * become empty, a "productEmpty" event is announced to its listeners.
	 * 
	 * @throws DisabledException
	 *             if this product rack is currently disabled.
	 * @throws EmptyException
	 *             if no products are currently contained in this product rack.
	 * @throws CapacityExceededException
	 *             if the output channel cannot accept the dispensed product.
	 */
	public void dispenseProduct() throws DisabledException, EmptyException,
	CapacityExceededException {

		Product product = removeFromRack();

		notifyProductRemoved(product);

		if(getSink() == null)
			throw new SimulationException("The output channel is not connected");

		getSink().acceptProduct(product);

		if(getQueue().isEmpty())
			notifyProductEmpty();
	}

	/**
	 * notifyProductAdded
	 * 
	 * Notification method used to custom tailor AbstractHardware associated reflection call
	 * - with the assumption that listeners registered to the affected ProductRack as defined in 
	 * ProductRackListener class will be notified (triggering a method invocation to productAdded).
	 * 
	 * @param product
	 * 
	 * @see	AbstractHardware, ProductRackListener, AbstractStub
	 */
	private void notifyProductAdded(Product product) {
		Class<?>[] parameterTypes =
				new Class<?>[] { ProductRack.class, Product.class };
		Object[] args = new Object[] { this, product };
		notifyListeners(ProductRackListener.class, "productAdded", parameterTypes, args);
	}

	/**
	 * notifyProductFull
	 * 
	 * 	Notification method used via AbstractHardware reflection API (notifyListeners)
	 *  to signal that the associated product rack is full of products.
	 * 
	 * 
	 * @see	AbstractHardware, ProductRackListener, AbstractStub
	 */
	private void notifyProductFull() {
		Class<?>[] parameterTypes =
				new Class<?>[] { ProductRack.class };
		Object[] args = new Object[] { this };
		notifyListeners(ProductRackListener.class, "productFull", parameterTypes, args);
	}

	/**
	 * notifyProductEmpty
	 * 
	 * 	Notification method used via AbstractHardware reflection API (notifyListeners)
	 * 	to signal that the associated product rack is empty of products.
	 * 
	 * 
	 * @see	AbstractHardware, CoinRackListener, CoinRackListenerStub, AbstractStub
	 */
	private void notifyProductEmpty() {
		Class<?>[] parameterTypes =
				new Class<?>[] { ProductRack.class };
		Object[] args = new Object[] { this };
		notifyListeners(ProductRackListener.class, "productEmpty", parameterTypes, args);
	}

	/**
	 * notifyProductRemoved
	 * 
	 * 	Notification method used to custom tailor AbstractHardware associated reflection call
	 * 	- with the assumption that listeners registered to the affected ProductRack as defined in 
	 * 	ProductRackListener class will be notified (triggering a method invocation to productRemoved).
	 * 
	 * @param product
	 * 
	 * @see	AbstractHardware, ProductRackListener, AbstractStub
	 */
	private void notifyProductRemoved(Product product) {
		Class<?>[] parameterTypes =
				new Class<?>[] { ProductRack.class, Product.class };
		Object[] args = new Object[] { this, product };
		notifyListeners(ProductRackListener.class, "productRemoved", parameterTypes, args);
	}
}
