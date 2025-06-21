package com.jspider.app.Bus_Ticket_booking.dto;

import java.util.List;

import org.springframework.stereotype.Component;


import jakarta.persistence.OneToMany;
import lombok.Data;



@Component
@Data
public class UserDto {
	
	private String uname;
	private String uemail;
	private String membership_type;
	@OneToMany
	private List<TicketDto> ticktes;
	@OneToMany
	private List<BookingHistoryDto> bookingHistories;
	
}
