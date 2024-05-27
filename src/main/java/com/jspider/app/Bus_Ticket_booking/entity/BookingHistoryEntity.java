package com.jspider.app.Bus_Ticket_booking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "BookingHistory")
public class BookingHistoryEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bhid;
	
	
	@NotNull(message = "From Location cannot be null")
	@NotBlank(message = "From Location cannot be blank")
	@Column(name = "startplace")
	private String from;
	
	
	@NotNull(message = "To Location cannot be null")
	@NotBlank(message = "To Location cannot be blank")
	@Column(name = "destination")
	private String to;
	
	
	@NotNull(message = "date cannot be null")
	@NotBlank(message = "date cannot be blank")
	private String date;
	
	
	@NotNull(message = "duration cannot be null")
	@NotBlank(message = "duration cannot be blank")
	private String duration;
	
	
	public int getBh_id() {
		return bhid;
	}


	public void setBh_id(int bh_id) {
		this.bhid = bh_id;
	}


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

