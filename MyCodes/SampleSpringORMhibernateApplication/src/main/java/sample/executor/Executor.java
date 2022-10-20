package sample.executor;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sample.dao.ClientDao;
import sample.model.Client;

public class Executor {

	private static final Scanner SCANNER = new Scanner(System.in);
	private static final Logger LOGGER = Logger.getLogger(Executor.class);

	// Generates mobile phone number.
	public String generateMobileNumber() {
		String mobileNumber = "";
		for (int count = 0; count <= 10; count++) {
			mobileNumber += ThreadLocalRandom.current().nextLong(0, 10);
		}
		return mobileNumber;
	}

	// Returns list of all clients in string format.
	public String extractAllClientsToStringFormat(ClientDao dao) {
		List<Client> clients = dao.getAllClients();
		if (clients.isEmpty()) {
			return "No clients found!";
		}
		String text = "All clients:\n------------\n";
		for (Client client : clients) {
			text += client + "\n";
		}
		return text;
	}

	// Adds 10 sample clients to database.
	public void addSampleClientsToDB(ClientDao dao) {
		for (int id = 1; id <= 10; id++) {
			dao.addNewClient(new Client(id, "CLI(" + id + ")", "cli[" + id + "]@email.com", "N/A"));
		}
	}

	// Updates random clients' mobile phone number.
	public void updateRandomClientsPhoneNumber(ClientDao dao, int numberOfUpdation) {
		while (numberOfUpdation > 0) {
			long id = ThreadLocalRandom.current().nextLong(1, 11);
			Client client = dao.getClient(id);
			if (client == null) {
				return;
			}
			client.setPhone(generateMobileNumber());
			dao.updateClient(client);
			numberOfUpdation--;
		}
	}

	// Deletes random clients.
	public void deleteRandomClients(ClientDao dao, int numberOfDeletion) {
		while (numberOfDeletion > 0) {
			long id = ThreadLocalRandom.current().nextLong(1, 11);
			Client client = dao.getClient(id);
			if (client == null) {
				return;
			}
			client.setName("LOREM{" + id + "}");
			dao.deleteClient(client);
			numberOfDeletion--;
		}
	}

	public static void main(String[] args) {

		// Configuring logger.
		BasicConfigurator.configure();

		// Creating Executor class object.
		Executor executor = new Executor();

		// Creating client DAO using ApplicationContext.
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config.xml");
		ClientDao clientDao = applicationContext.getBean("client_dao", ClientDao.class);

		// Adding 10 sample clients to database.
		LOGGER.info("\n\n\n\nPress \"Enter\" key to add 10 sample clients.\n");
		SCANNER.nextLine();
		executor.addSampleClientsToDB(clientDao);

		LOGGER.info("\n\n\n\nPress \"Enter\" key to show all clients.\n");
		SCANNER.nextLine();
		LOGGER.info("\n\n\n\n" + executor.extractAllClientsToStringFormat(clientDao) + "\n");

		// Updating random clients' mobile number.
		LOGGER.info("\n\n\n\nPress \"Enter\" key to update some random clients' mobile number.\n");
		SCANNER.nextLine();
		executor.updateRandomClientsPhoneNumber(clientDao, 7);

		LOGGER.info("\n\n\n\nPress \"Enter\" key to show all clients.\n");
		SCANNER.nextLine();
		LOGGER.info("\n\n\n\n" + executor.extractAllClientsToStringFormat(clientDao) + "\n");

		// Deleting random clients.
		LOGGER.info("\n\n\n\nPress \"Enter\" key to delete some random clients.\n");
		SCANNER.nextLine();
		executor.deleteRandomClients(clientDao, 5);

		LOGGER.info("\n\n\n\nPress \"Enter\" key to show all clients.\n");
		SCANNER.nextLine();
		LOGGER.info("\n\n\n\n" + executor.extractAllClientsToStringFormat(clientDao) + "\n");

		// Exiting.
		LOGGER.info("\n\n\n\nPress \"Enter\" key to exit.\n");
		SCANNER.nextLine();

		// Closing scanner and ApplicationContext.
		SCANNER.close();
		ClassPathXmlApplicationContext context = (ClassPathXmlApplicationContext) applicationContext;
		context.close();

		// Message implying successful execution.
		LOGGER.info("\n\n\n\nExection successful.");
	}
}
