package business.funds;

import java.util.HashMap;
import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.funds.Coin;
import hardware.funds.CoinReceptacle;
import hardware.funds.CoinReceptacleListener;

public class StorageBinTracker implements CoinReceptacleListener{
	
	int [] coinsPresent;
	int [] rackdenominations;
	HashMap<Integer,Integer> valueToDenominationMap;
	
	public StorageBinTracker(int[] coinRackDenominations){
		this.rackdenominations = coinRackDenominations;
		coinsPresent = new int[coinRackDenominations.length];
		valueToDenominationMap = new HashMap<Integer,Integer>();
		for(int i = 0; i < coinRackDenominations.length; i++){
			valueToDenominationMap.put(coinRackDenominations[i], i);
		}
	}
	
	public int getNumberOfDenominations(){
		return rackdenominations.length;
	}
	public int getValueAtIndex(int index){
		return rackdenominations[index];
	}
	public int getNumberOfCoinsByIndex(int index){
		return coinsPresent[index];
	}
	public int getTotalValueStoredInBin(){
		int value = 0;
		for(int i = 0; i< rackdenominations.length; i++){
			value += rackdenominations[i]*coinsPresent[i];
		}
		return value;
	}
	
	
	@Override
	public void coinAdded(CoinReceptacle receptacle, Coin coin) {
		int value = coin.getValue();
		int index = valueToDenominationMap.get(new Integer(value)).intValue();
		coinsPresent[index]++;
	}

	@Override public void coinsRemoved(CoinReceptacle receptacle) {}
	@Override public void coinsFull(CoinReceptacle receptacle) {}
	@Override public void enabled(CoinReceptacle receptacle) {}
	@Override public void disabled(CoinReceptacle receptacle) {}
	@Override public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {}
	@Override public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {}


}
