package hardware.test.stub;

import hardware.acceptors.AbstractCoinAcceptor;
import hardware.exceptions.CapacityExceededException;
import hardware.funds.Coin;

public class AbstractCoinAcceptorStub extends AbstractStub implements
        AbstractCoinAcceptor {
    private boolean hasSpaceResult;

    public AbstractCoinAcceptorStub(boolean hasSpaceResult) {
	this.hasSpaceResult = hasSpaceResult;
    }

    @Override
    public void acceptCoin(Coin coin) throws CapacityExceededException {
	recordCallTo("acceptCoin");
    }

    @Override
    public boolean hasSpace() {
	recordCallTo("hasSpace");
	return hasSpaceResult;
    }
}
