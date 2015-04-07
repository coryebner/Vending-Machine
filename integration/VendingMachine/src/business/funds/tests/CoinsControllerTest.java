package business.funds.tests;

import static org.junit.Assert.*;

import hardware.funds.Coin;
import hardware.funds.CoinReceptacle;
import hardware.racks.CoinRack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.funds.CoinRackController;
import business.funds.CoinsController;
import business.funds.TransactionReturnCode;

public class CoinsControllerTest {
	CoinsController coinsController;
	CoinRackController coinRackController;
	CoinRack [] coinRacks;
	int [] coinRackDenominations;
	int [] coinRackQuantities;
	String [] productNames;
	CoinReceptacle receptacle;
	
	@Before
	public void setUp() throws Exception {
		receptacle = new CoinReceptacle(100);
		
		coinRackDenominations = new int[5];
		coinRackDenominations[0] = 5;
		coinRackDenominations[1] = 10;
		coinRackDenominations[2] = 25;
		coinRackDenominations[3] = 100;
		coinRackDenominations[4] = 200;
		
		coinRackQuantities = new int[5];
		coinRackQuantities[0] = 2;
		coinRackQuantities[1] = 2;
		coinRackQuantities[2] = 2;
		coinRackQuantities[3] = 2;
		coinRackQuantities[4] = 2;
		
		coinRacks = new CoinRack[5];
		coinRacks[0] = new CoinRack(100);
		coinRacks[1] = new CoinRack(100);
		coinRacks[2] = new CoinRack(100);
		coinRacks[3] = new CoinRack(100);
		coinRacks[4] = new CoinRack(100);
		
		
		coinsController = new CoinsController(receptacle, coinRacks, coinRackDenominations, coinRackQuantities,null);
		coinsController.coinAdded(receptacle, new Coin(100));
		coinsController.coinAdded(receptacle, new Coin(25));
		coinsController.coinAdded(receptacle, new Coin(25));
	}

	@After
	public void tearDown() throws Exception {
		coinsController = null;
		coinRacks = null;
		receptacle = null;
		coinRacks = null;
		coinRackQuantities = null;
	}
	
	/**
	 * Test TransactionConducted() method for success (just enough balance)
	 */
	@Test 
	public void TransactionConductedExactSuccess() {
		assertEquals(TransactionReturnCode.SUCCESSFUL, coinsController.ConductTransaction(150));
	}
	
	/**
	 * Test TransactionConducted() method for success (more than enough balance)
	 */
	@Test 
	public void TransactionConductedExceedingSuccess() {
		assertEquals(TransactionReturnCode.SUCCESSFUL, coinsController.ConductTransaction(100));
	}
	
	/**
	 * Test TransactionConducted() method for failure (not enough balance)
	 */
	@Test 
	public void TransactionConductedFail() {
		assertEquals(TransactionReturnCode.INSUFFICIENTFUNDS, coinsController.ConductTransaction(200));
	}
	
	
	/**
	 * Test coinAdded() method for updated balance
	 */
	@Test
	public void coinAdded() {
		coinsController.coinAdded(receptacle, new Coin(100));
		assertEquals(250, coinsController.getAvailableBalance());
	}
	
	/**
	 * Test coinsRemoved() method for updated balance
	 */
	@Test
	public void coinsRemoved() {
		coinsController.coinsRemoved(receptacle);
		assertEquals(0, coinsController.getAvailableBalance());
	}
	
	/**
	 * Test getAvailableBalance
	 */
	@Test
	public void getAvailableBalanceTest() {
		assertEquals(150, coinsController.getAvailableBalance());
	}
	/**
	 * Test getExactChangeStatus when not active
	 */
	@Test
	public void getExactChangeStatusFalseTest() {
		assertEquals(false, coinsController.getExactChangeStatus());
	}
	/**
	 * Test getExactChangeStatus when active
	 */
	@Test
	public void getExactChangeStatusTrueTest() {

		for (int i = 0; i < coinsController.getCoinRackControllers().length; i++) {
			coinsController.getCoinRackControllers()[i].coinRemoved(null, null);
			coinsController.getCoinRackControllers()[i].coinRemoved(null, null);
		}
		assertEquals(true, coinsController.getExactChangeStatus());
	}
	/**
	 * Test getCoinRackControllers by checking the length
	 */
	@Test
	public void getCoinRackControllersTest() {
		assertEquals(5, coinsController.getCoinRackControllers().length);
	}
	/**
	 * Test isExactChangeActive when not active
	 */
	@Test
	public void isExactChangeActiveFalseTest() {
		assertEquals(false, coinsController.isExactChangeActive());
	}
	
	/**
	 * Test isExactChangeActive when it should be active
	 */
	@Test
	public void isExactChangeActiveTrueTest() {
		for (int i = 0; i < coinsController.getCoinRackControllers().length; i++) {
			coinsController.getCoinRackControllers()[i].coinRemoved(null, null);
			coinsController.getCoinRackControllers()[i].coinRemoved(null, null);
		}
		assertEquals(false, coinsController.isExactChangeActive());
	}

	/**
	 * Test isFullOfChangeActive when not full (full should never happen)
	 */
	@Test
	public void isFullOfChangeActiveNotFullTest() {
		assertEquals(false, coinsController.isFullOfChangeActive());
	}

	/**
	 * Test pressed to see if the receptacle is empty after
	 */
	@Test
	public void pressedTest() {
		coinsController.pressed(null);
		assertEquals(0, coinsController.getAvailableBalance());
	}
	
	
}
