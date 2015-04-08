package business.notifications;


import hardware.ui.IndicatorLight;
/*
 * Promises: Control the exact change light. Turn it on when change is unavailable, Turn it off when change in available.
 * 
 * Requires:
 * The exact Change light
 * The exact Change controller
 */

import business.funds.ExactChangeController;
import business.funds.ExactChangeControllerListener;

public class CoinsAvailableLightController implements ExactChangeControllerListener{
	
	IndicatorLight light;
	/**
	 * @param IndicatorLight light
	 *            - the exact change light on the vending Machine
	 * @param ExactChangeController ecc
	 *            - The exact change controller associated with the vending machine       
	 */
	public CoinsAvailableLightController(IndicatorLight light, ExactChangeController ecc) {
		this.light = light;
		ecc.register(this);
	}
	
	
	public void exactChangeAvailable(ExactChangeController ecc) {
		light.deactivate();
	}
	
	public void exactChangeUnavailable(ExactChangeController ecc) {
		light.activate();
	}
}
