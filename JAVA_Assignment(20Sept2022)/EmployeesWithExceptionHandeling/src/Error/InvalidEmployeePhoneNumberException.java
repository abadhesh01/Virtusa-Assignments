package Error;

@SuppressWarnings("serial")
public class InvalidEmployeePhoneNumberException extends Exception{
	
    public InvalidEmployeePhoneNumberException() {
    	super("Error: Employee phone cannot be empty(\"\") or null.");
	}

}
