package com.app.error;

public class IdNotFoundException extends Exception {

	private static final long serialVersionUID = -2251101664433462521L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public IdNotFoundException(String classType, long id) {
		super(classType + " with id='" + id + "' was not found! :(");
	}

	public IdNotFoundException() {
		super("Error: Customer/Product/Movie with the given id was not found! :(");
	}

	public IdNotFoundException(String message) {
		super(message);
	}

}
