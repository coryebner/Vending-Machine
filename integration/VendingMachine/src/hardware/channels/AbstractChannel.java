package hardware.channels;

import hardware.acceptors.IAcceptor;

public class AbstractChannel<T extends IAcceptor> implements IChannel<T> {
    private T sink;
    
    /**
     * Constructs a new banknote channel whose output is connected to the indicated
     * sink.
     */
    public AbstractChannel(T sink) {
    	this.sink = sink;
    }
	
    /**
     * Returns whether the sink has space for at least one more banknote.
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
