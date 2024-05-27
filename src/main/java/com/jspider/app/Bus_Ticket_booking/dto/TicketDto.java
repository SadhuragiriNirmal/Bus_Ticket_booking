package com.jspider.app.Bus_Ticket_booking.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Component
public class TicketDto {
	
	private String from;
	private String to;
	private String via;
	private String ageCategory;
	private String busno;
	private String seatno;
	private double price;
	@ManyToOne
	private UserDto user;
	@OneToOne
	private PassengerDto passenger;
	
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
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public String getAgeCategory() {
		return ageCategory;
	}
	public void setAgeCategory(String ageCategory) {
		this.ageCategory = ageCategory;
	}
	public String getBusno() {
		return busno;
	}
	public void setBusno(String busno) {
		this.busno = busno;
	}
	public String getSeatno() {
		return seatno;
	}
	public void setSeatno(String seatno) {
		this.seatno = seatno;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public PassengerDto getPassenger() {
		return passenger;
	}
	public void setPassenger(PassengerDto passenger) {
		this.passenger = passenger;
	}
	

}
