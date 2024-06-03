package com.jspider.app.Bus_Ticket_booking.dto;


import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Component
@Data
public class BusDto {
	
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
	private BusScheduleDto busSchedules;
	@JsonIgnore
	@OneToMany
	private List<SeatDto> seat;

}
