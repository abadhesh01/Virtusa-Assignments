
abstract class AbstractMovieTheatre
{
	private static double costINR; // Movie cost in rupees.
	
	//Setter
	public static void setCost(double cost)
	{
		costINR = cost;
	}
	
	//Getter
	public static double getCost()
	{
		return costINR;
	}
	
	//Show movie cost.
	public static void showCost()
	{
		System.out.println("\nMovie cost is INR " + costINR + ".\n");
	}
	
	public abstract void getTicket(double rate);
	public abstract void watchMovie();
}

class MovieTheatre extends AbstractMovieTheatre
{
	public void getTicket(double rate)
	{
		System.out.println("\nYour total bill for movie at \u001B[31m\"" + getClass().getName() + "\"\u001B[0m is \u001B[32mINR " + (getCost() + (getCost() * rate)) + "\u001B[0m.\n");
	}
	
	public void watchMovie()
	{
		System.out.println("\nYou are now watching movie at \u001B[33m\"" + getClass().getName() + "\"\u001B[0m.\n");
	}
}

class StageTheatre extends MovieTheatre {}

class Multiplex extends MovieTheatre {}

class IMAX extends MovieTheatre {}

class Service
{
	public Service()
	{
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		MovieTheatre.setCost(100.000);
		MovieTheatre.showCost();
		
		while(true)
		{
			try
			{
				System.out.print("\n[1] StageTheatre\n[2] Multiplex\n[3] IMAX\n[4] Exit\nEnter your choice: ");
				int choice = Integer.parseInt(scanner.nextLine());
				if((choice < 1) || (choice > 4))
					throw new Exception("Invalid choice !!!");
				if(choice == 4)
				{
				     System.out.println("\nBye :)\n");	
					 System.exit(0);
				}
				MovieTheatre movieTheatre = null;
				double rate = 0.0;
				switch(choice)
				{
					case 1: movieTheatre = new StageTheatre(); rate = 0.05; break;
					case 2: movieTheatre = new Multiplex(); rate = 0.10; break;
					case 3: movieTheatre = new IMAX(); rate = 0.30; break;
				}
				System.out.print("\n[1] Book Ticket\n[2] Watch Movie\nEnter your option: ");
				int option = Integer.parseInt(scanner.nextLine());
				if((option < 1) || (option > 2))
					throw new Exception("Invalid option !!!");
				switch(option)
				{
				    case 1: movieTheatre.getTicket(rate); break;
                    case 2:	movieTheatre.watchMovie(); break;				
				}
			}
			catch (Exception exception)
			{
				System.out.println("\nError: " + exception.getMessage() + "\n");
			}
		}
	}
}

public class Main2
{
   public static void main(String []args)
   {
	   new Service();  
   }   
}