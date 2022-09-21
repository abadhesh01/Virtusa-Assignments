package Service;

import java.util.List;

import Entity.Employee;
import Error.EmployeeDatabaseIsEmptyException;
import Error.EmployeeNotFoundException;
import Error.InvalidEmployeeIdException;
import Error.InvalidEmployeeNameException;
import Error.InvalidEmployeePhoneNumberException;
import Error.InvalidEmployeeSalaryException;

public interface EmployeeService {

	// Returns the list of all employees.
	public List<Employee> getAllEmployees() throws EmployeeDatabaseIsEmptyException, Exception;
	
	// Adds a new employee.
	public long addNewEmployee(String name, String phone, int salary) throws InvalidEmployeeNameException, InvalidEmployeePhoneNumberException, InvalidEmployeeSalaryException, Exception;
	
	// Remove existing employee.
	public void removeEmployee(long employeeId) throws EmployeeNotFoundException, InvalidEmployeeIdException, Exception;

	// Gets Employee by id.
	public Employee getEmployee(long employeeId) throws EmployeeNotFoundException, InvalidEmployeeIdException, Exception;

	// Get Employee by employees list index.
	public Employee getEmployee(int index) throws Exception;
	
	// Get Employee(s) by name.
	public List<Employee> getEmployees(String employeeName) throws EmployeeNotFoundException, InvalidEmployeeNameException, Exception;
	
	// Updating employee salary using employee id.
	public void updateEmployeeSalary(long id, int salary) throws InvalidEmployeeSalaryException, EmployeeNotFoundException, InvalidEmployeeIdException, Exception;
	
	// Updating an employee using employee id.
	public void updateEmployee(long id, Employee newEmployee) throws EmployeeNotFoundException, InvalidEmployeeIdException, Exception;
	
	// Sorting employees in the ascending order of their salary.
	public void sortEmployeesBySalary() throws EmployeeDatabaseIsEmptyException, Exception;
	
	// Splitting employee list using start index and end index.
	public List<Employee>[] splitAllEmployees() throws EmployeeDatabaseIsEmptyException, Exception;
	
}
