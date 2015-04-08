package business.funds.tests;

import static org.junit.Assert.*;
import java.util.Locale;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import business.funds.Currency;


public class CurrencyTest {
	
	Currency currency;
	float value = 0;;
	
	final float EXCHANGE_US = 1.00F;
	final float EXCHANGE_CAN = 0.79F;
	final float EXCHANGE_UK = 1.09F;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		currency = null;
		value = 0;
	}

	@Test
	public void testCurrencyConstructorValidUS() {
		currency = new Currency(Locale.US);
		assertEquals(Locale.US,currency.getVendingMachineLocale());
	}
	
	@Test
	public void testCurrencyConstructorValidCANADA() {
		currency = new Currency(Locale.CANADA);
		assertEquals(Locale.CANADA,currency.getVendingMachineLocale());
	}
	
	@Test
	public void testCurrencyConstructorValidUK() {
		currency = new Currency(Locale.UK);
		assertEquals(Locale.UK,currency.getVendingMachineLocale());
	}
	
	@Test
	public void testCurrencyConstructorInvalidCANADA_FRENCH() {
		currency = new Currency(Locale.CANADA_FRENCH);
		assertEquals(Locale.US,currency.getVendingMachineLocale());
	}
	
	public void testCurrencyConstructorInvalidNull() {
		currency = new Currency(null);
		assertEquals(Locale.US,currency.getVendingMachineLocale());
	}

	@Test
	public void testIsSupportedLocaleUS() {
		currency = new Currency(Locale.US);
		assertTrue(currency.isSupportedLocale(Locale.US));
	}
	
	@Test
	public void testIsSupportedLocaleCAN() {
		currency = new Currency(Locale.US);
		assertTrue(currency.isSupportedLocale(Locale.CANADA));
	}
	
	@Test
	public void testIsSupportedLocaleUK() {
		currency = new Currency(Locale.US);
		assertTrue(currency.isSupportedLocale(Locale.UK));
	}
	
	@Test
	public void testIsSupportedLocaleInvalid() {
		currency = new Currency(Locale.US);
		assertFalse(currency.isSupportedLocale(Locale.CANADA_FRENCH));
	}
	
	@Test
	public void testIsSupportedLocaleInvalidNull() {
		currency = new Currency(Locale.US);
		assertFalse(currency.isSupportedLocale(null));
	}

	@Test
	public void testExchangeToMachineCurrencyMachineUSDValueCAD() {
		currency = new Currency(Locale.US);
		value = 100;
		float expected = value * EXCHANGE_CAN;
		float actual = currency.ExchangeToMachineCurrency(Locale.CANADA, value);
		assertEquals(expected,actual,0.0000001);
		
	}
	
	@Test
	public void testExchangeToMachineCurrencyMachineUKValueCAD() {
		currency = new Currency(Locale.UK);
		value = 100;
		float expected = value * EXCHANGE_CAN/EXCHANGE_UK;
		float actual = currency.ExchangeToMachineCurrency(Locale.CANADA, value);
		assertEquals(expected,actual,0.0000001);
	}

	
	@Test
	public void testExchangeFromToMachineCurrencyMachineUSDValueCAD() {
		currency = new Currency(Locale.US);
		value = 100;
		float expected = value * EXCHANGE_CAN;
		float actual = currency.ExchangeFromToCurrency(Locale.CANADA, Locale.US, value);
		assertEquals(expected,actual,0.0000001);
		
	}
	
	@Test
	public void testExchangeFromToMachineCurrencyMachineUKValueCAD() {
		currency = new Currency(Locale.UK);
		value = 100;
		float expected = value * EXCHANGE_CAN/EXCHANGE_UK;
		float actual = currency.ExchangeFromToCurrency(Locale.CANADA,Locale.UK, value);
		assertEquals(expected,actual,0.0000001);
		assertEquals(72.47706422018348623853,actual,0.001);
	}

}
