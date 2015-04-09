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
		
		assertItemsReturned(new Product(), 1, "A product should have been dispensed");
	}

	private void assertItemsReturned(Object test, int n, String message) throws Exception {
		Object [] items = machine.getDeliveryChute().removeItems();
		int count = 0;
		
		for (Object i : items) {
			if (i.equals(test)) {
				++count;
			}
		}
		
		assertEquals(message, count, n);
	}
}
