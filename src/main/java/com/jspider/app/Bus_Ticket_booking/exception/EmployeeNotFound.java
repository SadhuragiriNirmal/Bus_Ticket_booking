package com.jspider.app.Bus_Ticket_booking.exception;

public class EmployeeNotFound extends RuntimeException
{
	
	String message;

	public String getMessage() {
		return message;
	}

	public EmployeeNotFound(String message) 
	{
		this.message = message;
	}

}