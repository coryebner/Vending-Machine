package SDK.localLog.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LocalLogTest {
	LocalLog log = null;

	@Before
	public void setUp() throws Exception {
		log = new LocalLog();
	}

	@After
	public void tearDown() throws Exception {
		log = null;
	}

	@Test
	public void testGetNumLines() {
		System.out.println(log.getNumLines());
	}

}
