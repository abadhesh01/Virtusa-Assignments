package com.app.service;

import java.util.List;

import com.app.error.IdNotFoundException;
import com.app.error.InvalidChoiceException;
import com.app.error.InvalidCountException;
import com.app.model.Customer;
import com.app.model.Movie;
import com.app.model.Product;

public interface Service {

	void setCustomers(List<Customer> customers);

	void setProducts(List<Product> products);

	void setMovies(List<Movie> movies);

	List<Customer> getCustomers();

	List<Product> getProducts();

	List<Movie> getMovies();

	void setCurrentYear(long currentYear);

	void searchProduct(String productType) throws InvalidChoiceException;

	void addProductToCart(long customerId, long productId, int count) throws IdNotFoundException, InvalidCountException;

	void removeProductFromCart(long customerId, long productId) throws IdNotFoundException;

	String[] displayCart(long customerId) throws IdNotFoundException;

	void buy(long customerId, long cash, String[] bill) throws IdNotFoundException;

	void showPurchaseHistory(long customerId) throws IdNotFoundException;

	void watchMovie(long customerId, long movieId) throws IdNotFoundException;

}
