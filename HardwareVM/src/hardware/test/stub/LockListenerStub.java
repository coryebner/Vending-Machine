package hardware.test.stub;

import hardware.Lock;
import hardware.LockListener;

public class LockListenerStub extends AbstractStub implements
        LockListener {
    @Override
    public void locked(Lock lock) {
	recordCallTo("locked");
    }

    @Override
    public void unlocked(Lock lock) {
	recordCallTo("unlocked");
    }
}
