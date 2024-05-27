package com.jspider.app.Bus_Ticket_booking.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.OneToMany;



@Component
public class UserDto {
	
	private String uname;
	private String uemail;
	private String membership_type;
	@JsonIgnore
	@OneToMany
	private List<TicketDto> ticktes;
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public String getMembership_type() {
		return membership_type;
	}
	public void setMembership_type(String membership_type) {
		this.membership_type = membership_type;
	}
	public List<TicketDto> getTicktes() {
		return ticktes;
	}
	public void setTicktes(List<TicketDto> ticktes) {
		this.ticktes = ticktes;
	}
	
}
