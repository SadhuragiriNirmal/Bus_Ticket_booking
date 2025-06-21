package com.jspider.app.Bus_Ticket_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jspider.app.Bus_Ticket_booking.entity.Bus;

public interface BusRepo extends JpaRepository<Bus, Integer> {
	
	@Query("select bus from Bus bus where bus.busno=?1 and bus.departureDate=?2")
	public Bus findBusByBusNumberAndDepartureDate(String busno, String departureDate); 
  
}
