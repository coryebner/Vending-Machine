package business.funds.tests;

import java.util.Locale;

import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.exceptions.EmptyException;
import hardware.funds.Coin;
import hardware.funds.CoinSlot;
import hardware.racks.CoinRack;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.funds.CoinRackController;

/**
 * @author Jan Clarin
 * @author Andrei (Andy) Savu
 * @author Arthur Lee
 * @author Olabode (Sam) Adegbayike
 *
 */
public class CoinRackControllerTest {
	
	CoinRackController coinRackController;
	CoinRack coinRack;
	Coin coin;
	int capacity = 0;
	int quantity = 0;
	int rackDenomination = 0;
	@Before
	public void setUp() throws Exception {
		capacity = 20;
		quantity = 10;
		rackDenomination = 10;
		coinRack = new CoinRack(capacity);
		coinRackController = new CoinRackController(coinRack, rackDenomination, quantity);
	}
	
	@After
	public void tearDown() throws Exception {
		capacity = 0;
		quantity = 0;
		rackDenomination = 0;
		coinRack = null;
		coinRackController = null;
	}
	
	@Test
	public void releaseCoinTest(){
		
		
	}
	
	@Test
	public void getCoinRackDenominationTest(){
		assertEquals(rackDenomination, coinRackController.getCoinRackDenomination());
	}
	
	@Test
	public void getQuantityTest(){
		assertEquals(quantity, coinRackController.getQuantity());
		
	}
	
	@Test
	public void isEmptyTest(){
		assertEquals(false, coinRackController.isEmpty());
		quantity = 0;
		coinRackController= new CoinRackController(coinRack, rackDenomination, quantity);
		assertEquals(true, coinRackController.isEmpty());
	}
	
	@Test
	public void coinsEmptyTest(){
		coinRackController.coinsEmpty(coinRack);
		assertEquals(0, coinRackController.getQuantity());
	}
	
	@Test
	public void coinAddedTest(){
		quantity = 15;
		coinRackController = new CoinRackController(coinRack, rackDenomination, quantity);
		coinRackController.coinAdded(coinRack, coin);
		assertEquals(quantity+1, coinRackController.getQuantity());		
	}
	
	@Test
	public void coinRemoved(){
		quantity = 15;
		coinRackController = new CoinRackController(coinRack, rackDenomination, quantity);
		coinRackController.coinRemoved(coinRack, coin);
		assertEquals(quantity-1, coinRackController.getQuantity());	
	}
	
}
