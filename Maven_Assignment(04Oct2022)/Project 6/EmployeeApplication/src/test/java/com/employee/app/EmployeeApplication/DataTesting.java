package com.employee.app.EmployeeApplication;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.employee.app.EmployeeApplication.Entity.Employee;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class DataTesting {

	private Employee employee;

	@Before
	public void createEmployee() {
		employee = new Employee(7, "Lorem Ipsium", "+91 96688 90898", "ipsium.lorem@email.com", 100_000);
	}

	@After
	public void destroyEmployee() {
		employee = null;
		System.gc();
	}

	@Test
	public void testId() {
		Assert.assertEquals(7, employee.getId());
	}

	@Test
	public void testName() {
		Assert.assertTrue(employee.getName().equals("Lorem Ipsium"));
	}

	@Test
	public void testPhone() {
		Assert.assertTrue(employee.getPhone().equals("+91 96688 90898"));
	}

	@Test
	public void testEmail() {
		Assert.assertTrue(employee.getEmail().equals("ipsium.lorem@email.com"));
	}

	@Test
	public void testSalary() {
		Assert.assertTrue(employee.getSalary() == 100_000);
	}

	@Ignore
	@Test
	public void testToString() {
		Assert.assertEquals("Employee@" + employee.hashCode(), employee.toString());
	}
}
