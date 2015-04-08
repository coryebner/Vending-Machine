package business.notifications;


import hardware.ui.IndicatorLight;


import business.funds.ExactChangeController;
import business.funds.ExactChangeControllerListener;

public class CoinsAvailableLightController implements ExactChangeControllerListener{
	
	IndicatorLight light;
	
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
