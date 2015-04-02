package notifications;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.funds.Coin;
import hardware.funds.CoinReceptacle;
import hardware.ui.IndicatorLight;

import com.vendingmachinesareus.CoinReceptacleListener;

public class OutOfOrderLightController implements hardware.funds.CoinReceptacleListener{
	IndicatorLight light;
	public OutOfOrderLightController(IndicatorLight light, CoinReceptacle storageBin) {
		this.light = light;
		storageBin.register(this);
		//TODO determine init state.
		/*
		 * this could be done in two ways:
		 * 1) we are given the state: inconvenient for a user!
		 * 2) we calculate the state: we need to know about how many coins are in the 
		 * storagebin 
		 */
	}
	
	@Override
	public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {
	}

	@Override
	public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {
	}

	@Override
	public void coinAdded(CoinReceptacle receptacle, Coin coin) {
	}

	@Override
	public void coinsRemoved(CoinReceptacle receptacle) {
		light.deactivate();
	}

	@Override
	public void coinsFull(CoinReceptacle receptacle) {
		light.activate();
	}

	@Override
	public void enabled(CoinReceptacle receptacle) {
	}

	@Override
	public void disabled(CoinReceptacle receptacle) {
	}

}
