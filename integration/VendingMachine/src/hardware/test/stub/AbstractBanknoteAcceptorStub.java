package hardware.test.stub;

import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.funds.Banknote;
import hardware.acceptors.AbstractBanknoteAcceptor;

public class AbstractBanknoteAcceptorStub extends AbstractStub implements
        AbstractBanknoteAcceptor {
    private boolean hasSpaceResult;

    public AbstractBanknoteAcceptorStub(boolean hasSpaceResult) {
    	super();
    	this.hasSpaceResult = hasSpaceResult;
    }

    @Override
    public boolean hasSpace() {
	return hasSpaceResult;
    }

	@Override
	public void acceptBanknote(Banknote banknote)
			throws CapacityExceededException, DisabledException {
		//recordCallTo("acceptBanknote");
		
	}
}
