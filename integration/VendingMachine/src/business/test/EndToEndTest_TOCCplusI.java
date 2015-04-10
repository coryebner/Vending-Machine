package business.test;

import org.junit.Before;

import business.config.Configuration;

public class EndToEndTest_TOCCplusI extends EndToENdTest_TOCCplus {
	@Before
	public void setUp() throws Exception {
		config = new Configuration();
		machine = config.load("VMRUS-TOC-C+/I", "offline");
	}
}
