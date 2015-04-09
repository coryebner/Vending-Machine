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
import business.selection_delivery.SelectionControllerListener;
import business.funds.FundsController;

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
	FundsController funds;
	/**
	 * 
	 * DisplayController must be registered to the following based on availability
	 * 			-CoinReceptacle
	 * 			-BankNotesReceptacle
	 * 			-SelectionHandler
	 * 
	 * @param Display display
	 *            - the main display on the vending machine
	 * @param FundsController funds
	 * 			  - the main FundsController associated with the vending machine
	 */
	public DisplayController(Display display, FundsController funds) {
		this.display = display;
		this.funds = funds;
		eventTimer = new Timer(5000, listener);
		
		display(centsToString(getTotalBalance()));
	}
	
	ActionListener listener = new ActionListener(){
		  public void actionPerformed(ActionEvent event){
			  display(centsToString(getTotalBalance()));
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
		this.display("Insufficient funds. Product costs: " + centsToString(fundsRequired));
		eventTimer.start();
    }

	@Override
	public void coinAdded(CoinReceptacle receptacle, Coin coin) {
		display(centsToString(getTotalBalance()));
	}

	@Override
	public void coinsRemoved(CoinReceptacle receptacle) {
		display(centsToString(getTotalBalance()));
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

	private int getTotalBalance() {
		return funds.getBankNoteController().getAvailableBalance() + funds.getCoinsController().getAvailableBalance();
	}
	
	private String centsToString(int cents){
		String ret = "";
		ret+= "$"+ cents/100+".";
		if(cents%100 <10){
			ret+= "0"+ cents%100;
		}
		else{
			ret+= cents%100;
		}
		return ret;
	}
}
