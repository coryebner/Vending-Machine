package business.selection_delivery.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import business.funds.FundsController;
import business.funds.PaymentMethods;
import business.notifications.DisplayController;
import business.selection_delivery.ButtonSelectionController;
import business.selection_delivery.InventoryController;
import hardware.racks.CoinRack;
import hardware.racks.ProductRack;
import hardware.ui.PushButton;

public class ButtonSelectionControllerTest {

	private ButtonSelectionController buttonSelectionController;

	//All the Controllers needed for ButtonSelectionController
	private  InventoryController inventoryController;
	private  DisplayController displayController;
	private  FundsController fundsController;
	private  PushButton[] pushButtonArray;
	private  int numberOfButtons;

	//Listener
	private PushButtonListenerStub pushButtonListenerStub;

	//Fields for InventoryController
	private int rackcount = 6;
	private ProductRack[] rack;
	private String[] names;
	private int[] costs;
	private int[] quantity;
	private int[] productID;

	//Fields for FundsController
	private Locale locale;
	private boolean bestEffortChange;
	private CoinRack[] coinRacks;
	private int[] coinRackDenominations;
	private int[] coinRackQuantities;
	private List<PaymentMethods> availablePaymentMethods;



	@BeforeClass
	public static void BeforeRoutine() {


	}

	@Before
	public void setup() {


		pushButtonListenerStub = new PushButtonListenerStub();

		rackcount = 6;
		numberOfButtons = 6;

		//Setting up the push buttons
		pushButtonArray = new PushButton[rackcount];
		for(int i = 0; i < rackcount; i++ )
		{
			pushButtonArray[i] = new PushButton();
			pushButtonArray[i].register(pushButtonListenerStub);
		}

		//Setting up the inventory manager
		rack = new ProductRack[rackcount];
		names = new String[rackcount];
		costs = new int[rackcount];
		quantity = new int[rackcount];
		productID = new int[rackcount];

		for(int i = 0; i < rackcount; i++)
			rack[i] = new ProductRack(10);

		for(int i = 0; i < rackcount; i++)
			names[i] = "Product" + i;

		for(int i = 0; i < rackcount; i++)
			costs[i] = 100;

		for(int i = 0; i < rackcount; i++)
			quantity[i] = 2;

		for(int i = 0; i < rackcount; i++)
			productID[i] = i + 1;

		inventoryController = new InventoryController(rack, rackcount, names, costs, quantity, productID);

		//Setting up the display controller
		displayController = new DisplayController();

		//Setting up the funds manager
		bestEffortChange = true;
		CoinRack[] coinRacks = new CoinRack[5];
		coinRacks[0] = new CoinRack(100);
		coinRacks[1] = new CoinRack(100);
		coinRacks[2] = new CoinRack(100);
		coinRacks[3] = new CoinRack(100);
		coinRacks[4] = new CoinRack(100);
		coinRackDenominations = new int[5];
		coinRackDenominations[0] = 5;
		coinRackDenominations[1] = 10;
		coinRackDenominations[2] = 25;
		coinRackDenominations[3] = 100;
		coinRackDenominations[4] = 200;

		coinRackQuantities = new int[5];
		coinRackQuantities[0] = 0;
		coinRackQuantities[1] = 0;
		coinRackQuantities[2] = 0;
		coinRackQuantities[3] = 0;
		coinRackQuantities[4] = 0;

		availablePaymentMethods = new ArrayList<PaymentMethods>();
		availablePaymentMethods.add(PaymentMethods.COINS);

		System.out.println(coinRackDenominations);
		System.out.println(coinRackQuantities);
		System.out.println(availablePaymentMethods);
		System.out.println(inventoryController);
		System.out.println(bestEffortChange);
		System.out.println(coinRacks);

		fundsController = new FundsController(Locale.CANADA, bestEffortChange, coinRacks, coinRackDenominations, coinRackQuantities, availablePaymentMethods, inventoryController);
		
		buttonSelectionController = new ButtonSelectionController(inventoryController, fundsController, pushButtonArray, numberOfButtons);


		pushButtonListenerStub.init();
	}

	@After
	public void teardown() {
		
		//Teardown the buttons and listeners
		for(int i = 0; i < rackcount; i++ )
		{
			pushButtonArray[i].deregisterAll();
			pushButtonArray[i] = null;
		}

		pushButtonListenerStub = null;

		//Teardown the inventory manager
		rack = null;
		names = null;
		costs = null;
		quantity = null;
		productID= null;
		inventoryController = null;

		//Teardown the FundsController
		bestEffortChange = false;
		coinRacks = null;
		coinRackDenominations = null;
		coinRackQuantities = null;
		availablePaymentMethods = null;
		fundsController = null;

		buttonSelectionController = null;
	}

	@Test
	public void testPressPushButtonLowerBound() {
		pushButtonListenerStub.expect("pressed");
		pushButtonArray[0].press();
		pushButtonListenerStub.assertProtocol();
	}
	
	@Test
	public void testPressPushButtonUpperBound() {
		pushButtonListenerStub.expect("pressed");
		pushButtonArray[5].press();
		pushButtonListenerStub.assertProtocol();
	}
	
	@Test
	public void testConstructorForPressPushButtonController() {
		
		assertFalse( buttonSelectionController == null);
	}
	
	@Test
	public void testGetIndexForButtonLowerBound() {
		
		assertEquals("Index should have value", 0, buttonSelectionController.getIndex(pushButtonArray[0]));
	}
	
	@Test
	public void testGetIndexForButtonUpperBound() {
		
		assertEquals("Index should have value", 5, buttonSelectionController.getIndex(pushButtonArray[5]));
	}
	
	@Test
	public void testGetIndexForButtonInvalidIndex() {
		
		numberOfButtons = 3;
		buttonSelectionController = new ButtonSelectionController(inventoryController, fundsController, pushButtonArray, numberOfButtons);
		assertEquals("Index should have value", -1, buttonSelectionController.getIndex(pushButtonArray[5]));
	}

}
