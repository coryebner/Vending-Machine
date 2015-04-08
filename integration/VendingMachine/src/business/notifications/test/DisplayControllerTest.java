package business.notifications.test;

import static org.junit.Assert.*;
import hardware.funds.Coin;
import hardware.funds.CoinReceptacle;
import hardware.ui.Display;

import org.junit.Test;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;

import business.funds.CoinsController;
import business.notifications.DisplayController;
import business.selection_delivery.SelectionController;

public class DisplayControllerTest {

	@Test
	public void test() {
	    Mockery context = new Mockery();
        final CoinsController coinsController = context.mock(CoinsController.class);
        final SelectionController selectionController = context.mock(SelectionController.class);
        CoinReceptacle receptacle = new CoinReceptacle(100);
        Display display = new Display();
        DisplayController displayController = new DisplayController(display, selectionController, coinsController, receptacle);
        context.checking(new Expectations() {{
            oneOf (coinsController).getAvailableBalance(); returnValue(100);
        }});
        
        displayController.coinAdded(receptacle, new Coin(0));
        
	    assertEquals("100", displayController.getMessage());
        
        
	}

}
