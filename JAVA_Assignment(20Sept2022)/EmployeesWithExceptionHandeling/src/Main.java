import java.util.List;

import Entity.Employee;
import Service.EmployeeService;
import Service.EmployeeServiceImpl;

public class Main {
	
 public static void printEmployees (List<Employee> employees)
 {
	 System.out.println("Dummy employees list:\n---------------------");
	 for(Employee employee : employees)
	  System.out.println(employee.toString());
	 System.out.println("\n\n");
 }
	
 public static void main(String[] args) {
   	
   // Creating employee service object.	 
   EmployeeService employeeService = new EmployeeServiceImpl();

   // Printing employee list
   try 
   {
	   Main.printEmployees(employeeService.getAllEmployees());
   } 
   catch (Exception exception) 
   {
	   System.out.println(exception.getClass().getName() + " : " + exception.getMessage());
   }
   
   
   // Inserting dummy employee in employees list.
   System.out.println("\n\n\nInserting dummy employees........");
   for(char i = 65; i <= 71; i ++)
   {
	   try 
	   {
		   System.out.println("New Employee id = " + employeeService.addNewEmployee("emp-"+i, "ph-"+(int)i, i * 1000));
	   } 
	   catch (Exception exception) 
	   {
		   System.out.println(exception.getClass().getName() + " : " + exception.getMessage());
	   }
   }  
   System.out.println("\n\n");
   
   
   
   // Printing employee list
   try 
   {
	   Main.printEmployees(employeeService.getAllEmployees());
   } 
   catch (Exception exception) 
   {
	   System.out.println(exception.getClass().getName() + " : " + exception.getMessage());
   }
   
   
   
   // Searching employees by their ids.
   System.out.println("Searching employees by their ids:\n---------------------------------");
   for(long i = -2; i < 10; i ++) 
   {
      try 
      {
	   System.out.println(employeeService.getEmployee(i)); 
      } 
      catch (Exception exception) 
      {
	   System.out.println(exception.getClass().getName() + " : " + exception.getMessage());
      }
   }
   
   
   
   // Searching employees by their names.
   System.out.println("\n\n\nSearching employees by their names:\n-----------------------------------");
      try 
	  {
		  System.out.println(employeeService.getEmployees(null));
	  } 
	  catch (Exception exception) 
      {
	   System.out.println(exception.getClass().getName() + " : " + exception.getMessage());
      }
      
      try 
	  {
		  System.out.println(employeeService.getEmployees(""));
	  } 
	  catch (Exception exception) 
      {
	   System.out.println(exception.getClass().getName() + " : " + exception.getMessage());
      }
       for(char ch = 'A'; ch <= 'J'; ch ++) 
      {   
        try 
        {
	     System.out.println(employeeService.getEmployees("emp-" + ch)); 
        } 
        catch (Exception exception) 
        {
	     System.out.println(exception.getClass().getName() + " : " + exception.getMessage());
        }
      }
       
       
       
    // Updating employees salary.
    System.out.println("\n\n\nUpdating employees salary:\n--------------------------");   
    try 
    {
	  employeeService.updateEmployeeSalary(1, -65_000);	
	} catch (Exception exception) 
    {
	 System.out.println(exception.getClass().getName() + " : " + exception.getMessage());	
	}
    try 
    {
	  employeeService.updateEmployeeSalary(1, 0);	
	} catch (Exception exception) 
    {
	 System.out.println(exception.getClass().getName() + " : " + exception.getMessage());	
	}
    for(int count = 10, count2 = 0; count >= 0; count --)
    {
    	count2 ++;
    	try 
    	{
		  employeeService.updateEmployeeSalary(count, count2 * 100_000);	
		} 
    	catch (Exception exception) 
    	{
    	 System.out.println(exception.getClass().getName() + " : " + exception.getMessage());
    	}		
    }
   
    
    System.out.println("\n\n");
    // Printing employee list
    try 
    {
 	   Main.printEmployees(employeeService.getAllEmployees());
    } 
    catch (Exception exception) 
    {
 	   System.out.println(exception.getClass().getName() + " : " + exception.getMessage());
    }
    
    
    
    // Employees split list.
    System.out.println("Employees split list:\n---------------------");
    try 
    {
    	List<Employee> splitArray[] = employeeService.splitAllEmployees();
    	System.out.println("Left half : " + splitArray[0]);
        System.out.println("Right half : " + splitArray[1]);
	} 
    catch (Exception exception) 
    {
    	System.out.println(exception.getClass().getName() + " : " + exception.getMessage());
	}
    
    
    
    
    // Removing employees with even number ids.
    System.out.println("\n\n\nRemoving employees using id where id is even:\n---------------------------------------------");
    for(int count = -4; count <= 12; count += 2)
    {
    	try 
    	{
			employeeService.removeEmployee(count);
		} 
    	catch (Exception exception) 
    	{
    		System.out.println(exception.getClass().getName() + " : " + exception.getMessage());
		}
    }
    
    
    
    System.out.println("\n\n");
    // Printing employee list
    try 
    {
 	   Main.printEmployees(employeeService.getAllEmployees());
    } 
    catch (Exception exception) 
    {
 	   System.out.println(exception.getClass().getName() + " : " + exception.getMessage());
    }
    
    
    
    // Sorting employees in their ascending order of their salary.
    try 
    {
	   employeeService.sortEmployeesBySalary();	
	} 
    catch (Exception exception) 
    {
    	System.out.println(exception.getClass().getName() + " : " + exception.getMessage());
	}
    
    
    
    System.out.println("\n\n");
    // Printing employee list
    try 
    {
 	   Main.printEmployees(employeeService.getAllEmployees());
    } 
    catch (Exception exception) 
    {
 	   System.out.println(exception.getClass().getName() + " : " + exception.getMessage());
    }
    
    
    
    // Employees split list.
    System.out.println("Employees split list:\n---------------------");
    try 
    {
    	List<Employee> splitArray[] = employeeService.splitAllEmployees();
    	System.out.println("Left half : " + splitArray[0]);
        System.out.println("Right half : " + splitArray[1]);
	} 
    catch (Exception exception) 
    {
    	System.out.println(exception.getClass().getName() + " : " + exception.getMessage());
	}
    
    
    
    
    // Updating an employee
    System.out.println("\n\n\nUpdating an employee:\n---------------------");
    Employee employee = new Employee();
    employee.setName("Abadhesh Mishra");
    employee.setPhoneString("+91 96681 94464");
    employee.setSalary(30_000);
    
    try 
    {
		employeeService.updateEmployee(-1, employee);
	} 
    catch (Exception exception) 
    {
       System.out.println(exception.getClass().getName() + " : " + exception.getMessage());
	}
    
    try 
    {
		employeeService.updateEmployee(0, employee);
	} 
    catch (Exception exception) 
    {
       System.out.println(exception.getClass().getName() + " : " + exception.getMessage());
	}
    
    try 
    {
		employeeService.updateEmployee(2, employee);
	} 
    catch (Exception exception) 
    {
       System.out.println(exception.getClass().getName() + " : " + exception.getMessage());
	}
    
    try 
    {
		employeeService.updateEmployee(3, employee);
	} 
    catch (Exception exception) 
    {
       System.out.println(exception.getClass().getName() + " : " + exception.getMessage());
	}
    
    
    
    System.out.println("\n\n");
    // Printing employee list
    try 
    {
 	   Main.printEmployees(employeeService.getAllEmployees());
    } 
    catch (Exception exception) 
    {
 	   System.out.println(exception.getClass().getName() + " : " + exception.getMessage());
    }
    
    
    
    // Getting employee from employees list index.
    System.out.println("Getting employee by employees list index:\n-----------------------------------------");
    for(int index = -2; index <= 5; index ++) {
     try 
     {
     	System.out.println(employeeService.getEmployee(index));
     } 
     catch (Exception exception) 
     {
 	    System.out.println(exception.getClass().getName() + " : " + exception.getMessage());
     }
    }
    
 }
}
