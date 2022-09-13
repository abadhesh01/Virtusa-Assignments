package com.app.Model;

import java.util.Arrays;

public class Department {
	
	private static int EMPLOYEE_ARRAY_SIZE;
	private static int DEPARTMENT_ARRAY_SIZE;
	
	public static void setEmployeeArraySize(int size)
	{
		if(size<=0)
		{
			System.out.println("Array size cannot be zero(0).");
			System.exit(0);
		}
		 
		    EMPLOYEE_ARRAY_SIZE = size;
	}
	
	public static void setDepartmentArraySize(int size)
	{
		if(size<=0)
		{
			System.out.println("Array size cannot be zero(0).");
			System.exit(0);
		}
			
		DEPARTMENT_ARRAY_SIZE = size;
	}
	
	public static String showArraysCapacity()
	{
		return "\nDepartment array size = " + DEPARTMENT_ARRAY_SIZE + "\nEmployee array size = " + EMPLOYEE_ARRAY_SIZE;
	}
	
	
	// Fields
	private long id;
	private String name;
	private String location;
	private Employee []employees = new Employee [EMPLOYEE_ARRAY_SIZE];
	
	
	// Getters and Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Employee[] getEmployees() {
		return employees;
	}
	public void setEmployees(Employee[] employees) {
		this.employees = employees;
	}
	
	

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", location=" + location + ", employees="
				+ Arrays.toString(employees) + "]";
	}

	// Parameterized constructor
	public Department(long id, String name, String location, Employee[] employees) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.employees = employees;
	}
	
	// Default constructor
	public Department() {}
	
	// Create array of departments.
	public static Department[] createDepartments()
	{
		return new Department[DEPARTMENT_ARRAY_SIZE];
	}
	
}
