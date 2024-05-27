package com.jspider.app.Bus_Ticket_booking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Entity
@Table(name = "Employee")
public class EmployeeEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int emp_id;
	
	@NotNull(message = "From Location cannot be null")
	@NotBlank(message = "From Location cannot be blank")
	private String name;
	
	
	@NotNull(message = "To Location cannot be null")
	@NotBlank(message = "To Location cannot be blank")
	private String mob;
	
	
	@NotNull(message = "date cannot be null")
	@NotBlank(message = "date cannot be blank")
	private String mail;
	
	@Positive
	@Min(value = 20000)
	@Max(value = 34999)
	private double salary;
	
	@NotNull(message = "Designation cannot be null")
	@NotBlank(message = "Designation cannot be blank")
	private String designation;

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

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
	
}
