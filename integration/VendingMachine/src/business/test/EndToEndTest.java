package business.test;

import static org.junit.Assert.*;

import java.util.Locale;

import org.junit.Test;

import hardware.funds.Card;
import hardware.funds.Card.CardType;
import hardware.funds.Coin;
import hardware.products.Product;
import hardware.simulators.AbstractVendingMachine;
import business.config.Configuration;

public class EndToEndTest {
	protected AbstractVendingMachine machine;
	protected Configuration config;

	protected void testPurchaseProductCoin() throws Exception {
		machine.getCoinSlot().addCoin(new Coin(100));
		machine.getSelectionButton(0).press();

		Object [] items = machine.getDeliveryChute().removeItems();
		assertItemTypesReturned(items, Product.class, 1, "A product should have been dispensed");
	}
	
	protected void testPurchaseProductNoFunds() throws Exception {
		machine.getSelectionButton(0).press();

		Object [] items = machine.getDeliveryChute().removeItems();
		assertItemTypesReturned(items, Product.class, 0, "No products should have been dispensed");
	}
	
	
	protected void testMakeChangeFromCoin() throws Exception {
		machine.getCoinSlot().addCoin(new Coin(100));
		machine.getCoinSlot().addCoin(new Coin(100));
		machine.getSelectionButton(0).press();

		Object [] items = machine.getDeliveryChute().removeItems();
		assertItemTypesReturned(items, Coin.class, 1, "A coin should have been returned");
	}
	
	protected void testMakeChangeFromPriorPurchase() throws Exception {
		machine.getCoinSlot().addCoin(new Coin(100));
		machine.getSelectionButton(0).press();
		machine.getDeliveryChute().removeItems();
		
		machine.getCoinSlot().addCoin(new Coin(200));
		machine.getSelectionButton(0).press();

		Object [] items = machine.getDeliveryChute().removeItems();
		assertItemTypesReturned(items, Coin.class, 1, "A coin should have been returned");
	}
	
	protected void testPurchaseProductPrepaid() throws Exception {
		machine.getCardSlot().insertCard(new Card(CardType.PREPAID,
												  "12345678",
												  "Liam Mitchell",
												  "0000",
												  "12/2019",
												  Locale.CANADA,
												  100));
		machine.getSelectionButton(0).press();

		Object [] items = machine.getDeliveryChute().removeItems();
		assertItemTypesReturned(items, Product.class, 1, "A product should have been vended");
	}
	
	protected void testCoinReturn() throws Exception {
		machine.getCoinSlot().addCoin(new Coin(100));
		machine.getReturnButton().press();

		Object [] items = machine.getDeliveryChute().removeItems();
		assertItemTypesReturned(items, Coin.class, 1, "A coin should have been returned");
	}

	protected void assertItemTypesReturned(Object [] items, Class c, int n, String message) throws Exception {
		int count = 0;
		
		for (Object i : items) {
			if (c.isInstance(i)) {
				++count;
			}
		}
		
		assertEquals(message, n, count);
	}
}
