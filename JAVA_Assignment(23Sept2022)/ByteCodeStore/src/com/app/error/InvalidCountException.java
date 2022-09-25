package com.app.error;

public class InvalidCountException extends Exception {

	private static final long serialVersionUID = 7502046636445053585L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public InvalidCountException() {
		super("Error: Product cannot be zero(0) or negative! :(");
	}

	public InvalidCountException(String message) {
		super(message);
	}

}
