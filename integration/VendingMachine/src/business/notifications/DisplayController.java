 package business.notifications;

 import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.funds.Coin;
import hardware.funds.CoinReceptacle;
import hardware.funds.CoinReceptacleListener;
import hardware.ui.Display;
import business.selection_delivery.ButtonSelectionController;
import business.selection_delivery.SelectionControllerListener;
import business.funds.CoinsController;

/*
 * Promises:
 * Controls messages displayed on the main display
 * 
 * Requires:
 * the main display
 * the ButtonSelectionController that handles Button selections in the machine
 * the CoinsController that tracks coins in the vending machine
 * the coin receptacle
 */


public class DisplayController implements SelectionControllerListener, CoinReceptacleListener{

	
	Display display;
	String currentMsg;
	Timer eventTimer;
	CoinsController coinsController;
	/**
	 * @param Display display
	 *            - the main display on the vending machine
	 * @param ButtonSelectionController selection
	 *            - The button selection controller associated with the machine   
	 * @param CoinsController coinsController
	 *            - The coin controller associated with the machine  
	 * @param CoinReceptacle receptacle
	 *            - The coin receptacle in the vending machine
	 */
	public DisplayController(Display display, ButtonSelectionController selection,
			CoinsController coinsController, CoinReceptacle receptacle) {
		this.display = display;
		this.coinsController = coinsController;
		eventTimer = new Timer(5000, listener);
		
		if(selection != null)
			selection.register(this);
		if(receptacle != null)
			receptacle.register(this);
		
		display(Integer.toString(this.coinsController.getAvailableBalance()));
	}
	
	ActionListener listener = new ActionListener(){
		  public void actionPerformed(ActionEvent event){
			  display(Integer.toString(coinsController.getAvailableBalance()));
			 eventTimer.stop();
		  }
	};
	
	private void display(String msg) {
		currentMsg = msg;
		display.display(currentMsg);
	}
	
	public String getMessage() {
		return currentMsg;
	}
	
    public void emptySelection() {
		eventTimer.setInitialDelay(4000);
		eventTimer.restart();
		this.display("Product empty");
		eventTimer.start();
    }
    

    public void invalidSelection() {
		eventTimer.setInitialDelay(4000);
		eventTimer.restart();
    	this.display("Invalid product selected");
		eventTimer.start();
	}

    public void insufficientFunds(int fundsRequired) {
		eventTimer.setInitialDelay(5000);
		eventTimer.restart();
		this.display("Insufficient funds. Product costs: " + Integer.toString(fundsRequired));
		eventTimer.start();
    }

	@Override
	public void coinAdded(CoinReceptacle receptacle, Coin coin) {
		display(Integer.toString(this.coinsController.getAvailableBalance()));
	}

	@Override
	public void coinsRemoved(CoinReceptacle receptacle) {
		display(Integer.toString(this.coinsController.getAvailableBalance()));
	}
	
	@Override
	public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {

	}

	@Override
	public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {
	}

	@Override
	public void coinsFull(CoinReceptacle receptacle) {

	}

	@Override
	public void enabled(CoinReceptacle receptacle) {

	}

	@Override
	public void disabled(CoinReceptacle receptacle) {

	}
}
