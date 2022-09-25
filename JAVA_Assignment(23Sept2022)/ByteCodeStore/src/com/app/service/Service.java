package com.app.service;

import java.util.List;

import com.app.error.IdNotFoundException;
import com.app.error.InvalidChoiceException;
import com.app.error.InvalidCountException;
import com.app.model.Customer;
import com.app.model.Product;

public interface Service {
	
	void setProducts(List<Product> products);
	
	void setCustomers(List<Customer> customers);

	void searchProduct(String productType) throws InvalidChoiceException;
	
	void addProductToCart(long customerId, long productId, int count) throws IdNotFoundException, InvalidCountException;
	
	void removeProductFromCart(long customerId, long productId) throws IdNotFoundException;
	
	void displayCart(long customerId) throws IdNotFoundException;
	
	void buy(long customerId, boolean delivery) throws IdNotFoundException;
	
    void watchMovie(long customerId, long movieId) throws IdNotFoundException;
	
}
