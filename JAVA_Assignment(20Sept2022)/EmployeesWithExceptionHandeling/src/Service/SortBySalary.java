package Service;

import java.util.Comparator;

import Entity.Employee;

public class SortBySalary implements Comparator<Employee>{

	public int compare(Employee employee1, Employee employee2)
	{
		return employee1.getSalary() - employee2.getSalary();
	}
	
}
