package hardware.funds;

import java.util.Locale;

import business.funds.VMCurrencies;

public class test {

	public static void main(String[] args) {
		Card card = new Card(Card.CardType.PREPAID, "123", "JoeyJoey", "1234", "06/2018", Locale.US, 1000);
		VMCurrencies vmc = new VMCurrencies(Locale.US);
		
		System.out.println("Card Locale: " + card.getCurrency().getCurrencyCode());
		System.out.println("Vending Machine Locale: " + vmc.getVMCurrency().getCurrencyCode());
		if(card.getCurrency().getCurrencyCode().contentEquals(vmc.getVMCurrency().getCurrencyCode()))
			System.out.println("Yes");
		else
			System.out.println("No");

	}

}
