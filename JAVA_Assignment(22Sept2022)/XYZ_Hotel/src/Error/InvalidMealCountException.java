package Error;

@SuppressWarnings("serial")
public class InvalidMealCountException extends Exception {

	public InvalidMealCountException() {
		super("Error: Meal count cannot be zero(0) or negative!");
	}

	public InvalidMealCountException(String message) {
		super(message);
	}

}
