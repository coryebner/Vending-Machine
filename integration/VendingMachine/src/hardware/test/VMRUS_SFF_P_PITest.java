package hardware.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.exceptions.NoSuchHardwareException;
import hardware.exceptions.SimulationException;
import hardware.funds.Card;
import hardware.funds.CardSlotListener;
import hardware.funds.Coin;
import hardware.products.PopCan;
import hardware.racks.CoinRack;
import hardware.racks.ProductRack;
import hardware.simulators.AbstractVendingMachine;
import hardware.simulators.VMRUS_SFF_P_PI;
import hardware.test.stub.CardSlotListenerStub;
import hardware.test.stub.CoinRackListenerStub;
import hardware.test.stub.CoinReceptacleListenerStub;
import hardware.test.stub.CoinSlotListenerStub;
import hardware.test.stub.DeliveryChuteListenerStub;
import hardware.test.stub.IndicatorLightListenerStub;
import hardware.test.stub.ProductRackListenerStub;
import hardware.test.stub.PushButtonListenerStub;
import hardware.ui.IndicatorLight;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VMRUS_SFF_P_PITest {

	private final int NO_COINRACKS = 0;
	private final int NO_PRODUCTRACKS = 6;
	private final int NO_SELECTIONBUTTONS = 6;

	private AbstractVendingMachine hardware;

	private CardSlotListenerStub cardSlotListener;
	private DeliveryChuteListenerStub deliveryChuteListener;
	private ProductRackListenerStub[] productRackListeners;
	private IndicatorLightListenerStub outOfOrderListener;
	private PushButtonListenerStub[] pushButtonListeners;
	private IndicatorLightListenerStub outOfProductListeners[];
	private Card card;

	@Before
	public void setup() throws NoSuchHardwareException {
		card = new Card(Card.CardType.PREPAID, "123", "JoeyJoey", "1234", "06/2018", Locale.CANADA, 100);
		hardware = new VMRUS_SFF_P_PI(Locale.CANADA);
		cardSlotListener = new CardSlotListenerStub();
		deliveryChuteListener = new DeliveryChuteListenerStub();

		productRackListeners = new ProductRackListenerStub[6];
		for (int i = 0; i < NO_PRODUCTRACKS; i++) {
			productRackListeners[i] = new ProductRackListenerStub();
			hardware.getProductRack(i).register(productRackListeners[i]);
		}

		pushButtonListeners = new PushButtonListenerStub[NO_SELECTIONBUTTONS];
		for (int i = 0; i < NO_SELECTIONBUTTONS; i++) {
			pushButtonListeners[i]=new PushButtonListenerStub();
			hardware.getSelectionButton(i).register(pushButtonListeners[i]);
		}

		outOfOrderListener = new IndicatorLightListenerStub();
		
		outOfProductListeners = new IndicatorLightListenerStub[NO_SELECTIONBUTTONS];
		for(int i=0; i<NO_SELECTIONBUTTONS; i++){
			outOfProductListeners[i]=new IndicatorLightListenerStub();
			hardware.getOutOfProductLight(i).register(outOfProductListeners[i]);
		}

		hardware.getCardSlot().register(cardSlotListener);
		hardware.getDeliveryChute().register(deliveryChuteListener);
		hardware.getOutOfOrderLight().register(outOfOrderListener);
	}

	@After
	public void teardown() throws NoSuchHardwareException {
		card=null;
		hardware.getCardSlot().deregister(cardSlotListener);
		hardware.getDeliveryChute().deregister(deliveryChuteListener);
		hardware.getOutOfOrderLight().deregister(outOfOrderListener);


		for (int i = 0; i < NO_PRODUCTRACKS; i++) {
			hardware.getProductRack(i).deregister(productRackListeners[i]);
			productRackListeners[i] = null;
		}

		for (int i = 0; i < NO_SELECTIONBUTTONS; i++) {
			hardware.getSelectionButton(i).deregisterAll();
			pushButtonListeners[i] = null;
		}
		
		for (int i = 0; i < NO_SELECTIONBUTTONS; i++) {
			hardware.getOutOfProductLight(i).deregisterAll();
			outOfProductListeners[i] = null;
		}

		hardware.getReturnButton().deregisterAll();
		

		hardware = null;

		cardSlotListener = null;
		deliveryChuteListener = null;

		productRackListeners = null;
		outOfOrderListener = null;
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
		cardSlotListener.expect("disabled", "enabled");
		deliveryChuteListener.expect("disabled", "enabled");
		outOfOrderListener.expect("activated", "deactivated");
		for (int i = 0; i < productRackListeners.length; i++)
			productRackListeners[i].expect("disabled", "enabled");

		assertFalse(hardware.isSafetyEnabled());
		hardware.enableSafety();
		assertTrue(hardware.isSafetyEnabled());
		hardware.disableSafety();
		assertFalse(hardware.isSafetyEnabled());
		cardSlotListener.assertProtocol();
		deliveryChuteListener.assertProtocol();
		for (int i = 0; i < productRackListeners.length; i++)
			productRackListeners[i].assertProtocol();
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

	// test dispense pop
	@Test
	public void testDispensePopRacks() throws NoSuchHardwareException, InvocationTargetException {
		ProductRack rack;
		Object[] chuteContents = hardware.getDeliveryChute().removeItems();
		assertTrue(chuteContents.length == 0);
		for (int i = 0; i < hardware.getNumberOfProductRacks(); i++) {
			rack = hardware.getProductRack(i);
			deliveryChuteListener.expect("itemDelivered");
			try {
				rack.addProduct(new PopCan());
				rack.dispenseProduct();
			} catch (Exception e) {
				fail("Unexpected Exception: " + e);
			}
			deliveryChuteListener.assertProtocol();
			chuteContents = hardware.getDeliveryChute().removeItems();
			assertTrue(chuteContents.length == 1);
			assertTrue(chuteContents[0].getClass() == PopCan.class);
		}

	}

	//test out of product indicator light
	@Test
	public void testOutOfProductIndicatorLights() throws NoSuchHardwareException{
		for(int i=0; i<NO_SELECTIONBUTTONS; i++){
			outOfProductListeners[i].expect("activated");
			hardware.getOutOfProductLight(i).activate();
			outOfProductListeners[i].assertProtocol();
		}
		for(int i=0; i<NO_SELECTIONBUTTONS; i++){
			outOfProductListeners[i].expect("deactivated");
			hardware.getOutOfProductLight(i).deactivate();
			outOfProductListeners[i].assertProtocol();
		}
	}
	//test card slot
	@Test
	public void testCardSlotInsertAndReturn() throws NoSuchHardwareException{
		cardSlotListener.expect("cardInserted");
		try{
			hardware.getCardSlot().insertCard(card);
		}catch(Exception e){
			fail("Unexpected Exception: "+e);
		}
		cardSlotListener.assertProtocol();
		cardSlotListener.expect("cardEjected");
		try{
			hardware.getCardSlot().ejectCard();
		}catch(Exception e){
			fail("Unexpected Exception: "+e);
		}
	}
	
	@Test
	public void testInsertCardWhileDisabled(){
		Exception caught=null;
		hardware.enableSafety();
		try{
			hardware.getCardSlot().insertCard(card);
		}catch(Exception e){
			caught=e;
		}
		assertTrue(caught.getClass()==DisabledException.class);
	}
	
	@Test
	public void testEjectCardWhileDisabled(){
		Exception caught=null;

		try{
			hardware.getCardSlot().insertCard(card);
		}catch(Exception e){
			caught=e;
		}
		assertTrue(caught==null);
		hardware.enableSafety();
		try{
			hardware.getCardSlot().ejectCard();
		}catch(Exception e){
			caught=e;
		}
		assertTrue(caught.getClass()==DisabledException.class);
	}
	

}
