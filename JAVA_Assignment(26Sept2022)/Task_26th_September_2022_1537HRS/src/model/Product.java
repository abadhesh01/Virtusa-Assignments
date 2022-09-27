package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Product {

	private int productId;
	private String productName;
	private Date date;
	private double price;
	private String company;

	// Getters and Setters
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	// toString()
	@Override
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return "Product [productId=" + productId + ", productName=" + productName + ", date="
				+ dateFormat.format(date) + ", price="
				+ price + ", company=" + company + "]";
	}

	// Parameterized constructor
	public Product(int productId, String productName, Date date2, double price, String company) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.date = date2;
		this.price = price;
		this.company = company;
	}

	// Default Constructor
	public Product() {
		super();
	}

}
