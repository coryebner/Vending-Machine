package hardware.ui;

import hardware.AbstractHardware;
import hardware.acceptors.AbstractCoinAcceptor;
import hardware.acceptors.AbstractProductAcceptor;
import hardware.exceptions.CapacityExceededException;
import hardware.exceptions.DisabledException;
import hardware.exceptions.SimulationException;
import hardware.funds.Coin;
import hardware.products.IRackable;
import hardware.products.Product;

import java.util.Vector;

/**
 * Represents a simple delivery chute device. The delivery chute has a finite
 * capacity of objects (pop cans or coins) that it can hold. This is obviously
 * not a realistic element of the simulation, but sufficient here.
 */
public class DeliveryChute extends
        AbstractHardware<DeliveryChuteListener> implements
        AbstractCoinAcceptor, AbstractProductAcceptor {
    private Vector<Object> chute = new Vector<Object>();
    private int maxCapacity;

    /**
     * Creates a delivery cute with the indicated maximum capacity of pop cans
     * and/or coins.
     * 
     * @throws SimulationException
     *             if the capacity is not a positive integer.
     */
    public DeliveryChute(int capacity) {
	if(capacity <= 0)
	    throw new SimulationException("Capacity must be a positive value: " + capacity);

	this.maxCapacity = capacity;
    }

    /**
     * Returns the maximum capacity of this delivery chute in number of products
     * and/or coins that it can hold. Causes no events.
     */
    public int getCapacity() {
	return maxCapacity;
    }


    /**
     * Tells this delivery chute to deliver the indicated coin. If the delivery
     * is successful, an "itemDelivered" event is announced to its listeners. If
     * the successful delivery causes the chute to become full, a "chuteFull"
     * event is announced to its listeners.
     * 
     * @throws CapacityExceededException
     *             if the chute is already full and the coin cannot be
     *             delivered.
     * @throws DisabledException
     *             if the chute is currently disabled.
     */
    @Override
    public void acceptCoin(Coin coin) throws CapacityExceededException,
	    DisabledException {
	if(isDisabled())
	    throw new DisabledException();
	
	if(chute.size() >= maxCapacity)
	    throw new CapacityExceededException();

	chute.add(coin);

	notifyItemDelivered();

	if(chute.size() >= maxCapacity)
	    notifyChuteFull();
    }

    /**
     * Simulates the opening of the door of the delivery chute and the removal
     * of all items therein. Announces a "doorOpened" event to its listeners
     * before the items are removed, and a "doorClosed" event after the items
     * are removed.  Disabling the chute does not prevent this.
     * 
     * @return The items that were in the delivery chute.
     */
    public Object[] removeItems() {
	notifyDoorOpened();
	Object[] items = chute.toArray();
	chute.clear();
	notifyDoorClosed();
	return items;
    }

    /**
     * Determines whether this delivery chute has space for at least one more
     * item.  Causes no events.
     */
    @Override
    public boolean hasSpace() {
	return chute.size() < maxCapacity;
    }

	public void loadWithoutEvents(IRackable... rackables) throws SimulationException {
	if(maxCapacity < chute.size() + rackables.length)
	    throw new SimulationException("Capacity of rack is exceeded by load");

	/* Type cast is safe given we've used bounded parameter types and have initialized the queue based on U */
	for(IRackable rackable : rackables)
	    chute.add(rackable);
    }


    private void notifyItemDelivered() {
	Class<?>[] parameterTypes =
	        new Class<?>[] { DeliveryChute.class };
	Object[] args = new Object[] { this };
	notifyListeners(DeliveryChuteListener.class, "itemDelivered", parameterTypes, args);
    }

    private void notifyDoorOpened() {
	Class<?>[] parameterTypes =
	        new Class<?>[] { DeliveryChute.class };
	Object[] args = new Object[] { this };
	notifyListeners(DeliveryChuteListener.class, "doorOpened", parameterTypes, args);
    }

    private void notifyDoorClosed() {
	Class<?>[] parameterTypes =
	        new Class<?>[] { DeliveryChute.class };
	Object[] args = new Object[] { this };
	notifyListeners(DeliveryChuteListener.class, "doorClosed", parameterTypes, args);
    }

    private void notifyChuteFull() {
	Class<?>[] parameterTypes =
	        new Class<?>[] { DeliveryChute.class };
	Object[] args = new Object[] { this };
	notifyListeners(DeliveryChuteListener.class, "chuteFull", parameterTypes, args);
    }

	
    /**
     * Tells this delivery chute to deliver the indicated pop can. If the
     * delivery is successful, an "itemDelivered" event is announced to its
     * listeners. If the successful delivery causes the chute to become full, a
     * "chuteFull" event is announced to its listeners.
     * 
     * @throws CapacityExceededException
     *             if the chute is already full and the pop can cannot be
     *             delivered.
     * @throws DisabledException
     *             if the chute is currently disabled.
     */
    @Override
    public void acceptProduct(Product product) throws CapacityExceededException,
	    DisabledException {
	if(isDisabled())
	    throw new DisabledException();

	if(chute.size() >= maxCapacity)
	    throw new CapacityExceededException();

	chute.add(product);

	notifyItemDelivered();

	if(chute.size() >= maxCapacity)
	    notifyChuteFull();
    }
}
