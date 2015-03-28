package hardware;

/**
 *
 */
public interface AbstractBanknoteAcceptor {
	/**
	 * 
	 * @param banknote
	 * 
	 * @throws CapacityExceededException
	 * 
	 * @throws DisabledException
	 */
    void acceptBanknote(Banknote banknote) throws CapacityExceededException, DisabledException;

    /**
     * Checks whether the device has enough space to expect one more item. If
     * this method returns true, an immediate call to acceptBanknote should not
     * throw CapacityExceededException, unless an asynchronous addition has
     * occurred in the meantime.
     * 
     * @return true if there is space, false if there is not space
     */
    boolean hasSpace();
}