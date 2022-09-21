package Error;

@SuppressWarnings("serial")
public class EmployeeDatabaseIsEmptyException extends Exception {
	
	public EmployeeDatabaseIsEmptyException()
	{
	    super("Employee database is empty! :(");
	}
}
