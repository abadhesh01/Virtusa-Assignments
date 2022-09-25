package com.app.model;

public class Prime {

	private long subscriptionYear;

	// Getters and Setters
	public long getSubscriptionYear() {
		return subscriptionYear;
	}

	public void setSubscriptionYear(long subscriptionYear) {
		this.subscriptionYear = subscriptionYear;
	}

	// toString()
	@Override
	public String toString() {
		return "Prime [subscriptionYear=" + subscriptionYear + "]";
	}

	// Parameterized constructor
	public Prime(long subscriptionYear) {
		super();
		this.subscriptionYear = subscriptionYear;
	}

	// Default constructor
	public Prime() {
		super();
	}

}
