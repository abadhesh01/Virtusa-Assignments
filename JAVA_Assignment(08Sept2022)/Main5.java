
class Board
{
	private String text;
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public String getText()
	{
	    return text;	
	}
	
	public void displayBoard()
	{
		System.out.println("\nBoard:\n------\n" + text + "\n");
	}
	
	public void clearBoard()
	{
		text = null;
	}
}

class Pen
{	
	public void write(Board board, String text)
	{
		if(board.getText() == null)
		{
			board.setText("");
		}
	
		board.setText(board.getText() + "\n" + text);
	}
}


public class Main5
{
  public static void main(String []args)
  {
	java.util.Scanner scanner = new java.util.Scanner(System.in);
    
    Board board = new Board();
    Pen pen = new Pen();

    boolean flag = true;
    while(flag)
	{
		System.out.print("\n[1] Write on board\n[2] Show board\n[3] Clear board\n[4] Exit\nEnter your choice:");
		try
		{
			int choice = Integer.parseInt(scanner.nextLine());
			switch(choice)
			{
				case 1: System.out.print("Enter your text: "); pen.write(board, scanner.nextLine()); System.out.println("\nBoard has been edited successfully.\n"); break;
				case 2: board.displayBoard(); break;
				case 3: board.clearBoard(); System.out.println("\nBoard has been cleared successfully.\n"); break;
				case 4: flag = false; System.out.println("\nBye :)\n"); break;
				default: System.out.println("\nInvalid choice!!!\n");
			}
		}
		catch(Exception exception)
		{
			System.out.println("\nError: "+exception.getMessage()+"\n");
		}	
	}
  }  
}