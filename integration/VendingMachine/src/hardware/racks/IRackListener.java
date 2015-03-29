package hardware.racks;

import hardware.channels.IChannel;

public interface IRackListener {
    /**
     * Announces that the indicated rack is full.
     */
    void rackFull(IRack<IChannel<?>> rack);

    /**
     * Announces that the indicated rack is empty.
     */
    void rackEmpty(IRack<IChannel<?>> rack);
}
