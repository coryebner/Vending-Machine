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
	
	//WHEN EVENTS ARE AVAILABLE
	
	//Listen for Internet turn ON
			//turn on on
	
	//Listen for Internet turn OFF
			//turn on off

	
	
}
