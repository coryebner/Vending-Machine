package hardware.test.stub;

import hardware.Coin;
import hardware.CoinSlotListener;
import hardware.CoinSlot;

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
