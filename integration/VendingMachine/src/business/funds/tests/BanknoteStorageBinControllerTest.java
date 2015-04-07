package business.funds.tests;

import hardware.funds.Banknote;
import hardware.ui.IndicatorLight;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.funds.BanknoteStorageBinController;
import static org.junit.Assert.assertEquals;

public class BanknoteStorageBinControllerTest {

	BanknoteStorageBinController controller;
	IndicatorLight outOfOrderLight;

	int quantity = 0;
	
	@Before
	public void setup() {
		outOfOrderLight = new IndicatorLight();
		controller = new BanknoteStorageBinController(quantity, outOfOrderLight);
	}

	@After
	public void teardown() {
		controller = null;
		outOfOrderLight = null;
		quantity = 0;
	}

	@Test
	public void getQuantityTest() {
		assertEquals(0, controller.getQuantity());
		controller.banknoteAdded(null, new Banknote(5));
		assertEquals(1, controller.getQuantity());
		controller.banknoteAdded(null, new Banknote(5));
		assertEquals(2, controller.getQuantity());
	}

//	@Test
//	public void isFullTest() {
//		assert(!controller.isFull());
//		
//		while(!controller.isFull()){
//			controller.banknoteAdded(null, new Banknote(5));
//		}
//		
//		assert(controller.isFull());
//	}

	@Test
	public void banknoteAddedTest() {
		controller.banknoteAdded(null, new Banknote(5));
		controller.banknoteAdded(null, new Banknote(5));
		assertEquals(2, controller.getQuantity());
	}

	@Test
	public void banknoteRemovedTest() {
		// Add a few notes
		controller.banknoteAdded(null, new Banknote(5));
		controller.banknoteAdded(null, new Banknote(5));
		controller.banknoteAdded(null, new Banknote(5));		
		controller.BanknoteRemoved(null);
		assertEquals(0, controller.getQuantity());
	}

	@Test
	public void banknoteStorageBinFullLightTest() {
		int value = 5;
		controller.banknoteAdded(null, new Banknote(value));
		assert(outOfOrderLight.isActive());
	}

}
