package hardware.racks;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.acceptors.IAcceptor;
import hardware.channels.IChannel;
import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.exceptions.EmptyException;
import hardware.exceptions.SimulationException;
import hardware.products.IRackable;

import java.util.LinkedList;
import java.util.Queue;

public abstract class AbstractRack<T extends AbstractHardwareListener, U extends IRackable, V extends IChannel<? extends IAcceptor>> extends AbstractHardware<T> 
implements IRack<V> {
    final private int maxCapacity;
    private Queue<U> queue = new LinkedList<U>();
    private V sink;
    
    
    public AbstractRack(int capacity) {
    	if(capacity <= 0)
    	    throw new SimulationException("Capacity cannot be non-positive: " + capacity);

    	this.maxCapacity = capacity;
    }
    
    /**
     * Returns the maximum capacity of this pop can rack. Causes no events.
     */
    public int getMaxCapacity() {
    	return this.maxCapacity;
    }
    
    public V getSink() {
    	return this.sink;
    }
    
    /**
     * Connects the pop can rack to an outlet channel, such as the delivery
     * chute. Causes no events.
     * 
     * @param sink
     *            The channel to be used as the outlet for dispensed pop cans.
     */
    public void connect(V sink) {
    	this.sink = sink;
    }
    
    /**
     * getQueue
     * 
     * @synopsis
     * 		Getter for the rack queue
     * 
     * @return		Queue<U> 		where U extends IRackable as denoted in the class def.
     */
    protected Queue<U> getQueue() {
    	return this.queue;
    }
    /**
     * Allows a set of funds to be loaded into the rack without events being
     * announced. Existing coins in the rack are not removed.
     * 
     * @throws SimulationException
     *             if the number of coins to be loaded exceeds the capacity of
     *             the rack.
     */
    // TODO: Change funds to something else (PopCans != Funds)

	@SuppressWarnings("unchecked")
	public void loadWithoutEvents(IRackable... rackables) throws SimulationException {
	if(maxCapacity < queue.size() + rackables.length)
	    throw new SimulationException("Capacity of rack is exceeded by load");

	for(IRackable rackable : rackables)
	    queue.add((U)rackable);
    }
        
	/**
	 * hasSpace
	 * 
	 * @synopsis
	 * 		determine whether the given rack currently has space for any more Rackable objects
	 * 
	 * @return			TRUE			if the size of the queue is less than configured capacity
	 * @return 			FALSE			otherwise
	 */
    public boolean hasSpace() {
    	return queue.size() < this.maxCapacity;
    }
    
    /**
     * addToRack
     * 
     * @synopsis
     * 		Adds an object extending the IRackable interface (U extends IRackable) to the 
     * 		queue associated to this rack.
     * 
     * @param rackable						The object to be added to the queue
     * @throws DisabledException			if the hardware is currently disabled
     * @throws CapacityExceededException	if the current queue is at capacity
     */
    public void addToRack(U rackable) throws DisabledException, CapacityExceededException {
    	if(isDisabled())
    	    throw new DisabledException();

    	if(getQueue().size() >= getMaxCapacity())
    	    throw new CapacityExceededException();

    	getQueue().add(rackable);
    }
    
    /**
     * removeFromRack
     * 
     * @synopsis
     * 		remove the first element from the front of the queue, and return it to the caller.
     * 
     * @return		U				(U extends IRackable) and could represent things like:
     * 								*	PopCan, Product, Coin, etc.
     * @throws DisabledException	if the current hardware is disabled
     * @throws EmptyException		if the queue is empty
     */
    public U removeFromRack() throws DisabledException, EmptyException {
    	if(isDisabled())
    	    throw new DisabledException();

    	if(getQueue().size() == 0)
    	    throw new EmptyException();

    	return getQueue().remove();
    }
}
