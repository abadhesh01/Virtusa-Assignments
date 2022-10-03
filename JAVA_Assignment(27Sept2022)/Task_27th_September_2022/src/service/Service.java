package service;

import java.util.Collection;

import model.Employee;
import model.SampleEmployee;

public interface Service {

	public void addDuplicateEmployeesToList(Collection<Employee> employees, int startValue, int numberOfElements,
			int increment);

	public void printEmployeeDetailsFromList(Collection<Employee> employees);

	public void addDuplicateSampleEmployeesToList(Collection<SampleEmployee> employees, int startValue,
			int numberOfElements, int increment);

	public void printSampleEmployeeDetailsFromList(Collection<SampleEmployee> employees);
}
