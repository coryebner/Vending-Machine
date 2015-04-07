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

	}

	@Test
	public void isFullTest() {
		assert(!controller.isFull());
		
		while(!controller.isFull()){
			controller.banknoteAdded(null, new Banknote(5));
		}
		
		assert(controller.isFull());
	}

	@Test
	public void banknoteAddedTest() {
		
	}

	@Test
	public void banknoteRemovedTest() {

	}

	@Test
	public void banknoteStorageBinFullLightTest() {
		int value = 5;
		controller.banknoteAdded(null, new Banknote(value));
		controller.banknoteAdded(null, new Banknote(value));
		controller.banknoteAdded(null, new Banknote(value));
		controller.banknoteAdded(null, new Banknote(value));
		controller.banknoteAdded(null, new Banknote(value));
		assert(outOfOrderLight.isActive());
	}

}
