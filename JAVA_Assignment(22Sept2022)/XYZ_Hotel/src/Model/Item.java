package Model;

public class Item {

	private Meal meal;
	private int count;

	// Getters and Setters
	public Meal getMeal() {
		return meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	// Constructors
	public Item(Meal meal, int count) {
		this.meal = meal;
		this.count = count;
	}

	// toString()
	public Item() {
	}

	@Override
	public String toString() {
		return "Item [meal=" + meal + ", count=" + count + "]";
	}

}
