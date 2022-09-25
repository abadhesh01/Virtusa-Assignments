package com.app.error;

public class PremiumNotSubscribedException extends Exception {

	private static final long serialVersionUID = 5907530300484744385L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PremiumNotSubscribedException(long id) {
		super("Customer with id='" + id + "' has not subscribed premium! :(");
	}

	public PremiumNotSubscribedException() {
		super("Customer has not subscribed premium! :(");
	}

	public PremiumNotSubscribedException(String message) {
		super(message);
	}

}
