package com.jspider.app.Bus_Ticket_booking.entity;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "passenger")
@Getter
@Setter
public class Passenger {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int passengerid;
	private String pname;
	private String pemail;
	private long pmobileno;
	private int pAge;
	private String pAgeCategory;
	private String paddress;
	@OneToOne(cascade = CascadeType.ALL)
	private Ticket ticket;
	
}
