package Error;

@SuppressWarnings("serial")
public class MealNotFoundException extends Exception {

	public MealNotFoundException() {
		super();
	}

	public MealNotFoundException(String message) {
		super(message);
	}

	public MealNotFoundException(long mealId) {
		super("Error: Meal with id='" + mealId + "' was not found! :(");
	}
}
