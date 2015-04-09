package hardware.racks;

import hardware.channels.IChannel;
import hardware.products.IRackable;


/**
 * Base rack
 *
 * @param <T> The associated sink of the rack 
 */
public interface IRack<T extends IChannel<?>> {

	/* return the maximum capacity of the associated rack */
	public int getMaxCapacity();
	/* return (true/false) - as to whether or not an associated Rackable item may be added to the rack */
	public boolean hasSpace();
	/* Permits the loading of rackable items into the rack without event triggering */
	public void loadWithoutEvents(IRackable...rackables);
	/* Connect a device that serves as a delivery point/connector to the given rack */
	public void connect(T sink);
	/* Retrieve the associated sink (see: connect) */
	public T getSink();
}
