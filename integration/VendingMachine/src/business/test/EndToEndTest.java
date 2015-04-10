package business.test;

import static org.junit.Assert.*;

import java.util.Locale;

import org.junit.Test;

import hardware.funds.Banknote;
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
		machine.getCoinSlot().addCoin(new Coin(25));
		machine.getCoinSlot().addCoin(new Coin(25));
		machine.getCoinSlot().addCoin(new Coin(25));
		machine.getCoinSlot().addCoin(new Coin(25));
		machine.getSelectionButton(0).press();
		Object [] items = machine.getDeliveryChute().removeItems();
		
		machine.getCoinSlot().addCoin(new Coin(200));
		machine.getSelectionButton(0).press();
		items = machine.getDeliveryChute().removeItems();
		assertItemTypesReturned(items, Coin.class, 4, "A coin should have been returned");
	}

    protected void testMakeMixedChangeFromCoin() throws Exception {
		machine.getCoinSlot().addCoin(new Coin(25));
		machine.getCoinSlot().addCoin(new Coin(25));
		machine.getCoinSlot().addCoin(new Coin(10));
		machine.getCoinSlot().addCoin(new Coin(10));
		machine.getCoinSlot().addCoin(new Coin(5));
		machine.getCoinSlot().addCoin(new Coin(25));
		machine.getSelectionButton(0).press();
		
		Object [] items = machine.getDeliveryChute().removeItems();
		
		machine.getCoinSlot().addCoin(new Coin(200));
		machine.getSelectionButton(0).press();
		items = machine.getDeliveryChute().removeItems();
		assertItemTypesReturned(items, Coin.class, 6, "Six coins should have been returned");
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

	/**@author M. Diaz
	 * Method to check that the prepaid card has precedence when coins are present
	 * at the moment of a pop purchase.
	 * */
	protected void testCheckPrepaidCardFirstOrderPrecedence() throws Exception {
		machine.getCardSlot().insertCard(new Card(CardType.PREPAID,
				"12345678",
				"Ruby",
				"0000",
				"12/2019",
				Locale.CANADA,
				200));
		machine.getCoinSlot().addCoin(new Coin(100));
		machine.getSelectionButton(0).press();
		assertNotEquals("Balance of prepaid card has decreased",200, config.getFunds().getPrepaidController().getAvailableBalance());
		assertEquals("Balance should still be 100 for coins",100,config.getFunds().getCoinsController().getAvailableBalance());

	}

	/**@author M. Diaz
	 * Method to check that coins have precedence as payment compared to banknotes
	 * */
	protected void testCheckCoinsPrecedenceOverBanknotes() throws Exception{
		machine.getCoinSlot().addCoin(new Coin(100));
		machine.getBanknoteSlot().addBanknote(new Banknote(500));
		machine.getSelectionButton(0).press();
		Object [] items = machine.getDeliveryChute().removeItems();
		// TODO the precendence for machine 4 fails for this. It takes banknotes before coins
		assertItemTypesReturned(items, Banknote.class, 1, "A banknote should have been returned");

	}

	/**@author M. Diaz
	 * Method to check that purchase fails when the machine is out of product
	 * */
	protected void testFailOutOfProduct() throws Exception{
		int numberOfProducts = config.getInventory().getCount(0);
		for(int i=0; i < numberOfProducts; i++){
			machine.getProductRack(0).dispenseProduct();
			Object [] items = machine.getDeliveryChute().removeItems();
			assertItemTypesReturned(items, Product.class, 1, "A product should be dispensed");
		}

		machine.getCoinSlot().addCoin(new Coin(100));
		machine.getSelectionButton(0).press();
		assertEquals(100,config.getFunds().getCoinsController().getAvailableBalance());
		assertEquals(0,config.getInventory().getCount(0));


	}

	/**@author M. Diaz
	 * Method to check that purchase fails when the machine is full of coins
	 * */
	protected void testFailFullOfCoinsRack() throws Exception {
		int coinValue = config.getFunds().getCoinRackControllers()[4].getCoinRackDenomination();
		while(machine.getCoinRack(4).hasSpace()){
			machine.getCoinRack(4).acceptCoin(new Coin(coinValue));
		}
		machine.getCoinSlot().addCoin(new Coin(coinValue));	
		machine.getSelectionButton(0);
		Object [] items = machine.getDeliveryChute().removeItems();
		assertItemTypesReturned(items, Product.class, 0, "A product should not be dispensed");
		assertEquals(100,config.getFunds().getCoinsController().getAvailableBalance());
		
		
		
		
	}
	
	/**@author Adrian Wu
	 * Method to test Out of Product light is on when machine is out of product
	 */
	protected void testPurchaseAllPop() throws Exception{
		Object[] itemRet = new Object[15];
		for(int i = 0; i < itemRet.length; i++){
			machine.getCoinSlot().addCoin(new Coin(100));
			machine.getSelectionButton(0).press();
			machine.getDeliveryChute().removeItems();
		}
		assertTrue("Rack should be empty", config.getInventory().isEmpty(0));
		machine.getCoinSlot().addCoin(new Coin(100));
		machine.getSelectionButton(0).press();
		assertEquals("Nothing should have been vended", 0, machine.getDeliveryChute().removeItems().length);
		assertTrue("Out of product light 0 should be on", machine.getOutOfProductLight(0).isActive());
	}
	
	/**@author Adrian Wu
	 * Method to test Out of Order light is on when storage bin is full of coins
	 */
	protected void testFullOfCoins() throws Exception{
		while(!config.getFunds().getCoinStorageBinTracker().isFull()){
			machine.getCoinSlot().addCoin(new Coin(5));
		}
		assertTrue("Out of Order light should be on", machine.getOutOfOrderLight().isActive());
	}
	
	/**@author M. Diaz
	 * Method to check that purchase fails when the machine is full of coins (coin slot)
	 * */
	protected void testFailFullOfCoinsSlot() throws Exception {
		
		while(machine.getCoinReceptacle().hasSpace()){
			machine.getCoinSlot().addCoin(new Coin(100));
		}
		
		machine.getCoinSlot().addCoin(new Coin(100));
		Object [] items = machine.getDeliveryChute().removeItems();
		assertItemTypesReturned(items, Coin.class, 1, "A coin should be returned");
	}
	
	/**@author M. Diaz
	 * Method to check notification light out of order is on when a product is empty
	 * */
	protected void testOutOfProductEmpty() throws Exception {
		
		int numberOfProducts = config.getInventory().getCount(0);
		for(int i=0; i < numberOfProducts; i++){
			machine.getProductRack(0).dispenseProduct();
			Object [] items = machine.getDeliveryChute().removeItems();
			assertItemTypesReturned(items, Product.class, 1, "A product should be dispensed");
		}

		machine.getCoinSlot().addCoin(new Coin(100));
		machine.getSelectionButton(0).press();
		assertEquals("Nothing should have been vended", 0, machine.getDeliveryChute().removeItems().length);
		assertTrue(machine.getOutOfProductLight(0).isActive());
		//machine.getConfigurationPanelTransmitter().
		
	}
	
}
