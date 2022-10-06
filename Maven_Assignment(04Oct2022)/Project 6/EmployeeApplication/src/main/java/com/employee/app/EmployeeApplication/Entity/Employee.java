package com.employee.app.EmployeeApplication.Entity;

public class Employee {

	private long id;
	private String name;
	private String phone;
	private String email;
	private double salary;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	// Default Constructor
	public Employee() {
		super();
	}

	// Parameterized constructor
	public Employee(long id, String name, String phone, String email, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.salary = salary;
	}

	// toString
	@Override
	public String toString() {
		return "\n\n Employee:\n[id=" + id + ",\n name=" + name + ",\n phone=" + phone + ",\n email=" + email
				+ ",\n Salary=" + salary + "]";
	}

}
