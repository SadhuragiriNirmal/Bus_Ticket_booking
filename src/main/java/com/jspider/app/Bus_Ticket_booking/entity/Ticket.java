package com.jspider.app.Bus_Ticket_booking.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ticket")
@Data
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticketid;
	private String ticketNumber; 
	private String ticketCategory;
	@OneToOne(cascade = CascadeType.ALL)
	private Passenger passenger;
	@ManyToOne
	private User user;
	@OneToOne(cascade = CascadeType.REMOVE)
	private Payment payment;
	@OneToOne(cascade = CascadeType.REMOVE)
	private BookingHistory bookingHistory;
	@ManyToOne
	private Bus bus;
}
