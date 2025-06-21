package com.jspider.app.Bus_Ticket_booking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspider.app.Bus_Ticket_booking.entity.BusSchedule;
import com.jspider.app.Bus_Ticket_booking.repository.BusScheduleRepo;

@Repository
public class BusScheduleDao {
	
	@Autowired
	BusScheduleRepo busScheduleRepo;
	
	//save bus schedule
	
	public BusSchedule saveBusSchedule(BusSchedule bs) {
		
		BusSchedule busSchedule = busScheduleRepo.save(bs);
		if(busSchedule != null) {
			
			return busSchedule;
		}
		else return null;
	}
	
	//get bus schedule
	
	public BusSchedule findBusScheduleByid(int bsid) {
		
		 Optional<BusSchedule> busSchedule =  busScheduleRepo.findById(bsid);
		 
		 if(busSchedule != null) {
			 
			 return busSchedule.get();
		 }
		 else return null;
	}
	
    //update bus schedule
	
	public BusSchedule updateBusSchedule(BusSchedule bs, int bsid) {
		
		BusSchedule existBusSchedule = findBusScheduleByid(bsid);
		
		if(existBusSchedule != null) {
			
			bs.setBusScheduleid(bsid);
			return busScheduleRepo.save(bs);
		}
		else return null;
		
	}
	
	//delete bus schedules
	
	public BusSchedule deleteBusSchedule(int bsid) {
		
		BusSchedule existBusSchedule = findBusScheduleByid(bsid);
		
	    if(existBusSchedule != null) {
	    	
	    	busScheduleRepo.delete(existBusSchedule);
	    	return existBusSchedule;
	    }
	    else return null;
	}
	
	
	//get all bus schedules
	
	public List<BusSchedule>  getAllBusSchedule() {
		
		List<BusSchedule> busSchedules = busScheduleRepo.findAll();
		if(busSchedules != null) {
			
			return busSchedules;
		}
		else return null;
	}
	
	//get bus schedule by departure place and destination place

	public BusSchedule findBusScheduleByFromAndTo(String startplace, String distination) {
		
		BusSchedule busSchedule = busScheduleRepo.findBusScheduleByFromAndTo(startplace, distination);
        if(busSchedule != null) {
        	
        	return busSchedule;
        }
        else return null; 
	}
}
