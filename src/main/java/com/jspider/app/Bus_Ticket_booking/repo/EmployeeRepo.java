package com.jspider.app.Bus_Ticket_booking.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jspider.app.Bus_Ticket_booking.entity.EmployeeEntity;


public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Integer>
{
	
}
