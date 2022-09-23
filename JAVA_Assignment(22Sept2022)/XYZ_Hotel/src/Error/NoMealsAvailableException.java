package Error;

@SuppressWarnings("serial")
public class NoMealsAvailableException extends Exception {

	public NoMealsAvailableException() {
		super("Error: No meals are available right now! :(");
	}

	public NoMealsAvailableException(String message) {
		super(message);
	}

}
