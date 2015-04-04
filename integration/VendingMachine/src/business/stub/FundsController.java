package business.stub;

import hardware.funds.CardSlot;

public class FundsController {
	public boolean conductTransaction(int price) {return true;}
	public CardSlot getCardSlot() {return new CardSlot();}
}
