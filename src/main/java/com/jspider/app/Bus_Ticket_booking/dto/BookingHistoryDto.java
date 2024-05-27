package com.jspider.app.Bus_Ticket_booking.dto;

import org.springframework.stereotype.Component;

@Component
public class BookingHistoryDto 
{

	private String from;

	private String to;

	private String date;

	private String duration;


//	@JsonIgnore
//	@OneToMany(cascade = CascadeType.ALL)
//	private UserDto user;	

	public String getFrom() {
		return from;
	}


	public void setFrom(String from) {
		this.from = from;
	}


	public String getTo() {
		return to;
	}


	public void setTo(String to) {
		this.to = to;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getduration() {
		return duration;
	}


	public void setduration(String duration) {
		this.duration = duration;
	}
		
	
	
}

