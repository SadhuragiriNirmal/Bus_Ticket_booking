package com.jspider.app.Bus_Ticket_booking.exception;

public class BookingHistoryNotFound extends RuntimeException
{
	
	String message;

	public String getMessage() {
		return message;
	}

	public BookingHistoryNotFound(String message) 
	{
		this.message = message;
	}
}
