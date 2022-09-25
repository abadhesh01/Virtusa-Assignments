package Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Error.CartIsEmptyException;
import Error.InavalidPayException;
import Error.InvalidMealCountException;
import Error.InvalidMealIdException;
import Error.MealNotFoundException;
import Error.NoMealsAvailableException;
import Model.Customer;
import Model.Item;
import Model.Meal;

public class ServiceImpl implements Service {

	private long receiptNumber = 1;
	private Customer customer = new Customer();
	private List<Meal> meals = new ArrayList<Meal>();

	public void setReceiptNumber(long receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}

	public void checkCart() throws CartIsEmptyException {
		if (customer.getCart().isEmpty())
			throw new CartIsEmptyException();
	}

	public Meal checkMeal(long mealId) throws InvalidMealIdException, MealNotFoundException {
		if (mealId <= 0)
			throw new InvalidMealIdException();

		Meal meal = null;
		Iterator<Meal> iterator = meals.iterator();
		while (iterator.hasNext()) {
			Meal temporaryMeal = (Meal) iterator.next();
			if (temporaryMeal.getId() == mealId) {
				meal = temporaryMeal;
				break;
			}
		}

		if (meal == null)
			throw new MealNotFoundException(mealId);

		return meal;
	}

	@Override
	public void viewMenu() throws NoMealsAvailableException, Exception {

		if (meals.isEmpty())
			throw new NoMealsAvailableException();

		Iterator<Meal> iterator = meals.iterator();

		System.out.println("-----\nMenu:\n-----");
		System.out.println(String.format("%-10s  %-35s  %s", "Meal Id", "Meal Name", "Price INR"));
		System.out.println(String.format("%-10s  %-35s  %s", "-------", "---------", "---------"));
		while (iterator.hasNext()) {
			Meal meal = (Meal) iterator.next();
			System.out.println(String.format("%-10s  %-35s  %s", meal.getId(), meal.getName(), meal.getPrice()));
		}
	}

	@Override
	public void viewCart() throws CartIsEmptyException, Exception {

		checkCart();

		double totalCost = 0;

		Iterator<Item> iterator = customer.getCart().iterator();

		System.out.println("-----\nCart:\n-----");
		System.out.println(String.format("%-10s  %-35s  %-20s  %s", "Meal Id", "Meal Name", "Price INR", "Count"));
		System.out.println(String.format("%-10s  %-35s  %-20s  %s", "-------", "---------", "---------", "-----"));
		while (iterator.hasNext()) {
			Item item = (Item) iterator.next();
			Meal meal = item.getMeal();
			totalCost += meal.getPrice() * item.getCount();
			System.out.println(String.format("%-10s  %-35s  %-20s  %s", meal.getId(), meal.getName(), meal.getPrice(),
					item.getCount()));
		}

		System.out.println("\nTotal cost INR : " + totalCost);
	}

	@Override
	public void addItem(long mealId, int mealCount)
			throws MealNotFoundException, InvalidMealIdException, InvalidMealCountException, Exception {

		if (mealCount <= 0)
			throw new InvalidMealCountException();

		Meal meal = checkMeal(mealId);

		Iterator<Item> iterator = customer.getCart().iterator();
		while (iterator.hasNext()) {
			Item temporaryItem = (Item) iterator.next();
			if (temporaryItem.getMeal().equals(meal)) {
				temporaryItem.setCount(mealCount);
				System.out.println("Meal added to cart updated successfully.");
				return;
			}
		}

		customer.getCart().add(new Item(meal, mealCount));
		System.out.println("Meal added to cart successfully.");
	}

	@Override
	public void removeItem(long mealId)
			throws CartIsEmptyException, MealNotFoundException, InvalidMealIdException, Exception {

		checkCart();

		Meal meal = checkMeal(mealId);

		Iterator<Item> iterator = customer.getCart().iterator();

		Item item = null;
		while (iterator.hasNext()) {
			Item tempItem = (Item) iterator.next();
			if (tempItem.getMeal().equals(meal)) {
				item = tempItem;
				break;
			}
		}

		if (item == null)
			throw new MealNotFoundException("Error: Meal with id='" + mealId + "' was not found from your cart! :(");

		customer.getCart().remove(item);
		System.out.println("Meal removed from cart successfully.");
	}

	@Override
	public String buy(double cash) throws CartIsEmptyException, InavalidPayException, Exception {

		checkCart();

		if (cash <= 0)
			throw new InavalidPayException();

		double bill = 0;

		List<Item> items = customer.getCart();
		String textString = "[Bill]\nReceipt Number : CUSTOMER-" + (receiptNumber) + "\n\n";
		textString += String.format("%-10s  %-35s  %-20s  %s", "Meal Id", "Meal Name", "Price INR", "Count") + "\n";
		textString += String.format("%-10s  %-35s  %-20s  %s", "-------", "---------", "---------", "-----") + "\n";
		for (Item temporaryItem : items) {
			Meal meal = temporaryItem.getMeal();
			bill += temporaryItem.getMeal().getPrice() * temporaryItem.getCount();
			textString += String.format("%-10s  %-35s  %-20s  %s", meal.getId(), meal.getName(), meal.getPrice(),
					temporaryItem.getCount()) + "\n";
		}

		if (cash != bill)
			throw new InavalidPayException("Yor bill is INR " + bill);

		textString += "\nTotal Cost INR : " + bill;
		customer = new Customer();

		receiptNumber++;
		return textString;
	}

	@Override
	public void cancleOrder() throws CartIsEmptyException, Exception {
		checkCart();
		customer = new Customer();
		System.out.println("Order cancelled successfully.");
	}

}
