package com.jspider.app.Bus_Ticket_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jspider.app.Bus_Ticket_booking.entity.BusSchedule;

public interface BusScheduleRepo extends JpaRepository<BusSchedule, Integer> {

	@Query("select bs from BusSchedule bs where bs.from=?1 and bs.to=?2")
	public BusSchedule findBusScheduleByFromAndTo(String startplace, String destination);
}
