package hardware.ui;

import hardware.AbstractHardware;
import hardware.AbstractHardwareListener;
import hardware.exceptions.CodeNotReadyException;

import java.util.HashMap;
import java.util.Map;

public class PushButtonCodeInterpreter extends AbstractHardware<PushButtonCodeInterpreterListener> implements PushButtonListener {
    private Map<PushButton, Character> characters;

    private enum State {
	START, WAIT, READY
    }

    private State state = State.START;
    private StringBuilder sb = new StringBuilder();

    public PushButtonCodeInterpreter(Map<PushButton, Character> characters) {
	this.characters = new HashMap<>(characters);

	for(PushButton button : characters.keySet())
	    button.register(this);
    }

    @Override
    public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {
    }

    @Override
    public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {
    }

    @Override
    public void pressed(PushButton button) {
	Character c = characters.get(button);
	if(state == State.START) {
	    if(c != null && Character.isLetter(c)) {
		sb.append(c);
		state = State.WAIT;
	    }
	}
	else {
	    if(c != null && Character.isDigit(c)) {
		sb.append(c);
		state = State.READY;
		notifyCodeEntered();
	    }
	    else {
		state = State.START;
		sb = new StringBuilder();
	    }
	}
    }

    /**
     * Signals whether the code is ready to be read. To avoid race conditions,
     * this call should be made within a synchronized statement on this object,
     * followed by a call to read within the synchronized statement:
     * 
     * <pre>
     * PushButtonCodeInterpreter obj = ...;
     * String code = null;
     * 
     * synchronized(obj) {
     *   if(obj.isReady())
     *     code = read();
     * }
     * </pre>
     */
    public boolean isReady() {
	return state == State.READY;
    }

    /**
     * If the code is ready for consumption, returns the code as a string.
     * Otherwise, it throws an exception. In either case, the state is reset to
     * START.
     * 
     * @throws CodeNotReadyException
     */
    public String read() throws CodeNotReadyException {
	try {
	    if(isReady())
		return sb.toString();
	    else
		throw new CodeNotReadyException();
	}
	finally {
	    state = State.START;
	    sb = new StringBuilder();
	}
    }

    private void notifyCodeEntered() {
	Class<?>[] parameterTypes = new Class<?>[] {String.class, PushButtonCodeInterpreter.class};
	Object[] args = new Object[] {this};
	notifyListeners(PushButtonCodeInterpreterListener.class, "pressed", parameterTypes, args);
    }

}
