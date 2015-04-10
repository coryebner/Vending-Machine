package business.config;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.ui.ConfigurationPanelTransmitter;
import hardware.ui.Display;

import business.selection_delivery.AbstractController;

public class ConfigPanelLogic extends AbstractController<ConfigurationListener> implements hardware.ui.ConfigurationPanelTransmitterListener {

	static int state;
	static int secondaryState;
	
	int productId;
	int newPrice;
	String newName;
	
	String tempProdId;
	String tempPrice;
	String tempName;
	
	Display disp;
	
	public ConfigPanelLogic(Display display){
		state = 0;
		secondaryState = 0;
		tempProdId = "";
		tempPrice = "";
		tempName = "";
		disp = display;
	}
	
	public void commandEntered(ConfigurationPanelTransmitter transmitter, String keyCode){
		switch(state){
		case 0:
			if(keyCode.equals("1"))
				state = 1; // newPrice
			else if(keyCode.equals("2"))
				state = 2; // newName
			disp.display("Enter index");
			break;
		case 1:
			switch(secondaryState){
			case 0:
				if(isNumeric(keyCode)){
					tempProdId = tempProdId + keyCode;
					disp.display(tempProdId);
				}
				else if(keyCode.equals("\n")){
					productId = Integer.parseInt(tempProdId);
					tempProdId = "";
					secondaryState = 1;
					disp.display("Enter new price");
				}
				else if(keyCode.equals("_correct")) {
					tempProdId = tempProdId.substring(0, tempProdId.length() - 1);
					disp.display(tempProdId);
				}
				break;
			case 1:
				if(isNumeric(keyCode)){
					tempPrice = tempPrice + keyCode;
					disp.display(tempPrice);
				}
				else if(keyCode.equals("\n")){
					newPrice = Integer.parseInt(tempPrice);
					tempPrice = "";
					secondaryState = 0;
					state = 0;
					notifyPriceChange();
				}
				else if(keyCode.equals("_correct")) {
					tempPrice = tempPrice.substring(0, tempPrice.length() - 1);
					disp.display(tempPrice);
				}
				break;
			}
			break;
			
		case 2:
			switch(secondaryState){
			case 0:
				if(isNumeric(keyCode)){
					tempProdId = tempProdId + keyCode;
					disp.display(tempProdId);
				}
				else if(keyCode.equals("\n")){
					productId = Integer.parseInt(tempProdId);
					tempProdId = "";
					secondaryState = 1;
				}
				else if(keyCode.equals("_correct")) {
					tempProdId = tempProdId.substring(0, tempProdId.length() - 1);
					disp.display(tempProdId);
				}
				break;
			case 1:
				if(keyCode.length() == 1 && !keyCode.equals("\n")){
					tempName = tempName + keyCode;
					disp.display(tempName);
				}
				if(keyCode.equals("\n")){
					newName = tempName;
					tempName = "";
					secondaryState = 0;
					state = 0;
					notifyNameChange();
				}
				else if(keyCode.equals("_correct")) {
					tempName = tempName.substring(0, tempName.length() - 1);
					disp.display(tempName);
				}
				break;
			}
			break;
		}
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  {  
	    Integer.parseInt(str);  
	  }  catch(NumberFormatException nfe) {  
	    return false;  
	  }  
	  return true;  
	}
	
	protected void notifyPriceChange()
	{
		Class<?>[] parameterTypes =
		        new Class<?>[] { Integer.TYPE, Integer.TYPE};
		Object[] args = new Object[] { productId,newPrice };
		notifyListeners(ConfigurationListener.class, "priceChanged", parameterTypes, args);
		disp.display("item " + productId + " price changed to " + newPrice);
	}
	protected void notifyNameChange()
	{
		Class<?>[] parameterTypes =
		        new Class<?>[] { Integer.TYPE, String.class };
		Object[] args = new Object[] { productId,newName };
		notifyListeners(ConfigurationListener.class, "nameChanged", parameterTypes, args);
		disp.display("item " + productId + " name changed to " + newName);
	}

	@Override
	public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {
		// TODO Auto-generated method stub
		
	}

}
