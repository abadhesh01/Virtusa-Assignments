
class Bulb
{
	private boolean status;
	
	public void switchStatus()
	{
	   status = !status;
       System.out.println("\nBulb status has been changed.\n");	   
	}
	
	public void showStatus()
	{
		System.out.println("\nBulb is on: " + status + "\n");
	}
}


public class Main1
{
	public static void main(String []args)
	{
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
	    Bulb bulb = new Bulb();
		
		boolean flag = true;
		while(flag){
		System.out.print("(1)Switch on/off.\n(2)Show bulb status.\n(3)Exit\nEnter your choice: ");
		char choice = scanner.nextLine().charAt(0);
		switch(choice)
		{
			case '1': bulb.switchStatus(); break;
			case '2': bulb.showStatus(); break;
			case '3': flag = false; break;
			default: System.out.println("\nInvalid Choice!!!\n");
		}}
		
	}
}