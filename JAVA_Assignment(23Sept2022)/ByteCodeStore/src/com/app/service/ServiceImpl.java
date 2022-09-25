package com.app.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.app.error.IdNotFoundException;
import com.app.error.InvalidChoiceException;
import com.app.error.InvalidCountException;
import com.app.model.Customer;
import com.app.model.Item;
import com.app.model.Product;

public class ServiceImpl implements Service {

	private List<Product> products = new ArrayList<Product>();
	private List<Customer> customers = new ArrayList<Customer>();

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
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
			System.out
					.println("\nOnly " + product.getCount() + " products with id ='" + productId + "' are available! :(");
			return;
		}

		product.setCount(productCount - count);
		if (customerItem != null)
			customer.getCart().remove(customerItem);
		customer.getCart().add(new Item(product, count));
		
		System.out.println("\nProduct successfully added to your cart. :)");
	}

	@Override
	public void removeProductFromCart(long customerId, long productId) throws IdNotFoundException {

		Customer customer = checkCustomerId(customerId);
		Product product = checkProductId(productId);
		Item customerItem = findProductFromCustomerCart(customer, productId);

        if(customerItem == null)
        	throw new IdNotFoundException("Product with id='" + product.getId() + "' was not found from your cart! :(");
        
        product.setCount(product.getCount() + customerItem.getCount());
        customer.getCart().remove(customerItem);
        
        System.out.println("\nProduct successfully removed from your cart. :)");
	}

	@Override
	public void displayCart(long customerId) throws IdNotFoundException
	{
		Customer customer = checkCustomerId(customerId);
		List<Item> items = customer.getCart();
		
		if(items.isEmpty())
		{
			System.out.println("\nCustomer with id='" + customerId + "' has empty cart! :(");
			return;
		}
		
		long totalCost = 0;
		System.out.println("\nCart Details for Customer ID: " + customerId);
		System.out.println("---------------------------------------------------");
		System.out.println(String.format("%s %-30s %-30s %-30s %-30s", "Product Id", "Product Name", "Product Type", "Product Cost", "Number of purchase"));
		System.out.println(String.format("%s %-30s %-30s %-30s %-30s", "----------", "------------", "------------", "------------", "------------------"));
        
		for(Item item : items)
		{
			Product product = item.getProduct();
			System.out.println(String.format("%-10s %-30s %-30s %-30s %-30s", product.getId(), product.getName(), product.getType(), product.getPrice(), item.getCount()));
		    totalCost += product.getPrice() * item.getCount();
		}
		System.out.println("\nTotal cost INR = " + totalCost);	
	}
	
	@Override
	public void buy(long customerId, boolean delivery) throws IdNotFoundException {

		
	}

	@Override
	public void watchMovie(long customerId, long movieId) throws IdNotFoundException {

		
	}

}
