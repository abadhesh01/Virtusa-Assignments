
public class Main1
{

    // String objects.	
	public String createStringObjects()
	{
		String text1 = "This is a string literal.";
		
		String text2 = new String("This is a string created using 'new' keyword and 'String' class constructor.");
		
		char []characterSequence = {'c','h','a','r','a','c','t','e','r',' ','s','e','q','u','e','n','c','e'};
		String text3 = new String(characterSequence);
	
	    String text = text1.hashCode() + " (String object) : " + text1 + " (Text Length : " + text1.length() + ")\n";
		text += text2.hashCode() + " (String object) : " + text2 + " (Text Length : " + text2.length() + ")\n";
		text += text3.hashCode() + " (String object) : " + text3 + " (Text Length : " + text3.length() + ")";
	
	    return text;
	}
	
	// StringBuffer objects.
	public StringBuffer createEmptyStringBuffer()
	{
		return new StringBuffer();
	}
	
	public StringBuffer createStringBuffer(String text)
	{
		return new StringBuffer(text);
	}
	
	public StringBuffer createStringBuffer(int length)
	{
		return new StringBuffer(length);
	}
	
	// StringBuilder objects.
	public StringBuilder createEmptyStringBuilder()
	{
		return new StringBuilder();
	}
	
	public StringBuilder createStringBuilder(String text)
	{
		return new StringBuilder(text);
	}
	
	public StringBuilder createStringBuilder(int length)
	{
		return new StringBuilder(length);
	}
	
	// Integer object.
	public Integer createrIntegerObjectFrom(String number)
	{
		return Integer.parseInt(number);
	}
	
	// Object object
	public Object createNewObject()
	{
		return new Object();
	}
	
	
	public static void main(String []args)
	{
		Main1 main = new Main1();
		
		// String objects.
		System.out.println(main.createStringObjects());
		
		// StringBuffer objects.
		StringBuffer stringBuffer1 = main.createEmptyStringBuffer();
		System.out.println(stringBuffer1.hashCode() + " (StringBuffer object): " + stringBuffer1 + " (Text Length : " + stringBuffer1.length() + ")");
		
		StringBuffer stringBuffer2 = main.createStringBuffer("String Buffer Object");
		System.out.println(stringBuffer2.hashCode() + " (StringBuffer object): " + stringBuffer2 + " (Text Length : " + stringBuffer2.length() + ")");
		
		StringBuffer stringBuffer3 = main.createStringBuffer(30);
		System.out.println(stringBuffer3.hashCode() + " (StringBuffer object): " + stringBuffer3 +  " (Text Length : " + stringBuffer3.length() + ")");
				
		// StringBuilder objects.
        StringBuilder stringBuilder1 = main.createEmptyStringBuilder();
		System.out.println(stringBuilder1.hashCode() + " (StringBuilder object): " + stringBuilder1 + " (Text Length : " + stringBuilder1.length() + ")");
		
		StringBuilder stringBuilder2 = main.createStringBuilder("String Builder Object");
		System.out.println(stringBuilder2.hashCode() + " (StringBuilder object): " + stringBuilder2 + " (Text Length : " + stringBuilder2.length() + ")");	
		
		StringBuilder stringBuilder3 = main.createStringBuilder(40);
		System.out.println(stringBuilder3.hashCode() + " (StringBuilder object): " + stringBuilder3 + " (Text Length : " + stringBuilder3.length() + ")");
		
		// Integer object.
		Integer integer =  main.createrIntegerObjectFrom("73875");
		System.out.println(integer.hashCode() + " (Integer object): " + integer);
		
		// Object object.
		Object object = main.createNewObject();
		System.out.println(object.hashCode() + " (Object object): " + object);
	}
}