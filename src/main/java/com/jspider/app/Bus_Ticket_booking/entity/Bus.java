package com.jspider.app.Bus_Ticket_booking.entity;

import java.util.List;

import org.springframework.stereotype.Component;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Component
@Data
public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String busno;
	private String company;
	private String type;
	private int capacity;
	private String departureTime;
	private String arrivaltime;
	private String departureplace;
	private String arrivalplace;	
	private String journyDuration;
	private double ticketprice;
	
	@ManyToOne
	private  BusSchedule busschedule;
	@OneToMany
	private List<Ticket> tickets;
	@OneToMany
	private List<Seat> seats;
	
		

}
