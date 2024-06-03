package com.jspider.app.Bus_Ticket_booking.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class BusSchedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int busScheduleid;
	@Column(name = "startplace")
	private String from;
	@Column(name = "destination")
	private String to;
	private String via;
	private int km;
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER)
	private List<Bus> bus;
	
}
