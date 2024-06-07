package com.jspider.app.Bus_Ticket_booking.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Component
@Data
public class TicketDto {
	
	private String ticketNumber; 
	private String ticketCategory;
	@JsonIgnore
	@ManyToOne
	private UserDto user;
	@OneToOne
	private PassengerDto passenger;
	@OneToOne
	private PaymentDto payment;
	@OneToOne
	private BookingHistoryDto bookingHistory;
	@ManyToOne
	private BusDto bus;
	

}
