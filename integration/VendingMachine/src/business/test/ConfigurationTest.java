package business.test;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;

import hardware.exceptions.NoSuchHardwareException;
import hardware.racks.ProductRack;
import hardware.ui.IndicatorLight;
import hardware.ui.PushButton;
import hardware.ui.PushButtonCodeInterpreter;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import business.config.Configuration;
import business.config.ConfigurationException;
import business.selection_delivery.ButtonSelectionController;
import business.selection_delivery.InventoryController;
import business.notifications.DisplayController;
import business.funds.FundsController;

public class ConfigurationTest extends Configuration {
	public ConfigurationTest()
	{
		super();
	}

	@Test
	public void testReadInitialConfigFile() throws Exception {
		BufferedReader input = new BufferedReader(new StringReader(
				"VMRUS-SFF-PC\n"
						+ "names Pepsi Coke 7-up Sprite\n"
						+ "prices 100 100 100 100\n"
						+ "quantities 0 0 0 0\n"
						+ "coinracks 0 0 0 0 0\n"
						+ "coinstorage 0 0 0 0 0\n"
						+ "billracks 0 0 0 0 0\n"
						+ "billstorage 0 0 0 0 0\n"
						+ "locale CA\n"));

		readConfigFile(input);

		String [] expectedNames = {"Pepsi", "Coke", "7-up", "Sprite"};
		int [] expectedPrices = {100, 100, 100, 100};
		int [] expectedQuantities = {0, 0, 0, 0};
		int [] expectedFunds = {0, 0, 0, 0, 0};

		assertArrayEquals("Correct names found", expectedNames, names);
		assertArrayEquals("Correct prices found", expectedPrices, prices);
		assertArrayEquals("Correct quantities found", expectedQuantities, quantities);
		assertArrayEquals("Correct coinracks found", expectedFunds, coinRackQuantities);
		//assertArrayEquals("Correct coinstorage found", expectedFunds, coinStorageQuantities);
		//assertArrayEquals("Correct billracks found", expectedFunds, billRackQuantities);
		assertArrayEquals("Correct billstorage found", expectedFunds, billStorageQuantities);
	}

	@Test(expected=ConfigurationException.class)
	public void testReadMissingLineConfigFile() throws Exception {
		BufferedReader input = new BufferedReader(new StringReader(
				"VMRUS-SFF-PC\n"
						+ "names Pepsi Coke 7-up Sprite\n"
						// + "prices 100 100 100 100\n"
						+ "quantities 0 0 0 0\n"
						+ "coinracks 0 0 0 0 0\n"
						+ "coinstorage 0 0 0 0 0\n"
						+ "billracks 0 0 0 0 0\n"
						+ "billstorage 0 0 0 0 0\n"
						+ "locale CA"));

		readConfigFile(input);
		fail("Exception should have been thrown by now");
	}

	@Test(expected=ConfigurationException.class)
	public void testReadBadTypeConfigFile() throws Exception {
		BufferedReader input = new BufferedReader(new StringReader(
				"NOT-A-VENDING-MACHINE\n"
						+ "names Pepsi Coke 7-up Sprite\n"
						+ "quantities 0 0 0 0\n"
						+ "coinracks 0 0 0 0 0\n"
						+ "coinstorage 0 0 0 0 0\n"
						+ "billracks 0 0 0 0 0\n"
						+ "billstorage 0 0 0 0 0\n"
						+ "locale CA"));

		readConfigFile(input);
		fail("Exception should have been thrown by now");
	}

	@Test(expected=ConfigurationException.class)
	public void testReadEmptyConfigFile() throws Exception {
		BufferedReader input = new BufferedReader(new StringReader(""));

		readConfigFile(input);
		fail("Exception should have been thrown by now");
	}

	@Test
	public void testReadSavedConfigFile() throws Exception {
		BufferedReader input = new BufferedReader(new StringReader(
				"VMRUS-SFF-PC\n"
				+ "names Pepsi Coke 7-up Sprite\n"
				+ "prices 125 150 200 100\n"
				+ "quantities 1 9 0 15\n"
				+ "coinracks 30 15 12 0 10\n"
				+ "coinstorage 90 23 12 14 55\n"
				+ "billracks 98 53 22 10 64\n"
				+ "billstorage 2 5 19 21 3\n"
				+ "locale CA"));

		readConfigFile(input);

		String [] expectedNames = {"Pepsi", "Coke", "7-up", "Sprite"};
		int [] expectedPrices = {125, 150, 200, 100};
		int [] expectedQuantities = {1, 9, 0, 15};
		int [] expectedCoinRacks = {30, 15, 12, 0, 10};
		int [] expectedCoinStorage = {90, 23, 12, 14, 55};
		int [] expectedBillRacks = {98, 53, 22, 10, 64};
		int [] expectedBillStorage = {2, 5, 19, 21, 3};

		assertArrayEquals("Correct names found", expectedNames, names);
		assertArrayEquals("Correct prices found", expectedPrices, prices);
		assertArrayEquals("Correct quantities found", expectedQuantities, quantities);
		assertArrayEquals("Correct coinracks found", expectedCoinRacks, coinRackQuantities);
		//assertArrayEquals("Correct coinstorage found", expectedCoinStorage, coinStorageQuantities);
		//assertArrayEquals("Correct billracks found", expectedBillRacks, billRackQuantities);
		assertArrayEquals("Correct billstorage found", expectedBillStorage, billStorageQuantities);
	}

	/**Test for createButtonSelectionController
	 * */
	@Test
	public void testCreateButtonSelectionController(){

		/**Create a  MockAbstractVendingMachine*/ 
		Mockery context = new Mockery(){{
			setImposteriser(ClassImposteriser.INSTANCE); 
			// Alows JMock to mock concrete classes, and not only interfaces
		}};

		//Assign mock vending machine to a context in the Mockery.
		final MockAbstractVendingMachine mockMachine = context.mock(MockAbstractVendingMachine.class);
		try {
			// Setting up the expectations for the mock object.
			context.checking(new Expectations() {{
				oneOf(mockMachine).getOutOfOrderLight(); will(returnValue(new IndicatorLight()));
				oneOf(mockMachine).getNumberOfSelectionButtons(); will(returnValue(1));
				PushButton button = new PushButton();
				exactly(2).of(mockMachine).getSelectionButton(0); will(returnValue(button));

			}});
		} catch (NoSuchHardwareException e) {
			e.printStackTrace();
		}
		// mocking other required objects for when the method is called
		final InventoryController inventory = context.mock(InventoryController.class);
		final DisplayController display = context.mock(DisplayController.class); 
		FundsController fundsC = context.mock(FundsController.class);
		this.machine= mockMachine;
		this.funds = fundsC;
		this.inventoryController = inventory;
		this.displayController = display;
		
		createFundsController(machine, false, false, false, false);

		// Assertion that the Button Selection Controller is null as it was not initialized before
		assertNull("Button Selection Controller doesn't exist",this.buttonSelectionController);
		createButtonSelectionController(this.machine);
		
		// Assertion that this buttonSelectionController object was created after the previous call
		assertNotNull("Button Selection Controller created", this.buttonSelectionController);
		try {
			// Only idea I had to test the listener registered was to deregister the object.
			// When deregistering a listener, it returns true if the listener passed as argument
			// was found and it was successfully deregistered
			boolean wasListenerDeregistered = this.machine.getSelectionButton(0).deregister(buttonSelectionController);
			assertTrue("The listener was found and deregistered", wasListenerDeregistered);
		} catch (NoSuchHardwareException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**Test for createCodeController*/
	@Test
	public void testCreateCodeController(){
		int offset = 2;
		/**Create a  MockAbstractVendingMachine*/ 
		Mockery context = new Mockery(){{
			setImposteriser(ClassImposteriser.INSTANCE); 
			// Allows JMock to mock concrete classes, and not only interfaces
		}};
		final MockAbstractVendingMachine mockMachine = context.mock(MockAbstractVendingMachine.class);

		// Setting up the expectations for the mock object.
		try {
			context.checking(new Expectations() {{

				Map<PushButton, Character> characters = new HashMap<PushButton, Character>();
				PushButtonCodeInterpreter pushButtonCode = new PushButtonCodeInterpreter(characters);
				exactly(3).of(mockMachine).getPushButtonCodeInterpreter(); will(returnValue(pushButtonCode));

			}});
		} catch (NoSuchHardwareException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// mocking other required objects for when the method is called
		final InventoryController inventory = context.mock(InventoryController.class);
		final DisplayController display = context.mock(DisplayController.class);
		final FundsController funds = context.mock(FundsController.class);
		this.inventoryController = inventory;
		this.displayController = display;
		this.funds = funds;
		this.machine = mockMachine;
		assertNull(this.codeSelectionController);
		createCodeController(this.machine,offset);
		assertNotNull(this.codeSelectionController);

		try {
			boolean isDeregistered = this.machine.getPushButtonCodeInterpreter().deregister(codeSelectionController);
			assertTrue(isDeregistered);
		} catch (NoSuchHardwareException e) {
			e.printStackTrace();
		}
	}

	/**Test for createCodeController*/
	@Test
	public void testcreateInventoryController(){

		// machine.getNumberOfProductRacks()
		// getNumberOfProductRacks()
		// getProductRack(i).getMaxCapacity()
		//this.names,this.prices,this.quantities
		this.names = new String[1];
		this.prices = new int[1];
		this.quantities = new int[1];

		/**Create a  MockAbstractVendingMachine*/ 
		final Mockery context = new Mockery(){{
			setImposteriser(ClassImposteriser.INSTANCE); 
			// Allows JMock to mock concrete classes, and not only interfaces
		}};
		final MockAbstractVendingMachine mockMachine = context.mock(MockAbstractVendingMachine.class);
		ProductRack pr = new ProductRack(10);
		// Setting up the expectations for the mock object.
		try {
			context.checking(new Expectations() {{
				ProductRack pr = context.mock(ProductRack.class);
//				oneOf(mockMachine).getOutOfOrderLight(); will(returnValue(new IndicatorLight()));
				oneOf(mockMachine).getProductRack(0); will(returnValue(pr));
				
				exactly(3).of(mockMachine).getNumberOfProductRacks(); will(returnValue(1));

			}});
		} catch (NoSuchHardwareException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		this.machine= mockMachine;
		createFundsController(machine, false, false, false, false);
		assertNull(this.inventoryController);
		createInventoryController(this.machine);
		assertNotNull(this.inventoryController);
	}
}
