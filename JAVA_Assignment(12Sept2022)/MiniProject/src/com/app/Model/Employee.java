package com.app.Model;

public class Employee {
	
	private long id;
	private String name;
	private String phone;
	private long salary;
	
	
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
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	
	// toString()
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", phone=" + phone + ", salary=" + salary + "]";
	}
	
	// Parameterized constructor
	public Employee(long id, String name, String phone, long salary) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.salary = salary;
	}

	// Default constructor
	public Employee() {}
	
}
