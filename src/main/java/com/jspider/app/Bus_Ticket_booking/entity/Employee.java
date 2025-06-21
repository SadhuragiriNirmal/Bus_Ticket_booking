package com.jspider.app.Bus_Ticket_booking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "Employee")
@Data
public class Employee 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int emp_id;

	private String name;
	
	private String mob;
	
	private String mail;
	
	private double salary;

	private String designation;

}
