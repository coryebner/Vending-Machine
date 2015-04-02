package hardware.ui;

import hardware.AbstractHardwareListener;

public interface TouchScreenKeyboardInterpreterListener extends AbstractHardwareListener  {
    public void commandEntered(TouchScreenKeyboardInterpreter intepreter, String inputString);
}
