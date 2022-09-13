
class InvalidValueException extends Exception
{
	public InvalidValueException(String message)
	{
		super(message);
	}
}

class Bottle
{
  private int BOTTLE_VOLUME_IN_ML;
  private int water_volume;

  public void setBottleVolume(int volume) throws InvalidValueException
  {
	  if(volume < 500)
		throw new InvalidValueException("Bootle capacity cannot be less than 500 ml.");

      if(volume > 5000)
        throw new InvalidValueException("Bottle capacity cannot be more than 5000 ml.");		  
	  
	  BOTTLE_VOLUME_IN_ML = volume;
  }  
  
  public void fillBottle(int water_amount_to_be_entered) throws InvalidValueException
  {
	  if(water_amount_to_be_entered <= 0)
		throw new InvalidValueException("Water amount to be entered cannot be zero or negative.");  
	  
	  if(BOTTLE_VOLUME_IN_ML == water_volume)
		throw new InvalidValueException("Bottle is already full.");
	 
	  if(water_amount_to_be_entered > (BOTTLE_VOLUME_IN_ML - water_volume))
	  {
		String message = "";
		message += "Water amount to be entered cannot be more than the remaining volume.";
		message += "\nBootle capacity = " + BOTTLE_VOLUME_IN_ML + " ml.";
		message += "\nVolume of water in the bottle = " + water_volume + " ml.";
		message += "\nRemaining volume in the bottle = " + (BOTTLE_VOLUME_IN_ML - water_volume) + " ml.";
		throw new InvalidValueException(message);  
	  }
		
	  water_volume += water_amount_to_be_entered;	
	  System.out.println("Remaining volume in bottle is " + (BOTTLE_VOLUME_IN_ML - water_volume) + " ml.");
  }
  
  public void sipWater(int water_amount_to_be_sipped) throws InvalidValueException
  {
	  if(water_amount_to_be_sipped <= 0)
		throw new InvalidValueException("Water amount to be sipped cannot be zero or negative.");  
	  
	  if(water_volume == 0)
        throw new InvalidValueException("Bottle is already empty.");
	  
	  if(water_amount_to_be_sipped > water_volume)
	  {
		String message = "";
		message += "Water amount to be removed cannot be more than the water volume present in the bottle.";
		message += "\nBootle capacity = " + BOTTLE_VOLUME_IN_ML + " ml.";
		message += "\nVolume of water in the bottle = " + water_volume + " ml.";
		message += "\nRemaining volume in the bottle = " + (BOTTLE_VOLUME_IN_ML - water_volume) + " ml.";
		throw new InvalidValueException(message);  
	  }
		
	  water_volume -= water_amount_to_be_sipped;	
	  System.out.println("Remaining volume in bottle is " + (BOTTLE_VOLUME_IN_ML - water_volume) + " ml.");
  }
  
  public void emptyBottle() throws InvalidValueException
  {
	 if(water_volume == 0)
        throw new InvalidValueException("Bottle is already empty.");
	
	 water_volume = 0;
	 System.out.println("\nBottle is empty now.\n");
  }
  
}

public class Main4
{
  public static void main(String []args)
  {
	java.util.Scanner scanner = new java.util.Scanner(System.in);
	
	Bottle bottle = new Bottle();
	
	boolean flag = true;
	while(flag)
	{
		System.out.print("Enter bottle capacity in ml: ");
		
		try
		{
			bottle.setBottleVolume(Integer.parseInt(scanner.nextLine()));
			flag = false;
		}
		catch (Exception exception)
		{
			System.out.println("\nError: "+exception.getMessage()+"\n");
		}
	}
	
	flag = true;
	while(flag)
	{
		try
		{
			System.out.print("\n[1] Fill Water\n[2] Sip Water\n[3] Empty Bottle\n[4] Exit\nEnter your choice: ");
		    int choice = Integer.parseInt(scanner.nextLine());
			
			switch (choice)
			{
				case 1: System.out.print("\nEnter the amount of water to be filled in ml: "); bottle.fillBottle(Integer.parseInt(scanner.nextLine())); break;
				case 2: System.out.print("\nEnter the amount of water to be sipped in ml: "); bottle.sipWater(Integer.parseInt(scanner.nextLine())); break;
				case 3: bottle.emptyBottle(); break;
				case 4: flag = false; break;
				default: System.out.println("\nInvalid choice!!!\n");
			}
		}
		catch (Exception exception)
		{
			System.out.println("\nError: "+exception.getMessage()+"\n");
		}
		
	}
	
  }  
}