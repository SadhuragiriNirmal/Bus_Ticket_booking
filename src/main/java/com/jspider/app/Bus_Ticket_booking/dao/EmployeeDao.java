package com.jspider.app.Bus_Ticket_booking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspider.app.Bus_Ticket_booking.entity.EmployeeEntity;
import com.jspider.app.Bus_Ticket_booking.repo.EmployeeRepo;


@Repository
public class EmployeeDao
{
	@Autowired
	EmployeeRepo repo;
	
	
	
	public EmployeeEntity saveEmployee(EmployeeEntity emp)
	{
		return repo.save(emp);
	}
	
	
	public EmployeeEntity findById(int id)
	{
		Optional<EmployeeEntity> opemp  = repo.findById(id);
		
		if(opemp.isPresent())
		{
			return opemp.get();
		}
		else
		{
			return null;
		}
	}
	
	
	public EmployeeEntity deleteEmployee(int  id)
	{
        EmployeeEntity emp  = findById(id);
		
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
	
	
	public EmployeeEntity updateEmployee(EmployeeEntity emp,  int  id)
	{
        EmployeeEntity ex_emp  = findById(id);
		
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
	
	
	public List<EmployeeEntity> getAllEmployees()
	{
		List<EmployeeEntity> emps = repo.findAll();
		
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
