package com.jspider.app.Bus_Ticket_booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jspider.app.Bus_Ticket_booking.dao.EmployeeDao;
import com.jspider.app.Bus_Ticket_booking.dto.EmployeeDto;
import com.jspider.app.Bus_Ticket_booking.entity.Employee;
import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;

@Service
public class EmployeeService
{
	@Autowired
	EmployeeDao eDao;
	
	@Autowired
	EmployeeDto eDto;
	
	
	
	public ResponseEntity<ResponseStructure<EmployeeDto>> saveEmployee(Employee emp)
	{
		ResponseStructure<EmployeeDto> structure = new ResponseStructure<>();
		
		Employee saveemp = eDao.saveEmployee(emp);
		
		if(saveemp != null)
		{
			eDto.setName(saveemp.getName());
			eDto.setMob(saveemp.getMob());
			eDto.setMail(saveemp.getMail());
			eDto.setSalary(saveemp.getSalary());
			eDto.setDesignation(saveemp.getDesignation());

			structure.setData(eDto);
			structure.setMessage("Employee saved successfully........");
			structure.setStatus(HttpStatus.CREATED.value());
			
			return new ResponseEntity<ResponseStructure<EmployeeDto>>(structure, HttpStatus.CREATED);
		}
		else
		{
			return null;
		}
	}
	
	
	
	
	public ResponseEntity<ResponseStructure<EmployeeDto>> findEmployee(int id)
	{
		ResponseStructure<EmployeeDto> structure = new ResponseStructure<>();
		
		Employee findEmployee = eDao.findById(id);
		
		if(findEmployee != null)
		{
			eDto.setName(findEmployee.getName());
			eDto.setMob(findEmployee.getMob());
			eDto.setMail(findEmployee.getMail());
			eDto.setSalary(findEmployee.getSalary());
			eDto.setDesignation(findEmployee.getDesignation());

			structure.setData(eDto);
			structure.setMessage("Employee Details Got successfully........");
			structure.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<EmployeeDto>>(structure, HttpStatus.FOUND);
		}
		else return null;
	}
	
	
	
	
	public ResponseEntity<ResponseStructure<EmployeeDto>> updateEmployee(Employee emp, int id)
	{
        ResponseStructure<EmployeeDto> structure = new ResponseStructure<>();
		
		Employee ex_emp = eDao.updateEmployee(emp, id);
		
		if(ex_emp != null)
		{
			eDto.setName(ex_emp.getName());
			eDto.setMob(ex_emp.getMob());
			eDto.setMail(ex_emp.getMail());
			eDto.setSalary(ex_emp.getSalary());
			eDto.setDesignation(ex_emp.getDesignation());

			structure.setData(eDto);
			structure.setMessage("Employee Details Updated successfully........");
			structure.setStatus(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<EmployeeDto>>(structure, HttpStatus.OK);
		}
		else
		{
			return null;
		}
	}
	
	
	
	public ResponseEntity<ResponseStructure<EmployeeDto>> deleteEmployee(int id)
	{
		ResponseStructure<EmployeeDto> structure = new ResponseStructure<>();
		
		Employee emp = eDao.deleteEmployee(id);
		
		if(emp != null)
		{
			eDto.setName(emp.getName());
			eDto.setMob(emp.getMob());
			eDto.setMail(emp.getMail());
			eDto.setSalary(emp.getSalary());
			eDto.setDesignation(emp.getDesignation());

			structure.setData(eDto);
			structure.setMessage("Employee Details Deleted successfully........");
			structure.setStatus(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<EmployeeDto>>(structure, HttpStatus.OK);
		}
		else
		{
			return null;
		}
		
	}
	
	
	
	public ResponseEntity<ResponseStructure<List<EmployeeDto>>> getAllEmployees()
	{
		ResponseStructure<List<EmployeeDto>> structure = new ResponseStructure<>();
		
		List<Employee> allempsentity = eDao.getAllEmployees();
		
		List<EmployeeDto> allemps = new ArrayList<>();
		
		if(allempsentity != null)
		{
			for(Employee e : allempsentity)
			{
				
				eDto.setName(e.getName());
				eDto.setMob(e.getMob());
				eDto.setMail(e.getMail());
				eDto.setSalary(e.getSalary());
				eDto.setDesignation(e.getDesignation());
				
				allemps.add(eDto);
			}

			structure.setData(allemps);
			structure.setMessage("All Employees Get successfully........");
			structure.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<List<EmployeeDto>>>(structure, HttpStatus.FOUND);
		}
		else
		{
			return null;
		}
		
	}
}
