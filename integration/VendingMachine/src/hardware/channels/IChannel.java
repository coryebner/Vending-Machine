package hardware.channels;

import hardware.acceptors.IAcceptor;

public interface IChannel<T extends IAcceptor> {
	
    /**
     * Returns whether the sink has space for at least one more banknote.
     */
    public boolean hasSpace();
    
    /**
     * Returns the sink to which this channel is connected.
     */
    public T getSink();
    /**
     * Returns whether the sink has space for at least one more banknote.
     */
}
