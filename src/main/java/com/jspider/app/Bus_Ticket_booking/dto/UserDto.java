package com.jspider.app.Bus_Ticket_booking.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.OneToMany;
import lombok.Data;



@Component
@Data
public class UserDto {
	
	private String uname;
	private String uemail;
	private String membership_type;
	@JsonIgnore
	@OneToMany
	private List<TicketDto> ticktes;
	
	}
