package Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Entity.Employee;
import Error.EmployeeDatabaseIsEmptyException;
import Error.EmployeeNotFoundException;
import Error.InvalidEmployeeIdException;
import Error.InvalidEmployeeNameException;
import Error.InvalidEmployeePhoneNumberException;
import Error.InvalidEmployeeSalaryException;

public class EmployeeServiceImpl implements EmployeeService {

	private List<Employee> employees = new ArrayList<Employee>();
	
	
	
	// Returns the list of all employees.
	public List<Employee> getAllEmployees() throws EmployeeDatabaseIsEmptyException, Exception
	{
		if(employees.isEmpty())
			throw new EmployeeDatabaseIsEmptyException();
			
		return employees;
	}
	
	
	
	// Adds a new employee.
	public long addNewEmployee(String name, String phone, int salary) throws InvalidEmployeeNameException, InvalidEmployeePhoneNumberException, InvalidEmployeeSalaryException, Exception 
	{
		if(name == "" || name == null)
			throw new InvalidEmployeeNameException();
		
		if(phone == "" || phone == null)
			throw new InvalidEmployeePhoneNumberException();
		
		if(salary <= 0)
			throw new InvalidEmployeeSalaryException();
			
		if(employees.size() == 0)
			employees.add(new Employee(employees.size() + 1, name, phone, salary));
		else
		    employees.add(new Employee(employees.get(employees.size() - 1).getId() + 1, name, phone, salary));
		
		return employees.get(employees.size() - 1).getId();
	}
	
	
	
	// Remove existing employee.
	public void removeEmployee(long employeeId) throws EmployeeNotFoundException, InvalidEmployeeIdException, Exception
	{
		Employee employee = getEmployee(employeeId);
		employees.remove(employee);
		System.out.println("Employee with id='"+ employeeId + "' removed successfully.");
	}
	
	
	
	// Gets Employee by id.
	public Employee getEmployee(long employeeId) throws EmployeeNotFoundException, InvalidEmployeeIdException, Exception
	{
		if(employeeId <= 0)
			throw new InvalidEmployeeIdException();
	
		Employee employee = null;
		for(Employee employeeTemporary: employees)
		{
			if(employeeTemporary.getId() == employeeId)
			{
				employee = employeeTemporary;
				break;
			}
		}
	
		if(employee == null)
			throw new EmployeeNotFoundException(employeeId);
		
		return employee;
	}
	
	
	
	// Get Employee by employees list index.
	public Employee getEmployee(int index) throws Exception
	{
		return employees.get(index);
	}
	
	
	
	// Get Employee(s) by name.
	public List<Employee> getEmployees(String employeeName) throws EmployeeNotFoundException, InvalidEmployeeNameException, Exception
	{
		if(employeeName == "" || employeeName == null)
			throw new InvalidEmployeeNameException();
		
		List<Employee> employeesList = new ArrayList<Employee>();
		for(Employee employeeTemporary: employees)
		{
			if(employeeTemporary.getName().toLowerCase().equals(employeeName.toLowerCase()))
			{
			  employeesList.add(employeeTemporary);
			}
		}
	
		if(employeesList.size() == 0)
			throw new EmployeeNotFoundException(employeeName);
			
		return employeesList;
	}
	
	
	
	// Updating employee salary using employee id.
	public void updateEmployeeSalary(long id, int salary) throws InvalidEmployeeSalaryException, EmployeeNotFoundException, InvalidEmployeeIdException, Exception
	{
		if(salary <= 0)
			throw new InvalidEmployeeSalaryException();
		
		Employee employee = getEmployee(id);
		employee.setSalary(salary);
		
		System.out.println("Salary for Employee with id='" + employee.getId() + "' has been updated.");
	}
	
	
	
	// Updating an employee using employee id.
	public void updateEmployee(long id, Employee newEmployee) throws EmployeeNotFoundException, InvalidEmployeeIdException, Exception
	{	
		Employee employee = getEmployee(id);
		employee.setName(newEmployee.getName());
		employee.setPhoneString(newEmployee.getPhoneString());
		employee.setSalary(newEmployee.getSalary());
		
		System.out.println("Details for Employee with id='" + employee.getId() + "' has been updated.");
	}
	
	
	
	// Sorting employees in the ascending order of their salary.
	public void sortEmployeesBySalary() throws EmployeeDatabaseIsEmptyException, Exception
	{
		if(employees.isEmpty())
			throw new EmployeeDatabaseIsEmptyException();
			
		Collections.sort(employees, new SortBySalary());
		System.out.println("Employee list has been sorted in the ascending order of their salary.");
	}
	
	
	
	// Splitting employee list using start index and end index.
	public List<Employee>[] splitAllEmployees() throws EmployeeDatabaseIsEmptyException, Exception
	{
		@SuppressWarnings("unchecked")
		List<Employee> []employeesSplit = new ArrayList[2];
		employeesSplit[0] = new ArrayList<Employee>();
		employeesSplit[1] = new ArrayList<Employee>();
		
		if(employees.size() == 0)
			throw new EmployeeDatabaseIsEmptyException();
		
		int startIndex = 0;
		int endIndex = employees.size() -1;
		
		if(endIndex == 1)
		{
			employeesSplit[0].add(employees.get(0));
			employeesSplit[1].add(employees.get(1));
			return employeesSplit;
		}
		
		int splitValue = (startIndex + endIndex) / 2;
		
		if(endIndex%2 != 0)
		{
			splitValue ++;
		}	
			
		// Left split
		for(int index = 0; index < splitValue; index ++)
		{
			employeesSplit[0].add(employees.get(index)) ;
		}
			
		// Right split
		for(int index = splitValue; index <= endIndex; index ++)
		{
			employeesSplit[1].add(employees.get(index));
		}
		
		
		return employeesSplit;
	}

}
