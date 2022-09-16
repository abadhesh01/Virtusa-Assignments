
class Colour
{
	public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
	public static final String WHITE_BACKGROUND = "\u001B[47m";
	public static final String BLACK_BACKGROUND = "\u001B[40m";
}

abstract class AbstractPrinter
{
    public abstract String print(String text, int mode, String colour);
}

class Printer extends AbstractPrinter
{
	public String print(String text, int mode, String colour)
	{
		if(mode == 1)
		{
			return  "Printing \""+ text + "\" from " + getClass().getName() + " in black and white mode.";
		}
		
		if(mode == 2)
		{
			if(colour.toLowerCase().equals("black"))
			   return   "Printing \""+ Colour.WHITE_BACKGROUND + Colour.BLACK + text + Colour.RESET + "\" from " + getClass().getName() + " in colour mode.";
		    else if(colour.toLowerCase().equals("red"))
			   return   "Printing \""+ Colour.BLACK_BACKGROUND + Colour.RED + text + Colour.RESET + "\" from " + getClass().getName() + " in colour mode.";
			else if(colour.toLowerCase().equals("green"))
			   return   "Printing \""+ Colour.BLACK_BACKGROUND + Colour.GREEN + text + Colour.RESET + "\" from " + getClass().getName() + " in colour mode.";
			else if(colour.toLowerCase().equals("yellow"))
			   return   "Printing \""+ Colour.BLACK_BACKGROUND + Colour.YELLOW + text + Colour.RESET + "\" from " + getClass().getName() + " in colour mode.";
            else if(colour.toLowerCase().equals("blue"))
			   return   "Printing \""+ Colour.BLACK_BACKGROUND + Colour.BLUE + text + Colour.RESET + "\" from " + getClass().getName() + " in colour mode.";
            else if(colour.toLowerCase().equals("purple"))
			   return   "Printing \""+ Colour.BLACK_BACKGROUND + Colour.PURPLE + text + Colour.RESET + "\" from " + getClass().getName() + " in colour mode.";
            else if(colour.toLowerCase().equals("cyan"))
			   return   "Printing \""+ Colour.BLACK_BACKGROUND + Colour.CYAN + text + Colour.RESET + "\" from " + getClass().getName() + " in colour mode.";
            else if(colour.toLowerCase().equals("white"))				
			   return   "Printing \""+ Colour.BLACK_BACKGROUND + Colour.WHITE + text + Colour.RESET + "\" from " + getClass().getName() + " in colour mode.";
		    else
			   return "Invalid colour option selected from " + getClass().getName() + ".";
		}
		
		return "Invalid printing mode selected from " + getClass().getName() + ".";
	}
}

class HP_Printer extends Printer {}

class Samsung_Printer extends Printer {}

class Cannon_Printer extends Printer {}

class Computer
{
	private java.util.Scanner scanner = new java.util.Scanner(System.in);
  
  public void printText()
  {
	
	boolean flag = true;
	while(flag)
	{
		try
		{
			System.out.print("\n[1] HP Printer\n[2] Samsung Printer\n[3] Cannon Printer\n[4] Exit\nSelect your printer: ");
			int choice = Integer.parseInt(scanner.nextLine());
			if(choice == 4)
			{	
				flag = false;
				System.out.println("\nBye! :)\n");
            }				
			else if((choice < 1) || (choice > 4))
				throw new Exception("Invalid choice !!!");
			else
			{
			    System.out.print("Enter your mode('1' for black & white and '2' for colour): "); 
				int mode = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter your text: "); 
				String text = scanner.nextLine();
                String colour = "Black & White";
				if(mode == 2)
				{
					System.out.print("Enter text colour: ");
					colour = scanner.nextLine();
				}
				switch(choice)
				{
					case 1: System.out.println("\n" + new HP_Printer().print(text, mode, colour) + "\n"); break;
					case 2: System.out.println("\n" + new Samsung_Printer().print(text, mode, colour) + "\n"); break;
					case 3: System.out.println("\n" + new Cannon_Printer().print(text, mode, colour) + "\n"); break;
				}
			}	
		}
		catch(Exception exception)
		{
			System.out.println("\nError: " + exception.getMessage() + "\n");
		}
	}
  }	
}

public class Main1
{
	 //public static final String ANSI_RESET = "\u001B[0m";
     //public static final String ANSI_BLACK = "\u001B[30m";
     //public static final String ANSI_RED = "\u001B[31m";
     //public static final String ANSI_GREEN = "\u001B[32m";
     //public static final String ANSI_YELLOW = "\u001B[33m";
     //public static final String ANSI_BLUE = "\u001B[34m";
     //public static final String ANSI_PURPLE = "\u001B[35m";
     //public static final String ANSI_CYAN = "\u001B[36m";
     //public static final String ANSI_WHITE = "\u001B[37m";
	
	public static void main(String []args)
	{
		/*
		HP_Printer printer1 = new HP_Printer();
		Samsung_Printer printer2 = new Samsung_Printer();
		Cannon_Printer printer3 = new Cannon_Printer();
		
		System.out.println("\n" + printer1.print("This is a HP printer.", 1, "Red"));
		System.out.println(printer1.print("This is a HP printer.", 2, "Red"));
		
		System.out.println("\n" + printer2.print("This is a Samsung printer.", 1, "Blue"));
		System.out.println(printer2.print("This is a Samsung printer.", 2, "Blue"));
		
		System.out.println("\n" + printer3.print("This is a Cannon printer.", 1, "Green"));
		System.out.println(printer3.print("This is a Cannon printer.", 2, "Green"));
		
		System.out.println("\n" + printer1.print("This is a HP printer.", 3, "Red"));
		System.out.println(printer2.print("This is a Samsung printer.", 4, "Blue"));
		System.out.println(printer3.print("This is a Cannon printer.", 5, "Green") + "\n");
		*/
		
		//System.out.println(ANSI_RED + "RED COLORED" + ANSI_RESET + " NORMAL");
		
		Computer computer = new Computer();
		computer.printText();
	}
}