package Error;

@SuppressWarnings("serial")
public class InvalidEmployeeNameException extends Exception {

	public InvalidEmployeeNameException() {
		super("Error: Employee name cannot be empty(\"\") or null.");
	}
   
}
