package com.jspider.app.Bus_Ticket_booking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Seat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seatid;
	private String seatno;
	private String seatType;
	private String seatPosition;
	private double seatPrice;
	private String bookedDate;
	@OneToOne
	private Passenger passenger;

}
