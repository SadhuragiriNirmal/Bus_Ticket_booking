package com.jspider.app.Bus_Ticket_booking.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jspider.app.Bus_Ticket_booking.entity.BookingHistoryEntity;


public interface BookingHistoryRepo extends JpaRepository<BookingHistoryEntity, Integer>
{

}
