package business.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import business.config.Configuration;

public class EndToEndTest_SFFPPI extends EndToEndTest {
	@Before
	public void setUp() throws Exception {
		config = new Configuration();
		machine = config.load("VMRUS-SFF-P/PI", "offline");
	}

	@Test
	public void testPurchasePrepaid() throws Exception {
		super.testPurchaseProductPrepaid();
	}

	@Test
	public void testPurchaseProductNoFunds() throws Exception {
		super.testPurchaseProductNoFunds();
	}
	
	@Test
	public void testDisplayChangesProductEmptyPrepaid() throws Exception {
		super.testDisplayChangesProductEmptyPrepaid();
	}
	
	@Test
	public void testDisplayChangesInsufficientFunds() throws Exception {
		super.testDisplayChangesInsufficientFunds();
	}
}
