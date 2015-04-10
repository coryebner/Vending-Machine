package SDK.rifffish.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoggerProblemExample {
	@Test
	public void test() {
		SDK.rifffish.Rifffish r = new SDK.rifffish.Rifffish("rsh_3wL4MyhWW4z3kfjoYfyN0gtt");
		SDK.rifffish.Machine m = r.createMachine(new SDK.rifffish.Machine("VENDINGMACHINE", "vmrs_sff_p_c", "in_service", "CAD"));
		SDK.rifffish.Product p = r.createProduct(new SDK.rifffish.Product(m.getId(), "Coke", 100, 10, 20));
		
		assertNotNull(p);
	}

}
