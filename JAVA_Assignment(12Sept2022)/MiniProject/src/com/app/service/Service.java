package com.app.service;

import com.app.Model.Department;
import com.app.Model.Employee;

public class Service {

	// Search department by department id and get department name and employees.
	public String getDepartmentNameAndEmployees(long departmentId, Department departments[])
	{
		if(departments.length == 0)
			return "Department array provided is empty! :(";
		
		
		for(int i=0; (i<departments.length) && (departments[i]!=null); i++)
		{
			String text = "";
			if(departments[i].getId() == departmentId)
			{
				Employee employees[] = departments[i].getEmployees();
				
				for(int j=0; (j<employees.length) && (employees[j]!=null); j++) 
					text += employees[j].toString() + ", ";
				
				text = text.substring(0, text.length() -2);
				
				return "Department [name=" + departments[i].getName() + ", employees=[" + text + "]";
			}        
		}
			
		return "Department with id(Department)='" + departmentId + "' was not found! :(";
	}
	
	// Search department name of the employee by using employee id.
	public String getDepartmentName(long employeeId, Department departments[])
	{
		if(departments.length == 0)
			return "Department array provided is empty! :(";
		
		for(int i=0; (i<departments.length) && (departments[i]!=null); i++)
		{
			Employee employees[] = departments[i].getEmployees();
			for(int j=0; (j<employees.length) && (employees[j]!=null); j++)
			{
				if(employees[j].getId() == employeeId)
					return "Department name with id(Employee)=" + employeeId + " : " + departments[i].getName();
			}
		}
		
			
		return "Department with id(Employee)='" + employeeId + "' was not found! :(";
	}
	
	// Search employee by employee id.
	public String getEmployee(long employeeId, Department departments[])
	{
		if(departments.length == 0)
			return "Department array provided is empty! :(";
		
		for(int i=0; (i<departments.length) && (departments[i]!=null); i++)
		{
			Employee employees[] = departments[i].getEmployees();
			for(int j=0; (j<employees.length) && (employees[j]!=null); j++)
			{
				if(employees[j].getId() == employeeId)
					return employees[j].toString();
			}
		}
			
		return "Employee with id(Employee)='" + employeeId + "' was not found! :(";	
	}
	
	// Search employee by employee name.
	public String getEmployee(Department departments[], String employeeName)
	{
		if(departments.length == 0)
			return "Department array provided is empty! :(";
			
		String text = null;
		for(int i=0; (i<departments.length) && (departments[i]!=null); i++)
		{
			Employee employees[] = departments[i].getEmployees();
			for(int j=0; (j<employees.length) && (employees[j]!=null); j++)
			{
				if(employees[j].getName().equals(employeeName))
				{
					if(text != null)
					   text += "\n" + employees[j].toString();	
					else 
					   text = employees[j].toString();
				}
						
			}
		}
				
			if(text != null)
				return "Employee with name(Employee)='" + employeeName + "' :\n" + text;
			
		return "Employee(s) with name(Employee)='" + employeeName + "' was not found! :(";
	}
	
	// Search employee by employee phone number.
	public String getEmployee(String phone, Department departments[])
	{
		if(departments.length == 0)
			return "Department array provided is empty! :(";
		
		for(int i=0; (i<departments.length) && (departments[i]!=null); i++)
		{
			Employee employees[] = departments[i].getEmployees();
			for(int j=0; (j<employees.length) && (employees[j]!=null); j++)
			{
				if(employees[j].getPhone().equals(phone))
					return employees[j].toString();
			}
		}
			
		return "Employee with phone(Employee)='" + phone + "' was not found! :(";
	}
	
	// Search employee by employee salary.
		public String getEmployee(Department departments[], long salary)
		{
			if(departments.length == 0)
				return "Department array provided is empty! :(";
			
			String text = null;
			for(int i=0; (i<departments.length) && (departments[i]!=null); i++)
			{   
				Employee employees[] = departments[i].getEmployees();
				for(int j=0; (j<employees.length) && (employees[j]!=null); j++)
				{
					if(employees[j].getSalary() == salary)
					{
						if(text != null)
							   text += "\n" + employees[j].toString();	
							else 
							   text = employees[j].toString();
					}
				}
			}
			
		    if(text != null)
		    	return "Employee with salary(Employee)='" + salary + "' :\n" + text;
				
			return "Employee with salary(Employee)='" + salary + "' was not found! :(";	
		}
	
}
