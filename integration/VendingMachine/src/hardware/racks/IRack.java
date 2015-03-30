package hardware.racks;

import hardware.channels.IChannel;
import hardware.products.IRackable;

public interface IRack<T extends IChannel<?>> {

	public int getMaxCapacity();
	public boolean hasSpace();
	public void loadWithoutEvents(IRackable...rackables);
	public void connect(T sink);
	public T getSink();
}
