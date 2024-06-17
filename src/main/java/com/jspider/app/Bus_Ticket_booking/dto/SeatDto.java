package com.jspider.app.Bus_Ticket_booking.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.OneToOne;
import lombok.Data;

@Component
@Data
public class SeatDto {
	
	private String seatno;
	private String seatType;
	private String seatPosition;
	private String bookedDate;
	private double seatPrice;
	@JsonIgnore
	@OneToOne
	private PassengerDto passenger;

}
