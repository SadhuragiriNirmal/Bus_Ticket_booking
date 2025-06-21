package com.jspider.app.Bus_Ticket_booking.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.ManyToOne;
import lombok.Data;

@Component
@Data
public class BookingHistoryDto 
{

    private String bookedDate;
	private String journeyDate;
	private int  seatAvilable;
	@ManyToOne
	private UserDto user;	
}

