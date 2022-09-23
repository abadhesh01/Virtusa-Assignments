package Model;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private String receiptNumber;
	private List<Item> cart = new ArrayList<Item>();

	// Getters and Setters
	public List<Item> getCart() {
		return cart;
	}

	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public void setCart(List<Item> cart) {
		this.cart = cart;
	}

	// Constructors
	public Customer(String receiptNumber, List<Item> cart) {
		super();
		this.receiptNumber = receiptNumber;
		this.cart = cart;
	}

	public Customer() {
	}

	// toString()
	@Override
	public String toString() {
		return "Customer [receiptNumber=" + receiptNumber + ", cart=" + cart + "]";
	}

}
