package business.test;

import static org.junit.Assert.*;
import hardware.funds.Coin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.config.Configuration;

public class EndToENdTest_TOCCplus extends EndToEndTest {

	@Before
	public void setUp() throws Exception {
		config = new Configuration();
		machine = config.load("VMRUS-TOC-C+", "offline");
		
	}

	@After
	public void tearDown() throws Exception {
		config = null;
		machine = null;
	}

	@Test
	public void testPurchaseWithCoin() throws Exception {
		super.testPurchaseProductCoin();
	}
	
	@Test
	public void testPurchaseWithNoFunds() throws Exception{
		super.testPurchaseProductNoFunds();
	}
	
	
	@Test
	public void testPrepaidPurchase() throws Exception{
		super.testPurchaseProductPrepaid();
	}

	@Test
	public void makeChangeFromCoins() throws Exception{
		super.testMakeChangeFromCoin();
	}
	
	@Test
	public void testCoinReturn() throws Exception{
		super.testCoinReturn();
	}
	
	@Test
	public void testPurchaseAll() throws Exception{
		super.testPurchaseAllPop();
	}
	
	@Test
	public void testMixedChange() throws Exception{
		super.testMakeMixedChangeFromCoin();
	}
	
	@Test
	public void testFullOfCoin() throws Exception{
		super.testFullOfCoins();
	}
	
	@Test
	public void testMakePurcaseWithBill() throws Exception{
		super.testPurchaseWithBills();
	}
	
	@Test
	public void testMakeChangeFromBills() throws Exception{
		super.testMakeChangeFromBills();
	}

}
