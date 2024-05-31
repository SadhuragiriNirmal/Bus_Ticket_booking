package com.jspider.app.Bus_Ticket_booking.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.OneToOne;
import lombok.Data;

@Component
@Data
public class PaymentDto 
{

	private String paymentType;
	private double paidAmount;
	private String paymentStatus;
	@JsonIgnore
	@OneToOne
	private TicketDto ticket;
}
