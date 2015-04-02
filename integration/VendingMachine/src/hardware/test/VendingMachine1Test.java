//INCOMPLETE: Tests for Out of Product Lights, Return Button
package hardware.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.AbstractVendingMachine;
import hardware.VendingMachine1;
import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.exceptions.NoSuchHardwareException;
import hardware.exceptions.SimulationException;
import hardware.funds.Coin;
import hardware.products.PopCan;
import hardware.racks.CoinRack;
import hardware.racks.PopCanRack;
import hardware.racks.ProductRack;
import hardware.test.stub.CoinRackListenerStub;
import hardware.test.stub.CoinReceptacleListenerStub;
import hardware.test.stub.CoinSlotListenerStub;
import hardware.test.stub.DeliveryChuteListenerStub;
import hardware.test.stub.IndicatorLightListenerStub;
import hardware.test.stub.PopCanRackListenerStub;
import hardware.test.stub.PushButtonListenerStub;
import hardware.ui.IndicatorLight;
import hardware.ui.PushButtonListener;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VendingMachine1Test {
	
	private final int NO_COINRACKS=6;
	private final int NO_PRODUCTRACKS=6;
	private final int NO_SELECTIONBUTTONS=6;
	
	private AbstractVendingMachine hardware;
	private Coin coin;

	private CoinSlotListenerStub coinSlotListener;
	private CoinReceptacleListenerStub coinReceptacleListener,
			storageBinListener;
	// private CardSlotListenerStub cardSlotListener;
	private DeliveryChuteListenerStub deliveryChuteListener;
	private CoinRackListenerStub[] coinRackListeners;
	private PopCanRackListenerStub[] popRackListeners;
	private IndicatorLightListenerStub outOfOrderListener, exactChangeListener;
	private PushButtonListenerStub[] pushButtonListeners;
	private PushButtonListenerStub returnButtonListener;

	@Before
	public void setup() throws NoSuchHardwareException {
		hardware = new VendingMachine1(new int[] { 5, 10, 25, 100, 200 },
				new int[] { 150, 150, 200, 200, 200, 200 }, new String[] {
						"Aquafina", "Aquafina", "Coca-Cola", "Coca-Cola",
						"Coke Zero", "7Up" });

		coin = new Coin(100);

		coinSlotListener = new CoinSlotListenerStub();
		coinReceptacleListener = new CoinReceptacleListenerStub();
		storageBinListener = new CoinReceptacleListenerStub();
		// cardSlotListener = new CardSlotListenerStub();
		deliveryChuteListener = new DeliveryChuteListenerStub();
		coinRackListeners = new CoinRackListenerStub[NO_COINRACKS];
		for (int i = 0; i < NO_COINRACKS; i++) {
			coinRackListeners[i] = new CoinRackListenerStub();
			hardware.getCoinRack(i).register(coinRackListeners[i]);
		}
		
		popRackListeners = new PopCanRackListenerStub[6];
		for (int i = 0; i < NO_PRODUCTRACKS; i++) {
			popRackListeners[i] = new PopCanRackListenerStub();
			hardware.getProductRack(i).register(popRackListeners[i]);
		}
		
		pushButtonListeners=new PushButtonListenerStub[NO_SELECTIONBUTTONS];
		for(int i=0; i<NO_SELECTIONBUTTONS; i++){
			hardware.getSelectionButton(i).register(pushButtonListeners[i]);
		}
		
		returnButtonListener=new PushButtonListenerStub();
		hardware.getReturnButton().register(returnButtonListener);
		
		outOfOrderListener = new IndicatorLightListenerStub();
		exactChangeListener = new IndicatorLightListenerStub();

		hardware.getCoinSlot().register(coinSlotListener);
		hardware.getCoinReceptacle().register(coinReceptacleListener);
		hardware.getStorageBin().register(storageBinListener);
		// hardware.getCardSlot().register(cardSlotListener);
		hardware.getDeliveryChute().register(deliveryChuteListener);
		hardware.getExactChangeLight().register(exactChangeListener);
		hardware.getOutOfOrderLight().register(outOfOrderListener);
	}

	@After
	public void teardown() throws NoSuchHardwareException {
		hardware.getCoinSlot().deregister(coinSlotListener);
		hardware.getCoinReceptacle().deregister(coinReceptacleListener);
		hardware.getStorageBin().deregister(storageBinListener);
		// hardware.getCardSlot().deregister(cardSlotListener);
		hardware.getDeliveryChute().deregister(deliveryChuteListener);
		hardware.getExactChangeLight().deregister(exactChangeListener);
		hardware.getOutOfOrderLight().deregister(outOfOrderListener);

		for (int i = 0; i < NO_COINRACKS; i++) {
			hardware.getCoinRack(i).deregister(coinRackListeners[i]);
			coinRackListeners[i] = null;
		}

		for (int i = 0; i < NO_PRODUCTRACKS; i++) {
			hardware.getProductRack(i).deregister(popRackListeners[i]);
			popRackListeners[i] = null;
		}
		
		for(int i=0; i<NO_SELECTIONBUTTONS; i++){
			hardware.getSelectionButton(i).deregisterAll();
			pushButtonListeners[i]=null;
		}
		
		hardware.getReturnButton().deregisterAll();
		returnButtonListener=null;

		hardware = null;
		coin = null;
		coinSlotListener = null;
		coinReceptacleListener = null;
		// cardSlotListener = null;
		deliveryChuteListener = null;
		storageBinListener = null;
		coinRackListeners = null;
		popRackListeners = null;
		exactChangeListener = null;
		outOfOrderListener = null;
	}

	@Test(expected = SimulationException.class)
	public void testNullCoinValues() {
		hardware = new VendingMachine1(null, new int[] { 150, 150, 200, 200,
				200, 200 }, new String[] { "Aquafina", "Aquafina", "Coca-Cola",
				"Coca-Cola", "Coke Zero", "7Up" });
	}

	@Test(expected = SimulationException.class)
	public void testNullPopCosts() {
		hardware = new VendingMachine1(new int[] { 5, 10, 25, 100, 200 }, null,
				new String[] { "Aquafina", "Aquafina", "Coca-Cola",
						"Coca-Cola", "Coke Zero", "7Up" });
	}

	@Test(expected = SimulationException.class)
	public void testNullPopNames() {
		hardware = new VendingMachine1(new int[] { 5, 10, 25, 100, 200 },
				new int[] { 150, 150, 200, 200, 200, 200 }, null);
	}

	@Test(expected = SimulationException.class)
	public void testInconsistentPopData() {
		hardware = new VendingMachine1(new int[] { 5, 10, 25, 100, 200 },
				new int[] { 150, 150, 200, 200, 200, 200 }, new String[] {
						"Aquafina", "Coca-Cola", "Coca-Cola", "Coke Zero",
						"7Up" });
	}

	@Test
	public void testCoinEntry() throws CapacityExceededException,
			DisabledException, NoSuchHardwareException {
		coinReceptacleListener.expect("coinAdded");
		coinSlotListener.expect("validCoinInserted");
		hardware.getCoinSlot().addCoin(coin);
		coinSlotListener.assertProtocol();
		coinReceptacleListener.assertProtocol();

		coinSlotListener.init();
		coinReceptacleListener.init();
		coinReceptacleListener.expect("coinsRemoved");
		deliveryChuteListener.expect("itemDelivered");
		hardware.getCoinReceptacle().returnCoins();
		coinReceptacleListener.assertProtocol();
		deliveryChuteListener.assertProtocol();

		coinReceptacleListener.init();
		deliveryChuteListener.init();
		deliveryChuteListener.expect("doorOpened", "doorClosed");
		Object[] items = hardware.getDeliveryChute().removeItems();
		assertTrue(items.length == 1);
		assertTrue(items[0] == coin);
		deliveryChuteListener.assertProtocol();
	}

	@Test
	public void testCoinEntryAndStoreToRack() throws CapacityExceededException,
			DisabledException, NoSuchHardwareException {
		coinSlotListener.expect("validCoinInserted");
		coinReceptacleListener.expect("coinAdded");
		hardware.getCoinSlot().addCoin(coin);
		coinSlotListener.assertProtocol();
		coinReceptacleListener.assertProtocol();

		coinSlotListener.init();
		coinReceptacleListener.init();
		coinReceptacleListener.expect("coinsRemoved");
		coinRackListeners[3].expect("coinAdded");
		hardware.getCoinReceptacle().storeCoins();
		coinReceptacleListener.assertProtocol();
		coinRackListeners[3].assertProtocol();
	}

	@Test
	public void testCoinEntryAndStoreToBin() throws CapacityExceededException,
			DisabledException, NoSuchHardwareException {
		int capacity = hardware.getCoinRack(3).getMaxCapacity(); // loonies rack

		for (int i = 0; i < capacity; i++) {
			coinSlotListener.expect("validCoinInserted");
			coinReceptacleListener.expect("coinAdded");
		}

		for (int i = 0; i < capacity; i++) {
			hardware.getCoinSlot().addCoin(coin);
		}

		coinSlotListener.assertProtocol();
		coinReceptacleListener.assertProtocol();

		coinSlotListener.init();
		coinReceptacleListener.init();
		coinSlotListener.expect("validCoinInserted");
		coinReceptacleListener.expect("coinAdded");
		hardware.getCoinSlot().addCoin(coin);
		coinSlotListener.assertProtocol();
		coinReceptacleListener.assertProtocol();

		coinSlotListener.init();
		coinReceptacleListener.init();
		coinReceptacleListener.expect("coinsRemoved");
		for (int i = 0; i < capacity; i++) {
			coinRackListeners[3].expect("coinAdded");
		}
		coinRackListeners[3].expect("coinsFull");
		storageBinListener.expect("coinAdded");
		hardware.getCoinReceptacle().storeCoins();
		coinReceptacleListener.assertProtocol();
		coinRackListeners[3].assertProtocol();
		storageBinListener.assertProtocol();
	}

	@Test
	public void testCoinEntryWhenDisabled() throws CapacityExceededException,
			DisabledException, NoSuchHardwareException {
		coinSlotListener.expect("disabled");
		// cardSlotListener.expect("disabled");
		deliveryChuteListener.expect("disabled");
		outOfOrderListener.expect("activated");
		for (int i = 0; i < coinRackListeners.length; i++)
			coinRackListeners[i].expect("disabled");
		for (int i = 0; i < popRackListeners.length; i++)
			popRackListeners[i].expect("disabled");

		hardware.enableSafety();

		coinSlotListener.assertProtocol();
		// cardSlotListener.assertProtocol();
		outOfOrderListener.assertProtocol();
		deliveryChuteListener.assertProtocol();
		for (int i = 0; i < coinRackListeners.length; i++)
			coinRackListeners[i].assertProtocol();
		for (int i = 0; i < popRackListeners.length; i++)
			popRackListeners[i].assertProtocol();

		outOfOrderListener.init();
		coinSlotListener.init();
		for (int i = 0; i < coinRackListeners.length; i++)
			coinRackListeners[i].init();
		for (int i = 0; i < popRackListeners.length; i++)
			popRackListeners[i].init();
		try {
			hardware.getCoinSlot().addCoin(coin);
			fail();
		} catch (DisabledException e) {
		}

		hardware.getCoinReceptacle().returnCoins();

		coinReceptacleListener.init();
		deliveryChuteListener.init();
		deliveryChuteListener.expect("doorOpened", "doorClosed");
		Object[] items = hardware.getDeliveryChute().removeItems();
		assertTrue(items.length == 0);
		deliveryChuteListener.assertProtocol();
	}

	@Test
	public void testCoinEntryAndStoreToRackWhenDisabled()
			throws CapacityExceededException, DisabledException,
			NoSuchHardwareException {
		coinSlotListener.expect("disabled");
		// cardSlotListener.expect("disabled");
		deliveryChuteListener.expect("disabled");
		outOfOrderListener.expect("activated");
		for (int i = 0; i < coinRackListeners.length; i++)
			coinRackListeners[i].expect("disabled");
		for (int i = 0; i < popRackListeners.length; i++)
			popRackListeners[i].expect("disabled");

		hardware.enableSafety();

		coinSlotListener.assertProtocol();
		// cardSlotListener.assertProtocol();
		deliveryChuteListener.assertProtocol();
		for (int i = 0; i < coinRackListeners.length; i++)
			coinRackListeners[i].assertProtocol();
		for (int i = 0; i < popRackListeners.length; i++)
			popRackListeners[i].assertProtocol();

		coinSlotListener.init();
		outOfOrderListener.init();
		for (int i = 0; i < coinRackListeners.length; i++)
			coinRackListeners[i].init();
		for (int i = 0; i < popRackListeners.length; i++)
			popRackListeners[i].init();

		hardware.getCoinReceptacle().storeCoins();
	}

	@Test(expected = SimulationException.class)
	public void testBadEvent() {
		class DummyHardware extends AbstractHardware<AbstractHardwareListener> {
			public void foo() {
				notifyListeners(AbstractHardwareListener.class, "foo", null,
						null);
			}
		}

		new DummyHardware().foo();
	}

	@Test
	public void testHardwareDisabledAndReenabled() {
		coinSlotListener.expect("disabled", "enabled");
		// cardSlotListener.expect("disabled", "enabled");
		deliveryChuteListener.expect("disabled", "enabled");
		outOfOrderListener.expect("activated", "deactivated");
		for (int i = 0; i < coinRackListeners.length; i++)
			coinRackListeners[i].expect("disabled", "enabled");
		for (int i = 0; i < popRackListeners.length; i++)
			popRackListeners[i].expect("disabled", "enabled");

		assertFalse(hardware.isSafetyEnabled());
		hardware.enableSafety();
		assertTrue(hardware.isSafetyEnabled());
		hardware.disableSafety();
		assertFalse(hardware.isSafetyEnabled());

		coinSlotListener.assertProtocol();
		// cardSlotListener.assertProtocol();
		deliveryChuteListener.assertProtocol();
		for (int i = 0; i < coinRackListeners.length; i++)
			coinRackListeners[i].assertProtocol();
		for (int i = 0; i < popRackListeners.length; i++)
			popRackListeners[i].assertProtocol();
	}

	@Test
	public void testOutOfOrderLight() throws NoSuchHardwareException {
		IndicatorLight outOfOrderLight = hardware.getOutOfOrderLight();
		outOfOrderListener.expect("deactivated", "activated");

		outOfOrderLight.loadWithoutEvents(true);
		assertTrue(outOfOrderLight.isActive());
		outOfOrderLight.deactivate();
		assertFalse(outOfOrderLight.isActive());
		outOfOrderLight.activate();
		assertTrue(outOfOrderLight.isActive());

		outOfOrderListener.assertProtocol();
	}
	@Test
	public void testExactChangeLight() throws NoSuchHardwareException {
		IndicatorLight exactChangeLight = hardware.getExactChangeLight();
		exactChangeListener.expect("deactivated", "activated");

		exactChangeLight.loadWithoutEvents(true);
		assertTrue(exactChangeLight.isActive());
		exactChangeLight.deactivate();
		assertFalse(exactChangeLight.isActive());
		exactChangeLight.activate();
		assertTrue(exactChangeLight.isActive());

		exactChangeListener.assertProtocol();
	}

	@Test
	public void testGetCoinRacks() throws NoSuchHardwareException {
		int count = hardware.getNumberOfCoinRacks();
		assertTrue(count == 5);
		assertFalse(hardware.getCoinRack(0) == null);
		assertFalse(hardware.getCoinRack(count - 1) == null);
		try {
			hardware.getCoinRack(count);
			fail();
		} catch (IndexOutOfBoundsException e) {
		}
	}

	@Test
	public void testGetPopRacks() throws NoSuchHardwareException {
		int count = hardware.getNumberOfProductRacks();
		assertTrue(count == 6);
		assertFalse(hardware.getProductRack(0) == null);
		assertFalse(hardware.getProductRack(count - 1) == null);
		try {
			hardware.getProductRack(count);
			fail();
		} catch (IndexOutOfBoundsException e) {
		}
	}

	@Test
	public void testGetSelectionButtons() throws NoSuchHardwareException {
		int count = hardware.getNumberOfSelectionButtons();
		assertTrue(count == 6);
		assertFalse(hardware.getSelectionButton(0) == null);
		assertFalse(hardware.getSelectionButton(count - 1) == null);
		try {
			hardware.getSelectionButton(count);
			fail();
		} catch (IndexOutOfBoundsException e) {
		}
	}

	@Test
	public void testGetDisplay() throws NoSuchHardwareException {
		assertFalse(hardware.getDisplay() == null);
	}
	
	//test dispense pop
	@Test
	public void testDispensePopRacks() throws NoSuchHardwareException{
		PopCanRack rack;
		Object[] chuteContents=hardware.getDeliveryChute().removeItems();
		assertTrue(chuteContents.length==0);
		for(int i=0; i<hardware.getNumberOfProductRacks(); i++){
			rack=hardware.getProductRack(i);
			deliveryChuteListener.expect("itemDelivered");
			try{
				rack.addPop(new PopCan());
				rack.dispensePop();
			}catch(Exception e){
				fail("Unexpected Exception: "+e);
			}
			deliveryChuteListener.assertProtocol();
			chuteContents=hardware.getDeliveryChute().removeItems();
			assertTrue(chuteContents.length==1);
			assertTrue(chuteContents[0].getClass()==PopCan.class);
		}
			
	}
	//test dispense coin
	@Test
	public void testReleaseCoinRacks() throws NoSuchHardwareException{
		CoinRack rack;
		Object[] chuteContents=hardware.getDeliveryChute().removeItems();
		assertTrue(chuteContents.length==0);
		for(int i=0; i<hardware.getNumberOfCoinRacks(); i++){
			rack=hardware.getCoinRack(i);
			deliveryChuteListener.expect("itemDelivered");
			try{
				rack.acceptCoin(new Coin(2));
				rack.releaseCoin();
			}catch(Exception e){
				fail("Unexpected Exception: "+e);
			}
		}
		deliveryChuteListener.assertProtocol();
		chuteContents=hardware.getDeliveryChute().removeItems();
		assertTrue(chuteContents.length==1);
		assertTrue(chuteContents[0].getClass()==Coin.class);
	} 
	//test return button
	@Test
	public void testReturnButton() throws NoSuchHardwareException{
		returnButtonListener.expect("pressed");
		hardware.getReturnButton().press();
		returnButtonListener.assertProtocol();
		
	}
	
}
