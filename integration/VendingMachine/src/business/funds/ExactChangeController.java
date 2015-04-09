package business.funds;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.Vector;


import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.funds.Coin;
import hardware.products.Product;
import hardware.racks.CoinRack;
import hardware.racks.CoinRackListener;
import hardware.racks.ProductRack;
import hardware.racks.ProductRackListener;
import business.config.ConfigurationListener;
import business.selection_delivery.InventoryController;

public class ExactChangeController implements ConfigurationListener, ProductRackListener, CoinRackListener {

	private class TrackedProduct{
		public boolean isEmpty;
		public int price;
		public int rackIndex;
	}
	
	private ArrayList<ExactChangeControllerListener> listeners = new ArrayList<ExactChangeControllerListener>();
	private boolean exactChangePossible = false;
	private HashMap<ProductRack, TrackedProduct> rackToProductMap;
	private TrackedProduct[] products;
	private InventoryController inventoryController;
	private CoinRackController coinRacks[];
	private Vector<Integer> returnValues;
	
	/**
	 * 
	 * ExactChangeController tracks coin funds and determines if exact change is available for all purchases.
	 * 
	 * The ExactChangeController promises to notify its listeners of exactChangeAvailable and exactChangeUnavailable
	 * Events when the status of exact change changes.
	 * 
	 * The ExactChangeController must be registered to
	 * 				-All coin rack events
	 * 				-All product rack events
	 * 				-Configuration events
	 * 
	 * @param InventoryController ic
	 * 			- the inventory controller associated with the vending machine
	 * @param CoinRackController coinRacks
	 * 			- the coin rack controllers associated with the vending machine
	 * @param ProductRack productRacks
	 * 			- the hardware product racks associated with the vending machine
	 * @param CoinRack hardwareCoinRacks
	 * 			- the hardware coin racks associated with the vending machine
	 */
	//ASSUME COIN RACKS ARE IN ASSENDING ORDER
	public ExactChangeController(InventoryController ic, CoinRackController coinRacks[]){
		if(ic == null){
			throw new InvalidParameterException();
		}
		
		inventoryController = ic;
		int numRacks = ic.getRackCount();
		this.coinRacks = coinRacks;
		products = new TrackedProduct[numRacks];
		returnValues = new Vector<Integer>();
		rackToProductMap = new HashMap<ProductRack, ExactChangeController.TrackedProduct>();
		
		for(int i = 0; i < numRacks; i++){
			products[i] = new TrackedProduct();
			products[i].isEmpty = ic.isEmpty(i);
			products[i].price = ic.getCost(i);
			products[i].rackIndex = i;
			rackToProductMap.put(ic.getRack(i), products[i]);
		}
		
		calculateChangeToMake();
		recalculateExactChange();
	}
	
	/**
	 * @exactChangeStatus - indicates if the exact change status is active or not
	 * returns true if the exact change light is on signaling exact change in unavailable
	 * returns false if the exact change light is off signaling exact change in available
	 */
	public boolean isExactChangeActive(){
		return !exactChangePossible;
	}
	
	/**
	 * @getReturnValues - returns all the possible coin return values based
	 * on the current product costs, product availability and coin denominations.
	 */
	public Vector<Integer> getReturnValues(){
		return returnValues;
	}
	
	/**
	 * Recalculates the exact change status after a transaction has taken place (pop dispensed)
	 */
	private void recalculateExactChange(){
		int sum;
		int amountUsed;
		
		exactChangePossible = true;

		for(int i = 0; i < returnValues.size(); i++) {
			sum = 0;
			for(int j = coinRacks.length - 1; j >= 0; j--) {
				amountUsed = 0;
				while((coinRacks[j].getQuantity() - amountUsed > 0) &&
						(sum < returnValues.elementAt(i).intValue())) {
					amountUsed++;
					sum+=coinRacks[j].getCoinRackDenomination();
				}
				if(sum > returnValues.elementAt(i).intValue()) {
					sum-=coinRacks[j].getCoinRackDenomination();
				}
			}
			if(sum != returnValues.elementAt(i).intValue()) {
				exactChangePossible = false;
				break;
			}
		}		
		
		if(exactChangePossible)
			notifyExactChangeAvailable();
		else
			notifyExactChangeUnavailable();
	}
	
	/**
	 * Recalculates possible amounts of change to make
	 */
	private void calculateChangeToMake(){
		//DepthfirstSearch
		Stack<Integer> trail = new Stack<Integer>();
		int sum = 0;
		int productPrice;
		int prevPop;
		
		
		returnValues.clear();
		
		for(int i = 0; i < products.length; i++) {
			if(products[i].isEmpty)
				continue;
			productPrice = products[i].price;
			trail.push(coinRacks.length - 1);
			sum = coinRacks[coinRacks.length - 1].getCoinRackDenomination();
			prevPop = coinRacks.length;
			while((!trail.isEmpty() || prevPop != 0)) {
				if(prevPop == 0) {
					prevPop = trail.pop();
					sum-= coinRacks[prevPop].getCoinRackDenomination();
					continue;
				}
				
				if((sum>productPrice) && (!trail.isEmpty())) {
					if(!returnValues.contains(new Integer(sum-productPrice)))
						returnValues.add(new Integer(sum-productPrice));
					prevPop = trail.pop();
					sum-= coinRacks[prevPop].getCoinRackDenomination();
				} else if((sum == productPrice) && (!trail.isEmpty())) {
					prevPop = trail.pop();
					sum-= coinRacks[prevPop].getCoinRackDenomination();
				} else {
					trail.push(prevPop - 1);
					sum+= coinRacks[prevPop - 1].getCoinRackDenomination();
				}
			}
		}
	}
	
	
	@Override 
	public void productRemoved(ProductRack productRack, Product product) {
	}
	
	
	@Override
	public void priceChanged(int index, int newPrice) {
		products[index].price = newPrice;	
		calculateChangeToMake();
	}

	@Override
	public void productAdded(ProductRack productRack, Product product) {
		TrackedProduct tempProduct = rackToProductMap.get(productRack);
		if(tempProduct.isEmpty != inventoryController.isEmpty(tempProduct.rackIndex)) {
			tempProduct.isEmpty = !tempProduct.isEmpty;
			calculateChangeToMake();
			}	
		}
	
	@Override
	public void productEmpty(ProductRack productRack) {
		TrackedProduct tempProduct = rackToProductMap.get(productRack);
		if(tempProduct.isEmpty != inventoryController.isEmpty(tempProduct.rackIndex)) {
			tempProduct.isEmpty = !tempProduct.isEmpty;
			calculateChangeToMake();
		}
	}

	public void register(ExactChangeControllerListener listener) {
		listeners.add(listener);
	}

	protected void notifyExactChangeAvailable() {
		for (ExactChangeControllerListener l : listeners)
			l.exactChangeAvailable(this);
	}
	
	protected void notifyExactChangeUnavailable() {
		for (ExactChangeControllerListener l : listeners)
			l.exactChangeUnavailable(this);
	}
	
	@Override public void nameChanged(int index, String newName) {}
	@Override public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {}
	@Override public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {}
	
	@Override public void productFull(ProductRack productRack) {}

	@Override
	public void coinsFull(CoinRack rack) {		
	}

	@Override
	public void coinsEmpty(CoinRack rack) {		
	}

	@Override
	public void coinAdded(CoinRack rack, Coin coin) {	
		recalculateExactChange();

	}

	@Override
	public void coinRemoved(CoinRack rack, Coin coin) {		
		recalculateExactChange();
	}

}
