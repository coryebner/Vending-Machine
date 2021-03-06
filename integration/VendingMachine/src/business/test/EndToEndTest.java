package business.test;

import static org.junit.Assert.*;

import java.util.Locale;

import org.junit.Test;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.funds.Banknote;
import hardware.funds.Card;
import hardware.funds.Card.CardType;
import hardware.funds.Coin;
import hardware.products.Product;
import hardware.simulators.AbstractVendingMachine;
import hardware.ui.Display;
import hardware.ui.DisplayListener;
import business.config.Configuration;

public class EndToEndTest {
	protected AbstractVendingMachine machine;
	protected Configuration config;
	
	//Blatant Hack, I'm over it. --Taylor
	private int getCoinValueFromIndex(int index) throws Exception{
		if (index == 0) return 5;
		else if (index == 1) return 10;
		else if (index == 2) return 25;
		else if (index == 3) return 100;
		else return 200;
	}
	
	protected void fillCoins(AbstractVendingMachine machine) throws Exception{
		for(int i = 0; i < machine.getNumberOfCoinRacks(); i++){
			for(int j = 0; j <10; j++){
				machine.getCoinRack(i).acceptCoin(new Coin(getCoinValueFromIndex(i)));
			}
		}
	}

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
	
	protected void testPurchaseProductNoFundsCode() throws Exception {
		machine.getSelectionButton(0).press();
		machine.getSelectionButton(6).press();

		Object [] items = machine.getDeliveryChute().removeItems();
		assertItemTypesReturned(items, Product.class, 0, "No products should have been dispensed");
		
	}

	protected void testMakeChangeFromCoin() throws Exception {
		fillCoins(machine);
		
		machine.getCoinSlot().addCoin(new Coin(200));
		machine.getSelectionButton(0).press();
		Object[] items = machine.getDeliveryChute().removeItems();
		assertItemTypesReturned(items, Coin.class, 1, "A coin should have been returned");
	}

    protected void testMakeMixedChangeFromCoin() throws Exception {
		fillCoins(machine);
		
		machine.getCoinSlot().addCoin(new Coin(200));
		machine.getCoinSlot().addCoin(new Coin(5));
		machine.getCoinSlot().addCoin(new Coin(10));
		machine.getSelectionButton(0).press();
		Object[] items = machine.getDeliveryChute().removeItems();
		assertItemTypesReturned(items, Coin.class, 3, "Three coins should have been returned");
	}
	
	protected void testMakeChangeFromPriorPurchase() throws Exception {
		fillCoins(machine);
		
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
	
	protected void testPurchaseWithBills() throws Exception {
		Banknote bnote = new Banknote(500);
		machine.getBanknoteSlot().addBanknote(bnote);
		machine.getSelectionButton(0).press();
		
		Object[] items = machine.getDeliveryChute().removeItems();
		assertItemTypesReturned(items, Product.class, 1, "A product should have been dispensed");
	}
	
	protected void testPurchaseProductBillCode() throws Exception {
		Banknote bnote = new Banknote(500);
		machine.getBanknoteSlot().addBanknote(bnote);
		machine.getSelectionButton(0).press();
		machine.getSelectionButton(6).press();
		
		Object[] items = machine.getDeliveryChute().removeItems();
		assertItemTypesReturned(items, Product.class, 1, "A product should have been dispensed");		
	}
	
	protected void testMakeChangeFromBills() throws Exception {
		fillCoins(machine);
		
		Banknote bnote = new Banknote(500);
		machine.getBanknoteSlot().addBanknote(bnote);
		machine.getSelectionButton(0).press();
		
		Object[] items = machine.getDeliveryChute().removeItems();
		assertItemTypesReturned(items, Coin.class, 2, "Two toonies should be given");
	}
	
	protected void testMakeChangeFromBillsCode() throws Exception {
		fillCoins(machine);
		
		Banknote bnote = new Banknote(500);
		machine.getBanknoteSlot().addBanknote(bnote);
		machine.getSelectionButton(0).press();
		machine.getSelectionButton(6).press();
		
		Object[] items = machine.getDeliveryChute().removeItems();
		assertItemTypesReturned(items, Coin.class, 2, "Two toonies should be given");
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
	protected void testPurchaseAllPop() throws Exception
	{
		for(int i = 0; i < machine.getProductRack(0).getMaxCapacity(); i++){
			machine.getCoinSlot().addCoin(new Coin(100));
			machine.getSelectionButton(0).press();
		}
		
		Object [] items = machine.getDeliveryChute().removeItems();
		assertItemTypesReturned(items, Product.class, 10, "A product should have been vended");
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
		while(machine.getCoinReceptacle().hasSpace()) {
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
	 * Method to check notification light product empty is on when a product is empty
	 * 
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
		
	}
	
	/**@author M. Diaz
	 * Method to check notification light out of order is on when machine is out of order
	 * */
	protected void testOutOfOrderLight() throws Exception {
		int coinValue = config.getFunds().getCoinRackControllers()[4].getCoinRackDenomination();
		while(machine.getCoinRack(4).hasSpace()){
			machine.getCoinRack(4).acceptCoin(new Coin(coinValue));
		}
		machine.getCoinSlot().addCoin(new Coin(coinValue));	
		machine.getSelectionButton(0);
		assertTrue(machine.getOutOfOrderLight().isActive());
	}
	
	
	/**@author M. Diaz
	 * Method to check display changes when a product is empty
	 * */
	protected void testDisplayChangesProductEmpty() throws Exception {

		DisplayLogger displayLogger = new DisplayLogger();
		this.machine.getDisplay().register(displayLogger);
		
		int numberOfProducts = config.getInventory().getCount(0);
		for(int i=0; i < numberOfProducts; i++){
			machine.getProductRack(0).dispenseProduct();
			Object [] items = machine.getDeliveryChute().removeItems();
			assertItemTypesReturned(items, Product.class, 1, "A product should be dispensed");
		}
		
		machine.getCoinSlot().addCoin(new Coin(100));
		machine.getSelectionButton(0).press();
		assertTrue(displayLogger.toString().contains("Product empty"));
		
	}
	
	protected void testDisplayChangesProductEmptyPrepaid() throws Exception {
		DisplayLogger displayLogger = new DisplayLogger();
		this.machine.getDisplay().register(displayLogger);
		
		int numberOfProducts = config.getInventory().getCount(0);
		for(int i=0; i < numberOfProducts; i++){
			machine.getProductRack(0).dispenseProduct();
			Object [] items = machine.getDeliveryChute().removeItems();
			assertItemTypesReturned(items, Product.class, 1, "A product should be dispensed");
		}
		
		machine.getCardSlot().insertCard(new Card(CardType.PREPAID,
												  "12345678",
												  "Liam",
												  "0000",
												  "12/2019",
												  Locale.CANADA,
												  200));
		machine.getSelectionButton(0).press();
		assertTrue(displayLogger.toString().contains("Product empty"));
	}
	

	/**@author Maria Diaz
	 * 
	 * test that checks that the Display has changed the message when insufficient funds
	 * */
	protected void testDisplayChangesInsufficientFunds() throws Exception{
		DisplayLogger displayLogger = new DisplayLogger();
		this.machine.getDisplay().register(displayLogger);
		
		machine.getSelectionButton(0).press();
		assertTrue(displayLogger.toString().contains("Insufficient funds. Product costs:"));
	}
	
	protected void testPurchaseProductCoinCode() throws Exception
	{
		machine.getCoinSlot().addCoin(new Coin(100));
		machine.getSelectionButton(0).press();
		machine.getSelectionButton(6).press();
		
		Object [] items = machine.getDeliveryChute().removeItems();
		
		assertItemTypesReturned(items, Product.class, 1, "Typing a code should dispense a product");
	}
	
	protected void testEnterInvalidCode() throws Exception
	{
		machine.getCoinSlot().addCoin(new Coin(100));
		machine.getSelectionButton(0).press();
		machine.getSelectionButton(0).press();
		
		Object [] items = machine.getDeliveryChute().removeItems();
		
		assertItemTypesReturned(items, Product.class, 0, "Typing the wrong code should not dispense product");
	}
	
	
	/**@author Liam Mitchell
	 * 
	 * This class listens for events of the Display, when the message displayed changes.
	 * */
	private class DisplayLogger implements DisplayListener
	{
	    private StringBuilder sb;

	    public DisplayLogger()
	    {
	    	sb = new StringBuilder();
	    }
	    
	    public String toString()
	    {
	    	if(sb!=null){
	    		return sb.toString();
	    	}
	    	else return " ";
	    }

		@Override
		public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void messageChange(Display display, String oldMsg, String newMsg) {
			sb.append(newMsg);
			
		}
	}
}
