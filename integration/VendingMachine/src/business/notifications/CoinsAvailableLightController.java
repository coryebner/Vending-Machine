package notifications;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.funds.Coin;
import hardware.racks.CoinRack;
import hardware.racks.CoinRackListener;
import hardware.ui.IndicatorLight;

import java.util.Iterator;
import java.util.Vector;


/*
 * two possible implementations
 * 
 * 1) CoinsAvailable listens for funds to tell us when to turn the light on
 * 			-MUST MUST MUST assume that coins are providing us with a change light event frequently enough
 * 2) We listen ourselves to figure out when to turn on the light.
 */

public class CoinsAvailableLightController implements CoinRackListener{

	//METHOD 1 -----------------------------------
	
	
	//Funds tells us to turn on light
			//turn on light
	
	//Funds tells us to turn off light
			//turn off light
	
	//METHOD 2 -----------------------------------
	
	int[] rackDenominations;
	Vector<Integer> changeToMake;
	IndicatorLight light;
	InternalCoinsAvailable internalCoinsAvailable;
	
	public CoinsAvailableLightController(IndicatorLight light, CoinRack racks[], coinRackListener coinRack, int[] rackDenominations, int[] popPrices) {
		for (int i = 0; i < racks.length; i++) {
			racks[i].register(this);
			
		}
		changeToMake = new Vector<Integer>();
		this.internalCoinsAvailable = ica;
		this.light = light;
		this.rackDenominations = rackDenominations;

		determineChangeToMake(rackDenominations, popPrices);
		refreshLightState();
	}

	@Override
	public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {		
	}

	@Override
	public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {		
	}

	@Override
	public void coinsFull(CoinRack rack) {		
	}

	@Override
	public void coinsEmpty(CoinRack rack) {		
	}

	@Override
	public void coinAdded(CoinRack rack, Coin coin) {		
	}

	@Override
	public void coinRemoved(CoinRack rack, Coin coin) {		
	}

	
	
	
	
}
