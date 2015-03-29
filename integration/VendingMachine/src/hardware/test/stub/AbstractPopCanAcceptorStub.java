package hardware.test.stub;

import hardware.acceptors.AbstractPopCanAcceptor;
import hardware.exceptions.CapacityExceededException;
import hardware.products.PopCan;

public class AbstractPopCanAcceptorStub extends AbstractStub implements
        AbstractPopCanAcceptor {
	
    private boolean hasSpaceResult;

    public AbstractPopCanAcceptorStub(boolean hasSpaceResult) {
	this.hasSpaceResult = hasSpaceResult;
    }
    
    public AbstractPopCanAcceptorStub() {
    	this.hasSpaceResult = false;
    }
    
    @Override
    public void acceptPop(PopCan pop) throws CapacityExceededException {
	recordCallTo("acceptPop");
    }
    @Override
    public boolean hasSpace() {
	recordCallTo("hasSpace");
	return hasSpaceResult;
    }
}
