package business.funds;

public interface ExactChangeControllerListener {
	void exactChangeAvailable(ExactChangeController ecc);
	
	void exactChangeUnavailable(ExactChangeController ecc);
}
