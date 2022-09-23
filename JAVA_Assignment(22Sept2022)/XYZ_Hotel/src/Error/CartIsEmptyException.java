package Error;

@SuppressWarnings("serial")
public class CartIsEmptyException extends Exception {

	public CartIsEmptyException() {
		super("Error: Cart is empty! :(");
	}

	public CartIsEmptyException(String message) {
		super(message);
	}

}
