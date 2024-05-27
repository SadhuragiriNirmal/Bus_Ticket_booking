package com.jspider.app.Bus_Ticket_booking.controller;

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
import com.jspider.app.Bus_Ticket_booking.entity.EmployeeEntity;
import com.jspider.app.Bus_Ticket_booking.service.EmployeeService;
import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("employee")
public class EmployeeController
{
	@Autowired
	EmployeeService service;
	
	
	
	@Operation(summary = "Used to Save Employee Details")
	@PostMapping
	public ResponseEntity<ResponseStructure<EmployeeDto>> saveEmployee(@RequestBody EmployeeEntity emp)
	{
		return service.saveEmployee(emp);
	}
	
	
	@Operation(summary = "Used to Find Employee Details")
	@GetMapping
	public ResponseEntity<ResponseStructure<EmployeeDto>> findEmployee(@RequestParam int id)
	{
		return service.findEmployee(id);
	}
	
	
	@Operation(summary = "Used to Delete Employee Details")
	@DeleteMapping
	public ResponseEntity<ResponseStructure<EmployeeDto>> deleteEmployee(@RequestParam int id)
	{
		return service.deleteEmployee(id);
	}
	
	
	
	@Operation(summary = "Used to Update Employee Details")
	@PutMapping
	public ResponseEntity<ResponseStructure<EmployeeDto>> updateEmployee(@RequestBody EmployeeEntity pay , @RequestParam int id)
	{
		return service.updateEmployee(pay, id);
	}
	
	
	
	@Operation(summary = "Used to Get All Booking History Details")
	@GetMapping("bookinghistoryDetails")
	public ResponseEntity<ResponseStructure<List<EmployeeDto>>> getAllAadhar()
	{
		return service.getAllEmployees();
	}
}

