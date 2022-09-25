package com.app.model;

public class Product {

	private long id;
	private String type;
	private String name;
	private String specs;
	private long price;
	private int count;

	// Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	// toString()
	@Override
	public String toString() {
		return "Product [id=" + id + ", type=" + type + ", name=" + name + ", specs=" + specs + ", price=" + price
				+ ", count=" + count + "]";
	}

	// Parameterized constructor
	public Product(long id, String type, String name, String specs, long price, int count) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.specs = specs;
		this.price = price;
		this.count = count;
	}

	// Default constructor
	public Product() {
		super();
	}

}
