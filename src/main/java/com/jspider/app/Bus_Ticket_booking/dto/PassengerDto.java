package com.jspider.app.Bus_Ticket_booking.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.OneToOne;
import lombok.Data;

@Component
@Data
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
	@OneToOne
	private SeatDto seat;
	 
}
