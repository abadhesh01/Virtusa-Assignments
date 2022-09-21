package Entity;

public class Employee {
	
	private long id;
	private String name;
	private String phone;
	private int salary;
	
	// Getters and Setters.
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
	public String getPhoneString() {
		return phone;
	}
	public void setPhoneString(String phoneString) {
		this.phone = phoneString;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	// toString();
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", phone=" + phone + ", salary=" + salary + "]";
	}
	
	// Constructors
	public Employee(long id, String name, String phoneString, int salary) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phoneString;
		this.salary = salary;
	}

	public Employee () {}
	
}
