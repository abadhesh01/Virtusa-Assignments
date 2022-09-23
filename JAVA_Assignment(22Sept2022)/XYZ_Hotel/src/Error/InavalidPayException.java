package Error;

@SuppressWarnings("serial")
public class InavalidPayException extends Exception {

	public InavalidPayException() {
		super("Error: Total amount to be paid cannot be zero(0) or negative!");
	}

	public InavalidPayException(String message) {
		super(message);
	}
}
