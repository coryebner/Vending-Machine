package business.test;

import org.junit.Before;
import org.junit.Test;

import business.config.Configuration;

public class EndToEndTest_SFFPC extends EndToEndTest {
	@Before
	public void setUp() throws Exception {
		config = new Configuration();
		machine = config.load("VMRUS-SFF-P/C", "offline");
	}
	
	@Test
	public void testPurchaseProductCoin() throws Exception {
		super.testPurchaseProductCoin();
	}
	
	@Test
	public void testPurchaseProductNoFunds() throws Exception {
		super.testPurchaseProductNoFunds();
	}
	
	@Test
	public void testMakeChangeFromCoin() throws Exception {
		super.testMakeChangeFromCoin();
	}
	
	@Test
	public void testMakeMixedChangeFromCoin() throws Exception {
		super.testMakeMixedChangeFromCoin();
	}
	
	@Test
	public void testMakeChangeFromPriorPurchase() throws Exception {
		super.testMakeChangeFromPriorPurchase();
	}
	
	@Test
	public void testCoinReturn() throws Exception {
		super.testCoinReturn();
	}
}
