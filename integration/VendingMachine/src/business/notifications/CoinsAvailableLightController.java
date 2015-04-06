package business.notifications;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.funds.Coin;
import hardware.racks.CoinRack;
import hardware.racks.CoinRackListener;
import hardware.ui.IndicatorLight;

import java.util.Iterator;
import java.util.Vector;

import business.funds.ExactChangeController;

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
