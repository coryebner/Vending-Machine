package hardware.channels;

import hardware.acceptors.AbstractProductAcceptor;
import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.products.Product;

/**
 * Represents the hardware through which a product is carried from one device to
 * another. Once the hardware is configured, product channels will not be used
 * directly by other applications.
 */
public class ProductChannel extends AbstractChannel<AbstractProductAcceptor> {

    /**
     * Creates a new product channel whose output will go to the indicated sink.
     * 
	 * @param sink
	 * 			the output to be associated to the new product channel
     */
    public ProductChannel(AbstractProductAcceptor sink) {
    	super(sink);
    }

    /**
     * This method should only be called from simulated hardware devices.
     * 
     * @param product
     * 			   the product to be moved to the associated channel sink.
     * @throws CapacityExceededException
     *             if the output sink cannot accept the product.
     * @throws DisabledException
     *             if the output sink is currently disabled.
     */

    public void acceptProduct(Product product) throws CapacityExceededException,
	    DisabledException {
    	getSink().acceptProduct(product);
    }
}
