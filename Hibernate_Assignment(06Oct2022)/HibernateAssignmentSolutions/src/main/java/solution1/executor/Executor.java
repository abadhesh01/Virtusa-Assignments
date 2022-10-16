package solution1.executor;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import solution1.model.Address;
import solution1.model.Customer;
import solution1.service.Service;
import solution1.service.ServiceImpl;

public class Executor {

	private static final Logger LOGGER = Logger.getLogger(Executor.class);
	private static final Scanner SCANNER = new Scanner(System.in);

	// Generating mobile number.
	public static String generateMobileNumber() {
		String mobileNumber = "";
		for (int count = 1; count <= 10; count++) {
			mobileNumber += (int) (Math.random() * 10);
		}
		return mobileNumber.substring(0, 5) + " " + mobileNumber.substring(5, 10);
	}

	// Generate random string.
	public static String generateRandomString(int approxLength) {
		if (approxLength < 0) {
			return null;
		}

		String string = "";
		approxLength /= 2;
		for (int count = 0; count < approxLength; count++) {
			string += (char) ThreadLocalRandom.current().nextInt(65, 91);
			string += (char) ThreadLocalRandom.current().nextInt(97, 123);
		}

		return string;
	}

	// Showing customers by random ids.
	public static void showRandomCustomers(Service service, int customerIdMaxValue, int totalRetrival) {
		String text = "\nRandom Customers:\n-----------------\n";
		for (int counter = 1; counter <= totalRetrival; counter++) {
			int random = ThreadLocalRandom.current().nextInt(1, customerIdMaxValue + 1);
			Customer customer = service.getCustomerById(random);
			if (customer == null) {
				continue;
			}
			text += customer + "\n|_______>" + customer.getAddress() + "\n";
		}
		LOGGER.info(text + "\n\n\n");
	}

	// Showing address by random ids.
	public static void showRandomAddresses(Service service, int customerIdMaxValue, int totalRetrival) {
		String text = "\nRandom Address:\n---------------\n";
		for (int counter = 1; counter <= totalRetrival; counter++) {
			int random = ThreadLocalRandom.current().nextInt(1, customerIdMaxValue + 1);
			Address address = service.getAddressById((random * 1000) + random);
			if (address == null) {
				continue;
			}
			text += address + "\n|_______>" + address.getCustomer() + "\n";
			LOGGER.trace(address.getCustomer());
		}
		LOGGER.info(text + "\n\n\n");
	}

	// Adding dummy customers to DB.
	public static void addDummyCustomerstoDB(Service service, int firstCustomerId, int totalCustomers) {
		if (firstCustomerId <= 0 || totalCustomers <= firstCustomerId) {
			return;
		}

		for (; firstCustomerId <= totalCustomers; firstCustomerId++) {
			Customer customer = new Customer(firstCustomerId, generateMobileNumber(),
					"cust.Id_" + firstCustomerId + "@email.com");
			Address address = new Address((firstCustomerId * 1000) + firstCustomerId, "N/A", "N/A");
			customer.setAddress(address);
			address.setCustomer(customer);
			service.addCustomer(customer);
		}
	}

	// Updating random customers' address.
	public static void updateAddressOfRandomCustomers(Service service, int firstCustomerId, int lastCustomerId,
			int numberOfUpdates) {
		if (numberOfUpdates <= 0 || firstCustomerId <= 0 || lastCustomerId < firstCustomerId) {
			return;
		}
		for (int count = 0; count <= numberOfUpdates; count++) {
			int random = ThreadLocalRandom.current().nextInt(firstCustomerId, lastCustomerId + 1);
			Customer customer = service.getCustomerById(random);
			if (customer == null) {
				continue;
			}
			Address address = customer.getAddress();
			address.setCurrentAddress("CA_" + generateRandomString(20));
			address.setPermanentAddress("PA_" + generateRandomString(20));
			service.updateCustomer(customer);
		}
	}

	// Deleting random customers.
	public static void deleteRandomCustomers(Service service, int firstCustomerId, int lastCustomerId,
			int numberOfDeletion) {
		if (numberOfDeletion <= 0 || firstCustomerId <= 0 || lastCustomerId < firstCustomerId) {
			return;
		}
		for (int count = 0; count <= numberOfDeletion; count++) {
			int random = ThreadLocalRandom.current().nextInt(firstCustomerId, lastCustomerId + 1);
			Customer customer = service.getCustomerById(random);
			if (customer == null) {
				continue;
			}
			service.deleteCustomer(customer);
		}
	}

	public static void main(String[] args) {

		// Log4j basic configuration.
		BasicConfigurator.configure();
		LOGGER.setLevel(Level.INFO);

		// Setting up services.
		Service service = new ServiceImpl();

		// Setting up connections to DB.
		service.setConnectionToDB();

		// Adding dummy customers to database.
		LOGGER.info("\n\n\n\nPress \"Enter\" key to add dummy customers to database.\n\n");
		SCANNER.nextLine();
		addDummyCustomerstoDB(service, 1, 10);

		LOGGER.info("\n\n\n\nPress \"Enter\" key to show random customers.\n\n");
		SCANNER.nextLine();
		showRandomCustomers(service, 10, 10);

		LOGGER.info("\n\n\n\nPress \"Enter\" key to show random addresses.\n\n");
		SCANNER.nextLine();
		showRandomAddresses(service, 10, 10);

		// Updating address of random customers.
		LOGGER.info("\n\n\n\nPress \"Enter\" key to update address of random customers.\n\n");
		SCANNER.nextLine();
		updateAddressOfRandomCustomers(service, 1, 10, 10);

		LOGGER.info("\n\n\n\nPress \"Enter\" key to show random customers.\n\n");
		SCANNER.nextLine();
		showRandomCustomers(service, 10, 10);

		LOGGER.info("\n\n\n\nPress \"Enter\" key to show random addresses.\n\n");
		SCANNER.nextLine();
		showRandomAddresses(service, 10, 10);

		// Deleting random customers.
		LOGGER.info("\n\n\n\nPress \"Enter\" key to delete random customers.\n\n");
		SCANNER.nextLine();
		deleteRandomCustomers(service, 1, 10, 10);

		LOGGER.info("\n\n\n\nPress \"Enter\" key to show random customers.\n\n");
		SCANNER.nextLine();
		showRandomCustomers(service, 10, 10);

		LOGGER.info("\n\n\n\nPress \"Enter\" key to show random addresses.\n\n");
		SCANNER.nextLine();
		showRandomAddresses(service, 10, 10);

		// Exiting.
		LOGGER.info("\n\n\n\nPress \"Enter\" key to exit.\n\n");
		SCANNER.nextLine();

		// Closing all connections.
		service.closeAllConnections();

		// Closing scanner.
		SCANNER.close();

	}
}
