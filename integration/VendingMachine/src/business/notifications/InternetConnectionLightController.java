package business.notifications;

import hardware.ui.IndicatorLight;

/*
 * Promises:
 * Set the internet light to its initial state
 * Requires
 * The internet light
 * True if internet is available, False if internet is unavailable
 */

public class InternetConnectionLightController {
	/**
	 * @param IndicatorLight light
	 *            - the internet available light on the vending machine
	 * @param boolean state
	 *            - true if internet is available on the machine
	 *            - false if internet is not available on the machine  
	 */
	IndicatorLight light;
	public InternetConnectionLightController(IndicatorLight light, boolean state) {
		this.light = light;
		if(state)
			light.activate();
		else
			light.deactivate();
	}	
}
