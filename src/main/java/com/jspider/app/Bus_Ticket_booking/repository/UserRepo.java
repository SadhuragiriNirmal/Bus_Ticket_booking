package com.jspider.app.Bus_Ticket_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jspider.app.Bus_Ticket_booking.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	public User findByuemail(String uemail);
}
