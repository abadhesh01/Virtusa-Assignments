import com.app.Model.Department;
import com.app.Model.Employee;
import com.app.service.Service;

public class Main {

	public static void main(String[] args) {
		
		// Set department array size.
		Department.setDepartmentArraySize(5);
		
		// Set employee array size.
		Department.setEmployeeArraySize(10);
		
		// Show array size.
		System.out.println(Department.showArraysCapacity());
		
		Department departments[] = Department.createDepartments();
		System.out.println("\nDepartment length = " + departments.length);
		for (int i=0; i<2; i++)
		{
			departments[i] = new Department();
			departments[i].setId(i+1);
			departments[i].setName("dept-" + (i+1));
			departments[i].setLocation("loc-" + (i + 1));
			Employee employees[] = departments[i].getEmployees();
			for(int j=0; j<5; j++)
			{
				employees[j] = new Employee(((i+1)*10)+(j+1), "emp-"+(((i+1)*10)+(j+1)), "mob-"+(((i+1)*10)+(j+1)), 10000 * (j+1));
			}
		
			System.out.println(departments[i].toString());
		}
		
		Service service = new Service();
		
		System.out.println("\nSearch department by department id and get department name and employees (test):\n" + service.getDepartmentNameAndEmployees(1, departments));
		System.out.println(service.getDepartmentNameAndEmployees(2, departments));
		System.out.println(service.getDepartmentNameAndEmployees(3, departments));
		
		System.out.println("\nSearch department name by employee id (test)\n" + service.getDepartmentName(11, departments));
		System.out.println(service.getDepartmentName(23, departments));
		System.out.println(service.getDepartmentName(17, departments));
		
		System.out.println("\nSearch employee by employee id (test):\n" + service.getEmployee(13, departments));
		System.out.println(service.getEmployee(24, departments));
		System.out.println(service.getEmployee(17, departments));
		
		System.out.println("\nSearch employee by employee name (test):\n" + service.getEmployee(departments, "emp-12"));
		System.out.println("---------------------------------------------------------\n" + service.getEmployee(departments, "emp-24"));
		System.out.println("---------------------------------------------------------\n" + service.getEmployee(departments, "emp-19"));
		
		System.out.println("\nSearch employee by employee phone number (test):\n" + service.getEmployee("mob-13", departments));
		System.out.println(service.getEmployee("mob-22", departments));
		System.out.println(service.getEmployee("mob-26", departments));
		
		System.out.println("\nSearch employee by employee salary (test):\n" + service.getEmployee(departments, 30000));
		System.out.println("---------------------------------------------------------\n" + service.getEmployee(departments, 10000));
		System.out.println("---------------------------------------------------------\n" + service.getEmployee(departments, 70000));
		
		
	}

}
