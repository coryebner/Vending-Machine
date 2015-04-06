package business.funds.tests;

import static org.junit.Assert.*;
import hardware.funds.Coin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.funds.StorageBinTracker;

public class StorageBinTrackerTest {

	StorageBinTracker tracker;
	int[] rackDenominations;
	
	@Before
	public void setUp() throws Exception {
		rackDenominations = new int[5];
		rackDenominations[0] = 5;
		rackDenominations[1] = 10;
		rackDenominations[2] = 25;
		rackDenominations[3] = 100;
		rackDenominations[4] = 200;
		tracker = new StorageBinTracker(rackDenominations);
	}

	@After
	public void tearDown() throws Exception {
		tracker = null;
		rackDenominations = null;
	}

	@Test
	public void testGetNumberOfDenominations() {
		assertEquals(rackDenominations.length, tracker.getNumberOfDenominations());
	}

	@Test
	public void testGetValueAtIndex() {
		assertEquals(rackDenominations[3],tracker.getValueAtIndex(3));
	}

	@Test
	public void testGetNumberOfCoinsByIndex() {
		tracker.coinAdded(null, new Coin(10));
		tracker.coinAdded(null, new Coin(10));
		tracker.coinAdded(null, new Coin(10));
		assertEquals(3,tracker.getNumberOfCoinsByIndex(1));
	}

	@Test
	public void testGetTotalValueStoredInBin() {
		tracker.coinAdded(null, new Coin(5));
		tracker.coinAdded(null, new Coin(10));
		tracker.coinAdded(null, new Coin(10));
		tracker.coinAdded(null, new Coin(25));
		tracker.coinAdded(null, new Coin(100));
		tracker.coinAdded(null, new Coin(200));
		assertEquals(350, tracker.getTotalValueStoredInBin());
	}

}
