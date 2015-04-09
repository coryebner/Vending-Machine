package business.funds.tests;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.TreeMap;

import hardware.funds.Coin;
import hardware.ui.IndicatorLight;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.funds.CoinStorageBinController;

public class CoinStorageBinControllerTest {

	CoinStorageBinController controller;
	IndicatorLight outOfOrderLight;
	Map<Integer,Integer> rackDenominations;
	
	@Before
	public void setUp() throws Exception {
		outOfOrderLight = new IndicatorLight();
		rackDenominations = new TreeMap<Integer,Integer>();
		
			rackDenominations.put(5, 0);
			rackDenominations.put(10, 0);
			rackDenominations.put(25, 0);
			rackDenominations.put(100, 0);
			rackDenominations.put(200, 0);
		controller = new CoinStorageBinController(rackDenominations, outOfOrderLight);
	}

	@After
	public void tearDown() throws Exception {
		controller = null;
		rackDenominations = null;
	}

	@Test
	public void testGetNumberOfDenominations() {
		assertEquals(rackDenominations.keySet().size(), controller.getNumberOfDenominations());
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
