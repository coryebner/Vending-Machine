package business.test;

import org.junit.Before;

import business.config.Configuration;

public class EndToEndTest_SFFPC extends EndToEndTest {
	@Before
	public void setUp() throws Exception {
		config = new Configuration();
		machine = config.load("VMRUS-SFF-P/C", "offline");
	}
}
