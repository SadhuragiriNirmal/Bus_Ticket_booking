package com.jspider.app.Bus_Ticket_booking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "BookingHistory")
@Data
public class BookingHistory 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bhid;

    private String bookedDate;

	private String journeyDate;
	
	private int  seatAvilable;
	
	@ManyToOne
	private User user;
	
}

