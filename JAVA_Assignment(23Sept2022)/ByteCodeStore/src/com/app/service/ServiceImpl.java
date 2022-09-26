package com.app.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.app.error.IdNotFoundException;
import com.app.error.InvalidChoiceException;
import com.app.error.InvalidCountException;
import com.app.model.Customer;
import com.app.model.Item;
import com.app.model.Movie;
import com.app.model.Product;

public class ServiceImpl implements Service {

	private List<Customer> customers = new ArrayList<Customer>();
	private List<Product> products = new ArrayList<Product>();
	private List<Movie> movies = new ArrayList<Movie>();
	private long currentYear;

	// Setters
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public void setCurrentYear(long currentYear) {
		this.currentYear = currentYear;
	}

	// Getters
	public List<Customer> getCustomers() {
		return customers;
	}

	public List<Product> getProducts() {
		return products;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public long getCurrentYear() {
		return currentYear;
	}

	// Check whether customer exists or not.
	public Customer checkCustomerId(long customerId) throws IdNotFoundException {
		Iterator<Customer> iterator = customers.iterator();
		while (iterator.hasNext()) {
			Customer temporaryCustomer = (Customer) iterator.next();
			if (temporaryCustomer.getId() == customerId)
				return temporaryCustomer;
		}
		throw new IdNotFoundException("Customer", customerId);
	}

	// Check whether product exists or not.
	public Product checkProductId(long productId) throws IdNotFoundException {
		Iterator<Product> iterator = products.iterator();
		while (iterator.hasNext()) {
			Product temporaryProduct = (Product) iterator.next();
			if (temporaryProduct.getId() == productId)
				return temporaryProduct;
		}
		throw new IdNotFoundException("Product", productId);
	}

	// Check whether movie exists or not.
	public Movie checkMovieId(long movieId) throws IdNotFoundException {
		Iterator<Movie> iterator = movies.iterator();
		while (iterator.hasNext()) {
			Movie temporaryMovie = (Movie) iterator.next();
			if (temporaryMovie.getId() == movieId)
				return temporaryMovie;
		}
		throw new IdNotFoundException("Movie", movieId);
	}

	// Find product from customer's cart.
	public Item findProductFromCustomerCart(Customer customer, long productId) {
		List<Item> cart = customer.getCart();

		if (cart.isEmpty())
			return null;

		Iterator<Item> iterator = cart.iterator();
		while (iterator.hasNext()) {
			Item temporaryItem = (Item) iterator.next();
			if (temporaryItem.getProduct().getId() == productId)
				return temporaryItem;
		}

		return null;
	}

	// Search for available products.
	@Override
	public void searchProduct(String productType) throws InvalidChoiceException {
		int productTypeLength = productType.length();
		String text = "\n";
		for (int i = 0; i <= productTypeLength; i++)
			text += "-";
		text += "\n";
		System.out.println(text + productType.toUpperCase() + ":" + text);
		Iterator<Product> iterator = products.iterator();
		while (iterator.hasNext()) {
			Product temporaryProduct = (Product) iterator.next();
			if (temporaryProduct.getType().toLowerCase().equals(productType)) {
				System.out.println("Product Id: " + temporaryProduct.getId());
				System.out.println("Product Name: ByteCode " + temporaryProduct.getName());
				System.out.println("Product Specifications: " + temporaryProduct.getSpecs());
				System.out.println("Product Cost INR: " + temporaryProduct.getPrice());
				System.out.println("Quantity left: " + temporaryProduct.getCount() + "\n\n");
			}
		}
	}

	// Add product to customer's cart.
	@Override
	public void addProductToCart(long customerId, long productId, int count)
			throws IdNotFoundException, InvalidCountException {

		Customer customer = checkCustomerId(customerId);
		Product product = checkProductId(productId);
		Item customerItem = findProductFromCustomerCart(customer, productId);

		int productCount = product.getCount();
		if (customerItem != null)
			productCount += customerItem.getCount();

		if (count > productCount) {
			System.out.println(
					"\nOnly " + product.getCount() + " products with id ='" + productId + "' are available! :(");
			return;
		}

		product.setCount(productCount - count);
		if (customerItem != null)
			customer.getCart().remove(customerItem);
		customer.getCart().add(new Item(product, count));

		System.out.println("\nProduct successfully added to your cart. :)");
	}

	// Remove product from customer's cart.
	@Override
	public void removeProductFromCart(long customerId, long productId) throws IdNotFoundException {

		Customer customer = checkCustomerId(customerId);
		Product product = checkProductId(productId);
		Item customerItem = findProductFromCustomerCart(customer, productId);

		if (customerItem == null)
			throw new IdNotFoundException("Product with id='" + product.getId() + "' was not found from your cart! :(");

		product.setCount(product.getCount() + customerItem.getCount());
		customer.getCart().remove(customerItem);

		System.out.println("\nProduct successfully removed from your cart. :)");
	}

	// Display customer's cart.
	@Override
	public String[] displayCart(long customerId) throws IdNotFoundException {
		Customer customer = checkCustomerId(customerId);
		List<Item> items = customer.getCart();

		if (items.isEmpty()) {
			System.out.println("\nCustomer with id='" + customerId + "' has empty cart! :(");
			return null;
		}

		String textString = "";
		long totalCost = 0;

		textString += "\nCart Details for Customer ID: " + customerId;
		textString += "\n---------------------------------------------------";
		textString += "\n" + String.format("%s %-30s %-30s %-30s %-30s", "Product Id", "Product Name", "Product Type",
				"Product Cost", "Number of purchases");
		textString += "\n" + String.format("%s %-30s %-30s %-30s %-30s", "----------", "------------", "------------",
				"------------", "------------------");
		for (Item item : items) {
			Product product = item.getProduct();
			textString += "\n" + String.format("%-10s %-30s %-30s %-30s %-30s", product.getId(), product.getName(),
					product.getType(), product.getPrice(), item.getCount());
			totalCost += product.getPrice() * item.getCount();
		}
		// Delivery charge for customers without prime subscription.
		if ((customer.getPrime() == null)
				|| ((customer.getPrime() != null) && (customer.getPrime().getSubscriptionYear() != currentYear)))
			totalCost += (totalCost / 2);

		textString += "\n\nTotal cost INR = " + totalCost;
		System.out.println(textString);
		String bill[] = new String[2];
		bill[0] = textString;
		bill[1] = Long.toString(totalCost);

		return bill;
	}

	// Buy products added to cart.
	@Override
	public void buy(long customerId, long cash, String bill[]) throws IdNotFoundException {

		Customer customer = checkCustomerId(customerId);

		if (customer.getCart().isEmpty()) {
			System.out.println("\nCustomer with id='" + customerId + "' has empty cart! :(");
			return;
		}

		if (cash != Long.parseLong(bill[1])) {
			System.out.println("\nPayement failed due to incorrect amount of cash! :(");
			return;
		}

		customer.getPurchaseHistoryList().add(bill[0] + "\nByteCode electronics pvt. ltd.\n");
		customer.setCart(new ArrayList<Item>());
		System.out.println("\nPurchase successful. :)");
	}

	// Show customer's purchase history.
	public void showPurchaseHistory(long customerId) throws IdNotFoundException {
		Customer customer = checkCustomerId(customerId);

		List<String> customerPurchaseHistoryList = customer.getPurchaseHistoryList();
		if (customerPurchaseHistoryList.isEmpty()) {
			System.out.println("\nNo purchases done yet by customer with id='" + customerId + "'! :(");
			return;
		}

		System.out.println("\n\nPurchase History for Customer-" + customerId + ":".toUpperCase());
		for (String text : customerPurchaseHistoryList)
			System.out.println(text);
	}

	// Watch a movie.
	@Override
	public void watchMovie(long customerId, long movieId) throws IdNotFoundException {
		Customer customer = checkCustomerId(customerId);
		Movie movie = checkMovieId(movieId);

		if ((customer.getPrime() == null)
				|| ((customer.getPrime() != null) && (customer.getPrime().getSubscriptionYear() != currentYear))) {
			System.out.println(
					"\nCustomer with id='" + customerId + "' has no prime subscription or prime has expired! :(");
			return;
		}

		System.out.println("\nCustomer with id='" + customerId + "' is watching " + movie.getMovieName() + "\n");
	}

}
