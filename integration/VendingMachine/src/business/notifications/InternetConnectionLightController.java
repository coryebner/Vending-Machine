package business.notifications;

import hardware.ui.IndicatorLight;

public class InternetConnectionLightController {

	IndicatorLight light;
	public InternetConnectionLightController(IndicatorLight light, boolean state) {
		this.light = light;
		if(state)
			light.activate();
		else
			light.deactivate();
	}	
}
