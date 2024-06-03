package com.jspider.app.Bus_Ticket_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jspider.app.Bus_Ticket_booking.entity.Seat;

public interface SeatRepo extends JpaRepository<Seat, Integer> {

}
