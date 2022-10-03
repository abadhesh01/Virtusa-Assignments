package model;

import java.util.Date;
import java.util.List;

public class Order {
	
	private long orderId; 
	private Date orderDate; 
	private List<Product> products; 
	private double totalPrice;
	
	// Getters and Setters
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	// toString()
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", products=" + products + ", totalPrice="
				+ totalPrice + "]";
	}
	
	// parameterized constructor
	public Order(long orderId, Date orderDate, List<Product> products, double totalPrice) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.products = products;
		this.totalPrice = totalPrice;
	}
	
	// Default constructor
	public Order() {
		super();
	}
	
}
