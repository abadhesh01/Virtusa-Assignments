package solution2.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import solution2.model.Company;
import solution2.model.Employee;

public class Executor {

	private static final Logger LOGGER = Logger.getLogger(Executor.class);
	private static final Scanner SCANNER = new Scanner(System.in);

	// Add dummy companies with dummy employees to database.
	public static void addDummyCompaniesWithDummyEmployees(int firstCompanyId, int totalCompanies,
			int totalEmployeesOfEachCompany, Session session) {
		if (firstCompanyId <= 0 || totalCompanies <= firstCompanyId || totalEmployeesOfEachCompany <= 0) {
			return;
		}
		for (; firstCompanyId <= totalCompanies; firstCompanyId++) {
			int employeeCount = totalEmployeesOfEachCompany;
			Company company = new Company(firstCompanyId, "COMPANY_" + firstCompanyId);
			List<Employee> employees = new ArrayList<Employee>();
			while (employeeCount > 0) {
				Employee employee = new Employee((firstCompanyId * 1000) + employeeCount,
						"Employee_" + (firstCompanyId * 1000) + employeeCount, "N/A",
						"emp_id_" + (firstCompanyId * 1000) + employeeCount + "@email.com");
				employee.setCompany(company);
				employees.add(employee);
				employeeCount--;
			}
			company.setEmployees(employees);
			session.save(company);
			session.beginTransaction().commit();
		}
	}

	// Update phone number of random employees of random companies.
	@SuppressWarnings("unchecked")
	public static void updatePhoneNumberOfRandomEmployeesOfRandomCompanies(int numberOfUpdates, Session session) {
		Criteria criteria = session.createCriteria(Company.class);
		List<Company> companies = criteria.list();
		if (companies.isEmpty()) {
			return;
		}
		while (numberOfUpdates > 0) {
			int random = ThreadLocalRandom.current().nextInt(0, companies.size());
			Company company = companies.get(random);
			List<Employee> employees = company.getEmployees();
			if (employees.isEmpty()) {
				numberOfUpdates--;
				continue;
			}
			random = ThreadLocalRandom.current().nextInt(0, employees.size());
			employees.get(random).setPhone(solution1.executor.Executor.generateMobileNumber());
			session.update(company);
			session.beginTransaction().commit();
			numberOfUpdates--;
		}
	}

	// Delete random companies.
	public static void deleteRandomCompanies(int firstCompanyId, int lastCompanyId, int numberOfDeletion,
			Session session) {
		while (numberOfDeletion > 0) {
			long random = ThreadLocalRandom.current().nextLong(firstCompanyId, lastCompanyId + 1);
			Company company = (Company) session.get(Company.class, random);
			if (company == null) {
				numberOfDeletion--;
				continue;
			}
			session.delete(company);
			session.beginTransaction().commit();
			numberOfDeletion--;
		}
	}

	// Show employees of random company.
	public static void showEmployeesOfRandomCompanies(int firstCompanyId, int lastCompanyId, Session session) {
		if (firstCompanyId <= 0 || lastCompanyId <= firstCompanyId) {
			return;
		}
		long random = ThreadLocalRandom.current().nextInt(firstCompanyId, lastCompanyId + 1);
		Company company = (Company) session.get(Company.class, random);
		if (company == null) {
			return;
		}
		String text = "\nRandom Company With Employees:\n------------------------------\n" + company;
		List<Employee> list = company.getEmployees();
		if (list.isEmpty()) {
			return;
		}
		for (Employee employee : list) {
			text += "\n|_____>" + employee;
		}
		LOGGER.info(text + "\n\n\n\n");
	}

	// Show company of random employee.
	@SuppressWarnings("unchecked")
	public static void showCompanyOfrandomEmployee(Session session) {
		Criteria criteria = session.createCriteria(Employee.class);
		List<Employee> employees = criteria.list();
		if (employees.isEmpty()) {
			return;
		}
		int random = ThreadLocalRandom.current().nextInt(0, employees.size());
		Employee employee = employees.get(random);
		String text = "\n\n\n\nRandom Employee With Company:\n------------------------------\n" + employee + "\n|_____>"
				+ employee.getCompany();
		LOGGER.info(text + "\n\n\n\n");
	}

	public static void main(String[] args) {

		// Configuring Log4j for hibernate.
		BasicConfigurator.configure();
		LOGGER.setLevel(Level.ALL);

		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		@SuppressWarnings("deprecation")
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();

		// Adding sample companies with sample employees to the database.
		LOGGER.info("\n\n\n\nPress \"Enter\" key to add dummy companies with dummy employees to database.\n\n");
		SCANNER.nextLine();
		addDummyCompaniesWithDummyEmployees(1, 10, 5, session);

		LOGGER.info("\n\n\n\nPress \"Enter\" key to show employees of a random company.\n\n");
		SCANNER.nextLine();
		showEmployeesOfRandomCompanies(1, 10, session);

		LOGGER.info("\n\n\n\nPress \"Enter\" key to show company of a random employee.\n\n");
		SCANNER.nextLine();
		showCompanyOfrandomEmployee(session);

		// Updating phone number of random employees of random companies.
		LOGGER.info(
				"\n\n\n\nPress \"Enter\" key to update phone number of random employees from random companies.\n\n");
		SCANNER.nextLine();
		updatePhoneNumberOfRandomEmployeesOfRandomCompanies(40, session);

		LOGGER.info("\n\n\n\nPress \"Enter\" key to show employees of a random company.\n\n");
		SCANNER.nextLine();
		showEmployeesOfRandomCompanies(1, 10, session);

		LOGGER.info("\n\n\n\nPress \"Enter\" key to show company of a random employee.\n\n");
		SCANNER.nextLine();
		showCompanyOfrandomEmployee(session);

		// Updating phone number of random employees of random companies.
		LOGGER.info("\n\n\n\nPress \"Enter\" key to delete random companies.\n\n");
		SCANNER.nextLine();
		deleteRandomCompanies(1, 50, 40, session);

		LOGGER.info("\n\n\n\nPress \"Enter\" key to show employees of a random company.\n\n");
		SCANNER.nextLine();
		showEmployeesOfRandomCompanies(1, 10, session);

		LOGGER.info("\n\n\n\nPress \"Enter\" key to show company of a random employee.\n\n");
		SCANNER.nextLine();
		showCompanyOfrandomEmployee(session);

		// Exiting
		LOGGER.info("\n\n\n\nPress \"Enter\" key to exit.\n\n");
		SCANNER.nextLine();

		// Closing all connections.
		session.close();
		sessionFactory.close();
		configuration = null;

		// Closing scanner.
		SCANNER.close();

		// Message for successful execution.
		LOGGER.info("\n\n\n\nExecution successful.");
	}
}
