package com.jspider.app.Bus_Ticket_booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jspider.app.Bus_Ticket_booking.dao.BookingHistoryDao;
import com.jspider.app.Bus_Ticket_booking.dto.BookingHistoryDto;
import com.jspider.app.Bus_Ticket_booking.entity.BookingHistory;
import com.jspider.app.Bus_Ticket_booking.exception.BookingHistoryNotFound;
import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;

@Service
public class BookingHistoryService
{
	@Autowired
	BookingHistoryDao dao;
	
	@Autowired
	BookingHistoryDto dto;
	
	@Autowired
	UserService uservice;
	
	
	//save booking history
	
	public ResponseEntity<ResponseStructure<BookingHistoryDto>> saveBookingHistory(BookingHistory bh)
	{
		ResponseStructure<BookingHistoryDto> structure = new ResponseStructure<>();
		BookingHistory existHistory = dao.saveBookingHistory(bh);
		
		if(existHistory != null)
		{
			dto.setBookedDate(existHistory.getBookedDate());
			dto.setJourneyDate(existHistory.getJourneyDate());
			dto.setSeatAvilable(existHistory.getSeatAvilable());
			dto.setUser(uservice.findByUserId(existHistory.getUser().getUserid()).getBody().getData());			
			structure.setData(dto);
			structure.setMessage("Booking History saved successfully........");
			structure.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<BookingHistoryDto>>(structure, HttpStatus.CREATED);
		}
		else
		{
			return null;
		}
	}
	
	//get booking history
	
	public ResponseEntity<ResponseStructure<BookingHistoryDto>> findBookingHistory(int bhid)
	{
		ResponseStructure<BookingHistoryDto> structure = new ResponseStructure<>();
		
		BookingHistory existHistory = dao.findById(bhid);
		
		if(existHistory != null)
		{
			dto.setBookedDate(existHistory.getBookedDate());
			dto.setJourneyDate(existHistory.getJourneyDate());
			dto.setSeatAvilable(existHistory.getSeatAvilable());
			dto.setUser(uservice.findByUserId(existHistory.getUser().getUserid()).getBody().getData());
			structure.setData(dto);
			structure.setMessage("Booking History Details Got successfully........");
			structure.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<BookingHistoryDto>>(structure, HttpStatus.FOUND);
		}
		else
		{
			throw new BookingHistoryNotFound("Booking ID Not Found in the Database");
		}
	}
	
	//update booking history by bhid
	
	public ResponseEntity<ResponseStructure<BookingHistoryDto>> updateBookingHistory(BookingHistory bh, int bhid)
	{
        ResponseStructure<BookingHistoryDto> structure = new ResponseStructure<>();
		
		BookingHistory existHistory = dao.updateBookingHistory(bh, bhid);
		
		if(existHistory != null)
		{
			dto.setBookedDate(existHistory.getBookedDate());
			dto.setJourneyDate(existHistory.getJourneyDate());
			dto.setSeatAvilable(existHistory.getSeatAvilable());
			dto.setUser(uservice.findByUserId(existHistory.getUser().getUserid()).getBody().getData());
			structure.setData(dto);
			structure.setMessage("Booking History Details Updated successfully........");
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<BookingHistoryDto>>(structure, HttpStatus.OK);
		}
		else
		{
			return null;
		}
	}
	
	
	//delete booking history by bhid
	
	public ResponseEntity<ResponseStructure<BookingHistoryDto>> deleteBookingHistory(int bhid)
	{
		ResponseStructure<BookingHistoryDto> structure = new ResponseStructure<>();
		
		BookingHistory existHistory = dao.deleteBookingHistory(bhid);
		
		if(existHistory != null)
		{
			dto.setBookedDate(existHistory.getBookedDate());
			dto.setJourneyDate(existHistory.getJourneyDate());
			dto.setSeatAvilable(existHistory.getSeatAvilable());
			dto.setUser(uservice.findByUserId(existHistory.getUser().getUserid()).getBody().getData());
			structure.setData(dto);
			structure.setMessage("Booking History Details Deleted successfully........");
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<BookingHistoryDto>>(structure, HttpStatus.OK);
		}
		else
		{
			return null;
		}
		
	}
	
	//get all booking history
	
	public ResponseEntity<ResponseStructure<List<BookingHistoryDto>>> getAllBookingHistorys()
	{
		ResponseStructure<List<BookingHistoryDto>> structure = new ResponseStructure<>();
		
		List<BookingHistory> existHistorys = dao.getAllHistory();
		List<BookingHistoryDto> newHistory = new ArrayList<>();
		
		if(existHistorys != null)
		{
			
			for(BookingHistory bh : existHistorys)
			{
				dto.setBookedDate(bh.getBookedDate());
				dto.setJourneyDate(bh.getJourneyDate());
				dto.setSeatAvilable(bh.getSeatAvilable());
				dto.setUser(uservice.findByUserId(bh.getUser().getUserid()).getBody().getData());
				newHistory.add(dto);
			}
			
			structure.setData(newHistory);
			structure.setMessage("All Booking History Details got successfully........");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<BookingHistoryDto>>>(structure, HttpStatus.FOUND);
		}
		else
		{
			return null;
		}
		
	}
	
}
