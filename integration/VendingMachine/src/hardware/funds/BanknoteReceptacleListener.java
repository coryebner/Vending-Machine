package hardware.funds;

import hardware.AbstractHardwareListener;

/**
 * Listens for events emanating from a banknote receptacle.
 */
public interface BanknoteReceptacleListener extends AbstractHardwareListener {
    /**
     * An event that announces that the indicated banknote has been added to the
     * indicated receptacle.
     */
    void banknoteAdded(BanknoteReceptacle receptacle, Banknote banknote);

    /**
     * An event that announces that all banknotes have been removed from the
     * indicated receptacle.
     */
    void banknoteRemoved(BanknoteReceptacle receptacle);

    /**
     * An event that announces that the indicated receptacle is now full.
     */
    void banknoteFull(BanknoteReceptacle receptacle);

    /**
     * An event that announces that the indicated receptacle is now enabled.
     */
    void enabled(BanknoteReceptacle receptacle);

    /**
     * An event that announces that the indicated receptacle is now disabled.
     */
    void disabled(BanknoteReceptacle receptacle);
}
