package com.jspider.app.Bus_Ticket_booking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspider.app.Bus_Ticket_booking.entity.BookingHistoryEntity;
import com.jspider.app.Bus_Ticket_booking.repo.BookingHistoryRepo;

@Repository
public class BookingHistoryDao 
{
	
	@Autowired
	BookingHistoryRepo repo;

	
	public BookingHistoryEntity saveBookingHistory(BookingHistoryEntity bh)
	{
		return repo.save(bh);
	}
	
	
	public BookingHistoryEntity findById(int id)
	{
		Optional<BookingHistoryEntity> opBookingHistory  = repo.findById(id);
		
		if(opBookingHistory.isPresent())
		{
			return opBookingHistory.get();
		}
		else
		{
			return null;
		}
	}
	
	
	public BookingHistoryEntity deleteBookingHistory(int  id)
	{
        BookingHistoryEntity history  = findById(id);
		
		if(history!=null)
		{
			repo.delete(history);
			return history;
		}
		else
		{
			return null;
		}
	}
	
	
	public BookingHistoryEntity updateBookingHistory(BookingHistoryEntity history,  int  id)
	{
        BookingHistoryEntity ex_history  = findById(id);
		
		if(ex_history!=null)
		{
			history.setBh_id(id);
			
			return repo.save(history);
		}
		else
		{
			return null;
		}
	}
	
	
	public List<BookingHistoryEntity> getAllHistory()
	{
		List<BookingHistoryEntity> history = repo.findAll();
		
		if(history != null)
		{
			return history;
		}
		else
		{
			return null;
		}
	}
	
}
