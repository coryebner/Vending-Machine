package business.funds;

import java.util.Currency;

public class SupportedCurrency {
	
	private final Currency curr;
	private float percentUSD;
	
	public SupportedCurrency(Currency curr, float usdExchangeRate){
		this.curr = curr;
		percentUSD = usdExchangeRate;
	}
	
	public Currency getCurrancy(){
		return curr;
	}
	
	public float getPercentUSD(){
		return percentUSD;
	}
	
	public void setPercentUSD(float newPercentUSD){
		percentUSD = newPercentUSD;
	}
	
}
