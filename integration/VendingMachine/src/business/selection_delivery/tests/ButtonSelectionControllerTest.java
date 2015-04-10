package business.selection_delivery.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import business.funds.FundsController;
import business.funds.PaymentMethods;
import business.funds.TransactionReturnCode;
import SDK.logger.Logger;
import SDK.rifffish.Machine;
import SDK.rifffish.Rifffish;
import business.selection_delivery.ButtonSelectionController;
import business.selection_delivery.InventoryController;
import hardware.acceptors.AbstractProductAcceptor;
import hardware.channels.ProductChannel;
import hardware.funds.CoinReceptacle;
import hardware.funds.CoinSlot;
import hardware.racks.CoinRack;
import hardware.racks.ProductRack;
import hardware.ui.DeliveryChute;
import hardware.ui.IndicatorLight;
import hardware.ui.PushButton;

public class ButtonSelectionControllerTest {

	private ButtonSelectionController buttonSelectionController;
	private Mockery context;

	//All the Controllers needed for ButtonSelectionController
	private  InventoryController inventoryController;
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
	private List<PaymentMethods> availablePaymentMethods;
	
	private boolean bestEffortChange;
	private int[] coinRackDenominations;
	private CoinSlot coinSlot;
	private int[] validValues = {5,10,25,100,200};
	
	CoinReceptacle tempCoinReceptacle;
	int tempCoinRecepticleBalance;
	
	CoinReceptacle overflowCoinReceptacle;
	Map<Integer, Integer> coinOverflowCoinRecepticleQuantities;
	
	private CoinRack[] coinRacks;
	private int[] coinRackQuantities;
	
	
	IndicatorLight outOfOrderLight;
	Logger logger = new Logger();





//	@BeforeClass
//	public static void BeforeRoutine() {
//
//
//	}

	@Before
	public void setup() {
		
		context = new Mockery() {{
			setImposteriser(ClassImposteriser.INSTANCE);
		}};

		pushButtonListenerStub = new PushButtonListenerStub();

		rackcount = 6;
		numberOfButtons = 6;
		
		//Setting up the push buttons
		pushButtonArray = new PushButton[rackcount];
		for(int i = 0; i < rackcount; i++ )
		{
			pushButtonArray[i] = new PushButton();
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
			
		inventoryController = new InventoryController(rack, rackcount, names, costs, quantity, null, new Logger(), 0);

		//Setting up the funds manager
		bestEffortChange = true;
		coinSlot = new CoinSlot(validValues);
		
		tempCoinReceptacle = new CoinReceptacle(100);
		overflowCoinReceptacle = new CoinReceptacle(100);
		
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
		
		fundsController = 	context.mock(FundsController.class);
		
		buttonSelectionController = new ButtonSelectionController(inventoryController, fundsController, pushButtonArray, numberOfButtons);

		//Add sinks to racks
		AbstractProductAcceptor acceptor = new DeliveryChute(100);
		ProductChannel sink = new ProductChannel(acceptor);
		
		for(int i=0; i<rackcount; i++){
			inventoryController.getRack(i).connect(sink);
		}

		pushButtonListenerStub.init();
	}

	@After
	public void teardown() {
		context = null;
		
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
		context.checking(new Expectations(){
			{
			atLeast(1).of(fundsController).ConductTransaction(with(any(Integer.class)),with(any(Integer.class)));
			will(returnValue(TransactionReturnCode.SUCCESSFUL));
			}
		});
		//pushButtonListenerStub.expect("pressed");
		buttonSelectionController.pressed(pushButtonArray[0]);
		//pushButtonListenerStub.assertProtocol();
	}
	
	@Test
	public void testPressPushButtonUpperBound() {
		context.checking(new Expectations(){
			{
			atLeast(1).of(fundsController).ConductTransaction(with(any(Integer.class)),with(any(Integer.class)));
			will(returnValue(TransactionReturnCode.SUCCESSFUL));
			}
		});
		//pushButtonListenerStub.expect("pressed");
		buttonSelectionController.pressed(pushButtonArray[5]);
		//pushButtonListenerStub.assertProtocol();
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
