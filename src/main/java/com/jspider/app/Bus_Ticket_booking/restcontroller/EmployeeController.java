package com.jspider.app.Bus_Ticket_booking.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jspider.app.Bus_Ticket_booking.dto.EmployeeDto;
import com.jspider.app.Bus_Ticket_booking.entity.Employee;
import com.jspider.app.Bus_Ticket_booking.service.EmployeeService;
import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;

@RestController
@RequestMapping("employee")
public class EmployeeController
{
	@Autowired
	EmployeeService service;
	
	
	@PostMapping
	public ResponseEntity<ResponseStructure<EmployeeDto>> saveEmployee(@RequestBody Employee emp)
	{
		return service.saveEmployee(emp);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<EmployeeDto>> findEmployee(@RequestParam int empid)
	{
		return service.findEmployee(empid);
	}
	
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<EmployeeDto>> deleteEmployee(@RequestParam int id)
	{
		return service.deleteEmployee(id);
	}
	

	@PutMapping
	public ResponseEntity<ResponseStructure<EmployeeDto>> updateEmployee(@RequestBody Employee emp , @RequestParam int empid)
	{
		return service.updateEmployee(emp, empid);
	}
	
	
	@GetMapping("bookinghistoryDetails")
	public ResponseEntity<ResponseStructure<List<EmployeeDto>>> getAllAadhar()
	{
		return service.getAllEmployees();
	}
}

