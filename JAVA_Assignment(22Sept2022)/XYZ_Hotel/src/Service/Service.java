package Service;

import java.util.List;

import Error.CartIsEmptyException;
import Error.InavalidPayException;
import Error.InvalidMealCountException;
import Error.InvalidMealIdException;
import Error.MealNotFoundException;
import Error.NoMealsAvailableException;
import Model.Meal;

public interface Service {

	void setReceiptNumber(long receiptNumber);

	void setMeals(List<Meal> meals) throws Exception;

	void viewMenu() throws NoMealsAvailableException, Exception;

	void viewCart() throws CartIsEmptyException, Exception;

	void addItem(long mealId, int mealCount)
			throws MealNotFoundException, InvalidMealIdException, InvalidMealCountException, Exception;

	void removeItem(long mealId) throws CartIsEmptyException, MealNotFoundException, InvalidMealIdException, Exception;

	String buy(double cash) throws CartIsEmptyException, InavalidPayException, Exception;

	void cancleOrder() throws CartIsEmptyException, Exception;

}
