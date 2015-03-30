package hardware.racks;

import hardware.channels.ProductChannel;
import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.exceptions.EmptyException;
import hardware.exceptions.SimulationException;
import hardware.products.Product;

public class ProductRack extends AbstractRack<ProductRackListener, Product, ProductChannel> {

	public ProductRack(int capacity) {
		super(capacity);
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

	private void notifyProductAdded(Product product) {
		Class<?>[] parameterTypes =
				new Class<?>[] { ProductRack.class, Product.class };
		Object[] args = new Object[] { this, product };
		notifyListeners(PopCanRackListener.class, "productAdded", parameterTypes, args);
	}

	private void notifyProductFull() {
		Class<?>[] parameterTypes =
				new Class<?>[] { ProductRack.class };
		Object[] args = new Object[] { this };
		notifyListeners(PopCanRackListener.class, "productFull", parameterTypes, args);
	}

	private void notifyProductEmpty() {
		Class<?>[] parameterTypes =
				new Class<?>[] { ProductRack.class };
		Object[] args = new Object[] { this };
		notifyListeners(ProductRackListener.class, "productEmpty", parameterTypes, args);
	}

	private void notifyProductRemoved(Product product) {
		Class<?>[] parameterTypes =
				new Class<?>[] { ProductRack.class, Product.class };
		Object[] args = new Object[] { this, product };
		notifyListeners(ProductRackListener.class, "productRemoved", parameterTypes, args);
	}
}
