package Error;

@SuppressWarnings("serial")
public class InvalidEmployeeIdException extends Exception {

	public InvalidEmployeeIdException() {
		super("Error: Employee id cannot be zero(0) or negative.");
	}
   
}
