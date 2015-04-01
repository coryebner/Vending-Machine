package business.funds.tests;

import static org.junit.Assert.*;

import java.util.Locale;

import hardware.Hardware;
import hardware.exceptions.DisabledException;
import hardware.exceptions.EmptyException;
import hardware.funds.Card;
import hardware.funds.CardSlotNotEmptyException;
import hardware.funds.Coin;
import hardware.funds.CoinReceptacle;
import hardware.funds.CoinSlot;
import hardware.racks.CoinRack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.funds.CoinRackController;
import business.funds.CoinsController;
import business.funds.TransactionReturnCode;
import business.funds.VMCurrencies;

public class CoinsControllerTest {
	CoinsController coinsController;
	CoinRackController coinRackController;
	CoinRack [] coinRacks;
	int [] coinRackDenominations;
	int [] coinRackQuantities;
	int [] productPrices;
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
		
		productPrices = new int[3];
		productPrices[0] = 75;
		productPrices[1] = 100;
		productPrices[2] = 215;

		
		coinRacks = new CoinRack[5];
		coinRacks[0] = new CoinRack(100);
		coinRacks[1] = new CoinRack(100);
		coinRacks[2] = new CoinRack(100);
		coinRacks[3] = new CoinRack(100);
		coinRacks[4] = new CoinRack(100);
		
		
		coinsController = new CoinsController(false, coinRacks, coinRackDenominations, coinRackQuantities, productPrices);
		coinsController.coinAdded(receptacle, new Coin(100));
		coinsController.coinAdded(receptacle, new Coin(25));
		coinsController.coinAdded(receptacle, new Coin(25));
	}

	@After
	public void tearDown() throws Exception {
		coinsController = null;
		coinRacks = null;
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
	 * Test provideChange() method for success
	 */
	@Test
	public void provideChangeSuccess() {
		assertEquals(TransactionReturnCode.SUCCESSFUL, coinsController.provideChange(150));
	}
	/**
	 * Test provideChange() method for failure
	 */
	@Test
	public void provideChangeFail() {
		assertEquals(TransactionReturnCode.UNSUCCESSFUL, coinsController.provideChange(1500));
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
	
	
}
