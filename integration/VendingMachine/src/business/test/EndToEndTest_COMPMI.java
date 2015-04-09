package business.test;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.config.Configuration;

/**@author M. Diaz
 * */
public class EndToEndTest_COMPMI extends EndToEndTest {
	
	@Before
	public void setUp() throws Exception{
		this.config = new Configuration();
		
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testPurchase(){
		
	}
}
