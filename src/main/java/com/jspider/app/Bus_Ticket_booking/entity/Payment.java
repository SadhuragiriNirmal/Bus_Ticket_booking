package com.jspider.app.Bus_Ticket_booking.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Payment")
@Data
public class Payment
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentId;

	private String paymentType;
	
	private double paidAmount;
	
	private String paymentStatus;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Ticket ticket;
	
}
