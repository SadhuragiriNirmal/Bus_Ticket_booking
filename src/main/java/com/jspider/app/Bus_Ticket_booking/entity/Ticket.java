package com.jspider.app.Bus_Ticket_booking.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
	@Column(name = "start_place")
	private String from;
	@Column(name = "destination")
	private String to;
	private String via;
	private String ageCategory;
	private String busno;
	private String seatno;
	private double price;
	@OneToOne(cascade = CascadeType.ALL)
	private Passenger passenger;
	@ManyToOne
	private User user;
	
	}
