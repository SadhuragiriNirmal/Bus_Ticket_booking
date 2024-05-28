package com.jspider.app.Bus_Ticket_booking.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jspider.app.Bus_Ticket_booking.entity.BusSchedule;
import com.jspider.app.Bus_Ticket_booking.entity.Seat;
import com.jspider.app.Bus_Ticket_booking.entity.Ticket;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
  
@Component
@Data
public class BusDto {
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
	
	private  BusSchedule busschedule;
	private List<Ticket> tickets;
	private List<Seat> seats;

}
