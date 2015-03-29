package hardware.racks;

import hardware.channels.IChannel;
import hardware.funds.IFund;

public interface IRack<T extends IChannel<?>> {

	public int getMaxCapacity();
	public boolean hasSpace();
	public void loadWithoutEvents(IFund...funds);
	public void connect(T sink);
	public T getSink();
}
