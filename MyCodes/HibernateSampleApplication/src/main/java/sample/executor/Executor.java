package sample.executor;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import sample.entity.ContactInfo;
import sample.entity.Engineer;
import sample.entity.Manager;
import sample.entity.Person;
import sample.service.Service;
import sample.service.ServiceImpl;

public class Executor {

	private static final Logger logger = Logger.getLogger(Executor.class);
	private static final Scanner scanner = new Scanner(System.in);

	// Generates engineering degree.
	public static String getEngineeringDegree() {
		int number = (int) (Math.random() * 10);
		if (number % 2 == 0) {
			return "B-TECH";
		}
		return "M-TECH";
	}

	// Generates engineering branch.
	public static String getEngineeringBranch() {
		int number = (int) (Math.random() * 10);
		if (number % 6 == 1) {
			return "CSE";
		} else if (number % 6 == 2) {
			return "CSIT";
		} else if (number % 6 == 3) {
			return "Mechanical";
		} else if (number % 6 == 4) {
			return "Electrical";
		} else if (number % 6 == 5) {
			return "ECE";
		}
		return "Civil";
	}

	// Generates manager role.
	public static String getManagerRole() {
		int number = (int) (Math.random() * 10);
		if (number % 2 == 0) {
			return "Line Manager";
		}
		return "HR";
	}

	// Generates years of experience.
	public static int generateYearsOfExperience(int limit) {
		return ThreadLocalRandom.current().nextInt(0, limit);
	}

	// Generates mobile number.
	public static String generatMobileNumber() {
		String text = "";
		for (int digitCount = 0; digitCount < 10; digitCount++) {
			text += (int) (Math.random() * 10);
		}
		return text.substring(0, 5) + " " + text.substring(5, 10);
	}

	// Shows random persons' details.
	public static void showPersonsByRandomIds(Service service, int personCount) {
		String text = "Getting Random Person(s):\n-------------------------\n";
		for (int count = 1; count <= personCount; count++) {
			int random = ThreadLocalRandom.current().nextInt(1, personCount + 1);
			Person person = service.getPerson(random);
			if (person == null) {
				continue;
			}
			text += person.toString() + "\n";
		}
		logger.info("\n\n\n" + text + "\n");
	}

	// Adding dummy persons to database.
	public static void addDummyPersonsToDatabase(Service service, int personCount) {
		for (int count = 1; count <= personCount; count++) {

			if (count % 3 == 1) {
				service.insertPerson(new Engineer(count, "Lorem_" + count + " Ipsium_" + count,
						new ContactInfo("N/A", "N/A"), getEngineeringDegree(), getEngineeringBranch()));
				continue;
			}

			if (count % 3 == 2) {
				service.insertPerson(new Manager(count, "Manager_" + count + " Management_" + count,
						new ContactInfo("N/A", "N/A"), getManagerRole(), generateYearsOfExperience(10)));
				continue;
			}

			service.insertPerson(
					new Person(count, "Engineer_" + count + " Professional_" + count, new ContactInfo("N/A", "N/A")));
		}
	}

	// Updating contact information of random persons.
	public static void updateContactInfoOfRandomPersons(Service service, int personCount) {
		for (int count = 1; count <= personCount; count++) {
			int random = ThreadLocalRandom.current().nextInt(1, personCount + 1);
			Person person = service.getPerson(random);
			if (person == null) {
				continue;
			}
			ContactInfo contactInfo = person.getContactInfo();
			contactInfo.setEmail("sample.id" + random + "@email.com");
			contactInfo.setPhone(generatMobileNumber());
			service.updatePerson(person);
		}
	}

	public static void main(String[] args) {

		BasicConfigurator.configure();

		// Setting up service.
		Service service = new ServiceImpl();

		// Setting up connection to database.
		service.setConnectionToDatabase();

		// Adding dummy persons to database.
		logger.info("\n\n\nPress Enter key to add new dummy persons to database.....\n");
		scanner.nextLine();
		addDummyPersonsToDatabase(service, 10);

		// Getting random persons from database.
		logger.info("\n\n\nPress Enter key to display random persons from database.....\n");
		scanner.nextLine();
		showPersonsByRandomIds(service, 10);

		logger.info("\n\n\nPress Enter key to uptadte random persons contact info into database.....\n");
		scanner.nextLine();
		updateContactInfoOfRandomPersons(service, 10);

		// Getting random persons from database.
		logger.info("\n\n\nPress Enter key to display random persons from database.....\n");
		scanner.nextLine();
		showPersonsByRandomIds(service, 10);

		// Closing all services and scanner.
		logger.info("\n\n\nPress Enter key to exit.....\n");
		scanner.nextLine();
		// Closing all connections.
		service.closeAllConnections();
		// Closing scanner.
		scanner.close();

		logger.info("\n\n\nExecution compleated.\n");
	}
}
