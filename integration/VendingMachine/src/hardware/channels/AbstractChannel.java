package hardware.channels;

import hardware.acceptors.IAcceptor;

public class AbstractChannel<T extends IAcceptor> implements IChannel<T> {
    private T sink;
    
    /**
     * Constructs a new bank note channel whose output is connected to the indicated sink.
     * @param sink
     * 			the output of the new channel 
     */
    public AbstractChannel(T sink) {
    	this.sink = sink;
    }
	
    /**
     * Returns whether the sink has space for at least one more bank note.
     */
    public boolean hasSpace() {
    	return getSink().hasSpace();
    }

    /**
     * Returns the sink to which this channel is connected.
     */
    public T getSink() {
    	return sink;
    }

}
