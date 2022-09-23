package Model;

import java.util.Objects;

public class Meal {

	private long id;
	private String name;
	private double price;

	// Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	// Constructors
	public Meal(long id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Meal() {
	}

	// hashCode()
	@Override
	public int hashCode() {
		return Objects.hash(id, name, price);
	}

	// equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Meal other = (Meal) obj;
		return id == other.id && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price);
	}

	// toString()
	@Override
	public String toString() {
		return "Meal [id=" + id + ", name=" + name + ", price=" + price + "]";
	}

}
