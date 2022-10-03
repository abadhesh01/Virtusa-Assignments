package service;

import java.util.Collection;

import model.Employee;
import model.SampleEmployee;

public class EmployeeService implements Service {

	@Override
	public void addDuplicateEmployeesToList(Collection<Employee> employees, int startValue, int numberOfElements,
			int increment) {
		for (int count = 1; count <= numberOfElements; count++, startValue += increment) {
			employees.add(new Employee(startValue, "Emp-" + startValue, "Mobile-" + startValue, startValue * 10_000));
			employees.add(new Employee(startValue, "Emp-" + startValue, "Mobile-" + startValue, startValue * 10_000));
		}

	}

	@Override
	public void printEmployeeDetailsFromList(Collection<Employee> employees) {
		employees.stream().forEach(System.out::println);
	}

	@Override
	public void addDuplicateSampleEmployeesToList(Collection<SampleEmployee> employees, int startValue,
			int numberOfElements, int increment) {
		for (int count = 1; count <= numberOfElements; count++, startValue += increment) {
			employees.add(
					new SampleEmployee(startValue, "Emp-" + startValue, "Mobile-" + startValue, startValue * 10_000));
			employees.add(
					new SampleEmployee(startValue, "Emp-" + startValue, "Mobile-" + startValue, startValue * 10_000));
		}
	}

	@Override
	public void printSampleEmployeeDetailsFromList(Collection<SampleEmployee> employees) {
		employees.stream().forEach(System.out::println);
	}
}
