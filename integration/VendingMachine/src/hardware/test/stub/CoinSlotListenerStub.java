package hardware.test.stub;

import hardware.funds.Coin;
import hardware.funds.CoinSlot;
import hardware.funds.CoinSlotListener;

public class CoinSlotListenerStub extends AbstractStub implements
        CoinSlotListener {
    @Override
    public void validCoinInserted(CoinSlot coinSlot, Coin coin) {
	recordCallTo("validCoinInserted");
    }

    @Override
    public void coinRejected(CoinSlot coinSlot, Coin coin) {
	recordCallTo("coinRejected");
    }
}
