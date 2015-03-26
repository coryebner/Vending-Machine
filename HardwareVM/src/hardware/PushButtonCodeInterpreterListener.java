package hardware;

public interface PushButtonCodeInterpreterListener extends AbstractHardwareListener {
    public void codeEntered(String code, PushButtonCodeInterpreter intepreter);
}
