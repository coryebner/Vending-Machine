package hardware.channels;

import hardware.acceptors.IAcceptor;

public interface IChannel<T extends IAcceptor> {
	

	/**
	 * Returns whether the sink has space for at least one more bank note.
	 * @return
	 * 		true if there is space for at least one more bank note.
	 */
    public boolean hasSpace();
    
    
    /**
     * Returns the attacked channel sink object
     * @return 
     * 		the associated sink 
     */
    public T getSink();
}
