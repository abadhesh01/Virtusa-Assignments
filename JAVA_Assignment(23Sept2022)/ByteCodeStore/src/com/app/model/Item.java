package com.app.model;

public class Item {

	private Product product;
	private int count;

	// Getters and setters
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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
		return "Item [product=" + product + ", count=" + count + "]";
	}

	// Parameterized constructor
	public Item(Product product, int count) {
		super();
		this.product = product;
		this.count = count;
	}

	// Default constructor
	public Item() {
		super();
	}

}
