package com.jspider.app.Bus_Ticket_booking.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.OneToOne;



@Component
public class PassengerDto {

	private String pname;
	private String pemail;
	private long pmobileno;
	private int pAge;
	private String pAgeCategory;
	private String paddress;
	@JsonIgnore
    @OneToOne
	private TicketDto ticket;
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPemail() {
		return pemail;
	}
	public void setPemail(String pemail) {
		this.pemail = pemail;
	}
	public long getPmobileno() {
		return pmobileno;
	}
	public void setPmobileno(long pmobileno) {
		this.pmobileno = pmobileno;
	}
	public int getpAge() {
		return pAge;
	}
	public void setpAge(int pAge) {
		this.pAge = pAge;
	}
	public String getpAgeCategory() {
		return pAgeCategory;
	}
	public void setpAgeCategory(String pAgeCategory) {
		this.pAgeCategory = pAgeCategory;
	}
	public String getPaddress() {
		return paddress;
	}
	public void setPaddress(String paddress) {
		this.paddress = paddress;
	}
	public TicketDto getTicket() {
		return ticket;
	}
	public void setTicket(TicketDto ticket) {
		this.ticket = ticket;
	}
    
	 
}
