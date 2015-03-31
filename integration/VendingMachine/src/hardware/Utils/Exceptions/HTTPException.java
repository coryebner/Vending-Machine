package hardware.Utils.Exceptions;

@SuppressWarnings("serial")
public class HTTPException extends Exception {

	public HTTPException () {}
	
    public HTTPException(String message)
    {
       super(message);
    }
}
