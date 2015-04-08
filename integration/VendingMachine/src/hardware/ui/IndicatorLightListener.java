package hardware.ui;

import hardware.AbstractHardwareListener;

/**
 * Listens for events emanating from an indicator light.
 */
public interface IndicatorLightListener extends
        AbstractHardwareListener {
    /**
     * An event that is announced when the indicated light has been activated
     * (turned on).
     */
    void activated(IndicatorLight light);

    /**
     * An event that is announced when the indicated light has been deactivated
     * (turned off).
     */
    void deactivated(IndicatorLight light);
}
