package executor;

import java.util.Scanner;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

import model.Person;

public class Executor {
	private static Logger logger = Logger.getLogger(executor.Executor.class);
	public static void main(String[] args) throws Exception {
		Appender appender = new ConsoleAppender(new SimpleLayout());
		logger.addAppender(appender);
		logger.setLevel(Level.INFO);
          
		Person person = new Person();
		person.getLogger().setLevel(Level.INFO);
		person.getAccessToSampleDatabase();
		
		@SuppressWarnings("resource")
		java.util.Scanner scanner = new Scanner(System.in);
		
		logger.info("\n\nPress any key to insert dummy persons to database.....");
		scanner.nextLine();
		person.insertDummyPersonsToDatabase();

		String text = "\n\nPress any key to print all person details from database.....";
		logger.info(text);
		scanner.nextLine();
		person.printPersons();
		
		logger.info("\n\nPress any key to delete dummy persons with odd ids from database.....");
		scanner.nextLine();
		person.deleteDummyPersonsWithOddIds();
		
		logger.info(text);
		scanner.nextLine();
		person.printPersons();
		
		logger.info("\n\nPress any key to truncate the database.....");
		scanner.nextLine();
		person.truncateDatabase();
		
		logger.info(text);
		scanner.nextLine();
		person.printPersons();
		
		logger.info("\n\nBye! :)\n\n");
	}
}
