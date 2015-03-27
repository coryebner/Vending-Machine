package hardware.test.stub;

import hardware.AbstractPopCanAcceptor;
import hardware.CapacityExceededException;
import hardware.PopCan;

public class AbstractPopCanAcceptorStub extends AbstractStub implements
        AbstractPopCanAcceptor {
    @Override
    public void acceptPop(PopCan pop) throws CapacityExceededException {
	recordCallTo("acceptPop");
    }
}
