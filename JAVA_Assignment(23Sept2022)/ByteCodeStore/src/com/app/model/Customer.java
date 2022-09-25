package com.app.model;

import java.util.List;

public class Customer {

	private long id;
	private String name;
	private String email;
	private List<Item> cart;
	private Prime prime;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Item> getCart() {
		return cart;
	}

	public void setICart(List<Item> cart) {
		this.cart = cart;
	}

	public Prime getPrime() {
		return prime;
	}

	public void setPrime(Prime prime) {
		this.prime = prime;
	}

	// toString()
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", cart=" + cart + ", prime=" + prime
				+ "]";
	}

	// Parameterized constructor
	public Customer(long id, String name, String email, List<Item> cart, Prime prime) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.cart = cart;
		this.prime = prime;
	}

	// Default constructor
	public Customer() {
		super();
	}

}
