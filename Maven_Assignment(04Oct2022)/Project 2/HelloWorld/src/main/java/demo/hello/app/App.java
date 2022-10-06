package demo.hello.app;

/**
 * Hello world!
 *
 */
public class App 
{
	private String text =  "Hello World!";
	
	public String getText()
	{
		return text;
	}
	
    public static void main( String[] args )
    {
		App app = new App();
        System.out.println(app.getText());
    }
}
