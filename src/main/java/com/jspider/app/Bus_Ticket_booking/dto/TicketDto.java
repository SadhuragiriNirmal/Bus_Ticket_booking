package com.jspider.app.Bus_Ticket_booking.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Component
@Data
public class TicketDto {
	
	private String from;
	private String to;
	private String via;
	private String ageCategory;
	private String busno;
	private String seatno;
	private double price;
	@ManyToOne
	private UserDto user;
	@OneToOne
	private PassengerDto passenger;
	
}
