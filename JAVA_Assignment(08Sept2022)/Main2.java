
class TopSpeedException extends Exception
{
	public TopSpeedException()
	{
		super("Vehicle is already at top speed.");
	}
}

class CarNotMovingException extends Exception
{
	public CarNotMovingException()
	{
		super("Vehicle is not moving.");
	}
}

class Car 
{
	private int speed_in_kmph;
	
	public void accelerate() throws TopSpeedException
	{
        if(speed_in_kmph == 100) 
			throw new TopSpeedException();
			
		speed_in_kmph += 10;
		System.out.println("\nCar speed has been increased by 10 kmph.\n");
	}
	
	public void deacceleration() throws CarNotMovingException
	{
		if(speed_in_kmph == 0) 
			throw new CarNotMovingException();
			
		speed_in_kmph -= 10;
		System.out.println("\nCar speed has been decreased by 10 kmph.\n");
	}
	
	public void showSpeed()
	{
		System.out.println("\nSpped of car is " + speed_in_kmph + " Kmph.\n");
	}
}

public class Main2
{
  public static void main(String []args)
  {
	    java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		Car car = new Car();
		
	    boolean flag = true;
		while(flag){try{
		System.out.print("(1)Accelerate the car.\n(2)Deaccelerate the car.\n(3)Show car speed.\n(4)Exit\nEnter your choice: ");
		char choice = scanner.nextLine().charAt(0);
		switch(choice)
		{
			case '1': car.accelerate(); break;
			case '2': car.deacceleration(); break;
			case '3': car.showSpeed(); break;
			case '4': flag = false; break;
			default: System.out.println("\nInvalid Choice!!!\n");
		}}
		  catch (Exception exception)
		  {
			System.out.println("\n" + exception.getMessage() + "\n");  
		  }
		}	
  }  
}