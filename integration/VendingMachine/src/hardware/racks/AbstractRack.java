package hardware.racks;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.channels.IChannel;
import hardware.exceptions.SimulationException;
import hardware.funds.IFund;

import java.util.LinkedList;
import java.util.Queue;

public abstract class AbstractRack<T extends AbstractHardwareListener, U extends IFund, V extends IChannel<?>> extends AbstractHardware<T> 
implements IRack<V> {
    private int maxCapacity;
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
	public void loadWithoutEvents(IFund... funds) throws SimulationException {
	if(maxCapacity < queue.size() + funds.length)
	    throw new SimulationException("Capacity of rack is exceeded by load");

	for(IFund fund : funds)
	    queue.add((U)fund);
    }
        
    private void notifyRackFull() {
	Class<?>[] parameterTypes =
	        new Class<?>[] { PopCanRack.class };
	Object[] args = new Object[] { this };
	notifyListeners(IRackListener.class, "rackFull", parameterTypes, args);
    }

    private void notifyRackEmpty() {
	Class<?>[] parameterTypes =
	        new Class<?>[] { PopCanRack.class };
	Object[] args = new Object[] { this };
	notifyListeners(IRackListener.class, "rackEmpty", parameterTypes, args);
    }
    
    public boolean hasSpace() {
	return queue.size() < this.maxCapacity;
    }
}
