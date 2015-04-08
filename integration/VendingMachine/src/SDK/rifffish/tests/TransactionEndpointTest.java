package SDK.rifffish.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import SDK.rifffish.Rifffish;
import SDK.rifffish.Transaction;
import SDK.rifffish.Rifffish.PaymentMethod;

public class TransactionEndpointTest {
	private Rifffish r = null;
	
	@Before
	public void setUp() throws Exception {
		// Local dev testing, API Key will need to be regenerated
		r = new Rifffish("rsh_3wL4MyhWW4z3kfjoYfyN0gtt", "http://rifffish.com/api");
	}

	@Test
	public void testWellFormedTransactionStatusPassed() {
		assertEquals(null, r.log(new Transaction(1, PaymentMethod.COIN, true)));
	}
	
	@Test
	public void testWellFormedTransactionStatusFailed() {
		assertEquals(null, r.log(new Transaction(1, PaymentMethod.COIN, false)));
	}
	
	@Test
	public void testMalformedTransactionStatusPassed() {
		assertEquals("Error -> 400 - Bad Request. Transaction Malformed.", 
				(r.log(new Transaction(999999, PaymentMethod.COIN, true))).toString());
	}
	
	@Test
	public void testMalformedTransactionStatusFailed() {
		assertEquals("Error -> 400 - Bad Request. Transaction Malformed.", 
				(r.log(new Transaction(9999999, PaymentMethod.COIN, false))).toString());
	}
	
	@Test
	public void testNegativeMalformedTransactionStatusFailed() {
		assertEquals("Error -> 400 - Bad Request. Transaction Malformed.", 
				(r.log(new Transaction(-1, PaymentMethod.COIN, false))).toString());
	}
	
	@Test
	public void testPartialTransactionStatusFailed() {
		assertEquals("Error -> 400 - Bad Request. Transaction Malformed.", 
				(r.log(new Transaction(null, PaymentMethod.COIN, false))).toString());
	}
	
	@Test
	public void testBadAPIKey() {
		// Local dev testing, API Key will need to be regenerated
		r = new Rifffish("BAD_KEY", "http://localhost:3000/api");
		assertEquals("Error -> 400 - Bad Request. Transaction Malformed.", 
				(r.log(new Transaction(null, PaymentMethod.COIN, false))).toString());
	}
	
	@Test
	public void testNonWorkingURL() {
		// Local dev testing, API Key will need to be regenerated
		r = new Rifffish("rsh_WTYxjQcwJhF1a26nPibqLwtt", "http://localhostErrorURL/api");
		assertEquals("Error -> 400 - Bad Request. Transaction Malformed.", 
				(r.log(new Transaction(null, PaymentMethod.COIN, false))).toString());
	}

}
