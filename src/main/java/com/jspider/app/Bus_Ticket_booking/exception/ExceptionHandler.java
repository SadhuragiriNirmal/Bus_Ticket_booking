package com.jspider.app.Bus_Ticket_booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler
{
	@org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ResponseStructure<String>> BookingHistoryNotFound(BookingHistoryNotFound exp)
    {
    	ResponseStructure<String> structure = new ResponseStructure<>();
		
		structure.setData(exp.getMessage());
		structure.setMessage("Booking History Details Not Found Check it Out");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
    }
	
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ResponseStructure<String>> PaymentIdNotFound(PaymentIdNotFound exp)
    {
    	ResponseStructure<String> structure = new ResponseStructure<>();
		
		structure.setData(exp.getMessage());
		structure.setMessage("Payment Details Not Found Check it Out");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
    }
	
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ResponseStructure<String>> EmployeeNotFound(EmployeeNotFound exp)
    {
    	ResponseStructure<String> structure = new ResponseStructure<>();
		
		structure.setData(exp.getMessage());
		structure.setMessage("Employee Details Not Found Check it Out");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
    }
}
