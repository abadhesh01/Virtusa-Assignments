
class CurrencyConverter
{
	public static String convertCurrency(String currency1Name, String currenc2Name, double currency1Amount, double conversionValue) throws Exception
	{
		if(conversionValue == 0)
			throw new Exception("Conversion value cannot be zero(0).");
		
		String text = "";
		
		if(currency1Amount == 1.0)
	        text += currency1Amount + " " + currency1Name + " = ";
        else		
	        text += currency1Amount + " " + currency1Name + "s = ";
		
		double currency2Amount = currency1Amount * conversionValue;
		
		if(currency2Amount == 1.0)
			text += currency2Amount + " " + currenc2Name;
		else
			text += currency2Amount + " " + currenc2Name + "s";
			
	
	    return "\n" + text + "\n";
	}
	
}

public class Main2
{
	public static void main(String []args)
	{
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		boolean flag = true;
		while(flag)
		{
		    try
		    {  
			   System.out.print("\nCurrency Converter:\n-------------------\n\nFrom (Enter the currency name): ");
			   String currency1Name = scanner.nextLine();
			   System.out.print("To (Enter the currency name): ");
			   String currency2Name = scanner.nextLine();
			   System.out.print("Enter the amount to be converted (" + currency1Name + "): ");
			   double currency1Amount = Double.parseDouble(scanner.nextLine());
			   System.out.print("Enter the conversion value: ");
			   double conversionValue = Double.parseDouble(scanner.nextLine());
			   System.out.println(CurrencyConverter.convertCurrency(currency1Name, currency2Name, currency1Amount, conversionValue) + "\nExecution compleated!\n");
			   flag = false;
		    } 
		    catch(Exception exception)
		    {
			  System.out.println("\nError: " + exception + "\n");
		    }	
		}
		
	}
}