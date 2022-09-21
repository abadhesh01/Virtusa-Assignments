package Error;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends Exception {
	
	public EmployeeNotFoundException() {}
	
	public EmployeeNotFoundException(long id)
	{
		super("Error: Employee with id='" + id + "' was not found! :(");
	}

	public EmployeeNotFoundException(String name)
	{
		super("Error: Employee with name='" + name + "' was not found! :(");
	}
}
