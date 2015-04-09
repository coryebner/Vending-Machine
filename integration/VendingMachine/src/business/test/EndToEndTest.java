package business.test;

import static org.junit.Assert.*;

import org.junit.Test;

import hardware.funds.Coin;
import hardware.products.Product;
import hardware.simulators.AbstractVendingMachine;
import business.config.Configuration;

public class EndToEndTest {
	protected AbstractVendingMachine machine;
	protected Configuration config;

	@Test
	public void testPurchaseProductWithCoin() throws Exception {
		machine.getCoinSlot().addCoin(new Coin(100));
		machine.getSelectionButton(0).press();
		
		assertItemTypesReturned(Product.class, 1, "A product should have been dispensed");
	}
	
	@Test
	public void testPurchaseProductNoFunds() throws Exception {
		machine.getSelectionButton(0).press();
		
		assertItemTypesReturned(Product.class, 0, "No products should have been dispensed");
	}
	
	@Test
	public void testMakeChangeFromCoin() throws Exception {
		machine.getCoinSlot().addCoin(new Coin(100));
		machine.getCoinSlot().addCoin(new Coin(100));
		machine.getSelectionButton(0).press();
		
		assertItemTypesReturned(Coin.class, 1, "A coin should have been returned");
	}

	private void assertItemTypesReturned(Class c, int n, String message) throws Exception {
		Object [] items = machine.getDeliveryChute().removeItems();
		int count = 0;
		
		for (Object i : items) {
			if (c.isInstance(i)) {
				++count;
			}
		}
		
		assertEquals(message, n, count);
	}
}
