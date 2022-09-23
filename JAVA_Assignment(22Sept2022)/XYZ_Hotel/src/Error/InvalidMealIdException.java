package Error;

@SuppressWarnings("serial")
public class InvalidMealIdException extends Exception {

	public InvalidMealIdException() {
		super("Error: Meal id cannot be zero(0) or negative!");
	}

	public InvalidMealIdException(String message) {
		super(message);
	}

}
