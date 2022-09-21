package Error;

@SuppressWarnings("serial")
public class InvalidEmployeeSalaryException extends Exception {
	
    public InvalidEmployeeSalaryException() {
    	super("Error: Employee salary cannot be zero(0) or negative.");
	}
    
}
