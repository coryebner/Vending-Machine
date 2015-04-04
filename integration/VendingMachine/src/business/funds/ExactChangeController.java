package business.funds;

import java.security.InvalidParameterException;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.products.Product;
import hardware.racks.ProductRack;
import hardware.racks.ProductRackListener;
import business.config.ConfigurationListener;
import business.selection_delivery.InventoryController;

public class ExactChangeController implements ConfigurationListener, ProductRackListener {

	private class TrackedProduct{
		private boolean isEmpty = false;
		private int price = 0;
	}
	
	
	private TrackedProduct[] products;
	private InventoryController inventoryController;
	
	private boolean exactChangeStatusActive = false;

	public ExactChangeController(InventoryController ic){
		if(ic == null){
			throw new InvalidParameterException();
		}
		
		inventoryController = ic;
		int numRacks = ic.getRackCount();
		
		products = new TrackedProduct[numRacks];
		for(int index = 0; index < numRacks; index++){
			products[index].isEmpty = ic.isEmpty(index);
			products[index].price = ic.getCost(index);
		}
	}
	
	/**
	 * @exactChangeStatus - indicates if the exact change status is active or not
	 */
	public boolean isExactChangeActive(){
		return exactChangeStatusActive;
	}
	
	/**
	 * Recalculates the exact change status after a transaction has taken place (pop dispensed)
	 */
	private void recalculateExactChange(){
		
	}
	
	/**
	 * Recalculates possible amounts of change to make
	 */
	private void calculateChangeToMake(){
		
	}
	
	
	@Override 
	public void productRemoved(ProductRack productRack, Product product) {
		recalculateExactChange();
	}
	
	
	@Override
	public void priceChanged(int index, int newPrice) {
		products[index].price = newPrice;	
	}

	@Override
	public void productAdded(ProductRack productRack, Product product) {
		
		for(int index = 0; index < products.length; index++){
			//Just Re-check the empty products
			if(products[index].isEmpty){
				products[index].isEmpty = inventoryController.isEmpty(index);
			}
		}
	}
	
	@Override
	public void productEmpty(ProductRack productRack) {
		for(int index = 0; index < products.length; index++){
			//Just Re-check the non-empty products
			if(products[index].isEmpty){
				products[index].isEmpty = inventoryController.isEmpty(index);
			}
		}
		
	}
	
	// DO NOT NEED TO LISTEN TO THESE :D
	@Override public void nameChanged(int index, String newName) {}
	@Override public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {}
	@Override public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {}
	
	@Override public void productFull(ProductRack productRack) {}

}
