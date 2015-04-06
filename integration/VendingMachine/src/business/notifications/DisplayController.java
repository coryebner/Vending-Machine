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
import business.selection_delivery.SelectionController;
import business.funds.CoinsController;

public class DisplayController implements SelectionControllerListener, CoinReceptacleListener{

	
	Display display;
	String currentMsg;
	Timer eventTimer;
	CoinsController coinsController;

	public DisplayController(Display display, SelectionController sc,
			CoinsController coinsController, CoinReceptacle receptacle) {
		this.display = display;
		eventTimer = new Timer(5000, listener);
		this.coinsController = coinsController;
		
		sc.register(this);
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
    	this.display("Invalid product selected");
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
		// TODO Auto-generated method stub
		
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
