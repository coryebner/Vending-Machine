package hardware.ui;

import hardware.AbstractHardware;

/**
 * A class that allows software to interact with the configuration panel
 */
public class ConfigurationPanelTransmitter extends AbstractHardware<ConfigurationPanelTransmitterListener>{

	public void enterCommand(String inputString){
		if(inputString != null)
			notifyStringEntered(inputString);
	}
	
	private void notifyStringEntered(String str) {
		Class<?>[] parameterTypes = new Class<?>[] {ConfigurationPanelTransmitter.class, String.class};
		Object[] args = new Object[] {this, str};
		notifyListeners(ConfigurationPanelTransmitterListener.class, "commandEntered", parameterTypes, args);
    }

}
