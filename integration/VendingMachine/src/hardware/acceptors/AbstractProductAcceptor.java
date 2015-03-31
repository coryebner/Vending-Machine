package hardware.acceptors;

import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.products.Product;

public interface AbstractProductAcceptor extends IAcceptor {
    /**
     * Instructs the device to take the product as input.
     * 
     * @param product
     *            The product to be taken as input.
     * @throws CapacityExceededException
     *             if the device does not have enough space for the pop can.
     * @throws DisabledException
     *             if the device is currently disabled.
     */
    public void acceptProduct(Product product) throws CapacityExceededException,
	    DisabledException;
}
