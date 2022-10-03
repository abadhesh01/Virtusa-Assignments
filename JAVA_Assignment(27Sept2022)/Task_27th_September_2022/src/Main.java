import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

import model.Employee;
import model.SampleEmployee;
import service.EmployeeService;
import service.Service;

public class Main {

	public static void main(String[] args) {

		/*
		 * Task: (1) Create employee objects with same values and add those objects to
		 * HashSet. (2) Create employee objects with different values and add those
		 * objects to TreeSet.
		 */

		Service service = new EmployeeService();
        
		System.out.println("\n\nEmployee HashSet:\n-----------------");
		Collection<Employee> employeeHashSet = new HashSet<Employee>();
		service.addDuplicateEmployeesToList(employeeHashSet, 1, 5, 2);
		service.printEmployeeDetailsFromList(employeeHashSet);

		System.out.println("\n\nSampleEmployee HashSet:\n-----------------------");
		Collection<SampleEmployee> sampleEmployeeHashSet = new HashSet<SampleEmployee>();
		service.addDuplicateSampleEmployeesToList(sampleEmployeeHashSet, 1, 5, 2);
		service.printSampleEmployeeDetailsFromList(sampleEmployeeHashSet);
         
		System.out.println("\n\nEmployee TreeSet:\n-----------------");
		Collection<Employee> employeeTreeSet = new TreeSet<Employee>();
		employeeTreeSet.add(new Employee(5, null, null, 0));
		service.addDuplicateEmployeesToList(employeeTreeSet, 2, 5, 2);
		service.printEmployeeDetailsFromList(employeeTreeSet);
        
		System.out.println("\n\nSampleEmployee TreeSet:\n-----------------------");
		Collection<SampleEmployee> sampleEmployeeTreeSet = new TreeSet<SampleEmployee>();
		service.addDuplicateSampleEmployeesToList(sampleEmployeeTreeSet, 2, 5, 2);
		service.printSampleEmployeeDetailsFromList(sampleEmployeeTreeSet);
        
	}

}
