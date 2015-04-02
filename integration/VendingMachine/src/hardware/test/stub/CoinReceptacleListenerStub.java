package hardware.test.stub;

import hardware.funds.Coin;
import hardware.funds.CoinReceptacle;
import hardware.funds.CoinReceptacleListener;

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
