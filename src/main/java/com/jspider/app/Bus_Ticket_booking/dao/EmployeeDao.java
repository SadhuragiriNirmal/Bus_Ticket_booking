package com.jspider.app.Bus_Ticket_booking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspider.app.Bus_Ticket_booking.entity.Employee;
import com.jspider.app.Bus_Ticket_booking.repository.EmployeeRepo;



@Repository
public class EmployeeDao
{
	@Autowired
	EmployeeRepo repo;
	
	
	
	public Employee saveEmployee(Employee emp)
	{
		return repo.save(emp);
	}
	
	
	public Employee findById(int id)
	{
		Optional<Employee> opemp  = repo.findById(id);
		
		if(opemp.isPresent())
		{
			return opemp.get();
		}
		else
		{
			return null;
		}
	}
	
	
	public Employee deleteEmployee(int  id)
	{
        Employee emp  = findById(id);
		
		if(emp!=null)
		{
			repo.delete(emp);
			return emp;
		}
		else
		{
			return null;
		}
	}
	
	
	public Employee updateEmployee(Employee emp,  int  id)
	{
        Employee ex_emp  = findById(id);
		
		if(ex_emp!=null)
		{
			emp.setEmp_id(id);
			
			return repo.save(emp);
		}
		else
		{
			return null;
		}
	}
	
	
	public List<Employee> getAllEmployees()
	{
		List<Employee> emps = repo.findAll();
		
		if(emps != null)
		{
			return emps;
		}
		else
		{
			return null;
		}
	}
}
