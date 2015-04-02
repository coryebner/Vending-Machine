package hardware.ui;

import hardware.AbstractHardwareListener;

public interface ConfigurationPanelTransmitterListener extends AbstractHardwareListener  {
    public void commandEntered(ConfigurationPanelTransmitter intepreter, String inputString);
}
