package business.notifications;

import java.util.HashMap;

import business.selection_delivery.InventoryController;
import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.products.Product;
import hardware.racks.ProductRack;
import hardware.racks.ProductRackListener;
import hardware.ui.IndicatorLight;

public class OutOfProductLightController implements ProductRackListener{

	HashMap<ProductRack,IndicatorLight> rackLightMap;

	/**
	 * @param IndicatorLight outOfProductLight[]
	 *            - an array of all the product lights
	 *            - each indicator light is associated with the productRacks[] at the same index.
	 * @param ProductRack productRacks[]
	 * 			  - an array of all the product racks
	 * 			  - each product rack is associated with the outOfProductLight[] at the same index.
	 * @param InventoryController inventory
	 * 			  - the inventory controller associated to the vending machine
	 */
	public OutOfProductLightController(IndicatorLight outOfProductLight[], ProductRack productRacks[],
			InventoryController inventory) {
		if(productRacks.length != outOfProductLight.length)
			throw new IllegalArgumentException();
		
		for(int i = 0; i < productRacks.length; i++) {
			productRacks[i].register(this);
			rackLightMap.put(productRacks[i], outOfProductLight[i]);
		}
		
		for(int i = 0; i < inventory.getRackCount(); i++) {
			if(inventory.isEmpty(i))
				outOfProductLight[i].deactivate();
			else
				outOfProductLight[i].activate();
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
