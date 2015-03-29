package business.funds;

import java.util.Currency;
import java.util.Vector;

public class VMCurrencies {
	private Vector<SupportedCurrency> supportedCurrencies;
	private SupportedCurrency machineLocale;
	private int iterator = 0;
	
	public VMCurrencies(SupportedCurrency machineLocale){
		this.machineLocale = machineLocale;
		supportedCurrencies = new Vector<SupportedCurrency>();
		supportedCurrencies.add(machineLocale);
	}
	
	public void addCurrency(SupportedCurrency newCurrency){	
		for(SupportedCurrency supported: supportedCurrencies){
			if(supported.getLocale() == newCurrency.getLocale()){
				return;	// already added
			}
		}
		supportedCurrencies.addElement(newCurrency);
	}
	
	public void removeCurrency(SupportedCurrency removal){
		if(machineLocale.getLocale() == removal.getLocale()){
			return; // cannot remove the local currency
		}
		supportedCurrencies.remove(removal);
	}
	
	public void updateCurrency(SupportedCurrency update){
		for(SupportedCurrency supported: supportedCurrencies){
			if(supported.getLocale() == update.getLocale()){
				supported.setPercentUSD(update.getPercentUSD());
				return;
			}
		}
		supportedCurrencies.addElement(update);
	}
	
	public void changeVMLocale(SupportedCurrency newLocale){

		for(SupportedCurrency supported: supportedCurrencies){
			if(supported.getLocale() == newLocale.getLocale()){
				supported.setPercentUSD(newLocale.getPercentUSD()); //ensure the exchange rate is updated
				machineLocale = supported;
				return;
			}
		}
		supportedCurrencies.addElement(newLocale);
		machineLocale = newLocale;

	}
	
	public SupportedCurrency getSupportedCurrency(Currency curr){
		for(SupportedCurrency supported: supportedCurrencies){
			if(supported.getLocale() == curr){
				return supported;
			}
		}
		return null;
	}
	
	public void resetIterator(){
		iterator = 0;
	}
	
	public boolean hasNext(){
		return iterator < supportedCurrencies.size();
	}
	
	public void increment(){
		if(hasNext()){
			iterator++;
		}
		
	}
	
	public SupportedCurrency getCurrentSupportedCurrency(){
		return supportedCurrencies.elementAt(iterator);
	}
	
	public float ExchangeToLocal(SupportedCurrency other, int value){
		float newValueInUSD = (value * other.getPercentUSD());
		float newValueInLocale = newValueInUSD/machineLocale.getPercentUSD();
		return newValueInLocale;
	}
	
	public static float ExchangeFromToCurrency(SupportedCurrency other, SupportedCurrency to, int value){
		float newValueInUSD = (value * other.getPercentUSD());
		float newValueInTo = newValueInUSD/to.getPercentUSD();
		return newValueInTo;
	}
	
}
