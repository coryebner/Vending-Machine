/**
 * Unit Test for Coin Rack Controller Class
 */
package business.funds.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import hardware.Hardware;
import hardware.acceptors.AbstractCoinAcceptor;
import hardware.channels.CoinChannel;
import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.exceptions.EmptyException;
import hardware.funds.Coin;
import hardware.racks.CoinRack;

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
	CoinChannel sink;
	AbstractCoinAcceptor abstractCoinAcceptor;
	Coin coin;
	int capacity = 0;
	int quantity = 0;
	int rackDenomination = 0;
	
	/**
	 * 
	 * Instantiating all objects
	 */
	@Before
	public void setUp() throws Exception {
		capacity = 20;
		quantity = 10;
		rackDenomination = 10;
		coinRack = new CoinRack(capacity);
		abstractCoinAcceptor = new AbstractCoinAcceptor() {
			
			@Override
			public boolean hasSpace() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void acceptCoin(Coin coin) throws CapacityExceededException,
					DisabledException {
				// TODO Auto-generated method stub
				
			}
		};
		sink = new CoinChannel(abstractCoinAcceptor);
		coinRack.connect(sink);
		coinRackController = new CoinRackController(coinRack, rackDenomination, quantity);
	}
	
	/**
	 * 
	 * Re initialize all objects
	 */
	@After
	public void tearDown() throws Exception {
		capacity = 0;
		quantity = 0;
		rackDenomination = 0;
		coinRack = null;
		coinRackController = null;
	}
	
	/**
	 * Test releaseCoin() method for quantity update
	 * @throws EmptyException 
	 */
	@Test
	public void releaseCoinTest(){
		try {
			coinRackController.releaseCoin();
		} catch (EmptyException e) {
			fail("Exception thrown, so test failed");
		}
		
		assertEquals(quantity, coinRackController.getQuantity());
		

	}
	/**
	 * @throws EmptyException
	 * 				Thrown for trying to release coin from an empty coin rack
	 */
	@Test(expected = EmptyException.class) 
	public void releaseCoinThrowException() throws EmptyException{		
		quantity = 0;
		coinRack= new CoinRack(10);
		coinRackController = new CoinRackController(coinRack, rackDenomination, quantity);
		coinRackController.releaseCoin();
		fail("Exception was not thrown.");
	}
	
	/**
	 * Test getCoinDenomination() method for getting coin amount in rack
	 */
	@Test
	public void getCoinRackDenominationTest(){
		assertEquals(rackDenomination, coinRackController.getCoinRackDenomination());
	}
	
	/**
	 * Test getQuantity() method for getting quantity of coins in coin rack
	 */
	@Test
	public void getQuantityTest(){
		assertEquals(quantity, coinRackController.getQuantity());
		
	}
	
	/**
	 * Test isEmpty() method to check if rack is empty or not
	 */
	@Test
	public void isEmptyTest(){
		assertEquals(false, coinRackController.isEmpty());
		quantity = 0;
		coinRackController= new CoinRackController(coinRack, rackDenomination, quantity);
		assertEquals(true, coinRackController.isEmpty());
	}
	
	/**
	 * Test coinsEmpty() method to check if rack is emptied after call
	 */
	@Test
	public void coinsEmptyTest(){
		coinRackController.coinsEmpty(coinRack);
		assertEquals(0, coinRackController.getQuantity());
	}
	
	/**
	 * Test coinsAdded() method to check if coin is added to rack
	 */
	@Test
	public void coinAddedTest(){
		coinRackController = new CoinRackController(coinRack, rackDenomination, quantity);
		coinRackController.coinAdded(coinRack, coin);
		assertEquals(quantity+1, coinRackController.getQuantity());		
	}
	
	/**
	 * Test coinsRemoved() method to check if only coin is removed from rack
	 */
	@Test
	public void coinRemovedTest(){
		coinRackController = new CoinRackController(coinRack, rackDenomination, quantity);
		coinRackController.coinRemoved(coinRack, coin);
		assertEquals(quantity-1, coinRackController.getQuantity());	
	}
	
}
