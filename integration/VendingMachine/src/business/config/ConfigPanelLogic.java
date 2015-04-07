package business.config;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.ui.ConfigurationPanelTransmitter;

import java.util.Scanner;

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
	
	public ConfigPanelLogic(){
		state = 0;
		secondaryState = 0;
		tempProdId = "";
		tempPrice = "";
		tempName = "";
	}
	
	public void commandEntered(ConfigurationPanelTransmitter transmitter, String keyCode){
		switch(state){
		case 0:
			if(keyCode.equals("newPrice"))
				state = 1;
			else if(keyCode.equals("newName"))
				state = 2;
			break;
		case 1:
			switch(secondaryState){
			case 0:
				if(isNumeric(keyCode)){
					tempProdId = tempProdId + keyCode;
				}
				else if(keyCode.equals("ENTER")){
					productId = Integer.parseInt(tempProdId);
					tempProdId = "";
					secondaryState = 1;
				}
				break;
			case 1:
				if(isNumeric(keyCode)){
					tempPrice = tempPrice + keyCode;
				}
				else if(keyCode.equals("ENTER")){
					newPrice = Integer.parseInt(tempPrice);
					tempPrice = "";
					secondaryState = 0;
					state = 0;
					notifyPriceChange();
				}
				break;
			}
			break;
			
		case 2:
			switch(secondaryState){
			case 0:
				if(isNumeric(keyCode)){
					tempProdId = tempProdId + keyCode;
				}
				else if(keyCode.equals("ENTER")){
					productId = Integer.parseInt(tempProdId);
					tempProdId = "";
					secondaryState = 1;
				}
				break;
			case 1:
				if(keyCode.length() == 1){
					tempName = tempName + keyCode;
				}
				else if(keyCode.equals("ENTER")){
					newName = tempName;
					tempName = "";
					secondaryState = 0;
					state = 0;
					notifyNameChange();
				}
				break;
			}
			break;
		}
	}
	
	
	public void TestPriceChange(int index, int price)
	{
		productId = index;
		newPrice = price;
		notifyPriceChange();
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
	}
	protected void notifyNameChange()
	{
		Class<?>[] parameterTypes =
		        new Class<?>[] { Integer.TYPE, String.class };
		Object[] args = new Object[] { productId,newName };
		System.out.println("New Name: " + newName);
		notifyListeners(ConfigurationListener.class, "nameChanged", parameterTypes, args);
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
