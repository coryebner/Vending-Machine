package business.test;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.config.Configuration;

/**@author M. Diaz
 * */
public class EndToEndTest_COMPMI extends EndToEndTest {
	
	@Before
	public void setUp() throws Exception{
		this.config = new Configuration();
		machine = config.load("VMRUS-COM-P/MI", "offline");
		
	}
	
	@After
	public void tearDown() throws Exception {
		this.config = null;
		this.machine = null; 
	}
	
	@Test
	public void testPurchaseCoins() throws Exception {

			super.testPurchaseProductCoin();

	}
	
	@Test
	public void testMakeChangeFromCoin() throws Exception {
		super.testMakeChangeFromCoin();
	}
	
	@Test
	public void  testMakeChangeFromPriorPurchase() throws Exception {
		super.testMakeChangeFromPriorPurchase();
	}
	
	@Test
	public void testPurchaseProductNoFunds() throws Exception {
		super.testPurchaseProductNoFunds();
	}
	
	@Test
	public void testPurchaseProductPrepaid() throws Exception {
		super.testPurchaseProductPrepaid();
	}
	
	@Test
	public void testCheckPrepaidCard() throws Exception {
		super.testCheckPrepaidCardFirstOrderPrecedence();
	}
}
