package com.jspider.app.Bus_Ticket_booking.dto;

import java.util.List;

import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class BusScheduleDto {
	
	private String busFrom;
	private String busTo;
	private String departureTime;
	private String arrivaltime;
	private String via;
	private double ticketprice;
	private double distance;
	private String journyDuration;	
	private List<BusDto> bus;
	
}
