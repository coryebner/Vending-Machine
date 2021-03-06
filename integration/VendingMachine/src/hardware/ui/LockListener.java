package hardware.ui;

import hardware.AbstractHardwareListener;

/**
 * Listens for events emanating from a lock.
 */
public interface LockListener extends AbstractHardwareListener {
    /**
     * An event that announces that the indicated lock has been locked.
     */
    void locked(Lock lock);

    /**
     * An event that announces that the indicated lock has been unlocked.
     */
    void unlocked(Lock lock);
}
