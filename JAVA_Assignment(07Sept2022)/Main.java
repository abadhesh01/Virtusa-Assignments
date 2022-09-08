
import java.util.Objects;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.Format;

class Name
{
  private String firstName;
  private String lastName;

  public void setFirstName(String name)
  {
     firstName = name;
  }

  public void setLastName(String name)
  {
     lastName = name;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }
 
  public String toString()
  {
     return "{firstName = " +  getFirstName() + ", lastName = " + getLastName() + "}";
  } 
  
}

class Person
{
   private Name name;
   private int age;
   private Date dob;
   private String address;
   
   public Person()
   {
	   name = new Name();
   }

   public void setName(Name name)
   {
	  this.name = name;   
   }  

   public void setAge(int age)
   {
	   this.age = age;
   } 

   public void setDOB(Date dob)
   {
	   this.dob = dob;
   }

   public void setAddress(String address)
   {
	   this.address = address;
   }   
   
   public Name getName()
   {       
	  return name;   
   }  

   public int getAge()
   {
	   return age;
   } 

   public String getDOB()
   {
	  if(dob == null)
		  return null;
	  
	  String DATE_PATTERN = "dd-MMMM-yyy";
  	  DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN); 
	  
	  return dateFormat.format(dob);
   }

   public String getAddress()
   {
	   return address;
   }   
   
   public String toString()
   {
	  return "{Name = " + name.toString() + ", age = " + getAge() + ", dob = " + getDOB() + ", address = " + address + "}";
   }   
}


public class Main
{
  public static void main(String []args) throws Exception
  {
	String DATE_PATTERN = "dd/MM/yyyy";
  	DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
	  
	  
     Person person1 = new Person();
	 System.out.println("Person-1("+person1.hashCode()+"): " + person1.toString());
	 
	 Person person2 = new Person();
	 person2.getName().setFirstName("Abadhesh");
	 person2.getName().setLastName("Mishra");
	 person2.setAge(22);
	 person2.setDOB(dateFormat.parse("16/07/2000"));
	 person2.setAddress("Odisha, India");
	 System.out.println("Person-2("+person2.hashCode()+"): " + person2.toString());
	 
	 Person person3 = new Person();
	 person3.getName().setFirstName("John");
	 person3.getName().setLastName("Rambo");
	 person3.setAge(31);
	 person3.setDOB(dateFormat.parse("05/12/1990"));
	 person3.setAddress("Colorado, USA");
	 System.out.println("Person-3("+person3.hashCode()+"): " + person3.toString());
	 
	 Person person4 = person2;
	 System.out.println("Person-4("+person4.hashCode()+"): " + person4.toString());
	 
	 person2.getName().setFirstName("Niko");
	 person4.getName().setLastName("Bellic");
	 person4.setAddress("Moscow, Russia");
	 
	 System.out.println("Person-2("+person2.hashCode()+"): " + person2.toString());
	 System.out.println("Person-4("+person4.hashCode()+"): " + person4.toString());
	 
  }
}