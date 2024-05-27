package com.jspider.app.Bus_Ticket_booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jspider.app.Bus_Ticket_booking.dao.BookingHistoryDao;
import com.jspider.app.Bus_Ticket_booking.dto.BookingHistoryDto;
import com.jspider.app.Bus_Ticket_booking.entity.BookingHistoryEntity;
import com.jspider.app.Bus_Ticket_booking.exception.BookingHistoryNotFound;
import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;

@Service
public class BookingHistoryService
{
	@Autowired
	BookingHistoryDao bhDao;
	
	@Autowired
	BookingHistoryDto bhDto;
	
	
	
	public ResponseEntity<ResponseStructure<BookingHistoryDto>> saveBookingHistory(BookingHistoryEntity history)
	{
		ResponseStructure<BookingHistoryDto> structure = new ResponseStructure<>();
		
		BookingHistoryEntity saveHistory = bhDao.saveBookingHistory(history);
		
		if(saveHistory != null)
		{
			bhDto.setFrom(saveHistory.getFrom());
			bhDto.setTo(saveHistory.getTo());
			bhDto.setDate(saveHistory.getDate());
			bhDto.setduration(saveHistory.getduration());
			
			
			structure.setData(bhDto);
			structure.setMessage("Booking History saved successfully........");
			structure.setStatus(HttpStatus.CREATED.value());
			
			return new ResponseEntity<ResponseStructure<BookingHistoryDto>>(structure, HttpStatus.CREATED);
		}
		else
		{
			return null;
		}
	}
	
	
	
	
	public ResponseEntity<ResponseStructure<BookingHistoryDto>> findBookingHistory(int id)
	{
		ResponseStructure<BookingHistoryDto> structure = new ResponseStructure<>();
		
		BookingHistoryEntity findBookingHistory = bhDao.findById(id);
		
		if(findBookingHistory != null)
		{
			bhDto.setFrom(findBookingHistory.getFrom());
			bhDto.setTo(findBookingHistory.getTo());
			bhDto.setDate(findBookingHistory.getDate());
			bhDto.setduration(findBookingHistory.getduration());
			
			
			structure.setData(bhDto);
			structure.setMessage("Booking History Details Got successfully........");
			structure.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<BookingHistoryDto>>(structure, HttpStatus.FOUND);
		}
		else
		{
			throw new BookingHistoryNotFound("Booking ID Not Found in the Database");
		}
	}
	
	
	
	
	public ResponseEntity<ResponseStructure<BookingHistoryDto>> updateBookingHistory(BookingHistoryEntity history, int id)
	{
        ResponseStructure<BookingHistoryDto> structure = new ResponseStructure<>();
		
		BookingHistoryEntity ex_history = bhDao.updateBookingHistory(history, id);
		
		if(ex_history != null)
		{
			bhDto.setFrom(ex_history.getFrom());
			bhDto.setTo(ex_history.getTo());
			bhDto.setDate(ex_history.getDate());
			bhDto.setduration(ex_history.getduration());
			
			
			structure.setData(bhDto);
			structure.setMessage("Booking History Details Updated successfully........");
			structure.setStatus(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<BookingHistoryDto>>(structure, HttpStatus.OK);
		}
		else
		{
			return null;
		}
	}
	
	
	
	public ResponseEntity<ResponseStructure<BookingHistoryDto>> deleteBookingHistory(int id)
	{
		ResponseStructure<BookingHistoryDto> structure = new ResponseStructure<>();
		
		BookingHistoryEntity history = bhDao.deleteBookingHistory(id);
		
		if(history != null)
		{
			bhDto.setFrom(history.getFrom());
			bhDto.setTo(history.getTo());
			bhDto.setDate(history.getDate());
			bhDto.setduration(history.getduration());
			
			structure.setData(bhDto);
			structure.setMessage("Booking History Details Deleted successfully........");
			structure.setStatus(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<BookingHistoryDto>>(structure, HttpStatus.OK);
		}
		else
		{
			return null;
		}
		
	}
	
	
	
	public ResponseEntity<ResponseStructure<List<BookingHistoryDto>>> getAllBookingHistorys()
	{
		ResponseStructure<List<BookingHistoryDto>> structure = new ResponseStructure<>();
		
		List<BookingHistoryEntity> historyEntity = bhDao.getAllHistory();
		
		
		List<BookingHistoryDto> bhHistory = new ArrayList<>();
		
		if(historyEntity != null)
		{
			
			for(BookingHistoryEntity bh : historyEntity)
			{
				bhDto.setFrom(bh.getFrom());
				bhDto.setTo(bh.getTo());
				bhDto.setDate(bh.getDate());
				bhDto.setduration(bh.getduration());
				
				bhHistory.add(bhDto);
			}
			
			
			structure.setData(bhHistory);
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
