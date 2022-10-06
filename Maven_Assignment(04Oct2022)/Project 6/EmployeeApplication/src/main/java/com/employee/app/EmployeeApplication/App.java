package com.employee.app.EmployeeApplication;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

import com.employee.app.EmployeeApplication.Entity.Employee;

/**
 * Hello world!
 *
 */
public class App 
{
	private static Logger logger = Logger.getLogger(Appender.class);
    public static void main( String[] args )
    {
       Layout layout = new SimpleLayout();
       Appender appender = new ConsoleAppender(layout);
       logger.addAppender(appender);
       logger.setLevel(Level.ALL);
       
		Employee employee = new Employee(7, "Lorem Ipsium", "+91 96688 90898", "ipsim.lorem@email.com", 100_000);
       logger.info(employee);
    }  
    
}
