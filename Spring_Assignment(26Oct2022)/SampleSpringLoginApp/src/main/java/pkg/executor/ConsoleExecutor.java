package pkg.executor;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pkg.base.model.Admin;
import pkg.base.model.User;
import pkg.dao.DAO;
import pkg.dao.DAOImpl;

public class ConsoleExecutor {

	private static final Logger LOGGER = Logger.getLogger(ConsoleExecutor.class);

	public static void main(String[] args) {

		// Setting up logger.
		BasicConfigurator.configure();
		LOGGER.setLevel(Level.ALL);

		// Setting up scanner.
		Scanner scanner = new Scanner(System.in);

		// Configuring application context.
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("dao.cfg.xml");
		DAO dao = applicationContext.getBean("daoImpl", DAOImpl.class);

		boolean exit = false;
		String options = "[1]Register a new user.\n[2]Register a new admin."
				+ "\n[3]Show all user(s).\n[4]Show all admin(s).\n[5]Exit." + "\nEnter your choice: ";
		while (!exit) {
			LOGGER.info("\n\n\n\n" + options);
			try {
				int choice = Integer.parseInt(scanner.nextLine());
				switch (choice) {
				case 1:
					// Creating a new user account.
					LOGGER.info("\nEnter new username: ");
					String username = scanner.nextLine();
					LOGGER.info("\nEnter new Password: ");
					String password = scanner.nextLine();
					String result = dao.createNewUserAccount(new User(username, password));
					if (result == null) {
						throw new Exception("Username already exists.");
					}
					LOGGER.info("\n\n\n\nUser account created successfully. :)\n\n\n");
					break;
				case 2:
					// Creating a new admin account.
					LOGGER.info("\nEnter new username: ");
					username = scanner.nextLine();
					LOGGER.info("\nEnter new Password: ");
					password = scanner.nextLine();
					result = dao.createNewAdminAccount(new Admin(username, password));
					if (result == null) {
						throw new Exception("Username already exists.");
					}
					LOGGER.info("\n\n\n\nAdmin account created successfully. :)\n\n\n");
					break;
				case 3:
					// Displaying all user accounts.
					List<User> users = dao.getAllUsers();
					result = "\n\n\n\nAll Users:\n----------\n";
					for (User user : users) {
						result += user + "\n";
					}
					result += "\n\n";
					LOGGER.info(result);
					break;
				case 4:
					// Displaying all admin accounts.
					List<Admin> admins = dao.getAllAdmins();
					result = "\n\n\n\nAll Admins:\n-----------\n";
					for (Admin admin : admins) {
						result += admin + "\n";
					}
					result += "\n\n";
					LOGGER.info(result);
					break;
				case 5:
					// Exiting.
					LOGGER.info("\n\n\n\nBye! :)\n\n\n");
					exit = true;
					break;
				default:
					throw new Exception("Invalid choice! :(");
				}
			} catch (Exception exception) {
				LOGGER.info("\n\n\n\n" + exception.getMessage() + "\n\n\n");
			}

		}

		// Closing application context.
		ClassPathXmlApplicationContext classPathXmlApplicationContext = (ClassPathXmlApplicationContext) applicationContext;
		classPathXmlApplicationContext.close();

		// Closing scanner.
		scanner.close();

		// Message for successful execution.
		LOGGER.info("\n\n\n\nExecution successful.\n\n\n");
	}

}
