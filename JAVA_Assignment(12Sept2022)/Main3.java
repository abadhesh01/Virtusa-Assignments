
class DistanceConverter
{
	private double MILES_TO_KILOMETRES = 1.60934;
	private double KILOMETRES_TO_METRES = 1000;
	
	public double getMilesToKilometres()
	{
		return MILES_TO_KILOMETRES;
	}
	
	public double getKilometresToMetres()
	{
		return KILOMETRES_TO_METRES;
	}
	
	public static double convert(double distance, double conversionValue) throws Exception
	{
		if(conversionValue == 0)
		   throw new Exception("Conversion value cannot be zero(0).");	
		
		return distance * conversionValue;
	}
}

public class Main3
{
	public static void main(String []args)
	{
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		DistanceConverter converter = new DistanceConverter();
		double distance = 0d;
		
		try
		{
			System.out.println("\nDistance converter:\n-------------------\n\n[1] Miles to Kilometres\n[2] Kilometers to Metres\n[3] Miles to Metres");
			System.out.print("[4] Kilometres to Miles\n[5] Metres to Kilometres\n[6] Metres to Miles\n[7] Custom\nEnter your choice: ");
			int choice = Integer.parseInt(scanner.nextLine());
			switch(choice)
			{
				case 1: System.out.print("\nEnter your distance in Mile(s): "); distance = Double.parseDouble(scanner.nextLine()); 
				        System.out.println("Distance in Kilometre(s): " + converter.convert(distance, converter.getMilesToKilometres()) + "\n"); break;
						
				case 2: System.out.print("\nEnter your distance in Kilometre(s): "); distance = Double.parseDouble(scanner.nextLine()); 
				        System.out.println("Distance in Metre(s): " + converter.convert(distance, converter.getKilometresToMetres()) + "\n"); break;		
						
				case 3: System.out.print("\nEnter your distance in Mile(s): "); distance = Double.parseDouble(scanner.nextLine()); 
				        System.out.println("Distance in Metres(s): " + converter.convert(distance, converter.getMilesToKilometres() * converter.getKilometresToMetres()) + "\n"); break;

				case 4: System.out.print("\nEnter your distance in Kilometre(s): "); distance = Double.parseDouble(scanner.nextLine()); 
				        System.out.println("Distance in Mile(s): " + converter.convert(distance, 1.0 / converter.getMilesToKilometres()) + "\n"); break;		
						
				case 5: System.out.print("\nEnter your distance in Metre(s): "); distance = Double.parseDouble(scanner.nextLine()); 
				        System.out.println("Distance in Kilometre(s): " + converter.convert(distance, 1.0 / converter.getKilometresToMetres()) + "\n"); break;

                case 6: System.out.print("\nEnter your distance in Metres(s): "); distance = Double.parseDouble(scanner.nextLine()); 
				        System.out.println("Distance in Miles(s): " + converter.convert(distance, 1.0 / (converter.getMilesToKilometres() * converter.getKilometresToMetres())) + "\n"); break;					

                case 7: System.out.println("\nChange in source code (here) for custom units if you want to calculate for other units apart from miles, kilometres and metre.\n"); break;

                default: System.out.println("\nInvalid choice!!!\n");			
			}
		}
		catch(Exception exception)
		{
			System.out.println("\nError: " + exception.getMessage() + "\n");
		}
	}
}