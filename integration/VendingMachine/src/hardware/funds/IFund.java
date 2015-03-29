package hardware.funds;

import java.util.Locale;

public interface IFund extends IRackable {
	
	/* Guaranteed methods related to IFund objects */
	public int getValue();
	public Locale getLocale();
}
