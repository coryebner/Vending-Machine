package hardware.test.stub;

import hardware.funds.Coin;
import hardware.racks.CoinRack;
import hardware.racks.CoinRackListener;

public class CoinRackListenerStub extends AbstractStub implements
        CoinRackListener {
    @Override
    public void coinsFull(CoinRack rack) {
	recordCallTo("coinsFull");
    }

    @Override
    public void coinsEmpty(CoinRack rack) {
	recordCallTo("coinsEmpty");
    }

    @Override
    public void coinAdded(CoinRack rack, Coin coin) {
	recordCallTo("coinAdded");
    }

    @Override
    public void coinRemoved(CoinRack rack, Coin coin) {
	recordCallTo("coinRemoved");
    }
}
