package hardware.test.stub;

import hardware.products.PopCan;
import hardware.racks.PopCanRack;
import hardware.racks.PopCanRackListener;

public class PopCanRackListenerStub extends AbstractStub implements
        PopCanRackListener {
    @Override
    public void popAdded(PopCanRack popRack, PopCan pop) {
	recordCallTo("popAdded");
    }

    @Override
    public void popRemoved(PopCanRack popRack, PopCan pop) {
	recordCallTo("popRemoved");
    }

    @Override
    public void popFull(PopCanRack popRack) {
	recordCallTo("popFull");
    }

    @Override
    public void popEmpty(PopCanRack popRack) {
	recordCallTo("popEmpty");
    }
}
