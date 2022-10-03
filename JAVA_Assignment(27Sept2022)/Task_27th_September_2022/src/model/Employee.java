package model;

import java.util.Objects;

public class Employee implements Comparable<Employee> {

	/*
	 * equals() is overridden.
	 */

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

	// hashCode()
	@Override
	public int hashCode() {
		return Objects.hash(id, name, phone, salary);
	}

	// equals()
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return id == other.id && Objects.equals(name, other.name) && Objects.equals(phone, other.phone)
				&& salary == other.salary;
	}

	// compareTo()
	@Override
	public int compareTo(Employee o) {
		return Long.compare(this.id, o.getId());
	}

	// Parameterized Constructor
	public Employee(long id, String name, String phone, long salary) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.salary = salary;
	}

	// Default Constructor
	public Employee() {
		super();
	}

}
