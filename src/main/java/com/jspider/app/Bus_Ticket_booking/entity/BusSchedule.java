package com.jspider.app.Bus_Ticket_booking.entity;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Component
@Data
public class BusSchedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sid;
	private String busFrom;
	private String busTo;
	private String departureTime;
	private String arrivaltime;
	private String via;
	private double ticketprice;
	private double distance;
	private String journyDuration;
	@OneToMany
	private List<Bus> bus;
	
}
