package business.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import business.config.Configuration;

public class EndToEndTest_COMCM extends EndToEndTest {
	@Before
	public void setUp() throws Exception {
		config = new Configuration();
		machine = config.load("VMRUS-COM-C/M", "offline");
	}

	@Test
	public void testPurchaseProductCoinCode() throws Exception {
		super.testPurchaseProductCoinCode();
	}
	
	@Test
	public void testPurchaseProductNoFundsCode() throws Exception {
		super.testPurchaseProductNoFundsCode();
	}
	
	@Test
	public void testEnterInvalidCode() throws Exception {
		super.testEnterInvalidCode();
	}
	
	@Test
	public void testPurchaseProductBillCode() throws Exception {
		super.testPurchaseProductBillCode();
	}
	
	@Test
	public void testMakeChangeFromBillsCode() throws Exception {
		super.testMakeChangeFromBillsCode();
	}
	
	@Test
	public void testCoinReturn() throws Exception {
		super.testCoinReturn();
	}
}
