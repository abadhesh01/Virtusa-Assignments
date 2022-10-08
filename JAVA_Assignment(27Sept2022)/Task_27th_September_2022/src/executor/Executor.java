package executor;

import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;
import model.Employee;
import model.SampleEmployee;
import service.EmployeeService;
import service.Service;

public class Executor {

	public static void main(String[] args) {

		System.out.println("\n\nHashSet-TreeSet Basic PROGRAM".toUpperCase() + "\n\n");

		try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {
			Service service = new EmployeeService();

			Collection<Employee> employeeHashSet = new HashSet<Employee>();
			Collection<SampleEmployee> sampleEmployeeHashSet = new HashSet<SampleEmployee>();
			Collection<Employee> employeeTreeSet = new TreeSet<Employee>();
			Collection<SampleEmployee> sampleEmployeeTreeSet = new TreeSet<SampleEmployee>();

			boolean flag = true;
			while (flag) {

				System.out.println("\n\n");
				System.out.println("[1] Create dummy elements with duplicates for Employee HashSet.");
				System.out.println("[2] Create dummy elements with duplicates for SampleEmployee HashSet.");
				System.out.println("[3] Create dummy elements with duplicates for Employee TreeSet.");
				System.out.println("[4] Create dummy elements with duplicates for SampleEmployee TreeSet.");
				System.out.println("[5] Display Employee HashSet.");
				System.out.println("[6] Display SampleEmployee HashSet.");
				System.out.println("[7] Display Employee TreeSet.");
				System.out.println("[8] Display SampleEmployee TreeSet.");
				System.out.println("[9] Clear all lists and sets.");
				System.out.println("[10] Exit");
				System.out.print("Enter your choice: ");

				try {

					int choice = Integer.parseInt(scanner.nextLine());
					System.out.println("\n\n");

					switch (choice) {
					case 1:
						System.out.print("Enter the id of first employee in Employee HashSet: ");
						int id = Integer.parseInt(scanner.nextLine());
						System.out.print("Enter the total number of employees : ");
						int numberOfEmployees = Integer.parseInt(scanner.nextLine());
						System.out.print("Enter the id increment value: ");
						int incrementBy = Integer.parseInt(scanner.nextLine());
						service.addDuplicateEmployeesToList(employeeHashSet, id, numberOfEmployees, incrementBy);
						break;

					case 2:
						System.out.print("Enter the id of first employee in SampleEmployee HashSet: ");
						id = Integer.parseInt(scanner.nextLine());
						System.out.print("Enter the total number of employees : ");
						numberOfEmployees = Integer.parseInt(scanner.nextLine());
						System.out.print("Enter the id increment value: ");
						incrementBy = Integer.parseInt(scanner.nextLine());
						service.addDuplicateSampleEmployeesToList(sampleEmployeeHashSet, id, numberOfEmployees,
								incrementBy);
						break;

					case 3:
						System.out.print("Enter the id of first employee in Employee TreeSet: ");
						id = Integer.parseInt(scanner.nextLine());
						System.out.print("Enter the total number of employees : ");
						numberOfEmployees = Integer.parseInt(scanner.nextLine());
						System.out.print("Enter the id increment value: ");
						incrementBy = Integer.parseInt(scanner.nextLine());
						service.addDuplicateEmployeesToList(employeeTreeSet, id, numberOfEmployees, incrementBy);
						break;

					case 4:
						System.out.print("Enter the id of first employee in Employee TreeSet: ");
						id = Integer.parseInt(scanner.nextLine());
						System.out.print("Enter the total number of employees : ");
						numberOfEmployees = Integer.parseInt(scanner.nextLine());
						System.out.print("Enter the id increment value: ");
						incrementBy = Integer.parseInt(scanner.nextLine());
						service.addDuplicateSampleEmployeesToList(sampleEmployeeTreeSet, id, numberOfEmployees,
								incrementBy);
						break;

					case 5:
						System.out.println("\n\nEmployee HashSet:\n-----------------");
						service.printEmployeeDetailsFromList(employeeHashSet);
						break;

					case 6:
						System.out.println("\n\nSampleEmployee HashSet:\n-----------------------");
						service.printSampleEmployeeDetailsFromList(sampleEmployeeHashSet);
						break;

					case 7:
						System.out.println("\n\nEmployee TreeSet:\n-----------------");
						service.printEmployeeDetailsFromList(employeeTreeSet);
						break;

					case 8:
						System.out.println("\n\nSampleEmployee TreeSet:\n-----------------------");
						service.printSampleEmployeeDetailsFromList(sampleEmployeeTreeSet);
						break;

					case 9:
						employeeHashSet.clear();
						sampleEmployeeHashSet.clear();
						employeeTreeSet.clear();
						sampleEmployeeTreeSet.clear();
						break;
						
					case 10:
						flag = false;
						scanner.close();
						System.out.println("\n\nBye! :)");
						break;

					default:
						throw new Exception("Invalid choice !!!");

					}

				} catch (Exception exception) {
					System.out.println("\n\n" + exception.getMessage() + "\n");
				}
			}
		}

	}

}
