package hardware.racks;

import hardware.channels.IChannel;

// TODO: Incorporate into current setup - minor priority 
public interface IRackListener {
	
	/**
	 * Announces that the indicated rack is full
	 * @param rack the indicated rack that is full
	 */
    void rackFull(IRack<IChannel<?>> rack);

    /**
     * Announces that the indicated rack is empty
     * @param rack the indicated rack that is empty
     */
    void rackEmpty(IRack<IChannel<?>> rack);
}
