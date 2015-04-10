package business.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import business.config.Configuration;

public class EndToEndTest_SFFPCI extends EndToEndTest_SFFPC {
	@Before
	public void setUp() throws Exception {
		config = new Configuration();
		machine = config.load("VMRUS-SFF-P/CI", "offline");
	}
}
