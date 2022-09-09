
import java.util.List;
import java.util.ArrayList;

class Chair
{
	private long id;
	 
	public Chair (long id)
    {
		this.id = id;
	}	
	
	public String toString()
	{
		return "Chair: {id = '" + id + "'}"; 
	}
}

class Student
{
	private long id;
	private String name;
	private String address;
	
	public Student (long id, String name, String address)
	{
		this.id = id;
		this.name = name;
		this.address = address;
	}
	
	public String toString()
	{
		return "Student: {id = '" + id + "', name = '" + name + "', address = '" + address + "'}";
	}
}

class Classroom
{
	private long id;
	private String name;
	private List<Chair> chairs;
	private List<Student> students;
	
	public Classroom (long id, String name)
	{
		this.id = id;
		this.name = name;
		chairs = new ArrayList<Chair>();
		students = new ArrayList<Student>();
		
		for(long i = 1; i <= 10; i ++)
		{
			chairs.add(new Chair(i));
		}
		
		students.add(new Student(1, "Abadhesh Mishra", "Odisha, India"));
		students.add(new Student(2, "Somyajeet Chakraborty", "West Bengal, India"));
		students.add(new Student(3, "Narendra Reddy Balaobanna", "Andhrapradesh, India"));
		students.add(new Student(4, "Anand Kumar Singh", "West Bengal, India"));
		students.add(new Student(5, "Prem Kumar", "Tamilnadu, India"));
		students.add(new Student(6, "Jaya Praveen", "Telengana, India"));
		students.add(new Student(7, "Rishabh Kumar", "Madhyapradesh, India"));
		students.add(new Student(8, "John Rambo", "Colorado, United States"));
		students.add(new Student(9, "James Bond", "Wales, United Kingdom"));
		students.add(new Student(10, "Niko Bellic", "Belgrade, Serbia"));
	}
	
	public String toString()
	{
	   String text = "";
	   text += "Classroom: {";
	   text += "id = '" + id + "',\n";
	   text += "name = '" + name + "',\n";
	   text += "chairs = {";
	   for (int i = 0; i < 10; i ++)
	   { 
		 text +=  chairs.get(i);
		 if (i != 9)
	        text += ",\n";
	   }
	   text += "},";
	   text += "\nstudents = {";
	   for (int i = 0; i < 10; i ++)
	   { 
		 text += students.get(i);
		 if (i != 9)
			text += ",\n";
	   }
	   text += "}";
	   text += "}";
	   
	   return text;
	}
}


public class Main6
{
  public static void main(String []args)
  {
	 Classroom classroom = new Classroom(7, "Vikram Sarabhai");
	 System.out.println(classroom.toString());
  }  
}