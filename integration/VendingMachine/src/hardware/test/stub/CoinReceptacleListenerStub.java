package hardware.test.stub;

import hardware.Coin;
import hardware.CoinReceptacleListener;
import hardware.CoinReceptacle;

public class CoinReceptacleListenerStub extends AbstractStub implements
        CoinReceptacleListener {
    @Override
    public void enabled(CoinReceptacle receptacle) {
	recordCallTo("enabled");
    }

    @Override
    public void disabled(CoinReceptacle receptacle) {
	recordCallTo("disabled");
    }

    @Override
    public void coinAdded(CoinReceptacle receptacle, Coin coin) {
	recordCallTo("coinAdded");
    }

    @Override
    public void coinsRemoved(CoinReceptacle receptacle) {
	recordCallTo("coinsRemoved");
    }

    @Override
    public void coinsFull(CoinReceptacle receptacle) {
	recordCallTo("coinsFull");
    }
}
