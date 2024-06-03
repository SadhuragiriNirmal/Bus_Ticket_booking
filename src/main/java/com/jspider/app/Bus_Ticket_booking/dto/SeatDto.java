package com.jspider.app.Bus_Ticket_booking.dto;

import org.springframework.stereotype.Component;



import jakarta.persistence.OneToOne;
import lombok.Data;

@Component
@Data
public class SeatDto {
	
	private String seatno;
	private String seatType;
	private String seatPosition;
	private double seatPrice;
	@OneToOne
	private PassengerDto passenger;

}
