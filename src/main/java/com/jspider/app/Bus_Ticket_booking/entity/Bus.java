package com.jspider.app.Bus_Ticket_booking.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Bus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int busid;
	private String company;
	private String busno;
	private String busType;
	private int busCapacity;
	private int seatsAvailable;
	private String departurePlace;
	private String arrivalPlace;
	private String departureDate;
	private String arrivalDate;
	private String departureTime;
	private String arrivalTime;
	private String journeyDuration;
	@ManyToOne
	private BusSchedule busSchedules;
	@OneToMany(fetch = FetchType.EAGER)
	private List<Seat> seat;
	@OneToMany(fetch = FetchType.EAGER)
	private List<Ticket> ticket;

}
