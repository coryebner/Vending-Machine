package hardware.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import hardware.Lock;
import hardware.test.stub.LockListenerStub;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LockTest {
    private Lock lock;
    private LockListenerStub listener;

    @Before
    public void setup() {
	lock = new Lock();
	listener = new LockListenerStub();
	lock.register(listener);

	listener.assertProtocol();
    }

    @After
    public void teardown() {
	lock.deregisterAll();
	lock = null;
	listener = null;
    }

    @Test
    public void testLock() {
	listener.expect("locked");

	assertTrue(lock.isLocked());
	lock.lock();
	assertTrue(lock.isLocked());

	listener.assertProtocol();
    }

    @Test
    public void testUnlock() {
	listener.expect("unlocked");

	assertTrue(lock.isLocked());
	lock.unlock();
	assertFalse(lock.isLocked());
	
	listener.assertProtocol();
    }

    @Test
    public void testUnlockAfterLoad() {
	lock.loadWithoutEvents(true);
	listener.expect("unlocked");

	assertTrue(lock.isLocked());
	lock.unlock();
	assertFalse(lock.isLocked());

	listener.assertProtocol();
    }

    @Test
    public void testLockAfterLoad() {
	lock.loadWithoutEvents(false);
	listener.expect("locked");

	assertFalse(lock.isLocked());
	lock.lock();
	assertTrue(lock.isLocked());

	listener.assertProtocol();
    }
}
