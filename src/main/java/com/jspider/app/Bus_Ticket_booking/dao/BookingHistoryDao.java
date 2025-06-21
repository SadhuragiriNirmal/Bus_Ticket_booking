package com.jspider.app.Bus_Ticket_booking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspider.app.Bus_Ticket_booking.entity.BookingHistory;
import com.jspider.app.Bus_Ticket_booking.repository.BookingHistoryRepo;

@Repository
public class BookingHistoryDao 
{
	
	@Autowired
	BookingHistoryRepo repo;

	
	public BookingHistory saveBookingHistory(BookingHistory bh)
	{
		return repo.save(bh);
	}
	
	
	public BookingHistory findById(int id)
	{
		Optional<BookingHistory> opBookingHistory  = repo.findById(id);
		
		if(opBookingHistory.isPresent())
		{
			return opBookingHistory.get();
		}
		else
		{
			return null;
		}
	}
	
	
	public BookingHistory deleteBookingHistory(int  bhid)
	{
        BookingHistory history  = findById(bhid);
		
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
	
	
	public BookingHistory updateBookingHistory(BookingHistory history,  int  bhid)
	{
        BookingHistory ex_history  = findById(bhid);
		
		if(ex_history!=null)
		{
			history.setBhid(bhid);
			
			return repo.save(history);
		}
		else
		{
			return null;
		}
	}
	
	
	public List<BookingHistory> getAllHistory()
	{
		List<BookingHistory> history = repo.findAll();
		
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
