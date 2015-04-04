 package business.notifications;

 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import javax.swing.Timer;
 
import hardware.ui.Display;
import business.selection_delivery.SelectionControllerListener;
import business.selection_delivery.SelectionController;

public class DisplayController implements SelectionControllerListener{

	
	Display display;
	String currentMsg;
	Timer eventTimer;


	public DisplayController(Display display, SelectionController sc) {
		this.display = display;
		eventTimer = new Timer(5000, listener);
		
		sc.register(this);
		display("TODO get coins available"); //TODO get coin credit
	}
	
	ActionListener listener = new ActionListener(){
		  public void actionPerformed(ActionEvent event){
			  display("TODO get coins available"); //TODO get coin credit
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
}
