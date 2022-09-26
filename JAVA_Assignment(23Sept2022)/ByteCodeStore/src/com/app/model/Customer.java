package com.app.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private long id;
	private String name;
	private String email;
	private List<Item> cart;
	private List<String> purchaseHistoryList;
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

	public void setCart(List<Item> cart) {
		this.cart = cart;
	}

	public Prime getPrime() {
		return prime;
	}

	public void setPrime(Prime prime) {
		this.prime = prime;
	}

	public List<String> getPurchaseHistoryList() {
		return purchaseHistoryList;
	}

	public void setPurchaseHistoryList(List<String> purchaseHistoryList) {
		this.purchaseHistoryList = purchaseHistoryList;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", cart=" + cart
				+ ", purchaseHistoryList=" + purchaseHistoryList + ", prime=" + prime + "]";
	}

	// Parameterized constructor
	public Customer(long id, String name, String email, Prime prime) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.prime = prime;
		cart = new ArrayList<Item>();
		purchaseHistoryList = new ArrayList<String>();
	}

	// Default constructor
	public Customer() {
		super();
	}

}
