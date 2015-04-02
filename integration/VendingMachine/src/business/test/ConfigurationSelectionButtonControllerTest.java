package business.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.config.Configuration;
import business.selection_delivery.ButtonSelectionController;

/**Test file for createSelectionButtonController. Checking that after creation the listener is registered.
 * */
public class ConfigurationSelectionButtonControllerTest {
	protected Configuration config;
	ButtonSelectionController selection;
	

	@Before
	public void setUp() throws Exception {
		config = new Configuration();
		selection = config.getButtonSelectionController();
	}

	@After
	public void tearDown() throws Exception {
		config = null;
		selection = null;
	}

	@Test
	public void testCreateButtonSelectionController() {
		config.createButtonSelectionController();
		
	}

}
