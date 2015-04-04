package hardware.funds;

import hardware.AbstractHardwareListener;

/**
 * Listens for events emanating from a bank note receptacle.
 */
public interface BanknoteReceptacleListener extends AbstractHardwareListener {
	/**
     * An event that announces that the indicated bank note has been added to the
     * indicated receptacle.
     * 
	 * @param receptacle
	 * 			   the receptacle in which a bank note has been added
	 * @param banknote
	 * 			   the bank note that was added to the associated bank note receptacle 
	 */
    void banknoteAdded(BanknoteReceptacle receptacle, Banknote banknote);

    /**
     * An event that announces that all bank notes have been removed from the indicated receptacle
     * @param receptacle
     * 			the indicated receptacle where all bank notes have been removed.
     */
    void banknoteRemoved(BanknoteReceptacle receptacle);
    
    /**
     * An event that announces that the indicated receptacle is now full.
     * 
     * @param receptacle
     * 		   the receptacle that is now full.
     */
    void banknoteFull(BanknoteReceptacle receptacle);
    
    /**
     * An event that announces that the indicated receptacle is now enabled.
     * 
     * @param receptacle
     * 		   the receptacle that is now enabled.
     */
    void enabled(BanknoteReceptacle receptacle);

    /**
     * An event that announces that the indicated receptacle is now disabled.
     * 
      * @param receptacle
     * 		   the receptacle that is now disabled.
     */
    void disabled(BanknoteReceptacle receptacle);
}
