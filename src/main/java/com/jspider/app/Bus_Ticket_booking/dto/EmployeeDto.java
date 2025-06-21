package com.jspider.app.Bus_Ticket_booking.dto;

import org.springframework.stereotype.Component;

import lombok.Data;


@Component
@Data
public class EmployeeDto 
{

	private String name;

	private String mob;

	private String mail;

	private double salary;

	private String designation;

}
