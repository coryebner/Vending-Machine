package SDK.rifffish;

/**
 * An Error Object
 * Errors are not always exceptional
 * 
 * Supports System.Out printing
 *
 */
public class Error {
	private String errorMessage = "";
	private boolean errorFlag = false;
	
	public Error(String error) {
		errorMessage = error;
		
		if (error != null)
			errorFlag = true;
	}
	
	public boolean foundError() {
		return errorFlag;
	}
	
	public String toString() {
		return "Error -> " + errorMessage;
	}
}
