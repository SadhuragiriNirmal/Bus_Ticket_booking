package com.jspider.app.Bus_Ticket_booking.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "user")
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	private String uname;
	private String uemail;
	private String upassword;
	private String membership_type;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Ticket> tickets;
    @OneToMany(fetch = FetchType.EAGER)
    private List<BookingHistory> bookingHistories;
    
}
