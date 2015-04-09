package business.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import business.config.Configuration;

public class EndToEndTest_COMPMI extends EndToEndTest {
	@Before
	public void setUp() throws Exception {
		config = new Configuration();
		machine = config.load("VMRUS-COM-P/MI", "offline");
	}

	@After
	public void tearDown() throws Exception {
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
	public void testMakeChangeFromPriorPurchase() throws Exception {
		super.testMakeChangeFromPriorPurchase();
	}
	
	@Test
	public void testCoinReturn() throws Exception {
		super.testCoinReturn();
	}
	
	@Test
	public void testPurchaseProductPrepaid() throws Exception {
		super.testPurchaseProductPrepaid();
	}
}
