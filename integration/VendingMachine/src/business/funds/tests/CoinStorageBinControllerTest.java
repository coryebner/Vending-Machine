package business.funds.tests;

import static org.junit.Assert.*;
import hardware.funds.Coin;
import hardware.ui.IndicatorLight;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.funds.CoinStorageBinController;

public class CoinStorageBinControllerTest {

	CoinStorageBinController controller;
	IndicatorLight outOfOrderLight;
	int[] rackDenominations;
	
	@Before
	public void setUp() throws Exception {
		outOfOrderLight = new IndicatorLight();
		rackDenominations = new int[5];
		rackDenominations[0] = 5;
		rackDenominations[1] = 10;
		rackDenominations[2] = 25;
		rackDenominations[3] = 100;
		rackDenominations[4] = 200;
		controller = new CoinStorageBinController(rackDenominations, outOfOrderLight);
	}

	@After
	public void tearDown() throws Exception {
		controller = null;
		rackDenominations = null;
	}

	@Test
	public void testGetNumberOfDenominations() {
		assertEquals(rackDenominations.length, controller.getNumberOfDenominations());
	}

	@Test
	public void testGetNumberOfCoinsByIndex() {
		int denomination = 10;
		controller.coinAdded(null, new Coin(denomination));
		controller.coinAdded(null, new Coin(denomination));
		controller.coinAdded(null, new Coin(denomination));
		assertEquals(3,controller.getQuantityByDenomination(denomination));
	}

	@Test
	public void testGetTotalValueStoredInBin() {
		controller.coinAdded(null, new Coin(5));
		controller.coinAdded(null, new Coin(10));
		controller.coinAdded(null, new Coin(10));
		controller.coinAdded(null, new Coin(25));
		controller.coinAdded(null, new Coin(100));
		controller.coinAdded(null, new Coin(200));
		assertEquals(350, controller.getTotalValueStoredInBin());
	}

}
