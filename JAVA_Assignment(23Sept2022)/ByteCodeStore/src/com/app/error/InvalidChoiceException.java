package com.app.error;

public class InvalidChoiceException extends Exception {

	private static final long serialVersionUID = -846335273993188005L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public InvalidChoiceException(int choice) {
		super("Error: The choice '" + choice + "' is not available! :(");
	}

	public InvalidChoiceException() {
		super("Error: Choice cnnnot be zero(0) or negative! :(");
	}

	public InvalidChoiceException(String message) {
		super(message);
	}

}
