package notifications;

import java.util.HashMap;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.products.Product;
import hardware.racks.ProductRack;
import hardware.racks.ProductRackListener;
import hardware.ui.IndicatorLight;

public class OutOfProductLightController implements ProductRackListener{

	HashMap<ProductRack,IndicatorLight> rackLightMap;

	
	public OutOfProductLightController(IndicatorLight outOfProductLight[], ProductRack productRacks[]) {
		if(productRacks.length != outOfProductLight.length)
			throw new IllegalArgumentException();
		
		for(int i = 0; i < productRacks.length; i++) {
			productRacks[i].register(this);
			rackLightMap.put(productRacks[i], outOfProductLight[i]);
		}
	}

	@Override
	public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {		
	}

	@Override
	public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {		
	}

	@Override
	public void productAdded(ProductRack productRack, Product product) {
		rackLightMap.get(productRack).activate();
	}

	@Override
	public void productRemoved(ProductRack productRack, Product product) {		
	}

	@Override
	public void productFull(ProductRack productRack) {
	}

	@Override
	public void productEmpty(ProductRack productRack) {		
		rackLightMap.get(productRack).deactivate();
	}
}
