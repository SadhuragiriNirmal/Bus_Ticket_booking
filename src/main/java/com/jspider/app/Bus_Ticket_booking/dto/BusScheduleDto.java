package com.jspider.app.Bus_Ticket_booking.dto;



import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.OneToMany;
import lombok.Data;

@Component
@Data
public class BusScheduleDto {
	

	private String from;
	private String to;
	private String via;
	private int km;
	@OneToMany
	private List<BusDto> bus;

}
