package com.jspider.app.Bus_Ticket_booking.dto;

import org.springframework.stereotype.Component;


@Component
public class EmployeeDto 
{

	private String name;

	private String mob;

	private String mail;

	private double salary;

	private String designation;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMob() {
		return mob;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}


//	@JsonIgnore
//	@OneToMany(cascade = CascadeType.ALL)
//	private UserDto user;
	
		
	
}
