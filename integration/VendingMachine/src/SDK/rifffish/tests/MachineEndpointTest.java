package SDK.rifffish.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import SDK.rifffish.Machine;
import SDK.rifffish.Rifffish;


public class MachineEndpointTest {
	private Rifffish r = null;
	private Machine m = null;
	@Before
	public void setUp() throws Exception {
		// Local dev testing, API Key will need to be regenerated
		r = new Rifffish("rsh_Wv9Q58YtKQBh7v7C0fzTvQtt", "http://localhost:3000/api");  //generate a ton of junk data!
		m = new Machine("Candy Pop", "vmrs_sff_p_c", "in_service", "CAD");

	}
	
	@Test
	public void testCreateMachineSuccessfully() {
		Machine responseMachine = r.createMachine(m);
		
		assertNotNull(responseMachine.getId());
		assertEquals(m.getName(), responseMachine.getName());
		assertEquals(m.getConfigName(), responseMachine.getConfigName());
		assertEquals(m.getMode(), responseMachine.getMode());
		assertEquals(m.getCurrency(), responseMachine.getCurrency()); // we don't care about being case sensitive....
	}
	
	@Test
	public void testGetMachineSuccessfully() {
		Machine machine = r.createMachine(m);
		
		Machine responseMachine = r.getMachine(machine.getId());
		
		assertEquals(machine.getId(), responseMachine.getId());
		assertEquals(machine.getName(), responseMachine.getName());
		assertEquals(machine.getConfigName(), responseMachine.getConfigName());
		assertEquals(machine.getMode(), responseMachine.getMode());
		assertEquals(machine.getCurrency(), responseMachine.getCurrency()); // we don't care about being case sensitive....
	}
	
	@Test
	public void testUpdateMachineSuccessfully() {
		Machine machine = r.createMachine(m);
		
		machine.setName("Candy Awesome");
		Machine responseMachine = r.updateMachine(machine);
		
		assertEquals(machine.getId(), responseMachine.getId());
		assertEquals(machine.getName(), responseMachine.getName());
		assertEquals(machine.getConfigName(), responseMachine.getConfigName());
		assertEquals(machine.getMode(), responseMachine.getMode());
		assertEquals(machine.getCurrency(), responseMachine.getCurrency()); // we don't care about being case sensitive....
	}
	
	@Test
	public void testDeleteMachineSuccessfully() {
		Machine machine = r.createMachine(m);
		assertEquals(null, r.deleteMachine(machine.getId()));
	}

	/*
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
*/
}
