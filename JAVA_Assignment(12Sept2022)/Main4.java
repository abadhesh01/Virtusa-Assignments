
class TimeConverter
{
	private int HOURS_TO_MINUTES = 60;
	private int MINUTES_TO_SECONDS = 60;
	private int HOURS_TO_SECONDS = HOURS_TO_MINUTES * MINUTES_TO_SECONDS;
	
	public String convert(int time, int choice) throws Exception
	{
		// Choice 1 : hours to minutes
		if(choice == 1)
			return "\nTime in minute(s) : " + (time * HOURS_TO_MINUTES) + "\n";
		
		// Choice 2 : minutes to seconds
		if(choice == 2)
			return "\nTime in second(s) : " + (time * MINUTES_TO_SECONDS) + "\n";
		
		// Choice 3 : houres to seconds
		if(choice == 3)
			return "\nTime in second(s) : " + (time * HOURS_TO_SECONDS) + "\n";
		
		// Choice 4 : minutes to hours
		if(choice == 4)
			return "\nTime in hour(s) : " + (time / HOURS_TO_MINUTES) + ", Remaining minute(s) : " + (time % HOURS_TO_MINUTES) + "\n";
		
		// Choice 5 : seconds to minutes
		if(choice == 5)
			return "\nTime in minute(s) : " + (time / MINUTES_TO_SECONDS) + ", Remaining second(s) : " + (time % MINUTES_TO_SECONDS) + "\n";
		
		// Choice 6 : seconds to hours
		if(choice == 6)
		{
			String text = "\nTime in hour(s) : " + (time / HOURS_TO_SECONDS) + ", Remaining minutes(s) : ";
			time %= HOURS_TO_SECONDS;
			text += (time / MINUTES_TO_SECONDS) + ", Remaining second(s) : ";
			time %= MINUTES_TO_SECONDS;
            text += time + "\n";
            return text;			
		}   	
		
		return "\nInvalid choice !!!\n";
	}
}

public class Main4
{
  public static void main(String []args)
  {
    java.util.Scanner scanner = new java.util.Scanner(System.in);	  
	TimeConverter converter = new TimeConverter();
	
	try
	{
		System.out.println("\nTime Converter:\n---------------\n[1] Hours to Minutes\n[2] Minutes to Seconds\n[3] Hours to Seconds\n[4] Minutes to Hours\n[5] Seconds to Minutes\n[6] Seconds to Hours");
		System.out.print("Enter your choice: ");
		int choice = Integer.parseInt(scanner.nextLine());
		
		if(choice < 1 || choice > 6)
		{
		   System.out.println(converter.convert(0, choice)); 
		   System.exit(0);	
		}
		
		if(choice == 1 || choice == 3)
			System.out.print("Enter time in hour(s): ");
		else if(choice == 2 || choice == 4)
		    System.out.print("Enter time in minutes(s): ");
		else
			System.out.print("Enter time in second(s): ");
		
		int time = Integer.parseInt(scanner.nextLine());
		System.out.println(converter.convert(time, choice));
		
	}
	catch(Exception exception)
	{
		System.out.println("\nError: " + exception.getMessage() + "\n");
	}
  }
}
