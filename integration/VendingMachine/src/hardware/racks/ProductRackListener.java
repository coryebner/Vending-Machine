package hardware.racks;
import hardware.AbstractHardwareListener;
import hardware.products.Product;

/**
 * ProductRackListener
 */
public interface ProductRackListener extends AbstractHardwareListener {
	
	/**
	 * An event announced when the indicated product is added to the indicated
     * product rack.
     * 
	 * @param productRack
	 * @param product
	 */
    void productAdded(ProductRack productRack, Product product);
    
    /**
     * An event announced when the indicated product is removed from the
     * indicated product rack.
     * 
     * @param productRack
     * @param product
     */
    void productRemoved(ProductRack productRack, Product product);

    
    /**
     * An event announced when the indicated product rack becomes full.
     * 
     * @param productRack
     */
    void productFull(ProductRack productRack);

    /**
     * An event announced when the indicated product rack becomes empty.
     * 
     * @param productRack
     */
    void productEmpty(ProductRack productRack);
}
