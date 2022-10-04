package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class Person {

	private long id;
	private String name;
	private String mobile;
	private String email;
	private Statement statement;
	private Logger logger = Logger.getLogger(Person.class);

	// Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	// toString()
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", mobile=" + mobile + ", email=" + email + "]\n";
	}

	// Default constructor
	public Person() {
		super();
		Appender appender = new ConsoleAppender(new SimpleLayout());
		logger.addAppender(appender);
		logger.setLevel(Level.ALL);
	}

	// Parameterized constructor
	public Person(long id, String name, String mobile, String email) {
		super();
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
	}

	// Generates random mobile number.
	public String generateRandomMobileNumber() {
		String mobileNumber = "";

		for (int count = 0; count < 10; count++) {
			mobileNumber += (int) (Math.random() * 10);
		}

		return mobileNumber;
	}

	// Grants access to database.
	public void getAccessToSampleDatabase() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampleDB", "user", "password");

		statement = connection.createStatement();
	}

	// Inserts dummy persons to database.
	public void insertDummyPersonsToDatabase() throws SQLException {
		String query = "insert into Person values\n";
		for (int id = 1; id <= 10; id++) {
			query += "(" + id + ", 'Lorem" + id + " Ipsium" + id + "', '" + generateRandomMobileNumber()
					+ "', 'ipsium.lorem_" + id + "@email.com'),\n";
		}
		query = query.substring(0, query.length() - 2) + ";";
		logger.trace(query);
		statement.executeUpdate(query);
		logger.info("Dummy persons inserted to the database successfully.");
	}

	// Deletes dummy persons with odd number ids from database.
	public void deleteDummyPersonsWithOddIds() throws SQLException {
		String query = "delete from Person where id = ";
		for (int id = 1; id <= 9; id += 2) {
			logger.trace(query + id);
			statement.executeUpdate(query + id);
		}
		logger.info("Dummy persons with odd ids deleted from the database successfully.");
	}

	// Extracts all persons details from database.
	public List<Person> getAllPersons() throws SQLException {
		List<Person> persons = new ArrayList<Person>();
		ResultSet resultSet = statement.executeQuery("select * from Person");
		while (resultSet.next()) {
			persons.add(new Person(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("mobile"),
					resultSet.getString("email")));
		}
		return persons;
	}

	// Prints all persons details from database.
	public void printPersons() throws SQLException {
		logger.info("\n\nAll Persons' Details:\n----------------------------");
		List<Person> persons = getAllPersons();
		for (Person person : persons) {
			logger.info(person);
		}
	}

	// Truncates the database.
	public void truncateDatabase() throws SQLException {
		statement.executeUpdate("truncate table person;");
		logger.info("Database truncated successfully.");
	}

	// Close access to database.
	public void revokeAccessFromDatabase() throws SQLException {
		statement.close();
	}
}
