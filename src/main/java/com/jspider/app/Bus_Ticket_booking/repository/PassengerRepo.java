package com.jspider.app.Bus_Ticket_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jspider.app.Bus_Ticket_booking.entity.Passenger;

public interface PassengerRepo extends JpaRepository<Passenger, Integer> {

}
