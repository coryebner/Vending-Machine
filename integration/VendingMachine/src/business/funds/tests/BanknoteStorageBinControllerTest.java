package business.funds.tests;

import hardware.funds.Banknote;
import hardware.ui.IndicatorLight;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.funds.BanknoteStorageBinController;
import static org.junit.Assert.assertEquals;

/**
 * Banknote Storage Bin Controller Tests
 * Unit tests
 * @author Andrei Savu
 *
 */
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
	/**
	 * Public test for the getQuantity method in the controller
	 * will test 0,1 and 2 levels
	 */
	public void getQuantityTest() {
		assertEquals(0, controller.getQuantity());
		controller.banknoteAdded(null, new Banknote(5));
		assertEquals(1, controller.getQuantity());
		controller.banknoteAdded(null, new Banknote(5));
		assertEquals(2, controller.getQuantity());
	}

	@Test
	/**
	 * Public test for the banknoteAdded() method in the controller
	 * will add one note and check if controller quantity ++
	 */
	public void banknoteAddedTest() {
		controller.banknoteAdded(null, new Banknote(5));
		controller.banknoteAdded(null, new Banknote(5));
		assertEquals(2, controller.getQuantity());
	}

	@Test
	/**
	 * Public test for banknoteRemoved() method in the controller
	 * will add 3 notes and then remove all of them
	 * assert that quantity = 0
	 */
	public void banknoteRemovedTest() {
		// Add a few notes
		controller.banknoteAdded(null, new Banknote(5));
		controller.banknoteAdded(null, new Banknote(5));
		controller.banknoteAdded(null, new Banknote(5));		
		controller.BanknoteRemoved(null);
		assertEquals(0, controller.getQuantity());
	}

	@Test
	/**
	 * Public test for BanknotesFull() method in controller
	 * Will test is light is active
	 */
	public void banknoteStorageBinFullLightTest() {
		int value = 5;
		controller.banknoteAdded(null, new Banknote(value));
		assert(outOfOrderLight.isActive());
	}

}
