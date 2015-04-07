package hardware.test.stub;

import hardware.products.Product;
import hardware.racks.ProductRack;
import hardware.racks.ProductRackListener;
public class ProductRackListenerStub extends AbstractStub implements ProductRackListener{

	@Override
	public void productAdded(ProductRack productRack, Product product) {
		recordCallTo("productAdded");
		
	}

	@Override
	public void productRemoved(ProductRack productRack, Product product) {
		recordCallTo("productRemoved");
		
	}

	@Override
	public void productFull(ProductRack productRack) {
		recordCallTo("productFull");
		
	}

	@Override
	public void productEmpty(ProductRack productRack) {
		recordCallTo("productEmpty");
		
	}

}
