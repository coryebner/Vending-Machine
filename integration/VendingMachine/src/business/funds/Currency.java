package business.funds;

import java.security.InvalidParameterException;
import java.util.Locale;
import java.util.Vector;

public class Currency {
	
	private Vector<MachineCurrency> machineCurrencies;
	private MachineCurrency machineCurrency;
	
	private static float EXCHANGE_US = 1.00F;
	private static float EXCHANGE_CAN = 0.79F;
	private static float EXCHANGE_UK = 1.09F;
	
	public Currency(Locale locale){
		machineCurrencies = new Vector<MachineCurrency>();
		setupSubsetOfCurrenciesHardCode(locale);
	}
	
	public Locale getVendingMachineLocale(){
		if(machineCurrency!=null){
			return machineCurrency.locale;
		}
		return null;
	}
	
	private void setupSubsetOfCurrenciesHardCode(Locale arg_locale){
		MachineCurrency curr;
		Locale loc ;
		
		loc = Locale.CANADA;
		curr = new MachineCurrency(loc,EXCHANGE_CAN);
		machineCurrencies.addElement(curr);
		if(loc.equals(arg_locale)){
			machineCurrency = curr;
		}
		
		loc = Locale.UK;
		curr = new MachineCurrency(loc,EXCHANGE_UK);
		machineCurrencies.addElement(curr);
		if(loc.equals(arg_locale)){
			machineCurrency = curr;
		}
		
		loc = Locale.US;
		curr = new MachineCurrency(loc,EXCHANGE_US);
		machineCurrencies.addElement(curr);
		if(loc.equals(arg_locale) || machineCurrency == null){
			machineCurrency = curr;
		}
		
	}
	
	public boolean isSupportedLocale(Locale locale){
		boolean found = false;;
		for(MachineCurrency m: machineCurrencies){
			if(m.locale == locale){
				found = true;
			}
		}
		return found;
	}

	
	public float ExchangeToMachineCurrency(Locale other, float value){
		if(!isSupportedLocale(other)){
			throw new InvalidParameterException();
		}
		MachineCurrency otherCurrency = machineCurrency;
		for(MachineCurrency m: machineCurrencies){
			if(m.locale.equals(other)){
				otherCurrency = m;
				break;
			}
		}
		float newValueInUSD = value * otherCurrency.exchangeRate;
		float newValueInMachingCurrency = newValueInUSD / machineCurrency.exchangeRate;
		return newValueInMachingCurrency;
	}
	
	public float ExchangeFromToCurrency(Locale from, Locale to, float value){
		if(!isSupportedLocale(from)){
			throw new InvalidParameterException();
		}
		if(!isSupportedLocale(to)){
			throw new InvalidParameterException();
		}
		MachineCurrency fromCurrency = machineCurrency;
		for(MachineCurrency m: machineCurrencies){
			if(m.locale.equals(from)){
				fromCurrency = m;
				break;
			}
		}
		MachineCurrency toCurrency = machineCurrency;
		for(MachineCurrency m: machineCurrencies){
			if(m.locale.equals(to)){
				toCurrency = m;
				break;
			}
		}
		
		float newValueInUSD = (value * fromCurrency.exchangeRate);
		float newValueInTo = newValueInUSD/toCurrency.exchangeRate;
		return newValueInTo;
	}
	
	
	private class MachineCurrency{
		private Locale locale;
		private float exchangeRate;
		
		MachineCurrency(Locale locale, float exchangeRate){
			this.locale = locale;
			this.exchangeRate = exchangeRate;
		}
	}
}
