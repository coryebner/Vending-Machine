package business.funds;

import java.util.Currency;

public class SupportedCurrency {
	
	private final Currency locale;
	private float percentUSD;
	
	public SupportedCurrency(Currency locale, float usdExchangeRate){
		this.locale = locale;
		percentUSD = usdExchangeRate;
	}
	
	public Currency getLocale(){
		return locale;
	}
	
	public float getPercentUSD(){
		return percentUSD;
	}
	
	public void setPercentUSD(float newPercentUSD){
		percentUSD = newPercentUSD;
	}
	
}
