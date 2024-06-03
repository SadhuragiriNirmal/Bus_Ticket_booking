package com.jspider.app.Bus_Ticket_booking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspider.app.Bus_Ticket_booking.entity.Seat;
import com.jspider.app.Bus_Ticket_booking.repository.SeatRepo;

@Repository
public class SeatDao {
	
	@Autowired
	SeatRepo seatRepo;
	
	//save Seat schedule
	
	public Seat saveSeat(Seat seat) {
		
		Seat existSeat = seatRepo.save(seat);
		if(existSeat != null) {
			
			return existSeat;
		}
		else return null;
	}
	
	//get Seat schedule
	
	public Seat findSeatByid(int Seatid) {
		
		 Optional<Seat> seat =  seatRepo.findById(Seatid);
		 
		 if(seat != null) {
			 
			 return seat.get();
		 }
		 else return null;
	}
	
    //update Seat schedule
	
	public Seat updateSeat(Seat seat, int seatid) {
		
		Seat existSeat = findSeatByid(seatid);
		
		if(existSeat != null) {
			
			seat.setSeatid(seatid);
			return seatRepo.save(seat);
		}
		else return null;
		
	}
	
	//delete Seat schedules
	
	public Seat deleteSeat(int seatid) {
		
		Seat existSeat = findSeatByid(seatid);
		
	    if(existSeat != null) {
	    	
	    	seatRepo.delete(existSeat);
	    	return existSeat;
	    }
	    else return null;
	}
	
	
	//get all Seat schedules
	
	public List<Seat>  getAllSeat() {
		
		List<Seat> seat = seatRepo.findAll();
		if(seat != null) {
			
			return seat;
		}
		else return null;
	}


}
