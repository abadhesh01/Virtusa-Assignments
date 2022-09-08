
class Car
{
	private boolean isRunning;
	
	public void setIsRunning()
	{
		isRunning = !isRunning;
	}
	
	public boolean getIsRunning()
	{
		return isRunning;
	}
	
	public void getStatus()
	{
		if(isRunning == true)
			System.out.println("\nThe car is running.\n");
		else
			System.out.println("\nThe car is not running.\n");
	} 
}

class Driver
{
	public void drive(Car car)
	{
		if(car.getIsRunning() == true)
			System.out.println("\nThe driver is already driving the car.\n");
		else
			car.setIsRunning();  System.out.println();
	}
	
	public void stop(Car car)
	{
		if(car.getIsRunning() == false)
			System.out.println("\nThe driver has already stopped the car.\n");
		else
			car.setIsRunning();  System.out.println();
	}
}

public class Main7
{
	public static void main(String []args)
	{
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		Car car = new Car();
		Driver driver = new Driver();
		
		boolean flag = true;
		while(flag)
		{
			System.out.print("[1] Drive the car.\n[2] Stop the car\n[3] See car status\n[4] Exit\nEnter your choice: ");
			try
			{
				int choice = Integer.parseInt(scanner.nextLine());
				switch(choice)
				{
                    case 1: driver.drive(car); break;
                    case 2: driver.stop(car); break;
                    case 3: car.getStatus(); break;
                    case 4: flag = false; System.out.println("\nBye! :)\n"); break; 	
                    default: System.out.println("\nInvalid choice!!!\n");					
				}
			}
			catch (Exception exception)
			{
				System.out.println("\nError: " + exception.getMessage() + "\n");
			}
		}
		
	}
}