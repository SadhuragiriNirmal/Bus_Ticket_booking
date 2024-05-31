package com.jspider.app.Bus_Ticket_booking.exception;

public class PaymentIdNotFound extends RuntimeException
{
	
	String message;

	public String getMessage() {
		return message;
	}

	public PaymentIdNotFound(String message) 
	{
		this.message = message;
	}

}