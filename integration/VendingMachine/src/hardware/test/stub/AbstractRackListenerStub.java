package hardware.test.stub;

import hardware.racks.IRack;
import hardware.racks.IRackListener;

@SuppressWarnings("rawtypes")
public abstract class AbstractRackListenerStub extends AbstractStub implements IRackListener {

    @Override
	public void rackFull(IRack rack) {
		recordCallTo("rackFull");		
	}
    
    @Override
	 public void rackEmpty(IRack rack)  {
		recordCallTo("rackEmpty");		
	}

}
