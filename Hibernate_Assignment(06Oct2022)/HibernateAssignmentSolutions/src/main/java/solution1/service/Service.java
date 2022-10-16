package solution1.service;

import solution1.model.Address;
import solution1.model.Customer;

public interface Service {

	// Setting up connection to DB.
	void setConnectionToDB();

	// Closing all connections to DB.
	void closeAllConnections();

	// Adding a new customer.
	void addCustomer(Customer customer);

	// Updating a customer.
	void updateCustomer(Customer customer);

	// Deleting a customer.
	void deleteCustomer(Customer customer);

	// Getting a customer by id.
	Customer getCustomerById(long id);

	// Getting a address by id.
	Address getAddressById(long id);
}
