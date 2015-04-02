package hardware.ui;

import hardware.AbstractHardwareListener;

public interface PushButtonCodeInterpreterListener extends AbstractHardwareListener {
    public void codeEntered(String code, PushButtonCodeInterpreter intepreter);
}
