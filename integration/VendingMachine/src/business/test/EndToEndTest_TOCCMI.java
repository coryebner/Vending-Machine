package business.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import business.config.Configuration;

public class EndToEndTest_TOCCMI extends EndToEndTest {

	@Before
	public void setUp() throws Exception {
		config = new Configuration();
		machine = config.load("VMRUS-TOC-C/MI", "offline");
	}

	@Test
	public void testPurchaseWithCoin() throws Exception{
		super.testPurchaseProductCoin();
	}
	
	@Test
	public void testPurchaseNoFunds() throws Exception{
		super.testPurchaseProductNoFunds();
	}
	
	@Test
	public void testSomething() throws Exception{
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
	public void testMakeChangeFromPrior() throws Exception{
		super.testMakeChangeFromPriorPurchase();
	}
	
	@Test
	public void testPurchaseAll() throws Exception{
		super.testPurchaseAllPop();
	}
}
