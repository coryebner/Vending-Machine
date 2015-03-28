package hardware.test.stub;

import hardware.acceptors.AbstractPopCanAcceptor;
import hardware.exceptions.CapacityExceededException;
import hardware.products.PopCan;

public class AbstractPopCanAcceptorStub extends AbstractStub implements
        AbstractPopCanAcceptor {
    @Override
    public void acceptPop(PopCan pop) throws CapacityExceededException {
	recordCallTo("acceptPop");
    }
}
