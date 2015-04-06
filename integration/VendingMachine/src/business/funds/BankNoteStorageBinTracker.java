package business.funds;

import java.util.HashMap;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.funds.Banknote;
import hardware.funds.BanknoteReceptacle;
import hardware.funds.BanknoteReceptacleListener;
import hardware.funds.Coin;
import hardware.funds.CoinReceptacle;
import hardware.funds.CoinReceptacleListener;

public class BankNoteStorageBinTracker implements BanknoteReceptacleListener{
	
	boolean full = false;
	int [] banknotesPresent;
	int [] banknoteDenominations;
	HashMap<Integer,Integer> valueToDenominationMap;
	
	public BankNoteStorageBinTracker(int[] bankNoteDenominations){
		this.banknoteDenominations = bankNoteDenominations;
		banknotesPresent = new int[bankNoteDenominations.length];
		valueToDenominationMap = new HashMap<Integer,Integer>();
		for(int i = 0; i < bankNoteDenominations.length; i++){
			valueToDenominationMap.put(bankNoteDenominations[i], i);
		}
	}
	
	public int getNumberOfDenominations(){
		return banknoteDenominations.length;
	}
	public int getValueAtIndex(int index){
		return banknoteDenominations[index];
	}
	public int getNumberOfBankNotesByIndex(int index){
		return banknotesPresent[index];
	}
	public int getTotalValueStoredInBin(){
		int value = 0;
		for(int i = 0; i< banknoteDenominations.length; i++){
			value += banknoteDenominations[i]*banknotesPresent[i];
		}
		return value;
	}
	public boolean isFull(){
		return full;
	}
	
	@Override
	public void banknoteAdded(BanknoteReceptacle receptacle, Banknote banknote) {
		int value = banknote.getValue();
		int index = valueToDenominationMap.get(new Integer(value)).intValue();
		banknotesPresent[index]++;
	}

	@Override
	public void BanknoteRemoved(BanknoteReceptacle receptacle) {
		// empties state of the overflow bin
		full = false;
		for(int i=0; i< banknotesPresent.length ; i++){
			banknotesPresent[i]=0;
		}
	}

	@Override
	public void BanknotesFull(BanknoteReceptacle receptacle) {
		full= true;
	}

	@Override public void enabled(BanknoteReceptacle receptacle) {}
	@Override public void disabled(BanknoteReceptacle receptacle) {}
	@Override public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {}
	@Override public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {}


}
