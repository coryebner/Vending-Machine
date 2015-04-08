package hardware.funds;

import hardware.products.IRackable;

import java.util.Locale;

public interface IFund extends IRackable {
	public int getValue();
	public Locale getLocale();
}
