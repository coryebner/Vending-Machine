package business.notifications;

import SDK.logger.Logger;
import SDK.rifffish.Problem;
import SDK.rifffish.Rifffish.ProblemTypes;
import business.funds.CoinStorageBinController;
import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.funds.Coin;
import hardware.funds.CoinReceptacle;
import hardware.ui.IndicatorLight;


/*
 * Promises:
 * Set the internet light to its initial state
 * Requires
 * The internet light
 * True if internet is available, False if internet is unavailable
 */

public class OutOfOrderLightController implements hardware.funds.CoinReceptacleListener{
	IndicatorLight light;
	Logger log;
	/**
	 * @param IndicatorLight light
	 *            - the out of order light on the vending machine
	 * @param CoinReceptacle storageBin
	 *            - the storageBin in the vending machine     
	 * @param CoinStorageBinController storagebinController
	 *            - the storageBin controller associated with the vending machine   
	 */
	public OutOfOrderLightController(IndicatorLight light, CoinReceptacle storageBin,
			CoinStorageBinController storagebinController, Logger log) {
		this.light = light;
		this.log = log;
		storageBin.register(this);
		if(storagebinController.isFull())
			light.activate();
		else
			light.deactivate();
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
		log.log(new Problem(ProblemTypes.OUTOFORDER));
	}

	@Override
	public void enabled(CoinReceptacle receptacle) {
	}

	@Override
	public void disabled(CoinReceptacle receptacle) {
	}

}
